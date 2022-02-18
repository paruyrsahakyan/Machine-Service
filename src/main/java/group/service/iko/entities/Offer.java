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

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @Column
    private int sum;





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

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public GregorianCalendar getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(GregorianCalendar offerDate) {
        this.offerDate = offerDate;
    }

    public GregorianCalendar getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(GregorianCalendar validationDate) {
        this.validationDate = validationDate;
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

    public void setOfferLines(Set<OfferLine> offerLines) {
        this.offerLines = offerLines;
    }

    public String getOfferCondition() {
        return offerCondition;
    }

    public void setOfferCondition(String offerCondition) {
        this.offerCondition = offerCondition;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
