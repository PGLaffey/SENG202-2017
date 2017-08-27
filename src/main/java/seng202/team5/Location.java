package seng202.team5;

/**
 * Location object stores the information about a specific location (i.e. wifi hotspot, retailer, toilet or point of interest).
 */
public class Location {
    private double lattitude;
    private double longitude;
    //TODO added type to location
    private String locationType;
    private int id;
    private String name;
    private boolean local;

    /**
     * Constructor for the location class, creates a new Location.
     * @param latitude The latitude of the new location.
     * @param longitude The longitude of the new location.
     * @param name The name of the new location.
     * @param locationType The type of location the new location is (i.e. point of interest, wifi spot, retailer or toilet).
     */
    public Location(double latitude, double longitude, String name, String locationType) {
        this.lattitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.locationType = locationType;
    }

    /**
     * Function that returns the logitude and latitude of the location in an Array.
     * @return The latitude and logitude of the location in an Array.
     */
    public double[] getCoords() {
        double[] coords = {lattitude, longitude};
        return coords;
    }

    public String getName() {
        return name;
    }

    //TODO Add this to the UML diagram
    public int getId() {
        return id;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO Should this be set?
    public void storeLocal(boolean local) {
        this.local = local;
    }
}
