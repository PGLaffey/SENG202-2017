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
     * Overloaded constructor used for cloning a toilet
     * @param toilet The toilet to be cloned
     */
    public Toilet(Toilet toilet) {
    	super(toilet);
    	this.forDisabled = toilet.getForDisabled();
    	this.uniSex = toilet.getUniSex();
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


    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    public Marker getMarker() {
        return marker;
    }


    /**
     * Returns whether the toilet locations are equal
     * @param other the toilet to be compared
     */
    @Override
    public boolean equals(Object other) {
        Toilet otherToilet = (Toilet) other;
        return (other != null && getLocationType() == otherToilet.getLocationType() &&
                getLatitude() == otherToilet.getLatitude() &&
                getLongitude() == otherToilet.getLongitude() &&
                getUniSex() == otherToilet.getUniSex() &&
                getForDisabled() == otherToilet.getForDisabled());
    }
    
    
    /**
     * Getter for if the toilet is disabled
     * @return If the toilets are for disabled
     */
    public boolean getForDisabled() {
        return forDisabled;
    }

    
    /**
     * Setter for if a toilet is for disabled
     * @param bool If a toilet has disabled
     */
    public void setForDisabled(boolean bool) { 
    	forDisabled = bool; 
    }

    
    /**
     * Getter for whether the toilet is unisex
     * @return boolean for if the toilet is unisex
     */
    public boolean getUniSex() {
        return uniSex;
    }

    
    /**
     * Setter for if the toilet is unisex
     * @param bool If the toilet is unisex
     */
    public void setUniSex(boolean bool) { 
    	uniSex = bool; 
    }
}

