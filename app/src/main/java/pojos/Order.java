package pojos;

import java.sql.Time;
import java.util.ArrayList;

public class Order {

    ArrayList<Product> products;
    int orderNumber;
    int customerNumber;
    int userId;
    boolean status;

    public Order(ArrayList<Product> products) {
        this.products = products;
    }

    public Order(int orderNumber, boolean status) {
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public Order(int orderNumber, int userId, boolean status) {
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.status = status;
    }

    public Order(ArrayList<Product> products, int orderNumber, boolean status) {
        this.products = products;
        this.orderNumber = orderNumber;
        this.status = status;
    }

    public Order(ArrayList<Product> products, int orderNumber,
                 int customerNumber) {
        this.products = products;this.orderNumber = orderNumber;
        this.customerNumber = customerNumber;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
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

    public int getUserId() {
        return userId;
    }
}
