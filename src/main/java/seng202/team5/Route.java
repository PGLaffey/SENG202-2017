package seng202.team5;

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
    private Location via1;
    private Location via2;
    private Location via3;

    /**
     * Constructs a route based on the start and end locations.
     * @param bikeID The ID of the bike used to make this route.
     * @param start The start location of the route.
     * @param end The end location of the route
     */
    public Route(String bikeID, Location start, Location end) {
        this.bikeID = bikeID;
        this.start = start;
        this.end = end;
        via1 = null;
        via2 = null;
        via3 = null;
    }

    /**
     * Overloaded constructor for the route class, gives the route a name.
     * @param bikeID The id of the bike used to make the route.
     * @param start The start location of the route.
     * @param end The end location of the route.
     * @param name The name of the route.
     * @param gender The gender of the cyclist that cycled this route.
     */
    public Route(String bikeID, Location start, Location end, String name, String gender) {
        this.bikeID = bikeID;
        this.start = start;
        this.end = end;
        this.name = name;
        this.gender = gender;
        via1 = null;
        via2 = null;
        via3 = null;
    }

    public String getName() {
        return name;
    }

    public String getBikeID() { return bikeID; }

    public String getGender() { return gender; }

    public double getDistance() {
        return distance;
    }

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

    public Location getEnd() {
        return end;
    }

    public void storeLocation(boolean local) {
        this.local = local;
    }
}
