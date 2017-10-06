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
     * Creates a new instance of the Wifi class, uses the constructor of the Location superclass.
     * @param latitude The latitude of the wifi hotspot.
     * @param longitude The longitude of the wifi hotspot.
     * @param name The name of the wifi hotspot location.
     * @param ssid The SSID of the wifi location.
     * @param type Distinguishes between free and limited_free wifi
     * @param provider The name of the provider of the wifi hotspot.
     */
    public Wifi(double latitude, double longitude, String name, String ssid, String type, String provider) {
        super(latitude, longitude, name, 3);
        this.provider = provider;
        this.ssid = ssid;
        this.type = type;
    }
    
    
    /**
     * Overloaded constructor used for cloning a wifi location
     * @param wifi The wifi location to clone
     */
    public Wifi(Wifi wifi) {
    	super(wifi);
    	this.provider = wifi.getProvider();
    	this.type = wifi.getType();
    	this.circle = wifi.getCircle();
    	this.ssid = wifi.getSsid();
    }
    
    
    /**
     * Overloaded constructor 
     * @param address The address of the wifi location.
     * @param name The name of the wifi hotspot location.
     * @param ssid The SSID of the wifi location.
     * @param type Distinguishes between free and limited_free wifi
     * @param provider The name of the provider of the wifi hotspot.
     */
    public Wifi(String address, String name, String ssid, String type, String provider) {
    	super(address, name, 3);
    	this.provider = provider;
    	this.ssid = ssid;
    	this.type = type;
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
    
    
    //TODO
    //Change to a proper override of the equals method like in Location
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
     * Getter for the SSID
     * @return The SSID of the wifi location
     */
    public String getSsid() {
        return this.ssid;
    }

    
    /**
     * Setter for the SSID
     * @param ss The new SSID for the wifi location
     */
    public void setSsid(String ss) { 
    	ssid = ss; 
    }

    
    /**
     * Getter for the wifi provider
     * @return The provider of the wifi location
     */
    public String getProvider() {
        return provider;
    }

    
    /**
     * Setter for the provider of the wifi location
     * @param prov The new provider for the wifi
     */
    public void setProvider(String prov) { 
    	provider = prov; 
    }

    
    /**
     * Getter for the marker of the wifi point
     * @return The circle for the wifi location
     */
    public Circle getCircle() { 
    	return circle; 
    }

    
    /**
     * Setter the circle for the wifi
     * @param circle The new circle for the wifi
     */
    public void setCircle(Circle circle) { 
    	this.circle = circle; 
    }

    
    /**
     * Getter for the wifi type
     * @return The type of the wifi
     */
    public String getType() {
        return type;
    }

    
    /**
     * Setter for the type of the wifi
     * @param ty The new type for the wifi
     */
    public void setType(String ty) { 
    	type = ty; 
    }
}
