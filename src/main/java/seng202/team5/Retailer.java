package seng202.team5;

/**
 * Retailer subclass of Location, contains the type of product sold, and a description of the retailer.
 */
public class Retailer extends Location {
    //TODO Changed "type" to "product" as this avoids confusion with the "type" of location
    private String product;
    private String description;
    private String address;

    /**
     * Constructor for the retailer subclass, uses the location superclass constructor.
     * @param address The address of the retailer
     * @param name The name of the retailer.
     * @param product The product the retailer sells.
     * @param description A description of the retailer at the location.
     */
    public Retailer(String address, String name, String product, String description) {
        super(address, name, "Retailer");
        this.address = address;
        this.product = product;
        this.description = description;
    }

    public String getAddress() { return address; }

    public String getProduct() {
        return product;
    }

    public String getDescription() {
        return description;
    }
}
