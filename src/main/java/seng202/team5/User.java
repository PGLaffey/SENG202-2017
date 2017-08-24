package seng202.team5;
import java.util.ArrayList;

public class User {
    private String name;
    private int id;
    private int routesCycled;
    private double distanceCycled;
    private double hoursCycled;
    private ArrayList<Route> routes;
    private ArrayList<Badge> badges;

    //TODO Should this be on the UML?
    public User(String name, int id) {
        this.name = name;
        this.id = id;
        this.routesCycled = 0;
        this.distanceCycled = 0;
        this.hoursCycled = 0;
        this.routes = new ArrayList<Route>();
        this.badges = new ArrayList<Badge>();
    }

    public String getName() {
        return this.name;
    }

    //TODO Add getId(void) to the UML diagram
    public int getId() {
        return this.id;
    }

    public Route getRoute(int id) {
        //TODO how are we using the route ID?
        return this.routes.get(id);
    }

    public double getHours() {
        return this.hoursCycled;
    }

    public double getDistance() {
        return this.distanceCycled;
    }

    public int getRoutesCycled() {
        return this.routesCycled;
    }

    public ArrayList<Badge> getBadges() {
        return this.badges;
    }
}
