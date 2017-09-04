package seng202.team5;

/**
 * The Route class, stores information about the route.
 */
public class Route {
    private String name;
    private double distance;
    private boolean local;
    private Location start;
    private Location end;
    private Location via1;
    private Location via2;
    private Location via3;

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
        via1 = null;
        via2 = null;
        via3 = null;
    }

    public String getName() {
        return name;
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

    public Location[] getVia() {
    	Location[] vias = {via1, via2, via3};
    	return vias;
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
