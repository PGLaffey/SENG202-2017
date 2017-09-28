package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Retailer subclass of Location, contains the type of product sold, and a description of the retailer.
 */
public class Retailer extends Location {
    //TODO Changed "type" to "product" as this avoids confusion with the "type" of location
    private String product;
    private String description;

    private Coord coord = null;

	/**
	 * Constructor for the retailer subclass, uses the location superclass constructor.
     * @param latitude The latitude of the new retailer.
     * @param longitude The longitude of the new retailer.
     * @param name The name of the retailer.
     * @param product The product the retailer sells.
     * @param description A description of the retailer at the location.
	 */
    public Retailer(double latitude, double longitude, String name, String product, String description) {
    	super(latitude, longitude, name, 2);
    	this.product = product;
    	this.description = description;
    }
    
    /**
     * Constructor for the retailer subclass, uses the location superclass constructor.
     * @param address The address of the retailer
     * @param name The name of the retailer.
     * @param product The product the retailer sells.
     * @param description A general description of what the retailer sells.
     */
    public Retailer(String address, String name, String product, String description) {
        super(address, name, 2);
        this.product = product;
        this.description = description;
    }

    /**
     * Specific overloaded constructor that is provided all the information.
     * @param latitude - Latitude of the retailer.
     * @param longitude - Longitude of the retailer.
     * @param address - The address of the retailer.
     * @param name - The name of the retailer.
     * @param product - The product that the retailer sells.
     * @param description - A general description of what the retailer sells.
     */
    public Retailer(double latitude, double longitude, String address, String name, String product, String description) {
        super(latitude, longitude, address, name, 2);
        this.product = product;
        this.description = description;
    }

    /**
     * Getter for the retailer product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Getter for the description of the retailer
     */
    public String getDescription() { 
        return description;
    }

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

    /**
     * Getter for the coordinates of the retailer
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Sets the coordinates of the retailer
     * @param coord Coord object which contains the coordinates of the retailers
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
