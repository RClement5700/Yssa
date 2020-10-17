package pojos;

public class Slot {

    private Product product;
    private int slotId;
    private int checkDigit;

    public Slot(Product product, int slotId, int checkDigit) {
        this.product = product;
        this.slotId = slotId;
        this.checkDigit = checkDigit;
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
