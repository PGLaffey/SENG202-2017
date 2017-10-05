package seng202.Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
User class stores the users information to be used in the app and stored.
 */
public class User {
    private String name;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String dateOfBirth;
    private int routesCycled;
    private double distanceCycled;
    private double minutesCycled;
    private ArrayList<Route> routes; // TODO: Possibly delete this, added saved and favourite routes (wasnt sure if this was being used of not)
    private ArrayList<Badge> badges; // First badge is distance, second time and third route
    private ArrayList<Route> savedRoutes = new ArrayList<Route>();
    private ArrayList<Route> favouriteRoutes = new ArrayList<Route>();


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
        this.minutesCycled = 0;
        this.routes = new ArrayList<Route>();
        this.badges = new ArrayList<Badge>();
        Badge distBadge = null;
        distBadge = new Badge("Distance");
        Badge timeBadge = new Badge("Time");
        Badge routeBadge = new Badge("Routes");
        badges.add(distBadge);
        badges.add(timeBadge);
        badges.add(routeBadge);
        // For testing
        Location s = new Location(123, 123, "start", 0);
        Location e = new Location(234, 234, "end", 0);
        savedRoutes.add(new Route(s, e, "saved1"));
        savedRoutes.add(new Route(s, e, "saved2"));
        favouriteRoutes.add(new Route(s, e, "fav1"));
        favouriteRoutes.add(new Route(s, e, "fav2"));
    }

    public User(String first, String last, String username, String doB, String password, int routes, double distance, double minutes) {
        this.name = first + " " + last;
        this.firstName = first;
        this.lastName = last;
        this.password = password;
        this.username = username;
        this.dateOfBirth = doB;
        this.routesCycled = routes;
        this.distanceCycled = distance;
        this.minutesCycled = minutes;
        this.routes = new ArrayList<Route>();
        this.badges = new ArrayList<Badge>();
        Badge distBadge = null;
        distBadge = new Badge("Distance");
        Badge timeBadge = new Badge("Time");
        Badge routeBadge = new Badge("Routes");
        badges.add(distBadge);
        badges.add(timeBadge);
        badges.add(routeBadge);
        // For testing
        Location s = new Location(123, 123, "start", 0);
        Location e = new Location(234, 234, "end", 0);
        savedRoutes.add(new Route(s, e, "saved1"));
        savedRoutes.add(new Route(s, e, "saved2"));
        favouriteRoutes.add(new Route(s, e, "fav1"));
        favouriteRoutes.add(new Route(s, e, "fav2"));
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
        return minutesCycled;
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
		minutesCycled += time;
	}

    /**
     * Increments the number of routes the user has cycled
     */
	public void addRoute() {routesCycled += 1; }

	public void setSavedRoutes(ArrayList<Route> routes) { savedRoutes = routes; }

	public ArrayList<Route> getSavedRoutes() { return savedRoutes; }

	public void addSavedRoutes(Route route) { savedRoutes.add(route); }

	public void setFavouriteRoutes(ArrayList<Route> routes) { favouriteRoutes = routes; }

	public ArrayList<Route> getFavouriteRoutes() { return favouriteRoutes; }

	public void addFavouriteRoute(Route route) { favouriteRoutes.add(route); }

    /**
     * Overrides the .equals function in the user class so it compares key values of the class to check if the
     * objects are equal to one another.
     * @param user The user to be compared.
     * @return Whether the current user has the same key values as the previous user.
     */
    @Override
    public boolean equals(Object user) {
        User currentUser = (User) user;
        return (getFirstName().equals(currentUser.getFirstName()) && getLastName().equals(currentUser.getLastName()) &&
                getUsername().equals(currentUser.getUsername())) && getClass().equals(user.getClass());
    }
}
