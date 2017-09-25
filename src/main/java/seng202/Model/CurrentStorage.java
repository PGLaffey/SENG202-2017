package seng202.Model;

import java.util.ArrayList;

/**
 * Class to store all current locations and routes for one instance of the program.
 */
public class CurrentStorage {
    private static ArrayList<Route> routeArray = new ArrayList<Route>();
    private static ArrayList<Integer> addedRoutes = new ArrayList<Integer>();
    private static ArrayList<Retailer> retailerArray = new ArrayList<Retailer>();
    private static ArrayList<Integer> addedRetailers = new ArrayList<Integer>();
    private static ArrayList<Wifi> wifiArray = new ArrayList<Wifi>();
    private static ArrayList<Integer> addedWifi = new ArrayList<Integer>();
    private static ArrayList<Toilet> toiletArray = new ArrayList<Toilet>();
    private static ArrayList<Integer> addedToilets = new ArrayList<Integer>();
    private static ArrayList<Poi> poiArray = new ArrayList<Poi>();
    private static ArrayList<Integer> addedPoi = new ArrayList<Integer>();
    private static ArrayList<Location> generalArray = new ArrayList<Location>();
    private static ArrayList<Integer> addedGeneral = new ArrayList<Integer>();
    private static ArrayList<Coord> coordsArray = new ArrayList<Coord>();
    private static User currentUser;
    
    private static Wifi wifiViewed;
    private static Retailer retailerViewed;
    private static Toilet toiletViewed;
    private static Poi poiViewed;
    private static Location locationViewed;
    private static Route routeViewed;

    private static Location newRouteStart;
    private static Location newRouteEnd;

	public static User getUser() {
		return currentUser;
	}

	public static void setUser(User user) {
		currentUser = user;
	}

	public static void setNewRouteStart(Location location) { newRouteStart = location; }

	public static Location getNewRouteStart() { return newRouteStart; }

	public static void setNewRouteEnd(Location location) { newRouteEnd = location; }

	public static Location getNewRouteEnd() { return newRouteEnd; }

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


	public static void addCoords(Coord coord) {
		coordsArray.add(coord);
	}

	public static ArrayList<Coord> getCoords() {
		return coordsArray;
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


	public static ArrayList<Integer> getAddedWifi() {
		return addedWifi;
	}

	public static ArrayList<Integer> getAddedToilets() {
		return addedToilets;
	}

	public static ArrayList<Integer> getAddedPoi() {
		return addedPoi;
	}

	public static ArrayList<Integer> getAddedGeneral() {
		return addedGeneral;
	}

	public static ArrayList<Integer> getAddedRetailers() {
		return addedRetailers;
	}


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

    
    public static Retailer findRetailer(Retailer find) {
    	int count = 0;
    	while (count < retailerArray.size()) {
    		if (retailerArray.get(count).equals(find)) {
    			return retailerArray.get(count);
    		}
    		count += 1;
    	}
    	return null;
    }

    
    public static Wifi findWifi(Wifi find) {
    	int count = 0;
    	while (count < wifiArray.size()) {
    		if (wifiArray.get(count).equals(find)) {
    			return wifiArray.get(count);
    		}
    		count += 1;
    	}
    	return null;
    }

    
    public static Poi findPoi(Poi find) {
    	int count = 0;
    	while (count < poiArray.size()) {
    		if (poiArray.get(count).equals(find)) {
    			return poiArray.get(count);
    		}
    		count += 1;
    	}
    	return null;
    }


    public static Toilet findToilet(Toilet find) {
    	int count = 0;
    	while (count < toiletArray.size()) {
    		if (toiletArray.get(count).equals(find)) {
    			return toiletArray.get(count);
    		}
    		count += 1;
    	}
    	return null;
    }


	public static void addWifi(Wifi wifi) {
		wifiArray.add(wifi);
	}


	public static boolean containsWifi(Wifi wifi) {
		for (Wifi currentWifi : wifiArray) {
			if (currentWifi.equals(wifi)) {
				return true;
			}
		}
		return false;
	}


	public static void addNewWifi(Wifi wifi) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsWifi(wifi))) {
			wifiArray.add(wifi);
			addedWifi.add(wifiArray.size()-1);
		}
	}


	public static void addRetailer(Retailer retailer) { // Used when bringing data in from database
		retailerArray.add(retailer);
	}


	public static boolean containsRetailer(Retailer retailer) {
		for (Retailer currentRetailer : retailerArray) {
			if (currentRetailer.equals(retailer)) {
				return true;
			}
		}
		return false;
	}

	public static void addNewRetailer(Retailer retailer) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsRetailer(retailer))) {
			retailerArray.add(retailer);
			addedRetailers.add(retailerArray.size()-1);
		}
	}


	public static void addToilet(Toilet toilet) {
		toiletArray.add(toilet);
	}


	public static boolean containsToilet(Toilet toilet) {
		for (Toilet currentToilet : toiletArray) {
			if (currentToilet.equals(toilet)) {
				return true;
			}
		}
		return false;
	}


	public static void addNewToilet(Toilet toilet) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsToilet(toilet))) {
			toiletArray.add(toilet);
			addedToilets.add(toiletArray.size()-1);
		}
	}


	public static void addPoi(Poi poi) {
		poiArray.add(poi);
	}


	public static boolean containsPoi(Poi poi) {
		for (Poi currentPoi : poiArray) {
			if (currentPoi.equals(poi)) {
				return true;
			}
		}
		return false;
	}


	public static void addNewPoi(Poi poi) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsPoi(poi))) {
			poiArray.add(poi);
			addedPoi.add(poiArray.size()-1);
		}
	}


    public static void addRoute(Route route) {
        routeArray.add(route);
    }
    

    public static boolean containsRoute(Route route) {
    	for (Route currentRoute : routeArray) {
    		if (currentRoute.equals(route)) {
    			return true;
			}
		}
		return false;
    }


    public static void addNewRoute(Route route) {
    	if (!(containsRoute(route))) {
			routeArray.add(route);
			addedRoutes.add(routeArray.size()-1);
		}
	}


	public static void addGeneral(Location location) {
		generalArray.add(location);
	}


	public static boolean containsGeneral(Location location) {
		for (Location currentLocation : generalArray) {
			if (currentLocation.equals(location)) {
				return true;
			}
		}
		return false;
	}


	public static void addNewGeneral(Location location) {
    	// Used when user adding new data (from GUI or CSV)
		if (!(containsGeneral(location))) {
			generalArray.add(location);
			addedGeneral.add(generalArray.size()-1);
		}
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
