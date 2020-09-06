package pojos;

public class Product {
    int productNumber;
    int length;
    int width;
    int height;
    double weight;
    String description;

    public Product(int productNumber, int length, int width, int height, double weight,
                   String description) {
        this.productNumber = productNumber;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }
}
