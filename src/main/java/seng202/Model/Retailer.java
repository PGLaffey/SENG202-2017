package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Retailer subclass of Location, contains the type of product sold, and a description of the retailer.
 */
public class Retailer extends Location {
    //TODO Changed "type" to "product" as this avoids confusion with the "type" of location
    private String product;
    private String description;
    private String borough;

    private Marker marker = null;
    private boolean noMarker = false;
    Coord coord = null;

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
    	super(latitude, longitude, name, 2, zip);
    	this.product = product;
    	this.description = description;
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
        super(address, name, 2, zip);
        this.product = product;
        this.description = description;
    }

    public String getProduct() {
        return product;
    }

    public String getDescription() { 
        return description;
    }

    public Marker getMarker() { return marker; }

    public void setMarker(Marker marker) {
        this.marker = marker;
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

    public String getBorough() {
        return this.borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public boolean hasNoMarker() {
        return noMarker;
    }

    public void setNoMarker(boolean bool) {
        noMarker = bool;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }
}
