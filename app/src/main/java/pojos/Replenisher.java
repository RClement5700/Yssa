package pojos;

import interfaces.EmptySlotListener;

public class Replenisher extends Employee implements EmptySlotListener {

    public Replenisher(int employeeId, String username, String password) {
        super(employeeId, username, password);
    }

    @Override
    public void replenish(Slot slot, Product product) {

    }
}
