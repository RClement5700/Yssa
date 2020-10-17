package pojos;

public class Product {
    int productId;
    int length;
    int width;
    int height;
    double weight;
    String description;

    public Product(int productId, int length, int width, int height, double weight,
                   String description) {
        this.productId = productId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }
}
