package seng202.Model;

import com.lynden.gmapsfx.shapes.Circle;

/**
 * Wifi subclass of Location, contains the information of the wifi provider.
 */
public class Wifi extends Location {
    private String provider;
    private String type;
    private Circle circle = null;
    private String ssid;

    /**
     * Creates a new instance of the wifi class, uses the constructor of the Location superclass.
     *
     * @param latitude  The latitude of the wifi hotspot.
     * @param longitude The longitude of the wifi hotspot.
     * @param name      The name of the wifi hotspot location.
     * @param type      Distinguishes between free and limited_free wifi
     * @param provider  The name of the provider of the wifi hotspot.
     */
    public Wifi(double latitude, double longitude, String name, String ssid, String type, String provider) {
        super(latitude, longitude, name, 3);
        this.provider = provider;
        this.ssid = ssid;
        this.type = type;
    }
    
    public Wifi(String address, String name, String ssid, String type, String provider) {
    	super(address, name, 3);
    	this.provider = provider;
    	this.ssid = ssid;
    	this.type = type;
    }

    /**
     * Getter for the SSID
     */
    public String getSsid() {
        return this.ssid;
    }

    /**
     * Getter for the wifi provider
     */
    public String getProvider() {
        return provider;
    }

    /**
     * Getter for the marker of the wifi point
     */
    public Circle getCircle() { return circle; }

    /**
     * Sets the marker for the wifi
     */
    public void setCircle(Circle circle) { this.circle = circle; }

    /**
     * Getter for the wifi type
     */
    public String getType() {
        return type;
    }

    /**
     *  @return True if wifi objects are equal
     */
    public boolean equals(Wifi other) {

        if (!getName().equals(other.getName())) {
            return false;
        }

        if (getLatitude() != other.getLatitude() && getLongitude() != other.getLongitude()) {
            return false;
        }

        if (!getProvider().equals(other.getProvider())) {
            return false;
        }

        if (!getType().equals(other.getType())) {
            return false;
        }
        return true;
    }

    /**
     * Overrides the toString method for a wifi object
     * @return A string representation of a wifi object
     */
    @Override
    public String toString() {
        return "SSID: " + getName() + "\nLocation: (" + getLatitude() + ", " + getLongitude() + ")\nProvider: "
                + getProvider() + "\nType: " + getType() + "\nBorough: " + getBorough();
    }

}
