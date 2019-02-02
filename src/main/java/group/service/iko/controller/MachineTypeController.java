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

import java.util.List;
import java.util.Set;

@Controller()
@RequestMapping("/machineType")
public class MachineTypeController {
    @Autowired
    private MachineTypeService machineTypeService;
    @Autowired
    private PeriodicMaintenanceService periodicMaintenanceService;


    @RequestMapping("/{id}")
    public ModelAndView getMachineType(@PathVariable("id") int id) {
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        List<PeriodicMaintenance> maintenanceList = machineType.getSortedMaintenanceList();
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
    public ModelAndView getNewTypeCreationPage() {
        ModelAndView modelAndView = new ModelAndView("machineType/machineTypeCreation");
        return modelAndView;
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

    @RequestMapping("/{machineTypeId}/update")
    public ModelAndView machineTypeUpdatePage(
            @PathVariable("machineTypeId") int id) {
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        ModelAndView modelAndView = new ModelAndView("machineType/machineTypeUpdate");
        modelAndView.addObject("machineType", machineType);
        return modelAndView;
    }

    @RequestMapping(value = "/{machineTypeId}/updated", method = RequestMethod.POST)
    public ModelAndView modifyMachineType(
            @PathVariable("machineTypeId") int id,
            @RequestParam("typeDescription") String typeDescription) {
        MachineType machineType = machineTypeService.getMachineTypeById(id);
        machineType.setTypeDescription(typeDescription);
        machineTypeService.updateMachineType(machineType);
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
        modelAndView.addObject("machineType", machineTypeService.getMachineTypeById(id));
        return modelAndView;
    }

    @RequestMapping(value = "/{machineTypeId}/deleted")
    public ModelAndView deleteMachineType(@PathVariable("machineTypeId") int id) {
        MachineType machineType = new MachineType();
        machineType.setId(id);
        machineTypeService.deleteMachineType(machineType);
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
        List<PeriodicMaintenance> sortedMaintenanceList = machineType.getSortedMaintenanceList();
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
        modelAndView.addObject("machineType", machineType);
        modelAndView.addObject("maintenanceList", sortedMaintenanceList);
        return modelAndView;
    }

    @RequestMapping("/{machineTypeId}/periodicMaintenance/{maintenanceId}")
    public ModelAndView getPeriodicMaintenance(@PathVariable("machineTypeId") int machineTypeId,
                                               @PathVariable("maintenanceId") int maintenanceId) {

        ModelAndView modelAndView = new ModelAndView("machineType/periodicMaintenance");
        modelAndView.addObject("machineType", machineTypeService.getMachineTypeById(machineTypeId));
        modelAndView.addObject("periodicMaintenance", periodicMaintenanceService.getMaintenanceById(maintenanceId));
        return modelAndView;
    }

    @RequestMapping("/{machineTypeId}/periodicMaintenance/{maintenanceId}/update")
    public ModelAndView getUpdatePage(@PathVariable("machineTypeId") int machineTypeId,
                                      @PathVariable("maintenanceId") int maintenanceId) {

        ModelAndView modelAndView = new ModelAndView("machineType/periodicMaintenanceUpdate");
        modelAndView.addObject("machineType", machineTypeService.getMachineTypeById(machineTypeId));
        modelAndView.addObject("periodicMaintenance", periodicMaintenanceService.getMaintenanceById(maintenanceId));
        return modelAndView;
    }

    @RequestMapping("/{machineTypeId}/periodicMaintenance/{maintenanceId}/deleted")
    public ModelAndView deletePeriodicMaintenance(@PathVariable("machineTypeId") int machineTypeId,
                                                  @PathVariable("maintenanceId") int maintenanceId) {
        ModelAndView modelAndView = new ModelAndView("machineType/machineType");
        PeriodicMaintenance periodicMaintenance = new PeriodicMaintenance();
        periodicMaintenance.setId(maintenanceId);
        periodicMaintenanceService.deletePeriodicMaintenance(periodicMaintenance);
        MachineType machineType = machineTypeService.getMachineTypeById(machineTypeId);
        List<PeriodicMaintenance> maintenanceList = machineType.getSortedMaintenanceList();
        modelAndView.addObject("machineType", machineType);
        modelAndView.addObject("maintenanceList", maintenanceList);
        return modelAndView;
    }

}
