package pojos;

public class Slot {

    private Product product;
    private int location;
    private int checkDigit;

    public Slot(Product product, int location, int checkDigit) {
        this.product = product;
        this.location = location;
        this.checkDigit = checkDigit;
    }

    public Product getProduct() {
        return product;
    }

    public int getLocation() {
        return location;
    }

    public int getCheckDigit() {
        return checkDigit;
    }
}
