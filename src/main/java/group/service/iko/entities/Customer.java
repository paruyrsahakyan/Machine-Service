package group.service.iko.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    @Column
    private String contacts;
    @Column
    private String otherInfo;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Machine> machineList;
    @ManyToOne()
    @JoinColumn(name = "worker_id")
    private Worker responsible;
    @Column
    private String contract;
    @OneToMany (mappedBy = "customer", fetch =FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PriceForCustomer> priceForCustomerList;


@Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contacts='" + contacts + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                                '}';
    }


    public Worker getResponsible() {
        return responsible;
    }

    public void setResponsible(Worker responsible) {
        this.responsible = responsible;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public List<Machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(List<Machine> machineList) {
        this.machineList = machineList;
    }

}
