package seng202.Model;
import seng202.Model.Route;
import seng202.team5.Badge;

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

    public String getName() { return name; }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Route getRoute(int id) {
        //TODO how are we using the route ID?
        return routes.get(id);
    }

    public String getDob() {
    	return dateOfBirth;
    }
    
    public double getHours() {
        return hoursCycled;
    }

    public double getDistance() {
        return distanceCycled;
    }

    public int getRoutesCycled() {
        return routesCycled;
    }
    
    public String getPassword() {
    	return password;
    }

    public ArrayList<Badge> getBadges() {
        return badges;
    }

	public String getUsername() {
		return username;
	}
	
	public void addDistance(double dist) {
		distanceCycled += dist;
	}
	
	public void addTime(double time) {
		hoursCycled += time;
	}
}
