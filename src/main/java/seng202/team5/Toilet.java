package seng202.team5;


public class Toilet extends Location {
    private boolean forDisabled;
    private boolean uniSex;

    public Toilet(double lattitude, double longitude, String name, boolean forDisabled, boolean uniSex) {
        super(lattitude, longitude, name, "Toilet");
        this.forDisabled = forDisabled;
        this.uniSex = uniSex;
    }

    public boolean getForDisabled() {
        return this.forDisabled;
    }

    public boolean getUniSex() {
        return this.uniSex;
    }
}

