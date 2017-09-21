package seng202.Model;

/**
 * Class to store known coordinates of addresses.
 * Created by Eiran Ling on 21/09/17.
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

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public String getAddress() {
        return address;
    }

    public boolean hasMarker() {
        return hasMarker;
    }

    public void setHasMarker(boolean value) {
        hasMarker = value;
    }

    public boolean equals(Coord c2) {
        if (address.contains(c2.getAddress()) || c2.getAddress().contains(address) || address.equalsIgnoreCase(c2.getAddress())) {
            return true;
        } else {
            return false;
        }
    }
}
