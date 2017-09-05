package seng202.team5;
import java.io.Serializable;
import java.util.ArrayList;

/**
User class stores the users information to be used in the app and stored.
 */
public class User implements Serializable {
    private String name;
    private int id;
    private String username;
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
    @param name The name of the new user
    @param id The unique id code for the user
     @param doB The date of birth for the new user
     */
    public User(String name, int id, String doB) {
        this.name = name;
        this.id = id;
        this.dateOfBirth = doB;
        this.routesCycled = 0;
        this.distanceCycled = 0;
        this.hoursCycled = 0;
        this.routes = new ArrayList<Route>();
        this.badges = new ArrayList<Badge>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Route getRoute(int id) {
        //TODO how are we using the route ID?
        return routes.get(id);
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

    public ArrayList<Badge> getBadges() {
        return badges;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
