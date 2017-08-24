package seng202.team5;

public class Route {
    private String name;
    private int id; // If we make this a static variable, can we then ensure that the route id is unique?
    private double distance;
    private boolean local;
    private Location start;
    private Location end;

    /**
     * Constructor for a Route.
     * @param start
     * @param end
     * @param name
     */
    public Route(Location start, Location end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //TODO Add getId(void) to the UML diagram
    public int getId() {
        return this.id;
    }

    public double getDistance() {
        return this.distance;
    }

    //TODO Add getLocal to UML diagram
    public boolean getLocal() {
        return this.local;
    }

    public Location getStart() {
        return this.start;
    }

    //TODO Add getEnd(void) to the UML diagram, getStart added twice
    public Location getEnd() {
        return this.end;
    }

    //TODO Should this be setLocation?
    public void storeLocation(boolean local) {
        this.local = local;
    }

}
