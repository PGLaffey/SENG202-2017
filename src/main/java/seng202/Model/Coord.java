package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

/**
 * Class to store known coordinates of addresses.
 * Created by Eiran Ling on 21/09/17.
 */
public class Coord {
    private String address;
    private double lat;
    private double lng;
    private Marker locMark;

    public Coord(String address, double lattitude, double longitude) {
        this.address = address;
        lat = lattitude;
        lng = longitude;
    }

    /**
     * Getter for the latitude
     */
    public double getLat() {
        return lat;
    }

    /**
     * Getter for the longitude
     */
    public double getLng() {
        return lng;
    }

    /**
     * Getter for the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @return whether the Coord has a marker
     */
    public Marker getMarker() {
        return locMark;
    }

    /**
     * Sets whether the coord has a marker
     * @param marker Marker to set the locMark
     */
    public void setMarker(Marker marker) {
        locMark = marker;
    }

    /**
     * Checks whether two coordinates are equal
     * @return boolean for whether the coordinates are equal
     */
    public boolean equals(Coord c2) {
        if (address.toLowerCase().contains(c2.getAddress().toLowerCase())
                || c2.getAddress().toLowerCase().contains(address.toLowerCase())
                || address.equalsIgnoreCase(c2.getAddress())) {
            return true;
        } else {
            return false;
        }
    }
}
