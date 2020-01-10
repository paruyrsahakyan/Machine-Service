package group.service.iko.dto;

import group.service.iko.entities.PriceForCustomer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PriceForCustomerDTO {
    private int id;
    private int customerId;
    private String article;
    private String description;
    private int price;
    private  String customerName;


public  PriceForCustomerDTO(PriceForCustomer priceForCustomer){
id= priceForCustomer.getId();
customerId=priceForCustomer.getCustomer().getId();
customerName=priceForCustomer.getCustomer().getName();
article = priceForCustomer.getArticle();
description= priceForCustomer.getDescription();
price= priceForCustomer.getPrice();
}

    public static Set<PriceForCustomerDTO> convertIntoDTO(Set<PriceForCustomer> priceForCustomers){
        Set<PriceForCustomerDTO> priceForCustomerDTOS = new HashSet<>();

        for (PriceForCustomer priceForCustomer:priceForCustomers){
            priceForCustomerDTOS.add(new PriceForCustomerDTO(priceForCustomer));

        }
        return  priceForCustomerDTOS;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
