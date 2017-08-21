package seng202.team5;

public class Poi extends Location {
    private String description;
    private double cost;

    public Poi(double lattitude, double longitude, String name, String description, double cost) {
        super(lattitude, longitude, name, "Poi");
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return this.description;
    }

    public double getCost() {
        return this.cost;
    }
}
