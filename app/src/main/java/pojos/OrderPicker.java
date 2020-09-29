package pojos;

public class OrderPicker extends Employee {

    Order order;
    Department department;

    public OrderPicker(int employeeId, String username, String password)
    {
        super(employeeId, username, password);
    }

    public OrderPicker(int employeeId, String username, String password, Department department,
                       Order order)
    {
        super(employeeId, username, password);
        this.order = order;
        this.department = department;
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

}
