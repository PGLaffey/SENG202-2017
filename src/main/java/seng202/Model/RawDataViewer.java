package seng202.Model;

import java.util.ArrayList;

/**
 * The raw data viewer class of the program.
 */
public class RawDataViewer {
    private ArrayList<Route> routeArrayList = new ArrayList<Route>();

    public RawDataViewer() {
        this.routeArrayList = CurrentStorage.getRouteArray();
    }

    public static ArrayList<Location> searchLocations(ArrayList<Location> locationsArrayList, String condition) {
        ArrayList<Location> foundLocations = new ArrayList<Location>();
        for (int i = 0; i < locationsArrayList.size(); i++) {
            if (locationsArrayList.get(i).getTypeString().toString().toLowerCase().contains(condition.toLowerCase())) {
                foundLocations.add(locationsArrayList.get(i));
            } else if (locationsArrayList.get(i).getName().toLowerCase().contains(condition.toLowerCase())) {
                foundLocations.add(locationsArrayList.get(i));
            } else if (String.valueOf(locationsArrayList.get(i).getLatitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundLocations.add(locationsArrayList.get(i));
            } else if (String.valueOf(locationsArrayList.get(i).getLongitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundLocations.add(locationsArrayList.get(i));
            }
        }
        return foundLocations;
    }
    
    /**
     * Filters through an array list of points of interest and returns an array list of Poi's that match the search condition
     * @param poiArrayList array list of points of interests to be searched through
     * @param condition the condition which is looked for in each Poi object
     * @return an array list of matched Poi objects
     */
    public static ArrayList<Poi> searchPoi(ArrayList<Poi> poiArrayList, String condition) {
        ArrayList<Poi> foundPoi = new ArrayList<Poi>();
        for (int i = 0; i < poiArrayList.size(); i++) {
            if (Double.toString(poiArrayList.get(i).getCost()).toLowerCase().contains(condition.toLowerCase())) {
                foundPoi.add(poiArrayList.get(i));
            } else if (poiArrayList.get(i).getDescription().toLowerCase().contains(condition.toLowerCase())) {
                foundPoi.add(poiArrayList.get(i));
            } else if (poiArrayList.get(i).getName().toLowerCase().contains(condition.toLowerCase())) {
                foundPoi.add(poiArrayList.get(i));
            } else if (String.valueOf(poiArrayList.get(i).getLatitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundPoi.add(poiArrayList.get(i));
            } else if (String.valueOf(poiArrayList.get(i).getLongitude()).toLowerCase().contains(condition.toLowerCase())) {
                foundPoi.add(poiArrayList.get(i));
            } else if (!poiArrayList.get(i).getAddress().isEmpty()) {
                if (poiArrayList.get(i).getAddress().toLowerCase().contains(condition.toLowerCase())) {
                    foundPoi.add(poiArrayList.get(i));
                }
            }
        }
        return foundPoi;
    }

    /**
     * Filters through an array list of retailers and returns an array list of retailers that match the search condition
     * @param retailerArrayList array list of retailers to be searched through
     * @param condition the condition which is looked for in each retailer object
     * @return an array list of matched retailer objects
     */
    public static ArrayList<Retailer> searchRetailer(ArrayList<Retailer> retailerArrayList, String condition) {
        ArrayList<Retailer> foundRetailer = new ArrayList<Retailer>();
        for (int i = 0; i < retailerArrayList.size(); i++) {
            if (retailerArrayList.get(i).getDescription().toLowerCase().contains(condition.toLowerCase())) {
                foundRetailer.add(retailerArrayList.get(i));
            } else if (retailerArrayList.get(i).getProduct().toLowerCase().contains(condition.toLowerCase())) {
                foundRetailer.add(retailerArrayList.get(i));
            } else if (Integer.toString(retailerArrayList.get(i).getZip()).contains(condition)) {
                foundRetailer.add(retailerArrayList.get(i));
            } else if (retailerArrayList.get(i).getName().toLowerCase().contains(condition.toLowerCase())) {
                foundRetailer.add(retailerArrayList.get(i));
            } else if (String.valueOf(retailerArrayList.get(i).getLatitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundRetailer.add(retailerArrayList.get(i));
            } else if (String.valueOf(retailerArrayList.get(i).getLongitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundRetailer.add(retailerArrayList.get(i));
            	System.out.println(retailerArrayList.get(i).getAddress());
            } else if (!retailerArrayList.get(i).getAddress().isEmpty()) {
                if (retailerArrayList.get(i).getAddress().toLowerCase().contains(condition.toLowerCase())) {
                    foundRetailer.add(retailerArrayList.get(i));
                }
            }
            
        }
        return foundRetailer;
    }

    /**
     * Filters through an array list of routes and returns an array list of routes that match the search condition
     * @param routeArrayList array list of routes to be searched through
     * @param condition the condition which is looked for in each route object
     * @return an array list of matched route objects
     */
    public static ArrayList<Route> searchRoutes(ArrayList<Route> routeArrayList, String condition) { // TODO: how will we check for start and end location? In the table I have it displaying the name of the start and end locations, so could search this?
        ArrayList<Route> foundRoutes = new ArrayList<Route>();
        for (int i = 0; i < routeArrayList.size(); i++) {
            if (routeArrayList.get(i).getName().toLowerCase().contains(condition.toLowerCase())) {
                foundRoutes.add(routeArrayList.get(i));
            } else if (Double.toString(routeArrayList.get(i).getDistance()).contains(condition)) {
                foundRoutes.add(routeArrayList.get(i));
            } else if (routeArrayList.get(i).getGender().toLowerCase().contains(condition.toLowerCase())) {
                foundRoutes.add(routeArrayList.get(i));
            } else if (routeArrayList.get(i).getBikeID().contains(condition)) {
                foundRoutes.add(routeArrayList.get(i));
            } else if (routeArrayList.get(i).getStart().getName().toLowerCase().contains(condition.toLowerCase())) {
                foundRoutes.add(routeArrayList.get(i));
            } else if (routeArrayList.get(i).getEnd().getName().toLowerCase().contains(condition.toLowerCase())) {
                foundRoutes.add(routeArrayList.get(i));
            }
        }
        return foundRoutes;
    }

    /**
     * Filters through an array list of wifi objects and returns an array list of wifi objects that match the search condition
     * @param wifiArrayList array list of wifi objects to be searched through
     * @param condition the condition which is looked for in each wifi object
     * @return an array list of matched wifi objects
     */
    public static ArrayList<Wifi> searchWifi (ArrayList<Wifi> wifiArrayList, String condition) {
        ArrayList<Wifi> foundWifi = new ArrayList<Wifi>();
        for (int i = 0; i < wifiArrayList.size(); i++) {
            if (wifiArrayList.get(i).getProvider().toLowerCase().contains(condition.toLowerCase())) {
                foundWifi.add(wifiArrayList.get(i));
            } else if (wifiArrayList.get(i).getBorough().toLowerCase().contains(condition.toLowerCase())) {
                foundWifi.add(wifiArrayList.get(i));
            } else if(wifiArrayList.get(i).getType().toLowerCase().contains(condition.toLowerCase())) {
                foundWifi.add(wifiArrayList.get(i));
            } else if (wifiArrayList.get(i).getName().toLowerCase().contains(condition.toLowerCase())) {
                foundWifi.add(wifiArrayList.get(i));
            } else if (String.valueOf(wifiArrayList.get(i).getLatitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundWifi.add(wifiArrayList.get(i));
            } else if (String.valueOf(wifiArrayList.get(i).getLongitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundWifi.add(wifiArrayList.get(i));
            } else if (String.valueOf(wifiArrayList.get(i).getSsid()).toLowerCase().contains(condition.toLowerCase())) {
                foundWifi.add(wifiArrayList.get(i));
            }
        }
        return foundWifi;
    }

    /**
     * Filter through an array of toilet objects and returns an array of toilet objects that match the search condition
     * @param toiletArrayList array list of toilet objects to be search through
     * @param condition the condition which is looked for in each toilet object
     * @return an array list of matched toilet objects
     */
    public static ArrayList<Toilet> searchToilets (ArrayList<Toilet> toiletArrayList, String condition) {
        ArrayList<Toilet> foundToilets = new ArrayList<Toilet>();
		for (int i = 0; i < toiletArrayList.size(); i++) {
            if (String.valueOf(toiletArrayList.get(i).getForDisabled()).toLowerCase().contains(condition.toLowerCase())) {
            	foundToilets.add(toiletArrayList.get(i));
            } else if (String.valueOf(toiletArrayList.get(i).getUniSex()).toLowerCase().contains(condition.toLowerCase())) {
            	foundToilets.add(toiletArrayList.get(i));
            } else if (toiletArrayList.get(i).getName().toLowerCase().contains(condition.toLowerCase())) {
            	foundToilets.add(toiletArrayList.get(i));
            } else if (String.valueOf(toiletArrayList.get(i).getLatitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundToilets.add(toiletArrayList.get(i));
            } else if (String.valueOf(toiletArrayList.get(i).getLongitude()).toLowerCase().contains(condition.toLowerCase())) {
            	foundToilets.add(toiletArrayList.get(i));
            }
		}
        return foundToilets;
        }

}