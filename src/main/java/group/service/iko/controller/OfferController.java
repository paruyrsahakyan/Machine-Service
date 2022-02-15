package group.service.iko.controller;


import group.service.iko.dto.*;
import group.service.iko.entities.*;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.*;

import java.io.*;
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
    public ModelAndView mainPage()  {

        ModelAndView modelAndView = new ModelAndView("order/offer");
        List<Offer> offerList = offerService.getCurrentOffers();
        modelAndView.addObject("currentOffers", OfferDTO.convertIntoDTO(offerList));
        modelAndView.addObject("allCustomers", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        return  modelAndView;
    }

    @RequestMapping("/newOffer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView newOfferPage(@RequestParam(value = "selectedCustomer", required = false, defaultValue = "0") int customerId,
                                     @RequestParam (required = false, defaultValue = "null") Request request) {
        ModelAndView modelAndView = new ModelAndView("order/newOffer");
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject("partsOnStock", WareHouseService.availablePartList);
        modelAndView.addObject("allPriceForCustomer", PriceForCustomerDTO.convertIntoDTO(priceForCustomerService.getAllPriceForCustomer()));
        if (customerId != 0){
            modelAndView.addObject("selectedCustomer", new CustomerDTO(customerService.getCustomerById(customerId)));
        }
        if (request != null){
            modelAndView.addObject("request", request);
                    }
        
         return modelAndView;
    }

    @RequestMapping("/newOffer/setRequestFromFile")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView setRequestFromFile(@RequestParam("customerName") String customerName,
                                           @RequestParam(value = "requestFile", required = false) MultipartFile uploadedFile,
                                           ModelMap modelMap) throws Throwable {

         modelMap.addAttribute("selectedCustomer", customerService.getCustomerByName(customerName).getId());
         modelMap.addAttribute("request", offerService.getRequestFromFile(customerName, uploadedFile));
        ModelAndView modelAndView = new ModelAndView("redirect:/offer/newOffer", modelMap);
        return modelAndView;
    }

}
