package pojos;

public class Slot {

    Product product;
    String productDescription;
    int positionId;
    int checkDigit;

    public Slot(Product product, String productDescription, int positionId, int checkDigit) {
        this.product = product;
        this.productDescription = productDescription;
        this.positionId = positionId;
        this.checkDigit = checkDigit;
    }
}
