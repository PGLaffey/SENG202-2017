package seng202.Model;

import seng202.Model.Location;

/**
 * Point of Interest (POI) subclass of location, stores a description of the location and the cost of visiting.
 */
public class Poi extends Location {
    private String description;
    private double cost;
    private String address;

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
        super(name, 1);
        this.address = address;
        this.description = description;
        this.cost = cost;
    }

    public String getAddress() { return address; }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}
