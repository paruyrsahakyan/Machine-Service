package group.service.iko.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "machine_type")
public class MachineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String machineDescription;
    @OneToMany(mappedBy = "periodic_maintenance", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PeriodicMaintenance> periodicMaintenanceList;
    @OneToMany(mappedBy = "machine", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Machine> machineList;

    @Override
    public String toString() {
        return "MachineType{" +
                "id=" + id +
                ", machineDescription='" + machineDescription + '\'' +
                ", periodicMaintenanceList=" + periodicMaintenanceList +
                ", machineList=" + machineList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachineDescription() {
        return machineDescription;
    }

    public void setMachineDescription(String machineDescription) {
        this.machineDescription = machineDescription;
    }

    public List<PeriodicMaintenance> getPeriodicMaintenanceList() {
        return periodicMaintenanceList;
    }

    public void setPeriodicMaintenanceList(List<PeriodicMaintenance> periodicMaintenanceList) {
        this.periodicMaintenanceList = periodicMaintenanceList;
    }

    public List<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
    }
}
