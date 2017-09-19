package seng202.Model;

import java.util.ArrayList;

/**
 * Class to store all current locations and routes for one instance of the program.
 */
public class CurrentStorage {
    private static ArrayList<Route> routeArray = new ArrayList<Route>();
    private static ArrayList<Retailer> retailerArray = new ArrayList<Retailer>();
    private static ArrayList<Wifi> wifiArray = new ArrayList<Wifi>();
    private static ArrayList<Toilet> toiletArray = new ArrayList<Toilet>();
    private static ArrayList<Poi> poiArray = new ArrayList<Poi>();
    private static User currentUser;
    
    private static Wifi wifiViewed;
    private static Retailer retailerViewed;
    private static Toilet toiletViewed;
    private static Poi poiViewed;
    private static Location locationViewed;
    private static Route routeViewed;
    
    
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

    public static void addRoute(Route route) {
        if (!(routeArray.contains(route))) {
            routeArray.add(route);
        }
    }

    public static void addRetailer(Retailer retailer) {
        retailerArray.add(retailer);
    }

    public static void addWifi(Wifi wifi) {
        wifiArray.add(wifi);
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
