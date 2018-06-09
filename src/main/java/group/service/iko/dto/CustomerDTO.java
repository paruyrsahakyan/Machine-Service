package group.service.iko.dto;

import group.service.iko.entities.Customer;

public class CustomerDTO {

    private int id;
    private String name;
    private String contacts;
    private String otherInfo;

    public CustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        contacts = customer.getContacts();
        otherInfo = customer.getOtherInfo();


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
