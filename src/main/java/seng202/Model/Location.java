package seng202.Model;

/**
 * Location object stores the information about a specific location (i.e. wifi hotspot, retailer, toilet or point of interest).
 */
public class Location {
    private double latitude;
    private double longitude;
    //TODO added type to location
    private int locationType; //toilet = 0, poi = 1, retailer = 2, wifi = 3, general = 4
    private String name;
    private boolean local;
    private int zip = 0;
    private String address = null;
    private String borough = null;
    private boolean secret = false;
    
    private User belongsTo = null;

    /**
     * Constructor for the location class, creates a new Location.
     * @param latitude The latitude of the new location.
     * @param longitude The longitude of the new location.
     * @param name The name of the new location.
     * @param locationType The type of location the new location is (i.e. point of interest, wifi spot, retailer or toilet).
     */
    public Location(double latitude, double longitude, String name, int locationType, int zip) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.locationType = locationType;
        this.zip = zip;
    }

    /**
     * Overloaded constructor to take an address and obtain a longitude and lattitude for the address.
     * @param address The address of the location.
     * @param name The name of the new location.
     * @param locationType The type of location the new location is (i.e. point of interest, wifi spot, retailer or toilet).
     */
    public Location(String address, String name, int locationType, int zip) {
        //this.latitude = Map.getLatitude(address);
        //this.longitude = Map.getLongitude(address);
		this.address = address;
		this.zip = zip;
        this.name = name;
        this.locationType = locationType;
    }

    @Override
    public boolean equals(Object other) {
    	if (other == null) {
    		return false;
    	}
    	if (other == this) {
    		return true;
    	}
    	if (!(other instanceof Location)) {
    		return false;
    	}
    	Location otherLocation = (Location)other;
    	if (otherLocation.getCoords() != this.getCoords()) {
    		return false;
    	}
    	if (otherLocation.getLocationType() != this.getLocationType()) {
    		return false;
    	}
    	return true;
    }
    
    /**
     * Function that returns the longitude and latitude of the location in an Array.
     * @return The latitude and longitude of the location in an Array.
     */
    public double[] getCoords() {
        double[] coords = {latitude, longitude};
        return coords;
    }
    
    public double getLatitude() {
    	return latitude;
    }
    
    public double getLongitude() {
    	return longitude;
    }

    public String getName() {
        return name;
    }

    public boolean getLocal() {
    	return local;
    }
    
    public User getOwner() {
    	return belongsTo;
    }
    
    public int getLocationType() {
        return locationType;
    }
    
    public String getTypeString() {
    	if (locationType == 0) {
    		return("Toilet");
    	} else if (locationType == 1) {
    		return("Point of interest");
    	} else if(locationType == 2) {
    		return("Retailer");
    	} else if(locationType == 3) {
    		return("Wifi");
    	} else {
    		return("Other");
    	}
    }

    public void setName(String name) {
        this.name = name;
    }

    //TODO Should this be set?
    public void storeLocal(boolean local) {
        this.local = local;
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}

    public String getBorough() {
        return this.borough;
    }

    public void setBorough(String borough) {
        this.borough = borough;
    }
}
