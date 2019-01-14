package group.service.iko.controller;

import group.service.iko.entities.MachineType;
import group.service.iko.entities.PeriodicMaintenance;
import group.service.iko.service.MachineTypeService;
import group.service.iko.service.PeriodicMaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Controller()
@RequestMapping("/machineType")
public class MachineTypeController {
    @Autowired
    private MachineTypeService machineTypeService;
    @Autowired
    private PeriodicMaintenanceService periodicMaintenanceService;


    @RequestMapping("/{id}")
    public ModelAndView getMachineType(@PathVariable("id") int id ){
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        Set<PeriodicMaintenance> maintenanceList = machineType.getPeriodicMaintenanceList();
        Set<PeriodicMaintenance> sortedMaintenanceList = maintenanceList.stream().
                sorted(Comparator.comparing(PeriodicMaintenance::getSmr)).collect(Collectors.toSet());
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
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

        periodicMaintenanceService.savePeriodicMaintenance(id, partNumberList, smr, descriptionList, unitList, quantityList);
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        Set<PeriodicMaintenance> periodicMaintenanceList = machineType.getPeriodicMaintenanceList();
        Set<PeriodicMaintenance> sortedMaintenanceList = periodicMaintenanceList.stream().
        sorted(Comparator.comparing(PeriodicMaintenance::getSmr)).collect(Collectors.toSet());
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
        modelAndView.addObject("machineType", machineType);
        modelAndView.addObject("maintenanceList", sortedMaintenanceList);
        return modelAndView;
    }
    @RequestMapping("/{machineTypeId}/periodicMaintenance/{maintenanceId}")
    public ModelAndView  getPeriodicMaintenance(@PathVariable("machineTypeId") int machineTypeId,
                                                @PathVariable("maintenanceId") int maintenanceId){

    ModelAndView modelAndView = new ModelAndView("machineType/periodicMaintenance");
    modelAndView.addObject("machineType", machineTypeService.getMachineTypeById(machineTypeId));
   modelAndView.addObject("periodicMaintenance", periodicMaintenanceService.getMaintenanceById(maintenanceId));
   return  modelAndView;
    }
    @RequestMapping("/{machineTypeId}/periodicMaintenance/{maintenanceId}/deleted")
    public ModelAndView deletePeriodicMaintenance(@PathVariable("machineTypeId") int machineTypeId,
                                                  @PathVariable("maintenanceId") int maintenanceId){
     ModelAndView modelAndView= new ModelAndView("machineType/machineType");
     MachineType machineType = machineTypeService.getMachineTypeById(maintenanceId);
     Set<PeriodicMaintenance> periodicMaintenance = machineType.getPeriodicMaintenanceList().stream().
             sorted(Comparator.comparing(PeriodicMaintenance::getSmr)).collect(Collectors.toSet());
     modelAndView.addObject("machineType", machineType);
     modelAndView.addObject("periodicMaintenance", periodicMaintenance);
     return modelAndView;

    }

    

  }
