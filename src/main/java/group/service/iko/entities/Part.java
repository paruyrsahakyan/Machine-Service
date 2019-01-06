package group.service.iko.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


public class Part {
    private int id;
    private String partNumber;
    private String Nomenclature;
    private String unit;
    private double quantity;

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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "partNumber='" + partNumber + '\'' +
                ", Nomenclature='" + Nomenclature + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
