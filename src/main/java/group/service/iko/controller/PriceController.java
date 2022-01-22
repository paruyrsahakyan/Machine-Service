package group.service.iko.controller;

import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.PriceForCustomerDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.PriceForCustomer;
import group.service.iko.service.CustomerService;
import group.service.iko.service.PriceForCustomerService;
import group.service.iko.service.WareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Controller()
@RequestMapping("/price")
public class PriceController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PriceForCustomerService priceForCustomerService;


    @RequestMapping(value = "/mainPage")
    public ModelAndView showPricePage(ModelMap modelMap,
                                      @RequestParam(value = "selectedCustomer", required = false, defaultValue = "0") int customerId) {
        ModelAndView modelAndView = new ModelAndView("price/priceMainPage");
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customerService.getAllCustomers()));
        modelAndView.addObject("priceList", PriceForCustomerDTO.convertIntoDTO(priceForCustomerService.getAllPriceForCustomer()));
        if (customerId != 0) {
            modelAndView.addObject("selectedCustomer", new CustomerDTO(customerService.getCustomerById(customerId)));
        }
        return modelAndView;

    }


    @RequestMapping(value = "/createdNewPrice", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addNewPrice(@RequestParam("customerName") String customerName,
                                    @RequestParam("article") String article,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") int price,
                                    ModelMap modelMap) {
        PriceForCustomer priceForCustomer = new PriceForCustomer();
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView deleteTheItem(@RequestParam("id") int id,
                                      ModelMap modelMap) {

        Customer customer = priceForCustomerService.getPriceForCustomerById(id).getCustomer();
        PriceForCustomer priceForCustomerToDelete = new PriceForCustomer();
        priceForCustomerToDelete.setId(id);
        priceForCustomerService.deletePriceForCustomer(priceForCustomerToDelete);
        modelMap.addAttribute("selectedCustomer", customer.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/price/mainPage", modelMap);
        return modelAndView;
    }


    @RequestMapping(value = "/itemUpdated", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateTheItem(@RequestParam("id") int id,
                                      @RequestParam("article") String article,
                                      @RequestParam("description") String description,
                                      @RequestParam("price") int price,
                                      ModelMap modelMap) {

        Customer customer = priceForCustomerService.getPriceForCustomerById(id).getCustomer();
        PriceForCustomer priceForCustomerToUpdate = priceForCustomerService.getPriceForCustomerById(id);
        priceForCustomerToUpdate.setArticle(article);
        priceForCustomerToUpdate.setDescription(description);
        priceForCustomerToUpdate.setPrice(price);
        priceForCustomerService.updatePriceForCustomer(priceForCustomerToUpdate);
        modelMap.addAttribute("selectedCustomer", customer.getId());
        ModelAndView modelAndView = new ModelAndView("redirect:/price/mainPage", modelMap);
        return modelAndView;
    }


    @RequestMapping(value = "/pricesByArticleForAllCustomers/{article}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView showArticlePricesForAllCustomers(@PathVariable("article") String article)

    {
        ModelAndView modelAndView = new ModelAndView("price/pricesByArticleForAllCustomers");
        List<PriceForCustomer> priceList = priceForCustomerService.getPriceForCustomerByArticle(article);
        modelAndView.addObject("priceList", PriceForCustomerDTO.convertIntoDTO(priceList));
        modelAndView.addObject("articleList", priceForCustomerService.getArticlesSet());
        modelAndView.addObject("selectedArticle", article);
        return modelAndView;
    }

    @RequestMapping(value = "/pricesByArticleForAllCustomers", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView showArticlePricesForAllCustomersBySearchInput(@RequestParam("article") String article) {
        ModelAndView modelAndView = new ModelAndView("price/pricesByArticleForAllCustomers");
        List<PriceForCustomer> priceList = priceForCustomerService.getPriceForCustomerByArticle(article);
        modelAndView.addObject("priceList", PriceForCustomerDTO.convertIntoDTO(priceList));
        modelAndView.addObject("articleList", priceForCustomerService.getArticlesSet());
        modelAndView.addObject("selectedArticle", article);
        return modelAndView;
    }


    @RequestMapping(value = "/setPriceListFromFile", method =RequestMethod.POST)
    public  ModelAndView setPriceListForCustomerFromFile(@RequestParam("customerName") String customerName,
                                                         @RequestParam(value = "priceFile", required = false) MultipartFile uploadedFile,
                                                         ModelMap modelMap) throws IOException {
        int customerId=new CustomerService().getCustomerByName(customerName).getId();
        new PriceForCustomerService().setPriceListForCustomerFromFile( customerId, uploadedFile);
        modelMap.addAttribute("selectedCustomer", customerName);
        ModelAndView modelAndView = new ModelAndView("redirect:/price/manPage", modelMap);
        return modelAndView;
    }
}

