package group.service.iko.entities;

import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "machine_type")
public class MachineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="type_description")
    private String typeDescription;

    @OneToMany(mappedBy = "machineType", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PeriodicMaintenance> periodicMaintenanceList;

    @OneToMany(mappedBy = "machineType", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Machine> machineList;

    @Override
    public String toString() {
        return "MachineType{" +
                "id=" + id +
                ", typeDescription='" + typeDescription + '\'' +
                ", periodicMaintenanceList=" + periodicMaintenanceList +
                ", machineList=" + machineList +
                '}';
    }
    public MachineType(){

    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

      public List<PeriodicMaintenance> getPeriodicMaintenanceList() {
        return periodicMaintenanceList;
    }

    public void setPeriodicMaintenanceList(List<PeriodicMaintenance> periodicMaintenanceList) {
        this.periodicMaintenanceList = periodicMaintenanceList;
    }

    public Set<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(Set<Machine> machineList) {
        this.machineList = machineList;
    }
}
