package group.service.iko.controller;


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
    public RedirectView saveOffer( @RequestParam("customerName") String customerName,
                                           @RequestParam(value = "requestFile", required = false) MultipartFile uploadedFile,
                                           RedirectAttributes redirectAttributes
    ) throws Throwable {
        Customer customer = customerService.getCustomerByName(customerName);
        redirectAttributes.addFlashAttribute("priceList", priceForCustomerService.getPriceListByCustomerName(customer.getName()));
        redirectAttributes.addFlashAttribute("offer", offerService.makeOfferFromRequestedFile(customerName, uploadedFile));
        redirectAttributes.addAttribute("selectedCustomer", customerService.getCustomerByName(customerName).getId());
        return new RedirectView("/offer/newOffer");
    }




}

