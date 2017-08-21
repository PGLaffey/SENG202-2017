package seng202.team5;


public class Retailer extends Location {
    //TODO Changed "type" to "product" as this avoids confusion with the "type" of location
    private String product;
    private String description;

    public Retailer(double lattitude, double longitude, String name, String product, String description) {
        super(lattitude, longitude, name, "Retailer");
        this.product = product;
        this.description = description;
    }

    public String getProduct() {
        return this.product;
    }

    public String getDescription() {
        return this.description;
    }
}
