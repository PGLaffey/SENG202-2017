package seng202.Model;

import java.util.ArrayList;

/**
 * User class stores the users information to be used in the app and stored.
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
    private ArrayList<Badge> badges; // First badge is distance, second time and third route


    /**
    Constructor for the User class, creates a new user instance.
     @param first    - The first name of the user
     @param last     - The last name of the user
     @param username - The username of the user
     @param doB      - The date of the user's birth
     @param password - The password the user has chosen
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
        this.badges = new ArrayList<Badge>();
        Badge distBadge = null;
        distBadge = new Badge("Distance");
        Badge timeBadge = new Badge("Time");
        Badge routeBadge = new Badge("Routes");
        badges.add(distBadge);
        badges.add(timeBadge);
        badges.add(routeBadge);
    }

    
    /**
     * Overloaded constructor.
     * @param first    - The first name of the user
     * @param last     - The last name of the user
     * @param username - The username of the user
     * @param doB      - The date of the user's birth
     * @param password - The password the user has chosen
     * @param routes   - The number of routes the user has cycled
     * @param distance - The total distance the user has cycled
     * @param minutes  - The total number of minutes the user has cycled for
     */
    public User(String first, String last, String username, String doB, String password, 
    		int routes, double distance, double minutes) {
        this.name = first + " " + last;
        this.firstName = first;
        this.lastName = last;
        this.password = password;
        this.username = username;
        this.dateOfBirth = doB;
        this.routesCycled = routes;
        this.distanceCycled = distance;
        this.minutesCycled = minutes;
        this.badges = new ArrayList<Badge>();
        Badge distBadge = null;
        distBadge = new Badge("Distance");
        Badge timeBadge = new Badge("Time");
        Badge routeBadge = new Badge("Routes");
        badges.add(distBadge);
        badges.add(timeBadge);
        badges.add(routeBadge);
    }

	
    /**
     * Overrides the .equals function in the user class so it compares key values of the class 
     * to check if the objects are equal to one another.
     * @param user - The user to be compared
     * @return - Whether the current user has the same key values as the previous user
     */
    @Override
    public boolean equals(Object user) {
        User currentUser = (User) user;
        return ((getFirstName().equals(currentUser.getFirstName())) && 
        		(getLastName().equals(currentUser.getLastName())) &&
                (getUsername().equals(currentUser.getUsername()))) && 
        		(getClass().equals(user.getClass()));
    }
    
    
    /**
     * Getter for the users full name.
     * @return - The full name of the user
     */
    public String getName() { 
    	return name; 
    }

    
    /**
     * Getter for the users first name.
     * @return -The first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    
    /**
     * Getter for the users last name.
     * @return - The last name of the user
     */
    public String getLastName() {
        return lastName;
    }


    /**
     * Getter for the users date of birth.
     * @return - The date of birth for the user
     */
    public String getDob() {
    	return dateOfBirth;
    }

    
    /**
     * Getter for the users minutes cycled.
     * @return - The total minutes the user has cycled
     */
    public double getHours() {
        return minutesCycled;
    }

    
    /**
     * Getter for the users distance stat.
     * @return - The total distance the user has cycled
     */
    public double getDistance() {
        return distanceCycled;
    }

    
    /**
     * Getter for the distance the user has cycled, rounded.
     * @return - The total distance the user has cycled rounded to 2 decimal places
     */
    public String getDistanceRounded() { 
    	return String.format("%.2f", distanceCycled); 
    }

    
    /**
     * Getter for the users routes that they have cycled.
     * @return - The total number of routes the user has cycled
     */
    public int getRoutesCycled() {
        return routesCycled;
    }

    
    /**
     * Getter for the users password.
     * @return - The password for the user
     */
    public String getPassword() {
    	return password;
    }

    
    /**
     * Getter for an arrayList of the users badges.
     * @return - ArrayList of the users badges
     */
    public ArrayList<Badge> getBadges() {
        return badges;
    }

    
    /**
     * Getter for the users username.
     * @return - The username for the user
     */
	public String getUsername() {
		return username;
	}

	
	/**
	 * Adds on to the users distance stat.
	 * @param dist - The distance to add to the users total distance cycled
	 */
	public void addDistance(double dist) {
		distanceCycled += dist;
	}

	
	/**
	 * Adds on to the users time spent cycling.
	 * @param time - The time to add to the users total time cycled
	 */
	public void addTime(double time) {
		minutesCycled += time;
	}

	
	/**
	 * Increments the number of routes the user has cycled by 1.
	 */
	public void addRoute() {
		routesCycled += 1; 
	}
}
