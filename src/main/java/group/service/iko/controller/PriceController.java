package group.service.iko.controller;

import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.PriceForCustomerDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.PriceForCustomer;
import group.service.iko.service.CustomerService;
import group.service.iko.service.PriceForCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        ModelAndView modelAndView = new ModelAndView("price/priceMainPage");
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject( "priceList", PriceForCustomerDTO.convertIntoDTO(priceForCustomerService.getAllPriceForCustomer()));
        return modelAndView;

    }


  @RequestMapping(value = "/createdNewPrice", method = RequestMethod.POST)
  public ModelAndView addNewPrice(@RequestParam("customerName") String customerName,
                                  @RequestParam("article") String article,
                                  @RequestParam("description") String description,
                                  @RequestParam("price") int price)
  {
    ModelAndView modelAndView = new ModelAndView("price/priceMainPage");
    PriceForCustomer priceForCustomer = new PriceForCustomer();
    customerService.getCustomerByName(customerName);
    Customer customer = customerService.getCustomerByName(customerName);
    priceForCustomer.setCustomer(customerService.getCustomerByName(customerName));
    priceForCustomer.setArticle(article);
    priceForCustomer.setDescription(description);
    priceForCustomer.setPrice(price);
    priceForCustomerService.savePriceForCustomer(priceForCustomer);
    modelAndView.addObject("priceList", PriceForCustomerDTO.convertIntoDTO(priceForCustomerService.getAllPriceForCustomer()));
    modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
    modelAndView.addObject("selectedCustomer", new CustomerDTO(customer));
      return modelAndView;


  }

  @RequestMapping(value = "/itemDeleted", method = RequestMethod.GET)
  public ModelAndView deleteTheItem(@RequestParam("id") String id)
  {
    ModelAndView modelAndView = new ModelAndView("price/priceMainPage");
    PriceForCustomer priceForCustomer = priceForCustomerService.getPriceForCustomerById(id);
    Customer customer = priceForCustomer.getCustomer();
    priceForCustomerService.deletePriceForCustomer(priceForCustomer);
    modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
    modelAndView.addObject( "priceList", PriceForCustomerDTO.convertIntoDTO(priceForCustomerService.getAllPriceForCustomer()));
    modelAndView.addObject("selectedCustomer", new CustomerDTO(customer));
    priceForCustomerService.deletePriceForCustomer(priceForCustomer);
     return modelAndView;
    }

    }
