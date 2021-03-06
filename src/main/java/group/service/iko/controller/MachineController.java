package group.service.iko.controller;

import group.service.iko.dto.DtoFactory;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.Customer;
import group.service.iko.entities.Machine;
import group.service.iko.service.CustomerService;
import group.service.iko.service.DetailedLaborHourService;
import group.service.iko.service.MachineService;
import group.service.iko.service.RecordFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller()
@RequestMapping("/customer/machine")
public class MachineController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private MachineService machineService;

    @RequestMapping(value = "/createMachine/{customerId}")
    public ModelAndView createMachine(@PathVariable("customerId") int customerId) {
        ModelAndView modelAndView = new ModelAndView("createMachine");
       String customerName = customerService.getCustomerById(customerId).getName();
        modelAndView.addObject("customerName", customerName);
        modelAndView.addObject("customerId", customerId);
        return modelAndView;
    }

    @RequestMapping(value = "/newMachine/{customerName}", method = RequestMethod.POST)
    public ModelAndView newMachine(@RequestParam("model") String model,                                 @RequestParam("serialNumber") String serialNumber,
                                   @RequestParam("engineModel") String engineModel,
                                   @RequestParam("engineSerialNumber") String engineSerialNumber,
                                   @RequestParam(name = "productionYear", defaultValue = "0") int productonYear,
                                   @PathVariable("customerName") int customerId,
                                   @RequestParam("otherInfo") String otherInfo
    ) {
        ModelAndView modelAndView = new ModelAndView("machine");
        Machine machine = new Machine();
        machine.setModel(model);
        machine.setSerialNumber(serialNumber);
        machine.setEngineModel(engineModel);
        machine.setEngineSerialNumber(engineSerialNumber);
        machine.setProductionYear(productonYear);
        machine.setOtherInfo(otherInfo);
        machine.setCustomer( customerService.getCustomerById(customerId));
        machineService.saveMachine(machine);
        Machine createdMachine = machineService.getLastRecord();
         modelAndView.addObject("machine", new MachineDTO(createdMachine));
         modelAndView.addObject("customerId", createdMachine.getCustomer().getId());
        return modelAndView;
    }

    @RequestMapping("/{machine.Id}")
    public ModelAndView machine(@PathVariable("machine.Id") int machineId) {
        ModelAndView modelAndView = new ModelAndView("machine");
        Machine machine = machineService.getMachineById(machineId);
        modelAndView.addObject("machine", new MachineDTO(machine));
        modelAndView.addObject("customerId", machine.getCustomer().getId());
        return modelAndView;
    }

    @RequestMapping("/updateMachine/{machineId}")
    public ModelAndView updateMachine(@PathVariable("machineId") int id) {
        ModelAndView modelAndView = new ModelAndView("updateMachine");
        Machine machine = machineService.getMachineById(id);
        modelAndView.addObject("machine", new MachineDTO(machine));
        return modelAndView;
    }

    @RequestMapping(value = "/updatedMachine/{machineId}", method = RequestMethod.POST)
    public ModelAndView updatedMachine(@PathVariable("machineId") int machineId,
                                       @RequestParam("model") String model,
                                       @RequestParam("serialNumber") String serialNumber,
                                       @RequestParam("engineModel") String engineModel,
                                       @RequestParam("engineSerialNumber") String engineSerialNumber,
                                       @RequestParam(name = "productionYear", defaultValue = "0") int productonYear,
                                       @RequestParam("otherInfo") String otherInfo
    ) {
        ModelAndView modelAndView = new ModelAndView("machine");
         Machine machine = machineService.getMachineById(machineId);
        machine.setId(machineId);
        machine.setModel(model);
        machine.setSerialNumber(serialNumber);
        machine.setEngineModel(engineModel);
        machine.setEngineSerialNumber(engineSerialNumber);
        machine.setOtherInfo(otherInfo);
        machine.setProductionYear(productonYear);
        machineService.updateMachine(machine);
        System.out.println(machineId);
        Machine updatedMachine = machineService.getMachineById(machineId);
        System.out.println(updatedMachine);
        modelAndView.addObject("machine", new MachineDTO(updatedMachine));
        return modelAndView;

    }

    @RequestMapping("/deleteMachine/{machineId}")
    public ModelAndView deleteMachine(@PathVariable("machineId") int machineId) {
        ModelAndView modelAndView = new ModelAndView("customer");
        int customerId = machineService.getCustomerIdByMachineId(machineId);
        machineService.deleteMachineById(machineId);
        modelAndView.addObject("customer", customerService.getCustomerById(customerId));


        return modelAndView;
    }
}

