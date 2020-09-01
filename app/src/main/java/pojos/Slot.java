package pojos;

public class Slot {

    String productDescription;
    int positionId;
    int checkDigit;

    public Slot(String productDescription, int positionId, int checkDigit) {
        this.productDescription = productDescription;
        this.positionId = positionId;
        this.checkDigit = checkDigit;
    }
}
