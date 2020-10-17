package pojos;

public class Slot {

    private int slotId;
    private int checkDigit;
    private Product product;
    Aisle aisle;
    Department department;

    public Slot(int slotId, int checkDigit, Product product) {
        this.slotId = slotId;
        this.checkDigit = checkDigit;
        this.product = product;
    }

    public Slot(int slotId, int checkDigit, Product product, Aisle aisle, Department department) {
        this.slotId = slotId;
        this.checkDigit = checkDigit;
        this.product = product;
        this.aisle = aisle;
        this.department = department;
    }

    public Product getProduct() {
        return product;
    }

    public int getslotId() {
        return slotId;
    }

    public int getCheckDigit() {
        return checkDigit;
    }
}
