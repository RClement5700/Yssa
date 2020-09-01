package pojos;

public class Slot {

    Product product;
    int positionId;
    int checkDigit;

    public Slot(Product product, int positionId, int checkDigit) {
        this.product = product;
        this.positionId = positionId;
        this.checkDigit = checkDigit;
    }
}
