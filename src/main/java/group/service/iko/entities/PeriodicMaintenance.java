package group.service.iko.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "periodic_maintenance")
public class PeriodicMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int smr;

    @OneToMany(mappedBy = "periodicMaintenance", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<MaintenancePart> maintenanceParts;

//    @ManyToOne()
//    @JoinColumn(name = "machine_type_id")
    private MachineType machineType ;

    @Override
    public String toString() {
        return "PeriodicMaintenance{" +
                "id=" + id +
                ", smr=" + smr +
                ", maintenanceParts=" + maintenanceParts +
                ", machineType=" + machineType +
                '}';
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
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

}
