package group.service.iko.controller;

import group.service.iko.entities.Customer;
import group.service.iko.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("/customer/")
public class CustomerController {



    @RequestMapping("/customer/{customerId}")
    public ModelAndView customerPage(@PathVariable("customerId") int id) {
        ModelAndView modelAndView = new ModelAndView("customer");
        CustomerService customerService = new CustomerService();
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject("customer", customer);
        return modelAndView;
    }

    @RequestMapping("/customer/createCustomer")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("createCustomer");
        return modelAndView;

    }

    @RequestMapping("/customer/newCustomer")
    public ModelAndView newCustomerPage(@RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("otherInfo") String otherInfo) {
        ModelAndView modelAndView = new ModelAndView("customer");
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContacts(contacts);
        customer.setOtherInfo(otherInfo);
        customerService.saveCustomer(customer);
        Customer createdCustomer = customerService.getCustomerByName(name);
        modelAndView.addObject("customer", createdCustomer);
        return modelAndView;
    }

    @RequestMapping("/customer/updateCustomer/{customerId}")
    public ModelAndView updateCustomer(@PathVariable("customerId") int id) {
        CustomerService customerService = new CustomerService();
        ModelAndView modelAndView = new ModelAndView("updateCustomer");
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject(customer);
        return modelAndView;
    }

    @RequestMapping("/customer/updatedCustomer/{customerId}")
    public ModelAndView updatedCustomer(@RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("otherInfo") String otherInfo,
                                        @PathVariable("customerId") int customerId) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Customer customer = new Customer();
        CustomerService customerService = new CustomerService();
        customer.setName(name);
        customer.setContacts(contacts);
        customer.setOtherInfo(otherInfo);
        customer.setId(customerId);
        customerService.updateCustomer(customer);
        Customer updatedCustomer = customerService.getCustomerById(customerId);
        modelAndView.addObject("customer", updatedCustomer);
        return modelAndView;
    }
    @RequestMapping("/customer/deleteCustomer/{customerId}")
    public ModelAndView deleteCustomer(@PathVariable("customerId") int customerId){
        ModelAndView modelAndView = new ModelAndView("index");
        CustomerService customerService = new CustomerService();
        customerService.deleteCustomerById(customerId);
        return modelAndView;

    }


}