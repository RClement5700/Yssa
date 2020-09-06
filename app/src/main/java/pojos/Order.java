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
    String doorNumber; //door/stage number

    public Order(ArrayList<Product> products, int orderNumber, int storeNumber, int palletCount,
                 Time completionTime, boolean status, String doorNumber) {
        this.products = products;
        this.orderNumber = orderNumber;
        this.storeNumber = storeNumber;
        this.palletCount = palletCount;
        this.completionTime = completionTime;
        this.status = status;
        this.doorNumber = doorNumber;
    }

    public int getProductCount() {
        return products.size();
    }

    public boolean getStatus() {
        return status;
    }
}
