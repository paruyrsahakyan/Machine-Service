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
@RequestMapping("/Offer")
public class OfferController {

    @Autowired
    private OfferService offerService;


    @RequestMapping("/mainPage")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView mainPage() {
        ModelAndView modelAndView = new ModelAndView("mainPAge");

        List<Offer> offerList = offerService.getCurrentOffers();
        modelAndView.addObject( "currentOffers", offerList);
        return modelAndView;

    }
}
