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
    private String machine;
    @Column
    private GregorianCalendar orderDate;
    @Column
    private int orderSmr;
    @ManyToOne()
    @JoinColumn(name = "periodic_maintenance_id")
    private PeriodicMaintenance periodicMaintenance;
    @ManyToOne()
    @JoinColumn(name = "worker_id")
    private Worker worker;
    @Column
    private String Workplace;
    @ManyToOne()
    @JoinColumn(name = "serviceMachine_id")
    private ServiceMachine serviceMachine;

    @Override
    public String toString() {
        return "WorkOrder{" +
                "id=" + id +
                ", machine='" + machine + '\'' +
                ", orderDate=" + orderDate +
                ", orderSmr=" + orderSmr +
                ", periodicMaintenance=" + periodicMaintenance +
                ", worker=" + worker +
                ", Workplace='" + Workplace + '\'' +
                ", serviceMachine=" + serviceMachine +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
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

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public String getWorkplace() {
        return Workplace;
    }

    public void setWorkplace(String workplace) {
        Workplace = workplace;
    }

    public ServiceMachine getServiceMachine() {
        return serviceMachine;
    }

    public void setServiceMachine(ServiceMachine serviceMachine) {
        this.serviceMachine = serviceMachine;
    }
}
