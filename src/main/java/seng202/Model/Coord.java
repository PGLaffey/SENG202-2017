package seng202.Model;

/**
 * Class to store known coordinates of addresses.
 */
public class Coord {
    private String address;
    private double lat;
    private double lng;
    private boolean hasMarker = false;

    
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
    public boolean hasMarker() {
        return hasMarker;
    }

    
    /**
     * Sets whether the coord has a marker
     * @param value boolean value for whether the marker has been set
     */
    public void setHasMarker(boolean value) {
        hasMarker = value;
    }

    
    /**
     * Checks whether two coordinates are equal
     * @return boolean for whether the coordinates are equal
     */
    public boolean equals(Coord c2) {
        if (address.contains(c2.getAddress()) || c2.getAddress().contains(address) || address.equalsIgnoreCase(c2.getAddress())) {
            return true;
        } else {
            return false;
        }
    }
}
