package group.service.iko.controller;

import group.service.iko.entities.Machine;
import group.service.iko.entities.MachineType;
import group.service.iko.entities.MaintenancePart;
import group.service.iko.entities.PeriodicMaintenance;
import group.service.iko.service.MachineService;
import group.service.iko.service.MachineTypeService;
import group.service.iko.service.PeriodicMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller()
@RequestMapping("/machineType")
public class MachineTypeController {
    @Autowired
    private MachineTypeService machineTypeService;
    @Autowired
    private PeriodicMaintenanceService periodicMaintenanceService;


    @RequestMapping("/{id}")
    public ModelAndView machine(@PathVariable("id") int id ){
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        Set<PeriodicMaintenance> maintenanceList = machineType.getPeriodicMaintenanceList();
        modelAndView.addObject("machineType", machineType);
        modelAndView.addObject("maintenanceList", maintenanceList);
        return modelAndView;

    }

    @RequestMapping("/allMachineTypes")
    public ModelAndView allMachineTypes() {
        ModelAndView modelAndView = new ModelAndView("machineType/allMachineTypes");
       modelAndView.addObject("machineTypeList", machineTypeService.getAllMachineTypes());

        return modelAndView;
    }

    @RequestMapping("/newTypeCreation")
    public ModelAndView getNewTypeCreationPage(){
        ModelAndView modelAndView = new ModelAndView("machineType/machineTypeCreation");
        return  modelAndView;
    }

    @RequestMapping(value = "/machineTypeCreated", method = RequestMethod.POST)
    public ModelAndView createMachineType(
            @RequestParam("typeDescription") String typeDescription) {
        MachineType machineType = new MachineType();
        machineType.setTypeDescription(typeDescription);
        machineTypeService.saveMachineType(machineType);
        ModelAndView modelAndView = new ModelAndView("machineType/allMachineTypes");
        modelAndView.addObject("machineTypeList", machineTypeService.getAllMachineTypes());
        return modelAndView;
    }

    @RequestMapping("/{machineTypeId}/maintenance/newMaintenanceCreation")
    public ModelAndView newMaintenanceCreation(@PathVariable("machineTypeId") int id) {
        ModelAndView modelAndView = new ModelAndView("machineType/periodicMaintenanceCreation");
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        Set<PeriodicMaintenance> maintenanceList = machineType.getPeriodicMaintenanceList();
        modelAndView.addObject("machineType", machineType);
        modelAndView.addObject("maintenanceList", maintenanceList);
        return modelAndView;
    }

    @RequestMapping(value = "/{machineTypeId}/maintenance/createdNew", method = RequestMethod.POST)
    public ModelAndView maintenanceCreation(
            @PathVariable("machineTypeId") int id,
            @RequestParam("smr") int smr,
            @RequestParam("partNumber[]") String[] partNumberList,
            @RequestParam("description[]") String[] descriptionList,
            @RequestParam("unit[]") String[] unitList,
            @RequestParam("quantity[]") int[] quantityList) {

        MachineType machineType = machineTypeService.getMachineTypeById(id);
        PeriodicMaintenance periodicMaintenance = periodicMaintenanceService.makePeriodicMaintenance(partNumberList, smr, descriptionList, unitList, quantityList);
        periodicMaintenance.setMachineType(machineType);
        periodicMaintenanceService.savePeriodicMaintenance(periodicMaintenance);
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
        machineTypeService.getMachineTypeById(id);
        return modelAndView;
    }

  }
