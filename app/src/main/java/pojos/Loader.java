package pojos;

import interfaces.LoadCompleteListener;

public class Loader extends Employee implements LoadCompleteListener {

    private Load loadManifest;
    public Loader(int employeeId, String username, String password)
    {
        super(employeeId, username, password);
    }

    public Loader(int employeeId, String username, String password,
                  Load loadManifest)
    {
        super(employeeId, username, password);
        this.loadManifest = loadManifest;
    }

    public Load getLoadManifest() {
        return loadManifest;
    }

    public void loadOrder(Order order) {
        //other operations
        loadManifest.getOrders().add(order);
    }

    public void unloadOrder(Order order) {

    }

    public void removeOrderFromLoad(Order order) {

    }

    public void mergeOrders(Order order1, Order order2) {

    }

    @Override
    public void completeLoad(Load load) {

    }
}
