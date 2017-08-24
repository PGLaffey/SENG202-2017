package seng202.team5;


import java.util.List;

public class Location {
    private double lattitude;
    private double longitude;
    //TODO added type to location
    private String type;
    private int id;
    private String name;
    private boolean local;

    //Added name and type to the location builder, is this correct?
    public Location(double latitude, double longitude, String name, String type) {
        this.lattitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.type = type;
    }

    //Should this be an array rather than a List?
    public double[] getCoords() {
        double[] coords = {this.lattitude, this.longitude};
        return coords;
    }

    public String getName() {
        return this.name;
    }

    //TODO Add this to the UML diagram
    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO Should this be set?
    public void storeLocal(boolean local) {
        this.local = local;
    }
}
