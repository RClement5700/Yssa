package pojos;

public class Product {
    private int productId;
    private int length;
    private int width;
    private int height;
    private int weight;
    private String description;

    public Product(int productId, int length, int width, int height, int weight,
                   String description) {
        this.productId = productId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }
    public Product(int productId, String description) {
        this.productId = productId;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
