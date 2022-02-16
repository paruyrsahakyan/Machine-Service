package group.service.iko.dto;

import group.service.iko.entities.Part;
import group.service.iko.entities.PriceForCustomer;
import group.service.iko.service.WareHouseService;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class PriceForCustomerDTO {
    private int id;
    private int customerId;
    private String article;
    private String description;
    private int price;
    private String customerName;
    private double quantityInStock;
    private double netCost;
    private int profit;




    public PriceForCustomerDTO(PriceForCustomer priceForCustomer) throws Throwable {

        id = priceForCustomer.getId();
        customerId = priceForCustomer.getCustomer().getId();
        customerName = priceForCustomer.getCustomer().getName();
        article = priceForCustomer.getArticle();
        description = priceForCustomer.getDescription();
        price = priceForCustomer.getPrice();
        WareHouseService wareHouseService = new WareHouseService();
        Part partInStock = wareHouseService.getAvailablePartList().get(article);
        if (partInStock != null) {
            quantityInStock = partInStock.getQuantity();
            netCost = wareHouseService.getAvailablePartList().get(article).getNetCost();
            profit =  (int) ((price - netCost)/price*100);
        }
    }

        public static List<PriceForCustomerDTO> convertIntoDTO (List < PriceForCustomer > priceForCustomers) throws Throwable {
            List<PriceForCustomerDTO> priceForCustomerDTOS = new ArrayList<>();

            for (PriceForCustomer priceForCustomer : priceForCustomers) {
                priceForCustomerDTOS.add(new PriceForCustomerDTO(priceForCustomer));

            }
            return priceForCustomerDTOS;
        }

        public int getId () {
            return id;
        }

        public void setId ( int id){
            this.id = id;
        }

        public int getCustomerId () {
            return customerId;
        }

        public void setCustomerId ( int customerId){
            this.customerId = customerId;
        }

        public String getArticle () {
            return article;
        }

        public void setArticle (String article){
            this.article = article;
        }

        public String getDescription () {
            return description;
        }

        public void setDescription (String description){
            this.description = description;
        }

        public int getPrice () {
            return price;
        }

        public void setPrice ( int price){
            this.price = price;
        }

        public String getCustomerName () {
            return customerName;
        }

        public void setCustomerName (String customerName){
            this.customerName = customerName;
        }

        public double getQuantityInStock () {
            return quantityInStock;
        }

        public void setQuantityInStock ( double quantityInStock){
            this.quantityInStock = quantityInStock;
        }

    public double getNetCost() {
        return netCost;
    }

    public void setNetCost(double netCost) {
        this.netCost = netCost;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
}

