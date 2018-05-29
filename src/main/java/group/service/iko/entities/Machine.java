package group.service.iko.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    @Column
    private String otherInfo;
    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "machine", fetch = FetchType.EAGER)
    private List<HistoryRecord> historyRecordList;

    public Machine() {

    }

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", engineModel='" + engineModel + '\'' +
                ", engineSerialNumber='" + engineSerialNumber + '\'' +
                ", productionYear=" + productionYear +
                ", otherInfo='" + otherInfo + '\'' +
                ", customer=" + customer.getName() +
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
