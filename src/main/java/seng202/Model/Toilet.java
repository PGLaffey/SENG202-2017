package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;
import seng202.Model.Location;
import seng202.team5.Main;

/**
 * Toilet subclass of Location, stores whether the toilet has facilities for wheelchair users, and whether the toilet is unisex or not.
 */
public class Toilet extends Location {
    private boolean forDisabled;
    private boolean uniSex;
    private Marker marker = null;

    /**
     * Toilet constructor, creates a new instance of the toilet object using the location superclass constructor.
     * @param latitude The latitude of the toilet location.
     * @param longitude The longitude of the toilet location.
     * @param name The name of the toilet location.
     * @param forDisabled Whether the toilet has facilities for wheelchair users.
     * @param uniSex Whether the toilet is unisex or has separate facilities for men and women.
     */
    public Toilet(double latitude, double longitude, String name, boolean forDisabled, boolean uniSex) {
        super(latitude, longitude, name, 0);
        this.forDisabled = forDisabled;
        this.uniSex = uniSex;
    }

    /**
     * Toilet contructor, creates a new instance of the toilet object using the location superclass constructor.
     * @param address The address of the toilet location.
     * @param name The name of the toilet location.
     * @param forDisabled Whether the toilet has facilities for wheelchair users.
     * @param uniSex Wheather the toilet is unisex or has sparate facilities for men and women.
     */
    public Toilet(String address, String name, boolean forDisabled, boolean uniSex) {
    	super(address, name, 0);
    	this.forDisabled = forDisabled;
    	this.uniSex = uniSex;
    }

    /**
     * Getter for if the toilet is disabled friendly
     */
    public boolean getForDisabled() {
        return forDisabled;
    }

    public void setForDisabled(boolean bool) { forDisabled = bool; }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }

    /**
     * Getter for whether the toilet is unisex
     * @return boolean for if the toilet is unisex
     */
    public boolean getUniSex() {
        return uniSex;
    }

    public void setUniSex(boolean bool) { uniSex = bool; }

    /**
     * Returns whether the toilet locations are equal
     * @param other the toilet to be compared
     */
    public boolean equals(Toilet other) {
        if (!getName().equals(other.getName())) {
            return false;
        }

        if (getLatitude() != other.getLatitude() &&
                getLongitude() != other.getLongitude()) {
            return false;
        }

        if (getForDisabled() != other.forDisabled || getUniSex() != other.getUniSex()) {
            return false;
        }
        return true;
    }


}

