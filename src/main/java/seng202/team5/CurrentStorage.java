package seng202.team5;

import java.util.ArrayList;

/**
 * Class to store all current locations and routes for one instance of the program.
 */
public class CurrentStorage {
    private ArrayList<Route> routeArray = new ArrayList<Route>();
    private ArrayList<Retailer> retailerArray = new ArrayList<Retailer>();
    private ArrayList<Wifi> wifiArray = new ArrayList<Wifi>();

    public ArrayList<Route> getRouteArray() {
        return routeArray;
    }

    public ArrayList<Retailer> getRetailerArray() {
        return retailerArray;
    }

    public ArrayList<Wifi> getWifiArray() {
        return wifiArray;
    }
}
