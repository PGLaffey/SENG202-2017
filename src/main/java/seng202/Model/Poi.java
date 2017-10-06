package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Point of Interest (POI) subclass of location, stores a description of the location and the cost of visiting.
 */
public class Poi extends Location {
    private String description;
    private double cost;
    private Marker marker = null;

    
    /**
     * The POI constructor, creates a new instance of the point of interest object using the location superclass constructor.
     * @param latitude The latitude of the point of interest.
     * @param longitude The longitude of the point of interest.
     * @param name The name of the point of interest.
     * @param description A description of the point of interest.
     * @param cost The cost of visiting the point of interest.
     */
    public Poi(double latitude, double longitude, String name, String description, double cost) {
        super(latitude, longitude, name, 1);
        this.description = description;
        this.cost = cost;
    }

    
    /**
     * Overloaded constructor
     * @param address The address of the point of interest.
     * @param name The name of the point of interest.
     * @param description A description of the point of interest.
     * @param cost The cost of visiting the point of interest.
     */
    public Poi(String address, String name, String description, double cost) {
        super(address, name, 1);
        this.description = description;
        this.cost = cost;
    }

    
    /**
     * Overloaded constructor used to clone a point of interest
     * @param poi The point of interest to clone
     */
    public Poi(Poi poi) {
    	super(poi);
    	this.description = poi.getDescription();
    	this.cost = poi.getCost();
    	this.marker = poi.getMarker();
    }
    
    
    /**
     * Overrides the equals methods for comparing when two Poi objects are equal
     * @param other The value to be compared to the current object.
     * @return Whether the two objects are equal.
     */
    @Override
    public boolean equals(Object poi) {
        Poi otherPoi = (Poi) poi;
        if (this.getLatitude() == otherPoi.getLatitude() && this.getLongitude() == otherPoi.getLongitude() &&
                this.getName().equals(otherPoi.getName()) && this.getDescription().equals(otherPoi.getDescription())) {
            return true;
        }
        return false;
    }
    
    
    /**
     * Getter for the description of the POI
     * @return The description of the POI
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * Setter for the description of the POI
     * @param desc The new description of the POI
     */
    public void setDescription(String desc) { 
    	description = desc; 
    }

    
    /**
     * Getter for the cost of the POI
     * @return The cost of the POI
     */
    public double getCost() {
        return cost;
    }

    
    /**
     * Setter for the cost of the POI
     * @param c The new cost of the POI
     */
    public void setCost(Double c) { 
    	cost = c; 
    }
    

    /**
     * Getter for the marker for the POI
     * @return The marker of the POI
     */
    public Marker getMarker() { 
    	return marker; 
    }

    
    /**
     * Setter for the marker for the POI
     * @param marker The new marker for the POI
     */
    public void setMarker(Marker marker) { 
    	this.marker = marker;
    }
}
