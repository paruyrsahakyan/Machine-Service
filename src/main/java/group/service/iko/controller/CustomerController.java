package group.service.iko.controller;

import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.Machine;
import group.service.iko.entityDao.MachineDAO;
import group.service.iko.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/{customerId}")
    public ModelAndView customerPage(@PathVariable("customerId") int id) {
        ModelAndView modelAndView = new ModelAndView("customer");

        Customer customer = customerService.getCustomerById(id);
        List<Machine> machineList = customer.getMachineList();
        modelAndView.addObject("customer", new CustomerDTO(customer));
        modelAndView.addObject("machineList", MachineDTO.convertIntoDTO(machineList));
        return modelAndView;
    }

    @RequestMapping("/createCustomer")
    public ModelAndView createCustomer() {
        ModelAndView modelAndView = new ModelAndView("createCustomer");
        return modelAndView;

    }

    @RequestMapping("/newCustomer")
    public ModelAndView newCustomerPage(@RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("otherInfo") String otherInfo,
                                        @RequestParam("contract") String contract) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContacts(contacts);
        customer.setOtherInfo(otherInfo);
        customer.setContract(contract);
        customerService.saveCustomer(customer);
        Customer createdCustomer = customerService.getCustomerByName(name);
        modelAndView.addObject("customer", createdCustomer);
        return modelAndView;
    }

    @RequestMapping("/updateCustomer/{customerId}")
    public ModelAndView updateCustomer(@PathVariable("customerId") int id) {
        ModelAndView modelAndView = new ModelAndView("updateCustomer");
        Customer customer = customerService.getCustomerById(id);
        modelAndView.addObject(customer);
        return modelAndView;
    }

    @RequestMapping("/updatedCustomer/{customerId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updatedCustomer(@RequestParam("name") String name,
                                        @RequestParam("contacts") String contacts,
                                        @RequestParam("otherInfo") String otherInfo,
                                        @PathVariable("customerId") int customerId,
                                        @RequestParam("contract") String contract) {
        ModelAndView modelAndView = new ModelAndView("customer");
        Customer customer = new Customer();
        customer.setName(name);
        customer.setContacts(contacts);
        customer.setOtherInfo(otherInfo);
        customer.setId(customerId);
        customer.setContract(contract);
        customerService.updateCustomer(customer);
        Customer updatedCustomer = customerService.getCustomerById(customerId);
        modelAndView.addObject("customer", updatedCustomer);
        return modelAndView;
    }

    @RequestMapping("/deleteCustomer/{customerId}")
    public ModelAndView deleteCustomer(@PathVariable("customerId") int customerId) {
        ModelAndView modelAndView = new ModelAndView("index");
        customerService.deleteCustomerById(customerId);
        return modelAndView;

    }


}