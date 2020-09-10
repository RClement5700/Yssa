package pojos;

import java.util.ArrayList;

public class Loader extends Employee {

    private Load loadManifest;
    public Loader(int employeeId, String username, String password, String assignment)
    {
        super(employeeId, username, password, assignment);
    }

    public Loader(int employeeId, String username, String password, String assignment,
                  Load loadManifest)
    {
        super(employeeId, username, password, assignment);
        this.loadManifest = loadManifest;
    }

    public Load getLoadManifest() {
        return loadManifest;
    }

    public void load(Order order) {
        //other operations
        loadManifest.getOrders().add(order);
    }
}
