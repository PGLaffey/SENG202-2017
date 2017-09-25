package seng202.Model;

import com.lynden.gmapsfx.javascript.object.Marker;

import java.util.Arrays;

/**
 * The Route class, stores information about the route.
 */
public class Route {
    private String name;
    private String bikeID;
    private String gender;
    private double distance;
    private boolean local;
    private Location start;
    private Location end;
    private Location via1;
    private Location via2;
    private Location via3;
    private boolean secret;
    private Marker startMarker = null;
    private Marker endMarker = null;

    /**
     * Constructs a route based on the start and end locations.
     * @param bikeID The ID of the bike used to make this route.
     * @param start The start location of the route.
     * @param end The end location of the route
     */
    public Route(String bikeID, Location start, Location end) {
        this.bikeID = bikeID;
        this.start = start;
        this.end = end;
        this.distance = Map.getDistance(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
        via1 = null;
        via2 = null;
        via3 = null;
    }

    /**
     * Overloaded constructor for the route class, gives the route a name.
     * @param bikeID The id of the bike used to make the route.
     * @param start The start location of the route.
     * @param end The end location of the route.
     * @param name The name of the route.
     * @param gender The gender of the cyclist that cycled this route.
     */
    public Route(String bikeID, Location start, Location end, String name, String gender) {
        this.bikeID = bikeID;
        this.start = start;
        this.end = end;
        this.name = name;
        this.gender = gender;
        this.distance = Map.getDistance(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
        via1 = null;
        via2 = null;
        via3 = null;
    }

    public Route(Location start, Location end, String name) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.distance = Map.getDistance(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());
        via1 = null;
        via2 = null;
        via3 = null;
    }

    /**
     * Getter for the route name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the bike id
     */
    public String getBikeID() { return bikeID; }

    /**
     * Getter for the route gender
     */
    public String getGender() { return gender; }

    public String getGenderType() {
        if (gender.equals("1")) {
            return "Male";
        } else if (gender.equals("2")) {
            return "Female";
        } else {
            return "Unknown";
        }
    }

    /**
     * Getter for the route distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * Getter for the route distance rounded
     */
    public String getDistanceRound() {
        return String.format("%.2f", distance);
    }

    /**
     * Getter for whether the route is local
    */
    public boolean getLocal() {
        return local;
    }

    /**
     * Getter for the start of the route
     */
    public Location getStart() {
        return start;
    }

    /**
     * Getter for the a location array containing all of the locations the
     * route goes via
     */
    public Location[] getVia() {
    	Location[] vias = {via1, via2, via3};
    	return vias;
    }

    /**
     * Getter for the end of the route
     */
    public Location getEnd() {
        return end;
    }

    /**
     * Getter for the name of the start of the route
     */
    public String getStartString() {
    	return start.getName();
    }

    /**
     * Getter for the name of the end of the route
     */
    public String getEndString() {
    	return end.getName();
    }

    /**
     * Determines whether the Route should be saved locally or just on a server.
     * @param local
     */
    public void storeLocation(boolean local) {
        this.local = local;
    }

    /**
     * A .equals method for route.
     * @param other - The object to compare to.
     * @return a boolean value based on whether the contents are similar.
     */
    public boolean equals(Route other) {
        if (getStart().getLatitude() != other.getStart().getLatitude() |
                getStart().getLongitude() != other.getStart().getLongitude() |
                getEnd().getLatitude() != other.getEnd().getLatitude() |
                getEnd().getLongitude() != other.getEnd().getLongitude()) {
            return false;
        }

        if (!Arrays.equals(getVia(), other.getVia())) {
            return false;
        }
        return true;
    }

    /**
     * Returns whether the route is private
     */
	public boolean isSecret() {
		return secret;
	}

    /**
     * Sets the route private
     */
	public void setSecret(boolean secret) {
		this.secret = secret;
	}

    /**
     * Getter for the start marker for the route
     */
	public Marker getStartMarker() {
        return startMarker;
    }

    /**
     * Setter for the start marker for the route
     */
    public void setStartMarker(Marker marker) {
        startMarker = marker;
    }

    /**
     * Getter for the end marker for the route
     */
    public Marker getEndMarker() {
        return endMarker;
    }

    /**
     * Setter for the end marker for the route
     */
    public void setEndMarker(Marker marker) {
        endMarker = marker;
    }
}
