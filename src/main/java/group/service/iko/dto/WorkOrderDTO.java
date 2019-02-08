package group.service.iko.dto;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.Machine;
import group.service.iko.entities.PeriodicMaintenance;
import group.service.iko.entities.WorkOrder;

import javax.persistence.*;
import java.util.GregorianCalendar;

public class WorkOrderDTO {

    private int id;

    private MachineDTO machine;

    private String orderDate;

    private int smr;

    private PeriodicMaintenance periodicMaintenance;

    private String worker;

    private String location;

    private String serviceMachine;


    public WorkOrderDTO(WorkOrder workOrder){
        this.id= workOrder.getId();
        this.machine= new MachineDTO(workOrder.getMachine());
        this.orderDate= CalendarAdapter.getStringFormat(workOrder.getOrderDate());
        this.smr=workOrder.getOrderSmr();
        this.periodicMaintenance=workOrder.getPeriodicMaintenance();
        this.location=workOrder.getLocation();
        this.worker=workOrder.getWorker();
        this.serviceMachine=workOrder.getServiceMachine();
    }

    @Override
    public String toString() {
        return "WorkOrderDTO{" +
                "id=" + id +
                ", machine='" + machine + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", smr=" + smr +
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

    public MachineDTO getMachine() {
        return machine;
    }

    public void setMachine(MachineDTO machine) {
        this.machine = machine;
    }

    public String getOrderDate() {

        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getSmr() {
        return smr;
    }

    public void setSmr(int smr) {
        this.smr = smr;
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
