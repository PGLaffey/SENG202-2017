package seng202.team5;

/**
 * Wifi subclass of Location, contains the information of the wifi provider.
 */
public class Wifi extends Location {
    private String provider;

    /**
     * Creates a new instance of the wifi class, uses the constructor of the Location superclass.
     * @param lattitude The lattitude of the wifi hotspot.
     * @param longitude The logitude of the wifi hotspot.
     * @param name The name of the wifi hotspot location.
     * @param provider The name of the provider of the wifi hotspot.
     */
    public Wifi(double lattitude, double longitude, String name, String provider) {
        super(lattitude, longitude, name, "Wifi");
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }
}
