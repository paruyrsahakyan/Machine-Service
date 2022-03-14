package group.service.iko.entities;

import java.util.*;

public class Request {

    private int position;
    private  Customer customer;
    private  String requestNumber;
    private List<RequestLine> requestLines;


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public List<RequestLine> getRequestLines() {
        return requestLines;
    }

    public void setRequestLines(List<RequestLine> requestLines) {
        this.requestLines = requestLines;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Request{" +
                "customer=" + customer +
                ", requestNumber='" + requestNumber + '\'' +
                ", requestLines=" + requestLines +
                '}';
    }
}
