package pojos;

public class Product {
    double weight;
    int length;
    int width;
    int height;
    String description;

    public Product(double weight, int length, int width, int height, String description) {
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
        this.description = description;
    }
}
