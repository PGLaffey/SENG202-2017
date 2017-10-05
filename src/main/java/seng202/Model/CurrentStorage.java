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
    
    private static ArrayList<Integer> usersSavedRoutes = new ArrayList<Integer>();
    private static ArrayList<Integer> usersFavRoutes = new ArrayList<Integer>();
    
    private static Location locationViewed;
    private static Route routeViewed;


	private static Integer toiletIndex;
	private static Integer retailerIndex;
	private static Integer poiIndex;
	private static Integer wifiIndex;

    private static Location newRouteStart;
    private static Location newRouteEnd;
    
    public static ArrayList<Integer> getSavedRoutes() {
    	return usersSavedRoutes;
    }
    
    public static ArrayList<Integer> getFavRoutes() {
    	return usersFavRoutes;
    }
    
    public static void addSavedRoute(int index) {
    	usersSavedRoutes.add(index);
    }
    
    public static void addFavRoute(int index) {
    	usersFavRoutes.add(index);
    }
    
    public static void setToiletIndex(Integer i) { 
    	toiletIndex = i; 
    }
    
    public static Integer getToiletIndex() {
    	return toiletIndex; 
    }

    public static void setRetailerIndex(Integer i) { 
    	retailerIndex = i; 
    }

    public static Integer getRetailerIndex() {
    	return retailerIndex; 
    }

    public static void setPoiIndex(Integer i) {
    	poiIndex = i; 
    }

    public static Integer getPoiIndex() {
    	return poiIndex; 
    }

    public static void setWifiIndex(Integer i) { 
    	wifiIndex = i; 
    }

    public static Integer getWifiIndex() { 
    	return wifiIndex; 
    }


	/**
	 * Getter for the current user
	 * @return  currentUser
	 */
	public static User getUser() {
		return currentUser;
	}

	/**
	 * Setter for the current user
	 * @param user User to be set as the current user
	 */
	public static void setUser(User user) {
		currentUser = user;
	}

	/**
	 * Setter for the newRouteStart
	 * @param location Location to be set as the start of new route being created
	 */
	public static void setNewRouteStart(Location location) { newRouteStart = location; }

	/**
	 * Getter for the newRouteStart
	 * @return newRouteStart The starting location for route being added
	 */
	public static Location getNewRouteStart() { return newRouteStart; }

	/**
	 * Setter for the newRouteEnd
	 * @param location Location to be set as the end of new route being created
	 */
	public static void setNewRouteEnd(Location location) { newRouteEnd = location; }

	/**
	 * Getter for newRouteEnd
	 * @return newRouteEnd The ending location for route being added
	 */
	public static Location getNewRouteEnd() { return newRouteEnd; }

	/**
	 * Getter for the routeArray
	 * @return the ArrayList of all routes in the application
	 */
	public static ArrayList<Route> getRouteArray() {
		return routeArray;
	}

	/**
	 * Getter for the retailerArray
	 * @return the ArrayList of all retailers in the application
	 */
	public static ArrayList<Retailer> getRetailerArray() {
		return retailerArray;
	}

	/**
	 * Getter for the wifiArray
	 * @return the ArrayList of all wifi in the applciation
	 */
	public static ArrayList<Wifi> getWifiArray() {
		return wifiArray;
	}

	/**
	 * Getter for the toiletArray
	 * @return the ArrayList of all toilets in the application
	 */
	public static ArrayList<Toilet> getToiletArray() {
		return toiletArray;
	}

	/**
	 * Getter for the poiArray
	 * @return ArrayList of all poi in the application
	 */
	public static ArrayList<Poi> getPoiArray() {
		return poiArray;
	}

	/**
	 * Getter for the generalArray
	 * @return ArrayList of all general locations in the application
	 */
	public static ArrayList<Location> getGeneralArray() {
		return generalArray;
	}

	/**
	 * Adds a coord to the coordArray
	 * @param coord Coord to be added
	 */
	public static void addCoords(Coord coord) {
		coordsArray.add(coord);
	}

	/**
	 * Getter for the coordsArray
	 * @return ArrayList of Coord
	 */
	public static ArrayList<Coord> getCoords() {
		return coordsArray;
	}

/*	*//**
	 * Setter for the wifi that is being viewed in a pop up
	 * @param wifi Wifi being viewed
	 *//*
	public static void setWifi(Wifi wifi) {
		wifiViewed = wifi;
	}

	*//**
	 * Getter for the wifi being viewed in a pop up
	 * @return Wifi being viewed
	 *//*
	public static Wifi getWifi() {
		return wifiViewed;
	}

	*//**
	 * Setter for the retailer that is being viewed in a pop up
	 * @param retailer Retailer being viewed
	 *//*
	public static void setRetailer(Retailer retailer) {
		retailerViewed = retailer;
	}

	*//**
	 * Getter for the retailer being viewed in a pop up
	 * @return Retailer being viewed
	 *//*
	public static Retailer getRetailer() {
		return retailerViewed;
	}

	*//**
	 * Getter for the toilet being viewed in a pop up
	 * @return Toilet being viewed
	 *//*
	public static Toilet getToilet() {
		return toiletViewed;
	}

	*//**
	 * Setter for the toilet that is being viewed in a pop up
	 * @param toilet Toilet being viewed
	 *//*
	public static void setToilet(Toilet toilet) {
		toiletViewed = toilet;
	}

	*//**
	 * Getter for the poi being viewed in a pop up
	 * @return Poi being viewed
	 *//*
	public static Poi getPoi() {
		return poiViewed;
	}

	*//**
	 * Setter for the poi that is being viewed in a pop up
	 * @param poi Poi being viewed
	 *//*
	public static void setPoi(Poi poi) {
		poiViewed = poi;
	}*/

	/**
	 * Getter for the location being viewed in a pop up
	 * @return Location being viewed
	 */
	public static Location getLocation() {
		return locationViewed;
	}

	/**
	 * Setter for the location that is being viewed in a pop up
	 * @param location Location being viewed
	 */
	public static void setLocation(Location location) {
		locationViewed = location;
	}

	/**
	 * Getter for the route being viewed in a pop up
	 * @return Route being viewed
	 */
	public static Route getRoute() {
		return routeViewed;
	}

	/**
	 * Setter for the route that is being viewed in a pop up
	 * @param route Route being viewed
	 */
	public static void setRoute(Route route) {
		routeViewed = route;
	}


	/**
	 * Getter for the indexes of newly added wifi
	 * @return ArrayList of indexes
	 */
	public static ArrayList<Integer> getAddedWifi() {
		return addedWifi;
	}

	/**
	 * Getter for the indexes of newly added toilets
	 * @return ArrayList of indexes
	 */
	public static ArrayList<Integer> getAddedToilets() {
		return addedToilets;
	}

	/**
	 * Getter for the indexes of newly added poi
	 * @return ArrayList of indexes
	 */
	public static ArrayList<Integer> getAddedPoi() {
		return addedPoi;
	}

	/**
	 * Getter for the indexes of newly added general locations
	 * @return ArrayList of indexes
	 */
	public static ArrayList<Integer> getAddedGeneral() {
		return addedGeneral;
	}

	/**
	 * Getter for the indexes of newly added retailer
	 * @return ArrayList of indexes
	 */
	public static ArrayList<Integer> getAddedRetailers() {
		return addedRetailers;
	}


	/**
	 * Adds an existing wifi to the application, adding to wifiArray
	 * @param wifi Wifi to be added
	 */
	public static void addWifi(Wifi wifi) {
		wifiArray.add(wifi);
	}

	/**
	 * Checks if a wifi is already in the application
	 * @param wifi Wifi to be checked
	 * @return true if in array, false otherwise
	 */
	public static boolean containsWifi(Wifi wifi) {
		for (Wifi currentWifi : wifiArray) {
			if (currentWifi.equals(wifi)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new wifi to the application, adding to wifiArray and its index to addedWifi
	 * @param wifi Wifi to be added
	 */
	public static void addNewWifi(Wifi wifi) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsWifi(wifi))) {
			wifiArray.add(wifi);
			addedWifi.add(wifiArray.size()-1);
		}
	}

	/**
	 * Adds an existing retailer to the application, adding to retailer Array
	 * @param retailer Retailer to be added
	 */
	public static void addRetailer(Retailer retailer) { // Used when bringing data in from database
		retailerArray.add(retailer);
	}

	/**
	 * Checks if a retailer is already in the application
	 * @param retailer Retailer to be checked
	 * @return true if in array, false otherwise
	 */
	public static boolean containsRetailer(Retailer retailer) {
		for (Retailer currentRetailer : retailerArray) {
			if (currentRetailer.equals(retailer)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new retailer to the application, adding to retailerArray and its index to addedRetailer
	 * @param retailer Retailer to be added
	 */
	public static void addNewRetailer(Retailer retailer) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsRetailer(retailer))) {
			retailerArray.add(retailer);
			addedRetailers.add(retailerArray.size()-1);
		}
	}

	/**
	 * Adds an existing toilet to the application, adding to toiletArray
	 * @param toilet Toilet to be added
	 */
	public static void addToilet(Toilet toilet) {
		toiletArray.add(toilet);
	}

	/**
	 * Checks if a toilet is already in the application
	 * @param toilet Toilet to be checked
	 * @return true if in array, false otherwise
	 */
	public static boolean containsToilet(Toilet toilet) {
		for (Toilet currentToilet : toiletArray) {
			if (currentToilet.equals(toilet)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new toilet to the application, adding to toiletArray and its index to addedToilet
	 * @param toilet Toilet to be added
	 */
	public static void addNewToilet(Toilet toilet) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsToilet(toilet))) {
			toiletArray.add(toilet);
			addedToilets.add(toiletArray.size()-1);
		}
	}

	/**
	 * Adds an existing poi to the application, adding to poiArray
	 * @param poi Poi to be added
	 */
	public static void addPoi(Poi poi) {
		poiArray.add(poi);
	}

	/**
	 * Checks if a poi is already in the application
	 * @param poi Poi to be checked
	 * @return true if in array, false otherwise
	 */
	public static boolean containsPoi(Poi poi) {
		for (Poi currentPoi : poiArray) {
			if (currentPoi.equals(poi)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new poi to the application, adding to poiArray and its index to addedPoi
	 * @param poi Poi to be added
	 */
	public static void addNewPoi(Poi poi) {
		// Used when user adding new data (from GUI or CSV)
		if (!(containsPoi(poi))) {
			poiArray.add(poi);
			addedPoi.add(poiArray.size()-1);
		}
	}

	/**
	 * Adds an existing route to the application, adding to routeArray
	 * @param route Route to be added
	 */
    public static void addRoute(Route route) {
        routeArray.add(route);
    }

	/**
	 * Checks if a route is already in the application
	 * @param route Route to be checked
	 * @return true if in array, false otherwise
	 */
    public static boolean containsRoute(Route route) {
    	for (Route currentRoute : routeArray) {
    		if (currentRoute.equals(route)) {
    			return true;
			}
		}
		return false;
    }

	/**
	 * Adds a new route to the application, adding to routeArray and its index to addedRoute
	 * @param route Route to be added
	 */
    public static void addNewRoute(Route route) {
    	if (!(containsRoute(route))) {
			routeArray.add(route);
			addedRoutes.add(routeArray.size()-1);
		}
	}

	/**
	 * Adds an existing general location to the application, adding to generalArray
	 * @param location Location to be added
	 */
	public static void addGeneral(Location location) {
		generalArray.add(location);
	}

	/**
	 * Checks if a general location is already in the application
	 * @param location Location to be checked
	 * @return true if in array, false otherwise
	 */
	public static boolean containsGeneral(Location location) {
		for (Location currentLocation : generalArray) {
			if (currentLocation.equals(location)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds a new general location to the application, adding to generalArray and its index to addedGeneral
	 * @param location Location to be added
	 */
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
        generalArray.clear();
    }
}
