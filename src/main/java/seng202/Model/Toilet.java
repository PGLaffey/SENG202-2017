package seng202.Model;

import seng202.Model.Location;

/**
 * Toilet subclass of Location, stores whether the toilet has facilities for wheelchair users, and whether the toilet is unisex or not.
 */
public class Toilet extends Location {
    private boolean forDisabled;
    private boolean uniSex;

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
    
    public Toilet(String address, String name, boolean forDisabled, boolean uniSex) {
    	super(address, name, 0);
    	this.forDisabled = forDisabled;
    	this.uniSex = uniSex;
    }

    public boolean getForDisabled() {
        return forDisabled;
    }

    public boolean getUniSex() {
        return uniSex;
    }

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

