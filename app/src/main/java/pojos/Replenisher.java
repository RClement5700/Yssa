package pojos;

import interfaces.EmptySlotListener;

public class Replenisher extends Employee implements EmptySlotListener {
    public Replenisher(int employeeId, String username, String password, String assignment) {
        super(employeeId, username, password, assignment);
    }

    @Override
    public void replenish(Slot slot, Product product) {

    }
}
