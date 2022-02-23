package group.service.iko.entities;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.*;

@Entity
@Table(name = "offer_line")
public class OfferLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name= "requested_part_name")
    private String requestedPartName;

    @Column (name= "requested_part_number")
    private String requestedPartNumber;

    @Column  (name= "offered_part_name")
    private String offeredPartName;

    @Column  (name= "offered_part_number")
    private String offeredPartNumber;

    @Column
    private int quantity;
    @Column
    private String unit;
    @Column
    private int price;
    @Column
    private int sum;

    @Column  (name= "supply_date")
    private int supplyDate;

    @Column
    private String producer;

    @Column  (name= "confirmation_condition")
    private String confirmationCondition;

    @ManyToOne()
        @JoinColumn(name = "offer_id")
    private Offer offer;
    @Column (name = "supplier_price")
    private Double supplierPrice;
    @Column (name = "last_offered_price")
    private int lastOfferedPrice;
    @Column (name = "availability")
    private int availability;

    @Column (name = "in_stock_net_cost")
    private int inStockNetCost;


    public OfferLine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestedPartName() {
        return requestedPartName;
    }

    public void setRequestedPartName(String requestedPartName) {
        this.requestedPartName = requestedPartName;
    }

    public String getRequestedPartNumber() {
        return requestedPartNumber;
    }

    public void setRequestedPartNumber(String requestedPartNumber) {
        this.requestedPartNumber = requestedPartNumber;
    }

    public String getOfferedPartName() {
        return offeredPartName;
    }

    public void setOfferedPartName(String offeredPartName) {
        this.offeredPartName = offeredPartName;
    }

    public String getOfferedPartNumber() {
        return offeredPartNumber;
    }

    public void setOfferedPartNumber(String offeredPartNumber) {
        this.offeredPartNumber = offeredPartNumber;
    }

    public String getConfirmationCondition() {
        return confirmationCondition;
    }

    public void setConfirmationCondition(String confirmationCondition) {
        this.confirmationCondition = confirmationCondition;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(int supplyDate) {
        this.supplyDate = supplyDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Double getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(Double supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public int getLastOfferedPrice() {
        return lastOfferedPrice;
    }

    public void setLastOfferedPrice(int lastOfferedPrice) {
        this.lastOfferedPrice = lastOfferedPrice;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getInStockNetCost() {
        return inStockNetCost;
    }

    public void setInStockNetCost(int inStockNetCost) {
        this.inStockNetCost = inStockNetCost;
    }
    @Override
    public String toString() {
        return "OfferLine{" +
                "id=" + id +
                ", requestedPartName='" + requestedPartName + '\'' +
                ", requestedPartNumber='" + requestedPartNumber + '\'' +
                ", offeredPartName='" + offeredPartName + '\'' +
                ", offeredPartNumber='" + offeredPartNumber + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                ", sum=" + sum +
                ", supplyDate=" + supplyDate +
                ", producer='" + producer + '\'' +
                ", confirmationCondition='" + confirmationCondition + '\'' +
                ", offer=" + offer +
                '}';
    }


}
