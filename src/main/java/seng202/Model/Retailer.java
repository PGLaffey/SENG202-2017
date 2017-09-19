package seng202.Model;

import seng202.Model.Location;

/**
 * Retailer subclass of Location, contains the type of product sold, and a description of the retailer.
 */
public class Retailer extends Location {
    //TODO Changed "type" to "product" as this avoids confusion with the "type" of location
    private String product;
    private String description;
    private String address;
    private int zip;

	/**
	 * Constructor for the retailer subclass, uses the location superclass constructor.
     * @param latitude The latitude of the new retailer.
     * @param longitude The longitude of the new retailer.
     * @param name The name of the retailer.
     * @param product The product the retailer sells.
     * @param description A description of the retailer at the location.
     * @param zip The zip code in which the retailer resides
	 */
    public Retailer(double latitude, double longitude, String name, String product, String description, int zip) {
    	super(latitude, longitude, name, 2);
    	this.product = product;
    	this.description = description;
    	this.zip = zip;
    }
    
    /**
     * Constructor for the retailer subclass, uses the location superclass constructor.
     * @param address The address of the retailer
     * @param name The name of the retailer.
     * @param product The product the retailer sells.
     * @param description A description of the retailer at the location.
     * @param zip The zip code in which the retailer resides
     *
     */
    public Retailer(String address, String name, String product, String description, int zip) {
        super(name, 2);
        this.address = address;
        this.product = product;
        this.description = description;
        this.zip = zip;
    }

    public String getAddress() { return address; }

    public String getProduct() {
        return product;
    }

    public String getDescription() { 
        return description;
    }

    public int getZip() { return zip; }

    /**
     * A .equals method for retailer.
     * @param other - The object to compare to.
     * @return a boolean value based on whether the contents are similar.
     */
    public boolean equals(Retailer other) {
        if (!getName().equals(other.getName())) {
            return false;
        }
        if (getLatitude() != other.getLatitude() &&
                getLongitude() != other.getLongitude()) {
            return false;
        }
        if (!getProduct().equals(other.getProduct())) {
            return false;
        }
        if (!getDescription().equals(other.getDescription())) {
            return false;
        }
        return true;
    }
}
