package pojos;

import java.sql.Time;
import java.util.ArrayList;

public class Order {

    ArrayList<Product> products;
    OrderPicker orderPicker;
    int orderNumber;
    int customerNumber;
    int sectionNumber; //department
    int palletCount;
    Time startTime;
    Time completionTime;
    String location; //door/stage number
    STATUS status;

    public enum STATUS {
        ASSIGNED,
        UNASSIGNED
    }

    public Order(ArrayList<Product> products) {
        this.products = products;
    }

    public Order(ArrayList<Product> products, OrderPicker orderPicker, int orderNumber,
                 int customerNumber, int sectionNumber, int palletCount, Time startTime,
                 Time completionTime, String location, STATUS status) {
        this.products = products;
        this.orderPicker = orderPicker;
        this.orderNumber = orderNumber;
        this.customerNumber = customerNumber;
        this.sectionNumber = sectionNumber;
        this.palletCount = palletCount;
        this.startTime = startTime;
        this.completionTime = completionTime;
        this.location = location;
        this.status = status;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public OrderPicker getOrderPicker() {
        return orderPicker;
    }

    public void setOrderPicker(OrderPicker orderPicker) {
        this.orderPicker = orderPicker;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public int getPalletCount() {
        return palletCount;
    }

    public void setPalletCount(int palletCount) {
        this.palletCount = palletCount;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Time completionTime) {
        this.completionTime = completionTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }
}
