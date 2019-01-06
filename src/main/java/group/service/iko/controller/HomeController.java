package group.service.iko.controller;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.CustomerDTO;
import group.service.iko.dto.DtoFactory;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.service.*;
import group.service.iko.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller()
public class HomeController {
    @Autowired
    private CustomerService customerService;

    private MachineService machineService;
    @Autowired
    private ServiceMachineService serviceMachineService;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        return modelAndView;

    }

    @RequestMapping("/allCustomers")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView allCustomers() {
        ModelAndView modelAndView = new ModelAndView("allCustomers");
        List<Customer> customers = customerService.getAllCustomers();
        modelAndView.addObject("customerList", CustomerDTO.convertIntoDTO(customers));
        return modelAndView;
    }

    @RequestMapping("/allMachines")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView allMachines() {
        ModelAndView modelAndView = new ModelAndView("allMachines");
        machineService = new MachineService();
        List<Machine> machineList = machineService.getAllMachines();
        modelAndView.addObject("machineList", DtoFactory.makeMachineDtoList(machineList));
         return modelAndView;
    }

    @RequestMapping("/allMachines/filtered")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView filteredMachines
            (@RequestParam(value = "model", defaultValue = "") String model,
             @RequestParam(value = "serialNumber", defaultValue = "") String serialNumber) {
        ModelAndView modelAndView = new ModelAndView("allMachines");
        List<Machine> machineList = machineService.getMachinesFiltered(model, serialNumber);
        modelAndView.addObject("machineList", DtoFactory.makeMachineDtoList(machineList));
        modelAndView.addObject("model", model);
        modelAndView.addObject("serialNumber", serialNumber);
        return modelAndView;
    }

    @RequestMapping("/allCustomers/filtered")
    public ModelAndView filteredCustomers
            (@RequestParam(value = "customerName", defaultValue = "") String customerName) {
        ModelAndView modelAndView = new ModelAndView("allCustomers");
        List<Customer> customerList = customerService.getCustomersFiltered(customerName);
        modelAndView.addObject("customerList", DtoFactory.makeCustomerDtoList(customerList));
        modelAndView.addObject("customerName", customerName);
        return modelAndView;
    }

    @RequestMapping("/favicon.ico")
    public  ModelAndView showFavicon() {
        ModelAndView modelAndView = new ModelAndView("index");
        return  modelAndView;

    }


}