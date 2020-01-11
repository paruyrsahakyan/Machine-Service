package group.service.iko.controller;

import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.PriceForCustomerDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.PriceForCustomer;
import group.service.iko.service.CustomerService;
import group.service.iko.service.PriceForCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.TimeUnit;

@Controller()
@RequestMapping("/price")
public class PriceController {
  @Autowired
  private CustomerService customerService;
  @Autowired
  private  PriceForCustomerService priceForCustomerService;


    @RequestMapping(value = "/mainPage" )
  public ModelAndView showPricePage(@RequestParam(value ="selectedCustomer", required = false, defaultValue = "0") int customerId ){
        ModelAndView modelAndView = new ModelAndView("price/priceMainPage");
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject( "priceList", PriceForCustomerDTO.convertIntoDTO(priceForCustomerService.getAllPriceForCustomer()));
        if(customerId!=0){
         modelAndView.addObject("selectedCustomer", new CustomerDTO(customerService.getCustomerById(customerId)));
        }
               return modelAndView;

    }


  @RequestMapping(value = "/createdNewPrice", method = RequestMethod.POST)
  public ModelAndView addNewPrice(@RequestParam("customerName") String customerName,
                                  @RequestParam("article") String article,
                                  @RequestParam("description") String description,
                                  @RequestParam("price") int price,
                                  ModelMap modelMap)
  {
    PriceForCustomer priceForCustomer = new PriceForCustomer();
    customerService.getCustomerByName(customerName);
    Customer customer = customerService.getCustomerByName(customerName);
    priceForCustomer.setCustomer(customerService.getCustomerByName(customerName));
    priceForCustomer.setArticle(article);
    priceForCustomer.setDescription(description);
    priceForCustomer.setPrice(price);
    priceForCustomerService.savePriceForCustomer(priceForCustomer);
    modelMap.addAttribute("selectedCustomer", customer.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/price/mainPage", modelMap);
      return modelAndView;


  }

  @RequestMapping(value = "/itemDeleted", method = RequestMethod.GET)
  public ModelAndView deleteTheItem(@RequestParam("id") int id,
                                    ModelMap modelMap)
  {

    Customer customer=  priceForCustomerService.getPriceForCustomerById(id).getCustomer();
    PriceForCustomer priceForCustomerToDelete = new PriceForCustomer();
    priceForCustomerToDelete.setId(id);
    priceForCustomerService.deletePriceForCustomer(priceForCustomerToDelete);
    modelMap.addAttribute("selectedCustomer", customer.getId());
    ModelAndView modelAndView = new ModelAndView("redirect:/price/mainPage", modelMap);
     return modelAndView;
    }

    }
