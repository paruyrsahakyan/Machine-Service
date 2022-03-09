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

        ModelAndView modelAndView = new ModelAndView("order/offer");
        List<Offer> offerList = offerService.getCurrentOffers();
        modelAndView.addObject("currentOffers", OfferDTO.convertIntoDTO(offerList));
        modelAndView.addObject("allCustomers", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        return modelAndView;
    }

    @RequestMapping("/{offerId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView getOffer(@PathVariable("offerId") int id) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("offer", offerService.getOfferById(id)).
                addObject("offerLines", offerLineService.getOfferLinesByOfferId(id));
        return modelAndView;

    }

    @RequestMapping("/newOffer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView newOfferPage(@RequestParam(value = "selectedCustomer", required = false, defaultValue = "0") int customerId,
                                     @ModelAttribute("offer") Offer offer,
                                      ModelMap modelMap) throws Throwable {
        ModelAndView modelAndView = new ModelAndView("order/newOffer");
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
    public ModelAndView saveOffer(@RequestParam("position[]") int[] position,
                                  @RequestParam("partName[]") String[] partName,
                                  @RequestParam("partNumber[]") String[] partNumber,
                                  @RequestParam("quantity[]") int[] quantity,
                                  @RequestParam("offeredPartNumber[]") String[] offeredPartNumber,
                                  @RequestParam("unit[]") String[] unit,
                                  @RequestParam("price[]") int[] price,
                                  @RequestParam("sum[]") int[] sum,
                                  @RequestParam("lastOfferPrice[]") int[] lastOfferPrice,
                                  @RequestParam("lastOfferDate[]") String[] lastOfferDate,
                                  @RequestParam("availability[]") int[] availability,
                                  @RequestParam("inStockNetCost[]") int[] inStockNetCost,
                                  @RequestParam("profitFromAvailable[]") int[] profitFromAvailable,
                                  @RequestParam("deliveryDate[]") int[] deliveryTime,
                                  @RequestParam("supplierPrice[]") double[] supplierPrice,
                                  @RequestParam("producer[]") String[] producer,
                                  @RequestParam("customerName") String customerName,
                                  @RequestParam("requestNumber") String requestNumber,
                                  @RequestParam("offerDate") String offerDate,
                                  @RequestParam("offerValidityDate") String offerValidityDate,
                                  @RequestParam("currency") String currency,
                                  @RequestParam("profit") int profitPercentage,
                                  @RequestParam("transportation") int transportation,
                                  @RequestParam("discount") int discount,
                                  @RequestParam("VAT") String VAT,
                                  ModelMap modelMap
    )
    {

        Offer offer = new Offer().setCustomer(customerService.getCustomerByName(customerName)).
                setRequestNumber(requestNumber).setOfferDate(CalendarAdapter.getGregCalendar(offerDate)).
                setValidationDate(CalendarAdapter.getGregCalendar(offerValidityDate)).setCurrency(currency).
                setProfitPercentage(profitPercentage).setTransportation(transportation).
                setProfitPercentage(profitPercentage).setDiscount(discount).setVAT(VAT);
        offerService.saveOffer(offer);
        Offer savedOffer = offerService.getLastSavedOffer();
        Set<OfferLine> offerLines = new HashSet<>();

        for (int i=0; i<position.length; i++) {
            OfferLine offerLine = new OfferLine(position[i], partName[i], partNumber[i], quantity[i], offeredPartNumber[i], unit[i], price[i],
                    sum[i], lastOfferPrice[i], lastOfferDate[i], availability[i], inStockNetCost[i],
                    profitFromAvailable[i], deliveryTime[i], supplierPrice[i], producer[i]);
            offerLine.setOffer(savedOffer);
            offerLineService.saveOfferLine(offerLine);
                }

                return new ModelAndView("redirect:/"+savedOffer.getId(), modelMap);
    }


}

