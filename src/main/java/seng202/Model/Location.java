package seng202.Model;

/**
 * Location object stores the information about a specific location (i.e. wifi hotspot, retailer, toilet or point of interest).
 */
public class Location {
    private double latitude = -91; // Maximum range for latitude is -90 to 90, so setting it outside this range tells up it was never set
    private double longitude = -181; // Maximum range for longitude is -180 to 180, so setting it outside this range tells us it was never set.
    private int locationType; //toilet = 0, poi = 1, retailer = 2, wifi = 3, general = 4
    private String name;
    private boolean local;
    private int zip = -1; // Automatic set to -1 to prevent mix ups.
    private String address = null;
    private String borough = null;
    private boolean secret = false;
    private User belongsTo = null;

    /**
     * Constructor for the location class, creates a new Location.
     *
     * @param latitude     The latitude of the new location.
     * @param longitude    The longitude of the new location.
     * @param name         The name of the new location.
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
     *
     * @param address      The address of the location.
     * @param name         The name of the new location.
     * @param locationType The type of location the new location is (i.e. point of interest, wifi spot, retailer or toilet).
     */
    public Location(String address, String name, int locationType) {
        double[] latLong = Map.getLatLong(address);
        this.latitude = latLong[0];
        this.longitude = latLong[1];
        this.address = address;
        this.name = name;
        this.locationType = locationType;
    }

    /**
     * Overloaded constructor
     * @param latitude - Latitude of the location
     * @param longitude - Longitude of the location
     * @param address - Address of the location
     * @param name - Name of the location
     * @param locationType - Type of location, 0
     */
    public Location(double latitude, double longitude, String address, String name, int locationType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.name = name;
        this.locationType = locationType;
    }

    public Location(double latitude, double longitude, int locationType) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationType = locationType;
    }

    /**
     * Getter for the locations latitude
     */
    public double getLatitude() {
        return latitude;
    }
    
    public boolean getSecret() {
    	return secret;
    }
    
    public void setLatitude(double lat) {
    	latitude = lat;
    }

    /**
     * Getter for the locations longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(double lon) {
    	longitude = lon;
    }

    /**
     * Getter for the locations address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the locations address
     * @param address address to be set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for the location name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the location type
     */
    public int getLocationType() {
        return locationType;
    }

    /**
     * Getter for whether the location is local
     */
    public boolean getLocal() {
        return local;
    }

    /**
     * Getter for the owner of the location
     */
    public User getOwner() {
        return belongsTo;
    }

    /**
     * Getter for the zip code of the location
     */
    public int getZip() {
        return zip;
    }

    /**
     * Sets the zip code of the location
     * @param zip zip code to be set
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * Stores whether the location is local
     */
    public void storeLocal(boolean local) {
        this.local = local;
    }

    /**
     * Returns whether the route is secret
     */
    public boolean isSecret() {
        return secret;
    }

    /**
     * Sets whether the location is secret
     * @param secret boolean value for whether the route is secret
     */
    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    /**
     * Getter for the borough of the location
     */
    public String getBorough() {
        return this.borough;
    }

    /**
     * Sets the name of the borough of the location
     * @param borough name of the borough to be set
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }

    /**
     * Sets the name of the location
     * @param name string to be set as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Overrides the equals methods for comparing when two location objects are equal
     * @param other The value to be compared to the current object.
     * @return Whether the two objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        Location otherLocation = (Location) other;
        return other != null && getLatitude() == otherLocation.getLatitude() &&
                getLongitude() == otherLocation.getLongitude() &&
                getLocationType() == otherLocation.getLocationType() &&
                getName().equals(otherLocation.getName());
    }

    /**
     * Function that returns the longitude and latitude of the location in an Array.
     * @return The latitude and longitude of the location in an Array.
     */
    public double[] getCoords() {
        double[] coords = {latitude, longitude};
        return coords;
    }

    /**
     * Getter for the type of location, returns this as a string
     */
    public String getTypeString() {
        if (locationType == 0) {
            return ("Toilet");
        } else if (locationType == 1) {
            return ("Point of interest");
        } else if (locationType == 2) {
            return ("Retailer");
        } else if (locationType == 3) {
            return ("Wifi");
        } else {
            return ("Other");
        }
    }
}
