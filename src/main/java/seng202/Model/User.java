package seng202.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
User class stores the users information to be used in the app and stored.
 */
public class User implements Serializable {
    private String name;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String dateOfBirth;
    private int routesCycled;
    private double distanceCycled;
    private double hoursCycled;
    private ArrayList<Route> routes;
    private ArrayList<Badge> badges;

    //TODO There is a suggestion that this should be packet-private, should this be something to consider?
    //TODO Did we agree that the id was to be randomly generated or chosen by the user?
    /**
    Constructor for the User class, creates a new user instance
     @param first The first name of the user.
     @param last The last name of the user.
     @param username The username of the user.
     @param doB The date of the user's birth.
     @param password The password the user has chosen.
     */
    public User(String first, String last, String username, String doB, String password) {
        this.name = first + " " + last;
        this.firstName = first;
        this.lastName = last;
        this.password = password;
        this.username = username;
        this.dateOfBirth = doB;
        this.routesCycled = 0;
        this.distanceCycled = 0;
        this.hoursCycled = 0;
        this.routes = new ArrayList<Route>();
        this.badges = new ArrayList<Badge>();
    }

    /**
     * Getter for the users full name
     */
    public String getName() { return name; }

    /**
     * Getter for the users first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Getter for the users last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Getter for the users routes
     */
    public Route getRoute(int id) {
        //TODO how are we using the route ID?
        return routes.get(id);
    }

    /**
     * Getter for the users date of birth
     */
    public String getDob() {
    	return dateOfBirth;
    }

    /**
     * Getter for the users hours
     * @return
     */
    public double getHours() {
        return hoursCycled;
    }

    /**
     * Getter for the users distance stat
     */
    public double getDistance() {
        return distanceCycled;
    }

    /**
     * Getter for the distance the user has cycled, rounded
     */
    public String getDistanceRounded() { return String.format("%.2f", distanceCycled); }

    /**
     * Getter for the users routes that they have cycled
     */
    public int getRoutesCycled() {
        return routesCycled;
    }

    /**
     * Getter for the users password
     */
    public String getPassword() {
    	return password;
    }

    /**
     * Getter for an arrayList of the users badges
     */
    public ArrayList<Badge> getBadges() {
        return badges;
    }

    /**
     * Getter for the users username
     */
	public String getUsername() {
		return username;
	}

    /**
     * Adds on to the users distance stat
     */
	public void addDistance(double dist) {
		distanceCycled += dist;
	}

    /**
     * Adds on to the users time spent cycling
     */
	public void addTime(double time) {
		hoursCycled += time;
	}

    /**
     * Increments the number of routes the user has cycled
     */
	public void addRoute() {routesCycled += 1; }
}
