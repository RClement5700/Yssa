package pojos;

public class OrderPicker extends Employee {

    Order order;

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
}
