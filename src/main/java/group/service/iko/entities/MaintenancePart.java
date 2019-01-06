package group.service.iko.entities;

import javax.persistence.*;
@Entity
@Table(name = "maintenance_part")
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
    private int quantity;
    @ManyToOne()
    @JoinColumn(name = "periodic_maintenance_id")
    private PeriodicMaintenance periodicMaintenance;

    @Override
    public String toString() {
        return "MaintenancePart{" +
                "id=" + id +
                ", partNumber='" + partNumber + '\'' +
                ", Nomenclature='" + Nomenclature + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                ", periodicMaintenance=" + periodicMaintenance +
                '}';
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
        return Nomenclature;
    }

    public void setNomenclature(String nomenclature) {
        Nomenclature = nomenclature;
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

