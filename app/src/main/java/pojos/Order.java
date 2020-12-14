package pojos;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;

public class Order implements Serializable {

    Map<Integer, Integer> products; // K = productId, V = quantity to be picked
    int customerId;
    int goalTime; //time in minutes
    int section; //ex. 90 = Meat, 40 = produce

    public Order(Map<Integer, Integer> products, int customerId, int goalTime, int section) {
        this.products = products;
        this.customerId = customerId;
        this.goalTime = goalTime;
        this.section = section;
    }
}
