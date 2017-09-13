package seng202.team5;

import java.util.ArrayList;

/**
 * Class to store all current locations and routes for one instance of the program.
 */
public class CurrentStorage {
    private static ArrayList<Route> routeArray = new ArrayList<Route>();
    private static ArrayList<Retailer> retailerArray = new ArrayList<Retailer>();
    private static ArrayList<Wifi> wifiArray = new ArrayList<Wifi>();
    private static User currentUser;
    
    
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

    public static void addRoute(Route route) {
        routeArray.add(route);
    }

    public static void addRetailer(Retailer retailer) {
        retailerArray.add(retailer);
    }

    public static void addWifi(Wifi wifi) {
        wifiArray.add(wifi);
    }
}
