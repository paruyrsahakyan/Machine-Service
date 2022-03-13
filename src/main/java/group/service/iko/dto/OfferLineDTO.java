package group.service.iko.dto;

import group.service.iko.calendarAdapter.*;
import group.service.iko.entities.*;

import javax.persistence.*;
import java.util.*;

public class OfferLineDTO {

    private int id;
    private int position;
    private String requestedPartName;
    private String requestedPartNumber;
    private String offeredPartName;
    private String offeredPartNumber;
    private int quantity;
    private String unit;
    private int price;
    private int sum;
    private int supplyTime;
    private String producer;
    private String confirmationCondition;
    private Offer offer;
    private Double supplierPrice;
    private int lastOfferedPrice;
    private String lastOfferDate;
    private int availability;
    private int profitFromAvailable;
    private int inStockNetCost;

     OfferLineDTO (OfferLine offerLine) {

         this.id = offerLine.getId();
         this.position = offerLine.getPosition();
         this.requestedPartName = offerLine.getRequestedPartName();
         this.requestedPartNumber = offerLine.getOfferedPartName();
         this.offeredPartName = offerLine.getOfferedPartName();
         this.offeredPartNumber =offerLine.getOfferedPartNumber();
         this.quantity =  offerLine.getQuantity();
         this.unit = offerLine.getUnit();
         this.price = offerLine.getPrice();
         this.sum = offerLine.getSum();
         this.supplyTime = offerLine.getSupplyTime();
         this.confirmationCondition = offerLine.getConfirmationCondition();
         this.supplierPrice = offerLine.getSupplierPrice();
         this.lastOfferedPrice = offerLine.getLastOfferedPrice();
         this.lastOfferDate = CalendarAdapter.getStringFormat(offerLine.getLastOfferDate());
         this.availability = offerLine.getAvailability();
         this.profitFromAvailable = offerLine.getProfitFromAvailable();
         this.inStockNetCost = offerLine.getInStockNetCost();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public int getSupplyTime() {
        return supplyTime;
    }

    public void setSupplyTime(int supplyTime) {
        this.supplyTime = supplyTime;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getConfirmationCondition() {
        return confirmationCondition;
    }

    public void setConfirmationCondition(String confirmationCondition) {
        this.confirmationCondition = confirmationCondition;
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

    public String getLastOfferDate() {
        return lastOfferDate;
    }

    public void setLastOfferDate(String lastOfferDate) {
        this.lastOfferDate = lastOfferDate;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getProfitFromAvailable() {
        return profitFromAvailable;
    }

    public void setProfitFromAvailable(int profitFromAvailable) {
        this.profitFromAvailable = profitFromAvailable;
    }

    public int getInStockNetCost() {
        return inStockNetCost;
    }

    public void setInStockNetCost(int inStockNetCost) {
        this.inStockNetCost = inStockNetCost;
    }
}
