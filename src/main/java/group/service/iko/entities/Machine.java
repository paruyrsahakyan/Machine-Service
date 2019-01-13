package group.service.iko.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "machine")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String model;
    @Column
    private String serialNumber;
    @Column
    private String engineModel;
    @Column
    private String engineSerialNumber;
    @Column
    private int productionYear;
    @ManyToOne()
    @JoinColumn(name = "machine_type_id")
    private MachineType machineType;
    @Column
    private String otherInfo;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "machine", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<HistoryRecord> historyRecordList;


    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", engineModel='" + engineModel + '\'' +
                ", engineSerialNumber='" + engineSerialNumber + '\'' +
                ", productionYear=" + productionYear +
                ", machineType=" + machineType +
                ", otherInfo='" + otherInfo + '\'' +
                ", customer=" + customer +
                ", historyRecordList=" + historyRecordList +
                '}';
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

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<HistoryRecord> getHistoryRecordList() {
        return historyRecordList;
    }

    public void setHistoryRecordList(List<HistoryRecord> historyRecordList) {
        this.historyRecordList = historyRecordList;
    }
}