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
    private String model;
    @Column
    private String serialNumber;
    @OneToMany(mappedBy = "peridic_maintenance", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<PeriodicMaintenance> periodicMaintenanceList;
    @OneToMany(mappedBy = "machine_id", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Machine> machineList;

}
