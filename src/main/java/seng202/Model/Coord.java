package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Class to store known coordinates of addresses.
 */
public class Coord {
    private String address;
    private double lat;
    private double lng;
    private Marker locMark;

    
    /**
     * Constructor for Coord
     * @param address The address of the coord
     * @param latitude The latitude of the coord 
     * @param longitude The longitude of the coord
     */
    public Coord(String address, double latitude, double longitude) {
        this.address = address;
        lat = latitude;
        lng = longitude;
    }

    
    //TODO make proper override of equals
    /**
     * Checks whether two coordinates are equal
     * @return boolean for whether the coordinates are equal
     */
    public boolean equals(Object c1) {
        Coord c2 = (Coord) c1;
        return (c2 != null && address.toLowerCase().contains(c2.getAddress().toLowerCase())
                && c2.getAddress().toLowerCase().contains(address.toLowerCase())
                && address.equalsIgnoreCase(c2.getAddress()));
    }
    
    
    /**
     * Getter for the latitude
     * @return The latitude of the coord
     */
    public double getLat() {
        return lat;
    }

    
    /**
     * Getter for the longitude
     * @return The longitude of the coord
     */
    public double getLng() {
        return lng;
    }

    
    /**
     * Getter for the address
     * @return The address of the coord
     */
    public String getAddress() {
        return address;
    }

    
    /**
     * Getter for the marker
     * @return The marker of the coord
     */
    public Marker getMarker() {
        return locMark;
    }

    
    /**
     * Sets the marker for the coord
     * @param marker The new marker for the coord
     */
    public void setMarker(Marker marker) {
        locMark = marker;
    }
}
