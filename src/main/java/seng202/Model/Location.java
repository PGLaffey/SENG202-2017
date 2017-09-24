package seng202.Model;

/**
 * Location object stores the information about a specific location (i.e. wifi hotspot, retailer, toilet or point of interest).
 */
public class Location {
    private double latitude = -91; // Maximum range for latitude is -90 to 90, so setting it outside this range tells up it was never set
    private double longitude = -181; // Maximum range for longitude is -180 to 180, so setting it outside this range tells us it was never set.
    //TODO added type to location
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getLocationType() {
        return locationType;
    }

    public boolean getLocal() {
        return local;
    }

    public User getOwner() {
        return belongsTo;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public void storeLocal(boolean local) {
        this.local = local;
    }

    public boolean isSecret() {
        return secret;
    }

    public void setSecret(boolean secret) {
        this.secret = secret;
    }

    public String getBorough() {
        return this.borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location otherLocation = (Location) other;
        if (otherLocation.getCoords() != this.getCoords()) {
            return false;
        }
        if (otherLocation.getLocationType() != this.getLocationType()) {
            return false;
        }
        return true;
    }

    /**
     * Function that returns the longitude and latitude of the location in an Array.
     *
     * @return The latitude and longitude of the location in an Array.
     */
    public double[] getCoords() {
        double[] coords = {latitude, longitude};
        return coords;
    }

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
