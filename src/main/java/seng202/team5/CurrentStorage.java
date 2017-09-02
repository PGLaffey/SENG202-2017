package seng202.team5;

import java.util.ArrayList;

/**
 * Class to store all current locations and routes for one instance of the program.
 */
public class CurrentStorage {
    private ArrayList<Route> routeArray;
    private ArrayList<Retailer> retailerArray;
    private ArrayList<Wifi> wifiArray;

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
