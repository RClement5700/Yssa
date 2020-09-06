package pojos;

public class Slot {

    Product product;
    int location;
    int checkDigit;

    public Slot(Product product, int location, int checkDigit) {
        this.product = product;
        this.location = location;
        this.checkDigit = checkDigit;
    }
}
