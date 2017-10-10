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
    private boolean secret = false; // true if private, false if public
    private Marker startMarker = null;
    private Marker endMarker = null;
    private User owner = null;

    
    /**
     * Constructs a route based on the start and end locations.
     * @param bikeID - The ID of the bike used to make this route
     * @param start  - The start location of the route
     * @param end    - The end location of the route
     */
    public Route(String bikeID, Location start, Location end) {
        this.bikeID = bikeID;
        this.start = start;
        this.end = end;
        this.distance = Map.getDistance(start.getLatitude(), start.getLongitude(), 
        		end.getLatitude(), end.getLongitude());
    }

    
    /**
     * Overloaded constructor for the route class, gives the route a name.
     * @param bikeID - The id of the bike used to make the route
     * @param start  - The start location of the route
     * @param end    - The end location of the route
     * @param name   - The name of the route
     * @param gender - The gender of the cyclist that cycled this route
     */
    public Route(String bikeID, Location start, Location end, String name, String gender) {
        this.bikeID = bikeID;
        this.start = start;
        this.end = end;
        this.name = name;
        this.gender = gender;
        this.distance = Map.getDistance(start.getLatitude(), start.getLongitude(), 
        		end.getLatitude(), end.getLongitude());
    }

    
    /**
     * Overloaded contructor for the route class, used when creating a route in the GUI.
     * @param start - The start location of the route
     * @param end   - The end location of the route
     * @param name  - The name of route
     */
    public Route(Location start, Location end, String name) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.distance = Map.getDistance(start.getLatitude(), start.getLongitude(), 
        		end.getLatitude(), end.getLongitude());
        this.gender = "0";
        this.bikeID = "0";
    }


    /**
     * Override of the .equals method for route.
     * @param other  - other The object to compare to
     * @return  - A boolean value based on whether the contents are similar
     */
    @Override
    public boolean equals(Object other) {
        Route otherRoute = (Route) other;
        return (other != null && 
        		getStart().getLatitude() == otherRoute.getStart().getLatitude() &&
                getStart().getLongitude() == otherRoute.getStart().getLongitude() &&
                getStart().getLocationType() == otherRoute.getStart().getLocationType() &&
                getEnd().getLatitude() == otherRoute.getEnd().getLatitude() &&
                getEnd().getLongitude() == otherRoute.getEnd().getLongitude() &&
                getGender().equals(otherRoute.getGender()) &&
                getBikeID().equals(otherRoute.getBikeID()));
    }
    
    
    /**
     * Setter for the distance of the route.
     * @param distance - The new distance for the route
     */
    public void setDistance(double distance) {
    	this.distance = distance;
    }
    
    
    /**
     * Getter for the owner of the route.
     * @return - The owner of the route
     */
    public User getOwner() {
    	return owner;
    }
    
    
    /**
     * Setter for the owner of the route.
     * @param user - The new owner for the route
     */
    public void setOwner(User user) {
    	owner = user;
    }
    
    
    /**
     * Getter for the routes name.
     * @return - The name of the route
     */
    public String getName() {
        return name;
    }

    
    /**
     * Getter for if the route is secret.
     * @return - If the route is secret
     */
    public boolean getSecret() {
    	return secret;
    }
    
    
    /**
     * Getter for the bikeID.
     * @return - The bikeID for the route
     */
    public String getBikeID() { 
    	return bikeID; 
    }

    
    /**
     * Getter for the route gender.
     * @return - The gender of the route
     */
    public String getGender() { 
    	return gender; 
    }

    
    /**
     * Getter for the gender type.
     * @return - The gender type based off integer representation
     */
    public String getGenderType() {
        if (gender.equals("1")) {
            return "Male";
        } 
        else if (gender.equals("2")) {
            return "Female";
        } 
        else {
            return "Unknown";
        }
    }

    
    /**
     * Getter for the route distance.
     * @return - The distance of the route
     */
    public double getDistance() {
        return distance;
    }

    
    /**
     * Getter for the route distance rounded.
     * @return - The distance of the route rounded to 2 decimal points
     */
    public String getDistanceRound() {
        return String.format("%.2f", distance);
    }

    
    /**
     * Getter for whether the route is local.
     * @return - If the route is local
     */
    public boolean getLocal() {
        return local;
    }

    
    /**
     * Getter for the start of the route.
     * @return - The start location of the route
     */
    public Location getStart() {
        return start;
    }


    /**
     * Getter for the end of the route.
     * @return - The location at the end of the route
     */
    public Location getEnd() {
        return end;
    }

    
    /**
     * Getter for the name of the start of the route.
     * @return - The location at the start of the route
     */
    public String getStartString() {
    	return start.getName();
    }

    
    /**
     * Getter for the name of the end of the route.
     * @return - The name of the location at the end of the route
     */
    public String getEndString() {
    	return end.getName();
    }

    
    /**
     * Determines whether the Route should be saved locally or just on a server.
     * @param local - If the location is to be local or not
     */
    public void storeLocation(boolean local) {
        this.local = local;
    }


    /**
     * Returns whether the route is private.
     * @return - If the route is secret
     */
	public boolean isSecret() {
		return secret;
	}

	
	/**
	 * Sets if the route is private.
	 * @param secret - If the route is to be secret
	 */
	public void setSecret(boolean secret) {
		this.secret = secret;
	}

	
	/**
	 * Getter for the start marker for the route.
	 * @return - The start marker for the route
	 */
	public Marker getStartMarker() {
        return startMarker;
    }

	
	/**
	 * Setter for the start marker for the route.
	 * @param marker - The new start marker for the route
	 */
    public void setStartMarker(Marker marker) {
        startMarker = marker;
    }

    
    /**
     * Getter for the end marker for the route.
     * @return - The end marker of the route
     */
    public Marker getEndMarker() {
        return endMarker;
    }

    
    /**
     * Setter for the end marker for the route.
     * @param marker - The new end marker of the route
     */
    public void setEndMarker(Marker marker) {
        endMarker = marker;
    }
}
