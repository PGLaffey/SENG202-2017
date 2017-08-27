package seng202.team5;

/**
 * The Route class, stores information about the route.
 */
public class Route {
    private String name;
    private int id; // If we make this a static variable, can we then ensure that the route id is unique?
    private double distance;
    private boolean local;
    private Location start;
    private Location end;

    /**
     * Constructor for a Route.
     * @param start The start location of the route.
     * @param end The end location of the route.
     * @param name The name of the route.
     */
    public Route(Location start, Location end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //TODO Add getId(void) to the UML diagram
    public int getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    //TODO Add getLocal to UML diagram
    public boolean getLocal() {
        return local;
    }

    public Location getStart() {
        return start;
    }

    //TODO Add getEnd(void) to the UML diagram, getStart added twice
    public Location getEnd() {
        return end;
    }

    //TODO Should this be setLocation?
    public void storeLocation(boolean local) {
        this.local = local;
    }

}
