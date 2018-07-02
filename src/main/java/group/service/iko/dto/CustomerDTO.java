package group.service.iko.dto;

import group.service.iko.entities.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private int id;
    private String name;
    private String contacts;
    private String otherInfo;
    private String contract;


    public CustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        contacts = customer.getContacts();
        otherInfo = customer.getOtherInfo();
        contract = customer.getContract();

    }

    public static List<CustomerDTO> convertIntoDTO(List<Customer> customerList){
        List<CustomerDTO> newCustomers = new ArrayList<CustomerDTO>();

        for (Customer customer :customerList){
            newCustomers.add(new CustomerDTO(customer));

        }
        return  newCustomers;
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
}
