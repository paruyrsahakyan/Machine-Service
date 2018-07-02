package group.service.iko.dto;

import group.service.iko.entities.Customer;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class MachineDTO {

    private int id;
    private String model;
    private String serialNumber;
    private String engineModel;
    private String engineSerialNumber;
    private int productionYear;
    private String otherInfo;
    private String customer;


    public  MachineDTO(){

    }
    public MachineDTO(Machine machine) {
        id = machine.getId();
        model = machine.getModel();
        serialNumber = machine.getSerialNumber();
        engineModel = machine.getEngineModel();
        engineSerialNumber = machine.getEngineSerialNumber();
        productionYear = machine.getProductionYear();
        otherInfo = machine.getOtherInfo();
        customer = machine.getCustomer().getName();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getEngineModel() {
        return engineModel;
    }

    public void setEngineModel(String engineModel) {
        this.engineModel = engineModel;
    }

    public String getEngineSerialNumber() {
        return engineSerialNumber;
    }

    public void setEngineSerialNumber(String engineSerialNumber) {
        this.engineSerialNumber = engineSerialNumber;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public static List<MachineDTO> convertIntoDTO(List<Machine> machineList) {
        List<MachineDTO> machineDTOList = new ArrayList<MachineDTO>();
        for (Machine machine : machineList){
            machineDTOList.add(new MachineDTO(machine));
                    }
        return  machineDTOList;
    }
}
