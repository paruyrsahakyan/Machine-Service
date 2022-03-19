package group.service.iko.entities;

import group.service.iko.calendarAdapter.*;
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
    @Column
    private int position;
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
    @Column  (name= "supply_time")
    private int supplyTime;
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
    @Column (name = "last_offer_date")
    private GregorianCalendar lastOfferDate;
    @Column (name = "availability")
    private int availability;
    @Column (name = "profit_from_available")
    private int profitFromAvailable;
    @Column (name = "in_stock_net_cost")
    private int inStockNetCost;

    public OfferLine() {
    }

    public OfferLine(int position, String partName, String partNumber, int quantity, String offeredPartNumber, String unit, int price, int sum, int lastOfferPrice,
                     String lastOfferDate, int availability, int inStockNetCost, int profitFromAvailable, int supplyTime, Double supplierPrice, String producer) {
    this.position = position;
    this.requestedPartName=partName;
    this. requestedPartNumber= partNumber;
    this.quantity = quantity;
    this.offeredPartNumber= offeredPartNumber;
    this.unit= unit;
    this.price = price;
    this.sum = sum;
    this.lastOfferedPrice = lastOfferPrice;
    if (lastOfferDate.contains("-")){
    this.lastOfferDate = CalendarAdapter.getGregCalendar(lastOfferDate);}
    this.availability=availability;
    this.inStockNetCost = inStockNetCost;
    this.profitFromAvailable = profitFromAvailable;
    this.supplyTime = supplyTime;
    this.supplierPrice = supplierPrice;
    this.producer = producer;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getSupplyTime() {
        return supplyTime;
    }

    public void setSupplyTime(int supplyTime) {
        this.supplyTime = supplyTime;
    }

    public GregorianCalendar getLastOfferDate() {
        return lastOfferDate;
    }

    public void setLastOfferDate(GregorianCalendar lastOfferDate) {
        this.lastOfferDate = lastOfferDate;
    }

    public int getProfitFromAvailable() {
        return profitFromAvailable;
    }

    public void setProfitFromAvailable(int profitFromAvailable) {
        this.profitFromAvailable = profitFromAvailable;
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
                ", supplyDate=" + supplyTime +
                ", producer='" + producer + '\'' +
                ", confirmationCondition='" + confirmationCondition + '\'' +
                ", offer=" + offer +
                '}';
    }

}
