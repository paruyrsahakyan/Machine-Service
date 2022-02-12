package group.service.iko.controller;


import group.service.iko.dto.*;
import group.service.iko.entities.*;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

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
    public ModelAndView newOfferPage() {
        ModelAndView modelAndView = new ModelAndView("order/newOffer");
        modelAndView.addObject("allCustomers", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject("partsOnStock", WareHouseService.availablePartList);
        modelAndView.addObject("allPriceForCustomer", priceForCustomerService.getAllPriceForCustomer());
         return modelAndView;
    }


}
