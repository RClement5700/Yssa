package pojos;

import java.util.ArrayList;

public class Order {

    //indexed from 1 to n and described by their location in the warehouse.
    ArrayList<Product> products;

    public Order(ArrayList<Product> products) {
        this.products = products;
    }
}
