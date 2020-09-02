package pojos;

import java.sql.Time;
import java.util.ArrayList;

public class Order {

    ArrayList<Product> products;
    int orderNumber;
    int storeNumber;
    int palletCount;
    Time completionTime;
    String location; //door/stage number

    public Order(ArrayList<Product> products, int orderNumber, int storeNumber, int palletCount,
                 Time completionTime, String location) {
        this.products = products;
        this.orderNumber = orderNumber;
        this.storeNumber = storeNumber;
        this.palletCount = palletCount;
        this.completionTime = completionTime;
        this.location = location;
    }
}
