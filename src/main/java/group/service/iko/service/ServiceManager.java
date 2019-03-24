package group.service.iko.service;

import group.service.iko.dto.DtoFactory;
import group.service.iko.dto.MachineDTO;
import group.service.iko.dto.PeriodicMaintenanceDTO;
import group.service.iko.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceManager {
    @Autowired
    private MachineService machineService;


    public Map<String, Integer> countParts(int days) {
        List<MaintenancePart> partList = new ArrayList<>();
        Map<String, Integer> partsMap = new HashMap<>();
        java.util.List<Machine> machineList = machineService.getMachinesMaintainedByIKO();
        for (Machine machine : machineList) {
            List<PeriodicMaintenanceDTO> periodicMaintenanceList = ServiceManager.predictMaintenances(machine, days);
            periodicMaintenanceList.forEach(maintenance -> partList.addAll(maintenance.getMaintenanceParts()));
        }
        for (MaintenancePart maintenancePart : partList) {
            String partNumber = maintenancePart.getPartNumber();
            Integer quantity = maintenancePart.getQuantity();
            if (partsMap.containsKey(partNumber)) {
                Integer oldValue = partsMap.get(partNumber);
                Integer newValue = oldValue + quantity;
                partsMap.replace(partNumber, newValue);
            } else partsMap.put(partNumber, quantity);
        }
        return partsMap;
    }

    private static List<PeriodicMaintenanceDTO> predictMaintenances(Machine machine, int days) {
        List<PeriodicMaintenanceDTO> result = new ArrayList<>();
        MachineDTO machineDTO = new MachineDTO(machine);
        int lastSmrOfMachine = machineDTO.getLastSMR();
        List<PeriodicMaintenanceDTO> periodicMaintenancesOfMachine= machine.getMachineType().getSortedMaintenanceList();
        for(int i=lastSmrOfMachine; i<lastSmrOfMachine+days; i++){
            PeriodicMaintenanceDTO periodicMaintenanceDTO = null;
            for (PeriodicMaintenanceDTO perMaintDTO: periodicMaintenancesOfMachine) {
                if (i % perMaintDTO.getSmr() == 0 ) {
                    periodicMaintenanceDTO = perMaintDTO;
                }
            }
            if(periodicMaintenanceDTO!=null) {
                result.add(periodicMaintenanceDTO);
            }
        }
        return result;
    }
}