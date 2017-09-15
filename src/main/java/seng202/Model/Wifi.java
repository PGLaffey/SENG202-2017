package seng202.Model;

import seng202.Model.Location;

/**
 * Wifi subclass of Location, contains the information of the wifi provider.
 */
public class Wifi extends Location {
    private String provider;
    private String borough;
    private String type;

    /**
     * Creates a new instance of the wifi class, uses the constructor of the Location superclass.
     *
     * @param latitude  The latitude of the wifi hotspot.
     * @param longitude The longitude of the wifi hotspot.
     * @param name      The name of the wifi hotspot location.
     * @param borough   The borough in which the wifi is located.
     * @param type      Distinguishes between free and limited_free wifi
     * @param provider  The name of the provider of the wifi hotspot.
     */
    public Wifi(double latitude, double longitude, String name, String borough, String type, String provider) {
        super(latitude, longitude, name, 3);
        this.provider = provider;
        this.borough = borough;
        this.type = type;
    }

    public String getProvider() {
        return provider;
    }

    public String getBorough() {
        return borough;
    }

    ;

    public String getType() {
        return type;
    }

    public boolean equals(Wifi other) {

        if (this.getName() != other.getName()) {
            return false;
        }
        if (getLatitude() != other.getLatitude()) {
            return false;
        }
        if (getLongitude() != other.getLongitude()) {
            return false;
        }
        if (getProvider() != other.getProvider()) {
            return false;
        }
        return true;
    }
}
