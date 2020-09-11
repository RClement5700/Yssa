package pojos;

import java.util.ArrayList;

public class OrderPicker extends Employee {

    Order order;
    ArrayList<Slot> goBacks;

    public OrderPicker(int employeeId, String username, String password, String assignment)
    {
        super(employeeId, username, password, assignment);
    }

    public OrderPicker(int employeeId, String username, String password, String assignment,
                       Order order)
    {
        super(employeeId, username, password, assignment);
        this.order = order;
    }

    public Product pickProduct(Slot slot)
    {
        //update inventory
        return slot.getProduct();
    }

    public Product damageProduct(Slot slot) {

        //update inventory
        //replace by implementing ProductDamagedListener interface
        return slot.getProduct();
    }

    public void shortProduct(Slot slot) {

    }

    public void skipSlot(Slot slot) {
        //add to end of pick list; update recyclerView
    }

    public void addToGoBacks(Slot slot) {
        goBacks.add(slot);
    }
}
