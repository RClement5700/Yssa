package pojos;

import java.sql.Time;
import java.util.ArrayList;

public class Order {

    ArrayList<Product> products;
    OrderPicker orderPicker;
    int orderNumber;
    int customerNumber;

    public enum STATUS {
        COMPLETE,
        INCOMPLETE
    }

    public Order(ArrayList<Product> products) {
        this.products = products;
    }

    public Order(ArrayList<Product> products, OrderPicker orderPicker, int orderNumber,
                 int customerNumber) {
        this.products = products;
        this.orderPicker = orderPicker;
        this.orderNumber = orderNumber;
        this.customerNumber = customerNumber;
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
}
