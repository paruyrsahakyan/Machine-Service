package group.service.iko.controller;


import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

@Controller()
@RequestMapping("/order")
public class OrderController {


    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public ModelAndView createWorkOrder(@RequestParam("customer") String customerId,
                                        @RequestParam("orderNumber") String orderNumber,
                                        @RequestParam("orderDate") String date
                                                                             )

    {
ModelAndView modelAndView = new ModelAndView();
return modelAndView;
    }


}

