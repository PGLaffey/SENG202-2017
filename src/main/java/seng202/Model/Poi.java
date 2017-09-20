package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Point of Interest (POI) subclass of location, stores a description of the location and the cost of visiting.
 */
public class Poi extends Location {
    private String description;
    private double cost;
    private Marker marker = null;
    private String borough = null; // TODO Remove this and the getter and setter when the function is rewritten.

    /**
     * The POI constructor, creates a new instance of the point of interest object using the location superclass constructor.
     * @param latitude The latitude of the point of interest.
     * @param longitude The longitude of the point of interest.
     * @param name The name of the point of interest.
     * @param description A description of the point of interest.
     * @param cost The cost of visiting the point of interest.
     */
    public Poi(double latitude, double longitude, String name, String description, double cost) {
        super(latitude, longitude, name, 1, 0);
        this.description = description;
        this.cost = cost;
    }

    public Poi(String address, String name, String description, double cost) {
        super(address, name, 1, 0);
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public String getBorough() {
        return this.borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public Marker getMarker() { return marker; }

    public void setMarker(Marker marker) { this.marker = marker;}
}
