package group.service.iko.dto;

import group.service.iko.calendarAdapter.*;
import group.service.iko.entities.*;

import javax.persistence.*;
import java.util.*;
import java.util.stream.*;

public class OfferDTO {


    private int id;
    private String requestNumber;
    private String offerDate;
    private String validationDate;
    private List<OfferLine> offerLines;
    private String offerCondition;
    private String currency;
    private String customerName;
    private double sum;
    private int profitPercentage;
    private int transportation;
    private int discount;
    private String VAT;
    private double exchangeRate;


    public OfferDTO(Offer offer, List<OfferLine> offerLines) {

        this.id = offer.getId();
        this.requestNumber= offer.getRequestNumber();
        this.offerDate = CalendarAdapter.getStringFormat(offer.getOfferDate());
        this.validationDate = CalendarAdapter.getStringFormat(offer.getValidationDate());
        this.offerLines =  offerLines.stream().sorted(Comparator.comparing(OfferLine::getPosition)).collect(Collectors.toList());;
        this.offerCondition= offer.getOfferCondition();
        this.customerName = offer.getCustomer().getName();
        this.sum = offer.getSum();
        this.profitPercentage = offer.getProfitPercentage();
        this.transportation = offer.getTransportation();
        this.discount = offer.getDiscount();
        this.VAT = offer.getVAT();
        this.exchangeRate = offer.getExchangeRate();
        this.requestNumber= offer.getRequestNumber();
        this.sum = offer.getSum();
            }

    public OfferDTO(Offer offer) {

        this.id = offer.getId();
        this.requestNumber= offer.getRequestNumber();
        this.offerDate = CalendarAdapter.getStringFormat(offer.getOfferDate());
        this.validationDate = CalendarAdapter.getStringFormat(offer.getValidationDate());
        this.offerLines =  offer.getOfferLines().stream().sorted(Comparator.comparing(OfferLine::getPosition)).collect(Collectors.toList());;
        this.offerCondition= offer.getOfferCondition();
        this.customerName = offer.getCustomer().getName();
        this.sum = offer.getSum();
        this.profitPercentage = offer.getProfitPercentage();
        this.transportation = offer.getTransportation();
        this.discount = offer.getDiscount();
        this.VAT = offer.getVAT();
        this.exchangeRate = offer.getExchangeRate();
        this.requestNumber= offer.getRequestNumber();
        this.sum = offer.getSum();
    }



    public static List<OfferDTO> convertIntoDTO(List<Offer> offerList) {


        List<OfferDTO> offerDTOList = new ArrayList<>();
        if (offerList != null) {

            for (Offer offer: offerList){
                offerDTOList.add(new OfferDTO(offer));
            }

        }
return offerDTOList;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public String getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(String validationDate) {
        this.validationDate = validationDate;
    }

    public List<OfferLine> getOfferLines() {
        return offerLines;
    }

    public void setOfferLines(List<OfferLine> offerLines) {
        this.offerLines = offerLines;
    }

    public String getOfferCondition() {
        return offerCondition;
    }

    public void setOfferCondition(String offerCondition) {
        this.offerCondition = offerCondition;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getProfitPercentage() {
        return profitPercentage;
    }

    public void setProfitPercentage(int profitPercentage) {
        this.profitPercentage = profitPercentage;
    }

    public int getTransportation() {
        return transportation;
    }

    public void setTransportation(int transportation) {
        this.transportation = transportation;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getVAT() {
        return VAT;
    }

    public void setVAT(String VAT) {
        this.VAT = VAT;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
