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

    public Poi(String address, String name, String description, double cost) {
        super(address, name, 1);
        this.description = description;
        this.cost = cost;
    }

    /**
     * Getter for the description of the POI
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) { description = desc; }

    /**
     * Getter for the cost of the POI
     */
    public double getCost() {
        return cost;
    }

    public void setCost(Double c) { cost = c; }

    /**
     * Getter for the marker for the POI
     */
    public Marker getMarker() { 
    	return marker; 
    }

    /**
     * Sets the marker for the POI
     */
    public void setMarker(Marker marker) { 
    	this.marker = marker;
    }

    @Override
    public boolean equals(Object poi) {
        Poi otherPoi = (Poi) poi;
        if (this.getLatitude() == otherPoi.getLatitude() && this.getLongitude() == otherPoi.getLongitude() &&
                this.getName().equals(otherPoi.getName()) && this.getDescription().equals(otherPoi.getDescription())) {
            return true;
        }
        return false;
    }
}
