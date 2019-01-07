package group.service.iko.entities;

import javax.persistence.*;
@Entity
@Table(name = "maintenance_part")
public class MaintenancePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="part_type")
    private String partType;
    @Column(name = "part_number")
    private String partNumber;
    @Column
    private String nomenclature;
    @Column
    private String unit;
    @Column
    private int quantity;
    @ManyToOne()
    @JoinColumn(name = "periodic_maintenance_id")
    private PeriodicMaintenance periodicMaintenance;


    @Override
    public String toString() {
        return "MaintenancePart{" +
                "id=" + id +
                ", partType='" + partType + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", nomenclature='" + nomenclature + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", periodicMaintenance=" + periodicMaintenance +
                '}';
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getNomenclature() {
        return nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        this.nomenclature = nomenclature;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PeriodicMaintenance getPeriodicMaintenance() {
        return periodicMaintenance;
    }

    public void setPeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        this.periodicMaintenance = periodicMaintenance;
    }
}

