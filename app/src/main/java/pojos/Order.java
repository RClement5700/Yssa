package pojos;

import java.sql.Time;
import java.util.ArrayList;

public class Order {

    ArrayList<Product> products;
    int orderNumber;
    int storeNumber;
    int palletCount;
    Time completionTime;
    boolean status; //complete or incomplete
    String location; //door/stage number

    public Order(ArrayList<Product> products, int orderNumber, int storeNumber, int palletCount,
                 Time completionTime, boolean status, String location) {
        this.products = products;
        this.orderNumber = orderNumber;
        this.storeNumber = storeNumber;
        this.palletCount = palletCount;
        this.completionTime = completionTime;
        this.status = status;
        this.location = location;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public int getProductCount() {
        return products.size();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public boolean getStatus() {
        return status;
    }
}
