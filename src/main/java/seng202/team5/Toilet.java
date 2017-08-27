package seng202.team5;

/**
 * Toilet subclass of Location, stores whether the toilet has facilities for wheelchair users, and whether the toilet is unisex or not.
 */
public class Toilet extends Location {
    private boolean forDisabled;
    private boolean uniSex;

    /**
     * Toilet constructor, creates a new instance of the toilet object using the location superclass constructor.
     * @param lattitude The lattitude of the toilet location.
     * @param longitude The longitude of the toilet location.
     * @param name The name of the toilet location.
     * @param forDisabled Whether the toilet has facilities for wheelchair users.
     * @param uniSex Whether the toilet is unisex or has seperate facilities for men and women.
     */
    public Toilet(double lattitude, double longitude, String name, boolean forDisabled, boolean uniSex) {
        super(lattitude, longitude, name, "Toilet");
        this.forDisabled = forDisabled;
        this.uniSex = uniSex;
    }

    public boolean getForDisabled() {
        return forDisabled;
    }

    public boolean getUniSex() {
        return uniSex;
    }
}

