package seng202.Model;

import java.util.ArrayList;

/**
 * Class to store all current locations and routes for one instance of the program.
 */
public class CurrentStorage {
    private static ArrayList<Route> routeArray = new ArrayList<Route>();
    private static ArrayList<Retailer> retailerArray = new ArrayList<Retailer>();
    private static ArrayList<Integer> addedRetailers = new ArrayList<Integer>();
    private static ArrayList<Wifi> wifiArray = new ArrayList<Wifi>();
    private static ArrayList<Toilet> toiletArray = new ArrayList<Toilet>();
    private static ArrayList<Poi> poiArray = new ArrayList<Poi>();
    private static ArrayList<Location> generalArray = new ArrayList<Location>();
    private static User currentUser;
    
    private static Wifi wifiViewed;
    private static Retailer retailerViewed;
    private static Toilet toiletViewed;
    private static Poi poiViewed;
    private static Location locationViewed;
    private static Route routeViewed;

    public static Location findLocation(Location find) {
    	int count = 0;
    	while (count < generalArray.size()) {
    		if (generalArray.get(count).equals(find)) {
    			return generalArray.get(count);
    		}
    		count += 1;
    	}
    	return null;
    }
    
    public static User getUser() {
    	return currentUser;
    }

	public static void setUser(User user) {
    	currentUser = user;
    }

    public static ArrayList<Route> getRouteArray() {
        return routeArray;
    }

    public static ArrayList<Retailer> getRetailerArray() {
        return retailerArray;
    }

    public static ArrayList<Wifi> getWifiArray() {
        return wifiArray;
    }
    
    public static ArrayList<Toilet> getToiletArray() {
    	return toiletArray;
    }
    
    public static ArrayList<Poi> getPoiArray() {
    	return poiArray;
    }

	public static ArrayList<Location> getGeneralArray() {
		return generalArray;
	}

	public static void addGeneral(Location location) {
		generalArray.add(location);
	}
	
    public static void addRoute(Route route) {
        if (!(routeArray.contains(route))) {
            routeArray.add(route);
        }
    }

    public static void addRetailer(Retailer retailer) { // Used when bringing data in from database
        retailerArray.add(retailer);
    }
    
    public static void addNewRetailer(Retailer retailer) { // Used when user adding new data (from GUI or CSV)
    	retailerArray.add(retailer);
    	addedRetailers.add(retailerArray.indexOf(retailer));
    }

    public static void addWifi(Wifi wifi) {
        if (!retailerArray.contains(wifi)) {
            wifiArray.add(wifi);
        }
    }
    
    public static void addToilet(Toilet toilet) {
    	toiletArray.add(toilet);
    }
    
    public static void addPoi(Poi poi) {
    	poiArray.add(poi);
    }
    
    public static void setWifi(Wifi wifi) {
    	wifiViewed = wifi;
    }
    
    public static Wifi getWifi() {
    	return wifiViewed;
    }
    
    public static void setRetailer(Retailer retailer) {
    	retailerViewed = retailer;
    }
    
    public static Retailer getRetailer() {
    	return retailerViewed;
    }
    
    public static Toilet getToilet() {
		return toiletViewed;
	}

	public static void setToilet(Toilet toilet) {
		toiletViewed = toilet;
	}

	public static Poi getPoi() {
		return poiViewed;
	}

	public static void setPoi(Poi poi) {
		poiViewed = poi;
	}

	public static Location getLocation() {
		return locationViewed;
	}

	public static void setLocation(Location location) {
		locationViewed = location;
	}

	public static Route getRoute() {
		return routeViewed;
	}

	public static void setRoute(Route route) {
		routeViewed = route;
	}

    /**
     * Flushes all data out of arrayLists.
     */
    public static void flush() {
    	currentUser = null;
        routeArray.clear();
        retailerArray.clear();
        wifiArray.clear();
        toiletArray.clear();
        poiArray.clear();
    }
}
