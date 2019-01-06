package group.service.iko.entities;

import javax.persistence.*;
@Entity
@Table(name = "maintencePart")
public class MaintenancePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String partNumber;
    @Column
    private String Nomenclature;
    @Column
    private String unit;
    @Column
    private double quantity;
    @ManyToOne()
    @JoinColumn(name = "periodicMaintenance_id")
    private PeriodicMaintenance periodicMaintenance;
   }

