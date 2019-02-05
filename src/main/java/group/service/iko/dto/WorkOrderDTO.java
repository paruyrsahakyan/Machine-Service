package group.service.iko.dto;

import group.service.iko.entities.Machine;
import group.service.iko.entities.PeriodicMaintenance;

import javax.persistence.*;
import java.util.GregorianCalendar;

public class WorkOrderDTO {

    private int id;

    private String machine;

    private String orderDate;

    private int smr;


    private PeriodicMaintenance periodicMaintenance;

    private String worker;


    private String location;

    private String serviceMachine;

}
