package group.service.iko.dto;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.Customer;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.entities.MachineType;
import group.service.iko.service.HistoryRecordService;
import group.service.iko.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class MachineDTO {

    private int id;
    private MachineType machineType;
    private String model;
    private String serialNumber;
    private String engineModel;
    private String engineSerialNumber;
    private int productionYear;
    private String otherInfo;
    private String customer;
    private String lastInfo;
    private int lastInfoId;
    private String lastInfoDate;
    private int lastSMR;
    private String maintainedByIko;
    @Autowired
    private MachineService machineService;

    public MachineDTO() {

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
        HistoryRecordDTO historyRecordDTO = machineService.getLastInfoOfMachine(machine);
        lastInfo = historyRecordDTO.getTitle();
        lastInfoDate = historyRecordDTO.getRecordDate();
        lastInfoId = historyRecordDTO.getId();
        lastSMR = historyRecordDTO.getSMR();
        maintainedByIko = machine.getMaintainedByIko();
        if (machine.getMachineType() == null) {
            machineType = new MachineType();
             } else {
            machineType = machine.getMachineType();

        }
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public int getLastSMR() {
        return lastSMR;
    }

    public void setLastSMR(int lastSMR) {
        this.lastSMR = lastSMR;
    }

    public String getLastInfo() {
        return lastInfo;
    }

    public void setLastInfo(String lastInfo) {
        this.lastInfo = lastInfo;
    }

    public int getLastInfoId() {
        return lastInfoId;
    }

    public void setLastInfoId(int lastInfoId) {
        this.lastInfoId = lastInfoId;
    }

    public String getLastInfoDate() {
        return lastInfoDate;
    }

    public void setLastInfoDate(String lastInfoDate) {
        this.lastInfoDate = lastInfoDate;
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

    public String getMaintainedByIko() {
        return maintainedByIko;
    }

    public void setMaintainedByIko(String maintainedByIko) {
        this.maintainedByIko = maintainedByIko;
    }

    public static List<MachineDTO> convertIntoDTO(List<Machine> machineList) {
        List<MachineDTO> machineDTOList = new ArrayList<MachineDTO>();
        if (machineList != null) {
            for (Machine machine : machineList) {
                machineDTOList.add(new MachineDTO(machine));
            }
        }
        return machineDTOList;
    }
}
