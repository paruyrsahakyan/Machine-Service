package group.service.iko.entities;

import javax.persistence.*;
import java.util.GregorianCalendar;

@Entity
@Table(name = "work_order")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "machine_id")
    private Machine machine;
    @Column(name="order_date")
    private GregorianCalendar orderDate;
    @Column(name="SMR")
    private int orderSmr;
    @ManyToOne()
    @JoinColumn(name = "periodic_maintenance_id")
    private PeriodicMaintenance periodicMaintenance;
    @Column
    private String worker;
    @Column(name = "location")
    private String location;
    @Column(name="service_machine")
    private String serviceMachine;
   // @Column (name = "condition")
   // private int condition;   //if completed - condition = 1, if not -condition = 0;


    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", machine=" + machine +
                ", orderDate=" + orderDate +
                ", orderSmr=" + orderSmr +
                ", periodicMaintenance=" + periodicMaintenance +
                ", worker='" + worker + '\'' +
                ", location='" + location + '\'' +
                ", serviceMachine='" + serviceMachine + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public GregorianCalendar getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(GregorianCalendar orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderSmr() {
        return orderSmr;
    }

    public void setOrderSmr(int orderSmr) {
        this.orderSmr = orderSmr;
    }

    public PeriodicMaintenance getPeriodicMaintenance() {
        return periodicMaintenance;
    }

    public void setPeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        this.periodicMaintenance = periodicMaintenance;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServiceMachine() {
        return serviceMachine;
    }

    public void setServiceMachine(String serviceMachine) {
        this.serviceMachine = serviceMachine;
    }

   

}
