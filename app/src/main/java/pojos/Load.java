package pojos;

import java.util.ArrayList;

public class Load {

    private ArrayList<Order> orders;

    public Load(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }
}
