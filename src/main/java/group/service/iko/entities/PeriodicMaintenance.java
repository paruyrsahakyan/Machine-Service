package group.service.iko.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "periodic_maintenence")
public class PeriodicMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int smr;
    @OneToMany(mappedBy = "periodic_maintenance", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<MaintenancePart> maintenanceParts;
    @Column
    private String serviceMachine;

    @Override
    public String toString() {
        return "PeriodicMaintenance{" +
                "id=" + id +
                ", smr=" + smr +
                ", maintenanceParts=" + maintenanceParts +
                ", serviceMachine='" + serviceMachine + '\'' +
                '}';
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

    public String getServiceMachine() {
        return serviceMachine;
    }

    public void setServiceMachine(String serviceMachine) {
        this.serviceMachine = serviceMachine;
    }
}
