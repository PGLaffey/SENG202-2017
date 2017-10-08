
package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Retailer subclass of Location, contains the type of product sold, and a description of the retailer.
 */
public class Retailer extends Location {
    //TODO Changed "type" to "product" as this avoids confusion with the "type" of location
    private String product;
    private String description;

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
     * Overloaded Constructor used for cloning retailers
     * @param retailer The retailer to clone
     */
    public Retailer(Retailer retailer) {
        super(retailer);
        this.product = retailer.getProduct();
        this.description = retailer.getDescription();
        this.marker = retailer.getMarker();
        this.noMarker = retailer.hasNoMarker();
        this.coord = retailer.getCoord();
    }


    /**
     * Getter for the retailer product
     * @return The product of the retailer
     */
    public String getProduct() {
        return product;
    }

    /**
     * Getter for the description of the retailer
     * @return The description of the retailer
     */
    public String getDescription() { 
        return description;
    }

    /**
     * Getter for the marker of the retailer
     * @return The marker for the retailer
     */
    public Marker getMarker() { 
    	return marker; 
    }

    /**
     * Sets the marker for the current retailer
     * @param marker marker object to be set for the retailer
     */
    public void setMarker(Marker marker) {
        this.marker = marker;
    }


    /**
     * A .equals method for retailer.
     * @param other - The object to compare to.
     * @return a boolean value based on whether the contents are similar.
     */
    @Override
    public boolean equals(Object other) {
        Retailer otherRetailer = (Retailer) other;
        return (otherRetailer != null && getName().equals(otherRetailer.getName()) &&
                getLatitude() == otherRetailer.getLatitude() &&
                getLongitude() == otherRetailer.getLongitude() &&
                getLocationType() == otherRetailer.getLocationType());
    }

    /**
     * Finds whether the retailer already has a marker set
     * @return True of the retailer already has a marker
     */
    public boolean hasNoMarker() {
        return noMarker;
    }

    /**
     * Sets the value of noMarker to true if there is no marker associated with the retailer
     * @param bool Set if the retailer has no marker
     */
    public void setNoMarker(boolean bool) {
        noMarker = bool;
    }

    /**
     * Getter for the coordinates of the retailer
     * @return The coords of the retailer as Coord type
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

    /**
     * Sets the product of the retailer
     * @param prod The new product for the retailer
     */
    public void setProduct(String prod) {
    	product = prod;
    }

    /**
     * Sets the description of the retailer
     * @param desc The new description of the retailer
     */
    public void setDescription(String desc) {
    	description = desc;
    }
}
