package group.service.iko.dto;

import group.service.iko.entities.MachineType;
import group.service.iko.entities.MaintenancePart;
import group.service.iko.entities.PeriodicMaintenance;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PeriodicMaintenanceDTO {


        private int id;
        private int smr;
        private List<MaintenancePart> maintenanceParts;
        private MachineType machineType ;

public  PeriodicMaintenanceDTO(){

}
  public  PeriodicMaintenanceDTO(PeriodicMaintenance periodicMaintenance){
      id = periodicMaintenance.getId();
      smr = periodicMaintenance.getSmr();
      machineType=periodicMaintenance.getMachineType();
      maintenanceParts = periodicMaintenance.getMaintenanceParts().stream().sorted(Comparator.comparing(MaintenancePart::getPartNumber)).collect(Collectors.toList());
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSmr() {
        return smr;
    }

    public void setSmr(int smr) {
        this.smr = smr;
    }

    public List<MaintenancePart> getMaintenanceParts() {
        return maintenanceParts;
    }

    public void setMaintenanceParts(List<MaintenancePart> maintenanceParts) {
        this.maintenanceParts = maintenanceParts;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }
}


