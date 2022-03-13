package group.service.iko.controller;


import group.service.iko.calendarAdapter.*;
import group.service.iko.dto.*;
import group.service.iko.entities.*;
import group.service.iko.service.*;
import org.apache.poi.openxml4j.exceptions.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.*;
import org.springframework.web.servlet.view.*;


import java.util.*;

@Controller()
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    private OfferService offerService;
    @Autowired
    private OfferLineService offerLineService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private WareHouseService wareHouseService;
    @Autowired
    private PriceForCustomerService priceForCustomerService;

    @RequestMapping("/mainPage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView mainPage() {

        ModelAndView modelAndView = new ModelAndView("order/offerMainPage");
        List<Offer> offerList = offerService.getCurrentOffers();
        modelAndView.addObject("currentOffers", OfferDTO.convertIntoDTO(offerList));
        modelAndView.addObject("allCustomers", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        return modelAndView;
    }

    @RequestMapping("/{offerId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getOffer(@PathVariable("offerId") int id) {

        ModelAndView modelAndView = new ModelAndView("order/newOffer");
                List<OfferLine> offerLines = offerLineService.getOfferLinesByOfferId(id);
         modelAndView.addObject("offer",  new OfferDTO(offerService.getOfferById(id), offerLines));
        return modelAndView;

    }

    @RequestMapping("/newOffer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView newOfferPage(@RequestParam(value = "selectedCustomer", required = false, defaultValue = "0") int customerId,
                                     @ModelAttribute("offer") Offer offer,
                                      ModelMap modelMap) throws Throwable {
        ModelAndView modelAndView = new ModelAndView("order/offer");
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));

        if (customerId != 0) {
            Customer customer = customerService.getCustomerById(customerId);
            String customerName = customer.getName();
            List<PriceForCustomer> priceList = priceForCustomerService.getPriceListByCustomerName(customer.getName());
            modelAndView.addObject("selectedCustomer", new CustomerDTO(customer));
            modelAndView.addObject("priceList", PriceForCustomerDTO.convertIntoDTO(priceList));
            modelAndView.addObject("offer", offer);
           }

        return modelAndView;
    }



    @RequestMapping("/newOffer/setRequestFromFile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RedirectView setRequestFromFile(@RequestParam("customerName") String customerName,
                                           @RequestParam(value = "requestFile", required = false) MultipartFile uploadedFile,
                                           RedirectAttributes redirectAttributes
    ) throws Throwable {
        Customer customer = customerService.getCustomerByName(customerName);
        redirectAttributes.addFlashAttribute("priceList", priceForCustomerService.getPriceListByCustomerName(customer.getName()));
        redirectAttributes.addFlashAttribute("offer", offerService.makeOfferFromRequestedFile(customerName, uploadedFile));
        redirectAttributes.addAttribute("selectedCustomer", customerService.getCustomerByName(customerName).getId());
        return new RedirectView("/offer/newOffer");
    }



    @RequestMapping("/newOffer/saveOffer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView saveOffer(@RequestParam(value="position[]", required = false) int[] position,
                                  @RequestParam(value="partName[]", required = false) String[] partName,
                                  @RequestParam(value="partNumber[]", required = false) String[] partNumber,
                                  @RequestParam(value="quantity[]", required = false) int[] quantity,
                                  @RequestParam(value="offeredPartNumber[]", required = false) String[] offeredPartNumber,
                                  @RequestParam(value="unit[]", required = false) String[] unit,
                                  @RequestParam(value="price[]", required = false) int[] price,
                                  @RequestParam(value="sum[]", required = false) int[] sum,
                                  @RequestParam(value="lastOfferPrice[]", required = false) int[] lastOfferPrice,
                                  @RequestParam(value="lastOfferDate[]", required = false) String[] lastOfferDate,
                                  @RequestParam(value="availability[]", required = false) int[] availability,
                                  @RequestParam(value="inStockNetCost[]", required = false) int[] inStockNetCost,
                                  @RequestParam(value="profitFromAvailable[]", required = false) int[] profitFromAvailable,
                                  @RequestParam(value="deliveryTime[]", required = false) int[] deliveryTime,
                                  @RequestParam(value="supplierPrice[]", required = false) double[] supplierPrice,
                                  @RequestParam(value="avia[]", required = false) int[] avia,
                                  @RequestParam(value="producer[]", required = false) String[] producer,
                                  @RequestParam(value="customerName" ) String customerName,
                                  @RequestParam(value="requestNumber", defaultValue = " ") String requestNumber,
                                  @RequestParam(value="offerDate", defaultValue = "111-11-11") String offerDate,
                                  @RequestParam(value="offerValidationDate", defaultValue = "111-11-11") String offerValidityDate,
                                  @RequestParam(value="currency", defaultValue = "AMD") String currency,
                                  @RequestParam(value="profit", defaultValue = "0") int profitPercentage,
                                  @RequestParam(value="transportation", defaultValue = "0") int transportation,
                                  @RequestParam(value="discount", defaultValue = "0") int discount,
                                  @RequestParam(value="exchangeRate", defaultValue = "1") double exchangeRate,
                                  @RequestParam(value="VAT", defaultValue = "without VAT") String VAT,
                                  @RequestParam(value = "totalSum", defaultValue = "0") double totalSum,
                                  ModelMap modelMap
    )    {

        Offer offer = new Offer().setCustomer(customerService.getCustomerByName(customerName)).
                setRequestNumber(requestNumber).setOfferDate(CalendarAdapter.getGregCalendar(offerDate)).
                setValidationDate(CalendarAdapter.getGregCalendar(offerValidityDate)).setCurrency(currency).
                setProfitPercentage(profitPercentage).setTransportation(transportation).
                setProfitPercentage(profitPercentage).setDiscount(discount).setVAT(VAT).setExchangeRate(exchangeRate).setOfferCondition("current").setSum(totalSum);
        offerService.saveOffer(offer);
        Offer savedOffer = offerService.getLastSavedOffer();
        Set<OfferLine> offerLines = new HashSet<>();


        for (int i=0; i<position.length; i++) {
            OfferLine offerLine = new OfferLine(position[i],
                    partName[i],
                    partNumber[i],
                    quantity[i],
                    offeredPartNumber[i],
                    unit[i],
                    price[i],
                    sum[i],
                    lastOfferPrice[i],
                    lastOfferDate[i],
                    availability[i],
                    inStockNetCost[i],
                    profitFromAvailable[i],
                    deliveryTime[i],
                    new Double(supplierPrice[i]),
                    producer[i]);
            offerLine.setOffer(savedOffer);
            offerLineService.saveOfferLine(offerLine);
                }

                return new ModelAndView("redirect:/offer/"+savedOffer.getId(), modelMap);
    }


}

