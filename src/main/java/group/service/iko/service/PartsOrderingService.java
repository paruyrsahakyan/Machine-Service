package group.service.iko.service;

import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.Machine;
import group.service.iko.entities.Part;
import group.service.iko.entities.PeriodicMaintenance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;



public class PartsOrderingService {
    @Autowired
    private MachineService machineService;


//    public List<Part> countParts(int days) {
////List<Machine> machineList = machineService.getMachinesMaintainedByIKO();
////       List<MachineDTO> machineDTOList=  MachineDTO.convertIntoDTO(machineList);
////       for(MachineDTO machineDTO: machineDTOList){
////           List<PeriodicMaintenance>  periodicMaintenancelist = ServiceManager.predictedMaintenances(days);
////
////       }
////
////
////return ;
//    }

}
