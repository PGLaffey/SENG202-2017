package seng202.team5;

public class Wifi extends Location {
    private String provider;

    public Wifi(double lattitude, double longitude, String name, String provider) {
        super(lattitude, longitude, name, "Wifi");
        this.provider = provider;
    }

    public String getProvider() {
        return this.provider;
    }
}
