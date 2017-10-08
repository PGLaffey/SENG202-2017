package seng202.Model;

import java.util.ArrayList;

/**
 * The raw data viewer class of the program.
 */
public class RawDataViewer {

    
    /**
     * Searches for all locations within a ArrayList that meet a condition.
     * @param locationsArrayList	- The list of locations to search through
     * @param condition 			- The condition to filter results by
     * @return 						- ArrayList of the locations that meet the condition
     */
    public static ArrayList<Location> searchLocations(ArrayList<Location> locationsArrayList, 
    		String condition) {
        ArrayList<Location> foundLocations = new ArrayList<Location>();
        String type = null;
        String name = null;
        String latitude = null;
        String longitude = null;
        condition = condition.toLowerCase();
        for (int i = 0; i < locationsArrayList.size(); i++) {
            if (locationsArrayList.get(i) != null) {
                type = locationsArrayList.get(i).getTypeString().toLowerCase();
                name = locationsArrayList.get(i).getName().toLowerCase();
                latitude = String.valueOf(locationsArrayList.get(i).getLatitude()).toLowerCase();
                longitude = String.valueOf(locationsArrayList.get(i).getLongitude()).toLowerCase();
                if (type.contains(condition)) {
                    foundLocations.add(locationsArrayList.get(i));
                }
                else if (name.contains(condition)) {
                    foundLocations.add(locationsArrayList.get(i));
                }
                else if (latitude.contains(condition)) {
                    foundLocations.add(locationsArrayList.get(i));
                }
                else if (longitude.contains(condition)) {
                    foundLocations.add(locationsArrayList.get(i));
                }
            }
        }
        return foundLocations;
    }
    
    
    /**
     * Filters through an array list of points of interest and returns an array list of Poi's 
     * that match the search condition.
     * @param poiArrayList 	- ArrayList of points of interests to be searched through
     * @param condition 	- The condition which is looked for in each Poi object
     * @return 				- An ArrayList of matched Poi objects
     */
    public static ArrayList<Poi> searchPoi(ArrayList<Poi> poiArrayList, String condition) {

        ArrayList<Poi> foundPoi = new ArrayList<Poi>();
        String cost = null;
        String description = null;
        String name = null;
        String latitude = null;
        String longitude = null;
        String address = null;
        String borough = null;
        condition = condition.toLowerCase();
        for (int i = 0; i < poiArrayList.size(); i++) {
            if (poiArrayList.get(i) != null) {
                cost = String.valueOf(poiArrayList.get(i).getCost()).toLowerCase();
                description = poiArrayList.get(i).getDescription().toLowerCase();
                name = poiArrayList.get(i).getName().toLowerCase();
                latitude = String.valueOf(poiArrayList.get(i).getLatitude()).toLowerCase();
                longitude = String.valueOf(poiArrayList.get(i).getLongitude()).toLowerCase();
                address = poiArrayList.get(i).getAddress().toLowerCase();
                borough = poiArrayList.get(i).getBorough();
                if (cost.contains(condition)) {
                    foundPoi.add(poiArrayList.get(i));
                }
                else if (description.contains(condition)) {
                    foundPoi.add(poiArrayList.get(i));
                }
                else if (name.contains(condition)) {
                    foundPoi.add(poiArrayList.get(i));
                }
                else if (latitude.contains(condition)) {
                    foundPoi.add(poiArrayList.get(i));
                }
                else if (longitude.contains(condition)) {
                    foundPoi.add(poiArrayList.get(i));
                }
                else if (!address.isEmpty()) {
                    if(address.contains(condition)) {
                        foundPoi.add(poiArrayList.get(i));
                    }
                }
                else if (borough != null) {
                    if (borough.toLowerCase().contains(condition)) {
                        foundPoi.add(poiArrayList.get(i));
                    }
                }
            }
        }
        return foundPoi;
    }

    
    /**
     * Filters through an array list of retailers and returns an array list of retailers 
     * that match the search condition.
     * @param retailerArrayList - ArrayList of retailers to be searched through
     * @param condition 		- The condition which is looked for in each retailer object
     * @return		 			- An ArrayList of matched retailer objects
     */
    public static ArrayList<Retailer> searchRetailer(ArrayList<Retailer> retailerArrayList, 
    		String condition) {
    	
        ArrayList<Retailer> foundRetailer = new ArrayList<Retailer>();
        String description = null;
        String product = null;
        String zip = null;
        String name = null;
        String latitude = null;
        String longitude = null;
        String address = null;
        String borough = null;
        condition = condition.toLowerCase();
        for (int i = 0; i < retailerArrayList.size(); i++) {
            if (retailerArrayList.get(i) != null) {
            	description = retailerArrayList.get(i).getDescription().toLowerCase();
            	product = retailerArrayList.get(i).getProduct().toLowerCase();
            	zip = Integer.toString(retailerArrayList.get(i).getZip());
            	name = retailerArrayList.get(i).getName().toLowerCase();
            	latitude = String.valueOf(retailerArrayList.get(i).getLatitude()).toLowerCase();
            	longitude = String.valueOf(retailerArrayList.get(i).getLongitude()).toLowerCase();
            	address = retailerArrayList.get(i).getAddress().toLowerCase();
                borough = retailerArrayList.get(i).getBorough();
            	if (description.contains(condition.toLowerCase())) {
                    foundRetailer.add(retailerArrayList.get(i));
                }
                else if (product.contains(condition)) {
                    foundRetailer.add(retailerArrayList.get(i));
                }
                else if (zip.contains(condition)) {
                    foundRetailer.add(retailerArrayList.get(i));
                }
                else if (name.contains(condition)) {            	
                    foundRetailer.add(retailerArrayList.get(i));
                }
                else if (latitude.contains(condition)) {             	
                    foundRetailer.add(retailerArrayList.get(i));
                }
                else if (longitude.contains(condition)) {               	
                    foundRetailer.add(retailerArrayList.get(i));
                }
                else if (!address.isEmpty()) {
                    if (address.contains(condition)) {                  	
                        foundRetailer.add(retailerArrayList.get(i));
                    }
                }
                else if (borough != null) {
                    if (borough.toLowerCase().contains(condition)) {
                        foundRetailer.add(retailerArrayList.get(i));
                    }
                }
            }
        }
        return foundRetailer;
    }

    
    /**
     * Filters through an array list of routes and returns an array list of routes that 
     * match the search condition.
     * @param routeArrayList ArrayList of routes to be searched through
     * @param condition The condition which is looked for in each route object
     * @return An ArrayList of matched route objects
     */
    public static ArrayList<Route> searchRoutes(ArrayList<Route> routeArrayList, String condition) { // TODO: how will we check for start and end location? In the table I have it displaying the name of the start and end locations, so could search this?
        ArrayList<Route> foundRoutes = new ArrayList<Route>();
        String name = null;
        String distance = null;
        String gender = null;
        String bikeID = null;
        String startName = null;
        String endName = null;
        condition = condition.toLowerCase();
        for (int i = 0; i < routeArrayList.size(); i++) {
            if (routeArrayList.get(i) != null) {
                name = routeArrayList.get(i).getName().toLowerCase();
                distance = String.valueOf(routeArrayList.get(i).getDistance()).toLowerCase();
                gender = routeArrayList.get(i).getGender().toLowerCase();
                bikeID = routeArrayList.get(i).getBikeID().toLowerCase();
                startName = routeArrayList.get(i).getStart().getName().toLowerCase();
                endName = routeArrayList.get(i).getEnd().getName().toLowerCase();
                if (name.contains(condition)) {
                    foundRoutes.add(routeArrayList.get(i));
                }
                else if (distance.contains(condition)) {
                    foundRoutes.add(routeArrayList.get(i));
                }
                else if (gender.contains(condition)) {
                    foundRoutes.add(routeArrayList.get(i));
                }
                else if (bikeID.contains(condition)) {
                    foundRoutes.add(routeArrayList.get(i));
                }
                else if (startName.contains(condition)) {
                    foundRoutes.add(routeArrayList.get(i));
                }
                else if (endName.contains(condition)) {
                    foundRoutes.add(routeArrayList.get(i));
                }
            }
        }
        return foundRoutes;
    }

    
    /**
     * Filters through an array list of wifi objects and returns an array list of wifi objects that match the search condition
     * @param wifiArrayList ArrayList of wifi objects to be searched through
     * @param condition The condition which is looked for in each wifi object
     * @return An ArrayList of matched wifi objects
     */
    public static ArrayList<Wifi> searchWifi (ArrayList<Wifi> wifiArrayList, String condition) {
        ArrayList<Wifi> foundWifi = new ArrayList<Wifi>();
        String provider = null;
        String type = null;
        String name = null;
        String latitude = null;
        String longitude = null;
        String ssid = null;
        String borough = null;
        condition = condition.toLowerCase();
        for (int i = 0; i < wifiArrayList.size(); i++) {
            if (wifiArrayList.get(i) != null) {
                provider = wifiArrayList.get(i).getProvider().toLowerCase();
                type = wifiArrayList.get(i).getType().toLowerCase();
                name = wifiArrayList.get(i).getName().toLowerCase();
                latitude = String.valueOf(wifiArrayList.get(i).getLatitude()).toLowerCase();
                longitude = String.valueOf(wifiArrayList.get(i).getLongitude()).toLowerCase();
                ssid = wifiArrayList.get(i).getSsid().toLowerCase();
                borough = wifiArrayList.get(i).getBorough();
                if (provider.contains(condition)) {
                    foundWifi.add(wifiArrayList.get(i));
                }
                else if(type.contains(condition)) {
                    foundWifi.add(wifiArrayList.get(i));
                }
                else if (name.contains(condition)) {
                    foundWifi.add(wifiArrayList.get(i));
                }
                else if (latitude.contains(condition)) {
                    foundWifi.add(wifiArrayList.get(i));
                }
                else if (longitude.contains(condition)) {
                    foundWifi.add(wifiArrayList.get(i));
                }
                else if (ssid.contains(condition)) {
                    foundWifi.add(wifiArrayList.get(i));
                }
                else if (borough != (null)) {
                    if (borough.toLowerCase().contains(condition)) {
                        foundWifi.add(wifiArrayList.get(i));
                    }
                }
            }
        }
        return foundWifi;
    }

    
    /**
     * Filter through an array of toilet objects and returns an array of toilet objects that match the search condition
     * @param toiletArrayList ArrayList of toilet objects to be search through
     * @param condition The condition which is looked for in each toilet object
     * @return An ArrayList of matched toilet objects
     */
    public static ArrayList<Toilet> searchToilets (ArrayList<Toilet> toiletArrayList, String condition) {
        ArrayList<Toilet> foundToilets = new ArrayList<Toilet>();
        String forDisabled = null;
        String uniSex = null;
        String name = null;
        String latitude = null;
        String longitude = null;
        condition = condition.toLowerCase();
		for (int i = 0; i < toiletArrayList.size(); i++) {
		    if (toiletArrayList.get(i) != null) {

                forDisabled = String.valueOf(toiletArrayList.get(i).getForDisabled()).toLowerCase();
                uniSex = String.valueOf(toiletArrayList.get(i).getForDisabled()).toLowerCase();
                name = toiletArrayList.get(i).getName().toLowerCase();
                latitude = String.valueOf(toiletArrayList.get(i).getLatitude()).toLowerCase();
                longitude = String.valueOf(toiletArrayList.get(i).getLongitude()).toLowerCase();

                if (forDisabled.contains(condition)) {
                    foundToilets.add(toiletArrayList.get(i));
                }
                else if (uniSex.contains(condition)) {
                    foundToilets.add(toiletArrayList.get(i));
                }
                else if (name.contains(condition)) {
                    foundToilets.add(toiletArrayList.get(i));
                }
                else if (latitude.contains(condition)) {
                    foundToilets.add(toiletArrayList.get(i));
                }
                else if (longitude.contains(condition)) {
                    foundToilets.add(toiletArrayList.get(i));
                }
            }
		}
        return foundToilets;
    }
}