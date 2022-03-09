package group.service.iko.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name= "request_number")
    private String requestNumber;
    @Column(name = "offer_date")
    private GregorianCalendar offerDate;
    @Column (name = "validation_date")
    private GregorianCalendar validationDate;

    @OneToMany (mappedBy = "offer", fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OfferLine> offerLines;
    @Column (name= "offer_condition")
    private String offerCondition;
    @Column (name= "currency")
    private String currency;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column
    private int sum;
    @Column  (name= "profit_percentage")
    private int profitPercentage;
    @Column  (name= "transportation")
    private int transportation;
    @Column  (name= "discount")
    private int discount;
    @Column  (name= "VAT")
    private String VAT;


    public Offer() {
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

    public Offer setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Offer setCustomer(Customer customer) {
        this.customer = customer;
        return  this;
    }

    public GregorianCalendar getOfferDate() {
        return offerDate;
    }

    public Offer setOfferDate(GregorianCalendar offerDate) {
        this.offerDate = offerDate;
        return  this;
    }

    public GregorianCalendar getValidationDate() {
        return validationDate;
    }

    public Offer setValidationDate(GregorianCalendar validationDate) {
        this.validationDate = validationDate;
        return  this;
    }

    public String getCurrency() {
        return currency;
    }

    public Offer setCurrency(String currency) {
        this.currency = currency;
        return  this;
    }

    public int getProfitPercentage() {
        return profitPercentage;
    }

    public Offer setProfitPercentage(int profitPercentage) {
        this.profitPercentage = profitPercentage;
        return  this;
    }

    public int getTransportation() {
        return transportation;
    }

    public Offer setTransportation(int transportation) {
        this.transportation = transportation;
        return  this;
    }

    public int getDiscount() {
        return discount;
    }

    public Offer setDiscount(int discount) {
        this.discount = discount;
        return this;
    }

    public String getVAT() {
        return VAT;
    }

    public Offer setVAT(String VAT) {
        this.VAT = VAT;
        return  this;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", requestNumber='" + requestNumber + '\'' +
                ", offerDate=" + offerDate +
                ", validationDate=" + validationDate +
                ", offerLineSet=" + offerLines +
                ", offerCondition='" + offerCondition + '\'' +
                ", customer=" + customer +
                ", sum=" + sum +
                '}';
    }

    public Set<OfferLine> getOfferLines() {
        return offerLines;
    }

    public Offer setOfferLines(Set<OfferLine> offerLines) {

        this.offerLines = offerLines;
        return  this;
    }

    public String getOfferCondition() {
        return offerCondition;
    }

    public Offer setOfferCondition(String offerCondition) {

        this.offerCondition = offerCondition;
        return this;
    }

    public int getSum() {
        return sum;
    }

   public Offer setSum(int sum) {
                this.sum = sum;
                return this;
    }




}
