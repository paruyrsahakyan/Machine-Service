package group.service.iko.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private GregorianCalendar confirmationDate;
      @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany (mappedBy = "order", fetch =FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OrderLine> oderLines;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GregorianCalendar getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(GregorianCalendar confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    }

