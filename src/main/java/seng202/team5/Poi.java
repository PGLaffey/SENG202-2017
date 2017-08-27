package seng202.team5;

/**
 * Point of Interest (POI) subclass of location, stores a description of the location and the cost of visiting.
 */
public class Poi extends Location {
    private String description;
    private double cost;

    /**
     * The POI constructor, creates a new instance of the point of interest object using the location superclass constructor.
     * @param lattitude The lattitude of the point of interest.
     * @param longitude The longitude of the point of interest.
     * @param name The name of the point of interest.
     * @param description A description of the point of interest.
     * @param cost The cost of visiting the point of interest.
     */
    public Poi(double lattitude, double longitude, String name, String description, double cost) {
        super(lattitude, longitude, name, "Poi");
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }
}
