package group.service.iko.dto;

import group.service.iko.calendarAdapter.*;
import group.service.iko.entities.*;

import javax.persistence.*;
import java.util.*;

public class OfferDTO {



    private int id;
    private String requestNumber;
    private String offerDate;
    private String validationDate;
    private List<OfferLine> offerLineSet;
    private String offerCondition;
    private String customer;
    private int  sum;



    public OfferDTO(Offer offer) {

        this.id = offer.getId();
        this.offerDate = CalendarAdapter.getStringFormat(offer.getOfferDate());
        this.validationDate = CalendarAdapter.getStringFormat(offer.getValidationDate());
        this.offerLineSet =  offer.getOfferLineSet();
        this.offerCondition= offer.getOfferCondition();
        this.customer = offer.getCustomer().getName();
        this.requestNumber= offer.getRequestNumber();
        this.sum = offer.getSum();
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

    public List<OfferLine> getOfferLineSet() {
        return offerLineSet;
    }

    public void setOfferLineSet(List<OfferLine> offerLineSet) {
        this.offerLineSet = offerLineSet;
    }

    public String getOfferCondition() {
        return offerCondition;
    }

    public void setOfferCondition(String offerCondition) {
        this.offerCondition = offerCondition;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
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

}
