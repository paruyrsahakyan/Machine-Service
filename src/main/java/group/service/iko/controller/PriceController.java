package group.service.iko.controller;

import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.Machine;
import group.service.iko.entities.PriceForCustomer;
import group.service.iko.service.CustomerService;
import group.service.iko.service.PriceForCustomerService;
import group.service.iko.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("/price")
public class PriceController {
  @Autowired
  private CustomerService customerService;
  @Autowired
  private  PriceForCustomerService priceForCustomerService;


    @RequestMapping(value = "/mainPage")
  public ModelAndView showPricePage(){
        ModelAndView modelAndView = new ModelAndView("price/priceForCustomer");
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject( modelAndView.addObject("priceList", priceForCustomerService.getAllPriceForCustomer()));
        return modelAndView;

    }


  @RequestMapping(value = "/createdNewPrice", method = RequestMethod.POST)
  public ModelAndView addNewPrice(@RequestParam("newCustomerId") int customerId,
                                 @RequestParam("article") String article,
                                 @RequestParam("description") String description,
                                 @RequestParam("price") int price)
  {
    ModelAndView modelAndView = new ModelAndView("price/priceForCustomer");
    PriceForCustomer priceForCustomer = new PriceForCustomer();
    priceForCustomer.setCustomer(customerService.getCustomerById(customerId));
    priceForCustomer.setArticle(article);
    priceForCustomer.setDescription(description);
    priceForCustomer.setPrice(price);
    priceForCustomerService.savePriceForCustomer(priceForCustomer);
    modelAndView.addObject("priceList", priceForCustomerService.getAllPriceForCustomer());
    modelAndView.addObject(CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
      return modelAndView;
  }


  }
