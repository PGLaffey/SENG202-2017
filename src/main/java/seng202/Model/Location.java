package seng202.Model;

/**
 * Location object stores the information about a specific location (i.e. wifi hotspot, retailer, toilet or point of interest).
 */
public class Location {
    private double latitude;
    private double longitude;
    //TODO added type to location
    private int locationType; //toilet = 0, poi = 1, retailer = 2, wifi = 3, general = 4
    private String name;
    private boolean local;
    private User belongsTo;

    /**
     * Constructor for the location class, creates a new Location.
     * @param latitude The latitude of the new location.
     * @param longitude The longitude of the new location.
     * @param name The name of the new location.
     * @param locationType The type of location the new location is (i.e. point of interest, wifi spot, retailer or toilet).
     */
    public Location(double latitude, double longitude, String name, int locationType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.locationType = locationType;
    }

    /**
     * Overloaded constructor to take an address and obtain a longitude and lattitude for the address.
     * @param address The address of the location.
     * @param name The name of the new location.
     * @param locationType The type of location the new location is (i.e. point of interest, wifi spot, retailer or toilet).
     */
    public Location(String address, String name, int locationType) {
        this.latitude = Map.getLattitude(address);
        this.longitude = Map.getLongitude(address);
        this.name = name;
        this.locationType = locationType;
    }

    /**
     * Function that returns the logitude and latitude of the location in an Array.
     * @return The latitude and logitude of the location in an Array.
     */
    public double[] getCoords() {
        double[] coords = {latitude, longitude};
        return coords;
    }

    public String getName() {
        return name;
    }

    public boolean getLocal() {
    	return local;
    }
    
    public User getOwner() {
    	return belongsTo;
    }
    
    public int getLocationType() {
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
