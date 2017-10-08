package seng202.Model;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.Math.toRadians;

/**
 * Class to handle all the GoogleMapAPI services.
 */
public class Map {
	
    private Route currentRoute;
    private Location currentLocation;
    private static GeocodingService geoService;
    private static boolean retailerVisible = false;
    private static boolean routeStartVisible = false;
    private static boolean wifiVisible = false;
    private static boolean toiletVisible = false;
    private static boolean poiVisible = false;
    private static LatLong startLoc = null;
    private static LatLong endLoc = null;

    private static Marker startMarker = null;
    private static Marker endMarker = null;

    
    /**
     * Setter for if the retailers are visible on the map.
     * @param value - If the retailers are to be visible
     */
    public static void setRetailerVisible(boolean value) {
        retailerVisible = value;
    }

    
    /**
     * Getter for whether the retailers are visible on the map.
     * @return - If the retailers are visible
     */
    public static boolean getRetailerVisible() {
        return retailerVisible;
    }

    
    /**
     * Getter for whether the toilets are visible on the map.
     * @return - If the toilets are visible
     */
    public static boolean getToiletVisible() {
        return toiletVisible;
    }

    
    /**
     * Setter for if the toilets are visible on the map.
     * @param value - If the toilets are to be visible
     */
    public static void setToiletVisible(boolean value) {
        toiletVisible = value;
    }

    
    /**
     * Getter for whether the wifi are visible on the map.
     * @return - If the wifi are visible
     */
    public static boolean getWifiVisible() {
        return wifiVisible;
    }

    
    /**
     * Setter for if the wifi are visible on the map.
     * @param value - If the wifi are to be visible
     */
    public static void setWifiVisible(boolean value) {
        wifiVisible = value;
    }

    
    /**
     * Getter for whether the poi are visible on the map.
     * @return - If the poi are visible
     */
    public static boolean getPoiVisible() {
        return poiVisible;
    }

    
    /**
     * Setter for if the poi are visible on the map.
     * @param value - If the poi are to be visible
     */
    public static void setPoiVisible(boolean value) {
        poiVisible = value;
    }

    
    /**
     * Getter for whether the routes are visible on the map.
     * @return - If the routes are visible
     */
    public static boolean getRouteVisible() { 
    	return routeStartVisible; 
    }

    
    /**
     * Sets if the start of the routes are visible on the map.
     * @param value - If the start of the routes are to be visible
     */
    public static void setRouteStartVisible(boolean value) { 
    	routeStartVisible = value; 
    }

    
    /**
     * Getter for the start location on the map.
     * @return - The start location on the map as LatLong
     */
    public static LatLong getStartLoc() {
        return startLoc;
    }

    
    /**
     * Getter for the end location on the map.
     * @return - The end location on the map as LatLong
     */
    public static LatLong getEndLoc() {
        return endLoc;
    }

    
    /**
     * Repositions or creates the startMarker.
     * @param latLong 	- A LatLong object of the mouse position
     * @param map 		- A googleMap object to place the startMarker on
     */
    public static void setStartMarker(LatLong latLong, GoogleMap map) {
        startLoc = latLong;
        //Sets up the options for the startMarker
        MarkerOptions routeMarkerOptns = new MarkerOptions().animation(Animation.DROP)
                .visible(true)
                .title("Start")
                .position(startLoc);

        if (startMarker != null) {
            map.clearMarkers();
            map.removeMarker(startMarker);
            startMarker = new Marker(routeMarkerOptns);
            map.addMarker(startMarker);
        } 
        else {
            map.clearMarkers();
            startMarker = new Marker(routeMarkerOptns);
            map.addMarker(startMarker);
        }
    }

    
    /**
     * Resets the start marker on the map.
     */
    public static void resetStartMarker() {
        startMarker = null;
    }

    
    /**
     * Resets the end marker on the map.
     */
    public static void resetEndMarker() {
        endMarker = null;
    }


    /**
     * Repositions or sets the end marker.
     * @param latLong 	- A LatLong object of the mouse position
     * @param map 		- A googleMap object to place the startMarker on
     */
    public static void setEndMarker(LatLong latLong, GoogleMap map) {
        endLoc = latLong;
        MarkerOptions routeMarkerOptns = new MarkerOptions().animation(Animation.DROP)
                .visible(true)
                .title("End")
                .position(endLoc);

        if (endMarker != null) {
            map.removeMarker(endMarker);
            endMarker = new Marker(routeMarkerOptns);
            map.addMarker(endMarker);
        } 
        else {
            endMarker = new Marker(routeMarkerOptns);
            map.addMarker(endMarker);
        }
    }

    
    /**
     * Sends a GET-request to the google geocoding service and parses the returned JSON to find 
     * the latitude and longitude.
     * @param address   - Address to find
     * @return 			- An array of doubles containing the latitude and longitude
     */
    public static double[] getLatLong(String address) {
        double latitude = 0;
        double longitude = 0;
        for (Coord coord : CurrentStorage.getCoords()) {
            if (new Coord(address, 0, 0).equals(coord)) {
                double[] latLong = {coord.getLat(), coord.getLng()};
                return latLong;
            }
        }
        try {
            //Sets the HTTP request
            URL mapsUrl = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" 
            + address.replaceAll(" ", "%20") + ",%20New%20York&sensor=true");
            HttpURLConnection request = (HttpURLConnection) mapsUrl.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            // Parses the main objects of the JSON
            JsonParser jp = new JsonParser();
            JsonElement thing = jp.parse(new InputStreamReader(
            		(InputStream) request.getContent()));
            
            JsonObject thingObj = thing.getAsJsonObject();

            // Slowly works down the JSON to find the correct field.
            JsonObject resultsObj = thingObj.get("results").getAsJsonArray().get(0)
            		.getAsJsonObject();
            
            JsonObject geometry = resultsObj.getAsJsonObject("geometry");
            JsonObject location = geometry.getAsJsonObject("location");
            latitude = location.get("lat").getAsDouble();
            longitude = location.get("lng").getAsDouble();

        } 
        catch (Exception e){

            e.printStackTrace();
        }
        double[] latLong = {latitude, longitude};

        CurrentStorage.addCoords(new Coord(address, latitude, longitude));
        return latLong;
    }

    
    /** 
     * Calculates the displacement of two latitudes and longitudes by using the Haversine 
     * formula.
     * @param srcLat 	- Source Latitude
     * @param srcLong 	- Source longitude
     * @param destLat 	- Destination latitude
     * @param destLong 	- Destination longitude
     * @return 			- Displacement between the two points in metres
     */
    public static double getDistance(double srcLat, double srcLong, double destLat, 
    		double destLong) {
        double earthRadius = 6371e3; // metres
        double lat1 = toRadians(srcLat);
        double lat2 = toRadians(destLat);
        double phi = toRadians(destLat-srcLat);
        double delta = toRadians(destLong-srcLong);

        double a = (Math.sin(phi/2) * Math.sin(phi/2)) + Math.cos(lat1) * Math.cos(lat2) 
        		* (Math.sin(delta/2) * Math.sin(delta/2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;
        return distance;
    }

    
    /**
     * Overloaded distance method to calculate the displacement between two points via the 
     * Haversine formula.
     * @param start - Starting point of type LatLong
     * @param end 	- Ending point of type LatLong
     * @return 		- The displacement between the two points in metres
     */
    public static double getDistance(LatLong start, LatLong end) {
        return getDistance(start.getLatitude(), start.getLongitude(), end.getLatitude(), 
        		end.getLongitude());
    }

    
    /**
     * Locates an address on the maps in the application given a Location object.
     * @param givenLocation - A initialized Location object
     * @param map 			- The map to place the marker on
     */
    public static void findLocation(Location givenLocation, GoogleMap map) {
        String locationName = givenLocation.getName();
        LatLong latLong = new LatLong(givenLocation.getLatitude(), givenLocation.getLongitude());

        MarkerOptions markOptns = new MarkerOptions()
                .animation(Animation.DROP)
                .position(latLong)
                .title(locationName);

        map.addMarker(new Marker(markOptns));
        map.setCenter(latLong);
    }

    
    /**
     * An overloaded method to handle a lone address and display it as a marker on a GoogleMap 
     * object.
     * @param location 	- An address passed as a string
     * @param map 		- The map object to place the marker on
     * @param service 	- A GeocodingService from the GMapsFX API
     */
    public static void findLocation(String location, GoogleMap map, GeocodingService service) {
        //Obtains a geocode location around latLong
        service.geocode(location+", NY", (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                //parses the object returned by Google Maps APIs
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                        results[0].getGeometry().getLocation().getLongitude());
            } 
            else {
                //parses the object returned by Google Maps APIs
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                        results[0].getGeometry().getLocation().getLongitude());
            }
            //Create a new marker on the map to identify the location
            map.addMarker(new Marker(new MarkerOptions().animation(Animation.DROP)
            		.position(latLong)));
            // Centres the map on the marker
            map.setCenter(latLong);
            ArrayList<Location> nearby = findNearby(latLong.getLatitude(), latLong.getLongitude());
            for (Location loc : nearby) {
                if (loc.getLocationType() == 0) {
                    Map.findLocation(loc, map);
                } 
                else if (loc.getLocationType() == 1) {
                    Map.findPoi((Poi) loc, map);
                } 
                else if (loc.getLocationType() == 2) {
                    Map.findRetailers((Retailer) loc, map);
                } 
                else if (loc.getLocationType() == 3) {
                    Map.findWifi((Wifi) loc, map);
                }
            }
        });
    }

    
    /**
     * Method to find a route given two addresses.
     * @param startAddress 	- Starting address of the route
     * @param endAddress 	- Destination address of the route
     * @param mapView 		- The mapView for the renderer to draw on
     * @param service 		- The DirectionsService object used to obtain the route
     * @param callback 		- The callback to return the object to
     * @param pane 			- The DirectionsPane object used in the DirectionsRenderer for the 
     * 						  service
     */
    public static void findRoute(String startAddress, String endAddress, GoogleMapView mapView, 
    		DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane, 
    		DirectionsRenderer directionsRenderer) {
        // Creates a new directions request for the Google Maps API
        DirectionsRequest request = new DirectionsRequest(startAddress, endAddress, 
        		TravelModes.BICYCLING);

        // Obtains the result of the request using the Directions Service.
        service.getRoute(request, callback, directionsRenderer);
    }

    
    /**
     * Overloaded method to find a route given two locations.
     * @param startLoc 	- Starting location
     * @param endLoc 	- Destination
     * @param mapView 	- MapView to render the route on
     * @param service 	- DirectionsService Object to use
     * @param callback 	- Callback to return the results to
     * @param pane 		- Directions pane used for the directionsRenderer
     */
    public static void findRoute(Location startLoc, Location endLoc, GoogleMapView mapView, 
    		DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane, 
    		DirectionsRenderer renderer) {
        renderer.clearDirections();
        DirectionsRequest request = new DirectionsRequest(new LatLong(startLoc.getLatitude(), 
        		startLoc.getLongitude()), new LatLong(endLoc.getLatitude(), endLoc.getLongitude()),
        		TravelModes.BICYCLING);

        service.getRoute(request, callback, renderer);
    }

    
	/**
	 * Overloaded method to find a route given two LatLongs.
	 * @param startLat	- Start latitude
	 * @param startLong - Start longitude
	 * @param endLat	- End latitude
	 * @param endLong	- End longitude
     * @param mapView 	- MapView to render the route on
     * @param service 	- DirectionsService Object to use
     * @param callback 	- Callback to return the results to
     * @param pane 		- Directions pane used for the directionsRenderer
	 * @param renderer	- Associated DirectionsRenderer to display the route on
	 */
    public static void findRoute(double startLat, double startLong, double endLat, double endLong,
    		GoogleMapView mapView, DirectionsService service, DirectionsServiceCallback callback, 
    		DirectionsPane pane, DirectionsRenderer renderer) {

        DirectionsRequest request = new DirectionsRequest(startLat + "," + startLong , endLat + 
        		"," + endLong, TravelModes.BICYCLING);
        service.getRoute(request, callback, renderer);
    }

    
    /**
     * Finds a given route on the map.
     * @param route 	- The route to find
     * @param mapView 	- The mapView to render the route on
     * @param service 	- The DirectionsService to use
     * @param callback 	- The DirectionsServiceCallback to return the results to
     * @param pane 		- The directionsPane to use in the DirectionsRenderer
     */
    public static void findRoute(Route route, GoogleMapView mapView, DirectionsService service, 
    		DirectionsServiceCallback callback, DirectionsPane pane, DirectionsRenderer renderer) {

        DirectionsRequest request = new DirectionsRequest(route.getStart().getLatitude() + ", " 
        		+ route.getStart().getLongitude(), route.getEnd().getLatitude() + "," 
        		+ route.getEnd().getLongitude(), TravelModes.BICYCLING);

        service.getRoute(request, callback, renderer);
    }

    
    /**
     * Sets the pointer for the wifi location on the map.
     * @param wifi 	- Wifi object to use
     * @return 		- The location of the pointer on the map
     */
    public static void findWifi(Wifi wifi, GoogleMap map) {
        //Creates a new circle and places it on a map.
        MarkerOptions wifiMarkOptns = new MarkerOptions()
                .title(wifi.getSsid())
                .animation(Animation.DROP)
                .visible(true)
                .position(new LatLong(wifi.getLatitude(), wifi.getLongitude()))
                .icon("http://maps.google.com/mapfiles/ms/micons/blue-dot.png");
        Marker wifiMarker = new Marker(wifiMarkOptns);
        wifi.setMarker(wifiMarker);
        map.addMarker(wifiMarker);
    }

    
    /**
     * Sets marker for the toilets on the map.
     * @param toilet - Toilet object to find
     * @param map 	 - Map to place marker on
     */
    public static void findToilets(Toilet toilet, GoogleMap map) {
        MarkerOptions toiletMarkOptns = new MarkerOptions().animation(Animation.DROP)
                .position(new LatLong(toilet.getLatitude(), toilet.getLongitude()))
                .visible(true)
                .icon("http://maps.google.com/mapfiles/ms/micons/toilets.png");

        if (toilet.getAddress() == null) {
            toiletMarkOptns.title(toilet.getName() + "\n" + toilet.getLatitude() + ", "
            + toilet.getLongitude());
        }
        else {
            toiletMarkOptns.title(toilet.getName() + "\n" + toilet.getAddress());
        }
        Marker toiletMark = new Marker(toiletMarkOptns);
        toilet.setMarker(toiletMark);
        map.addMarker(toiletMark);
    }

    
    /**
     * Sets markers for the retailers into a cluster.
     * @param retailer - Retailer object to be used
     */
    public static void findRetailers(Retailer retailer, GoogleMap map) {
        //Obtain the position for the marker and convert into the format required.
        LatLong latLong = new LatLong(retailer.getLatitude(), retailer.getLongitude());

        // Set up the marker options
        MarkerOptions markerOptns = new MarkerOptions()
                .animation(Animation.DROP)
                .position(latLong)
                .title(retailer.getName())
                .visible(true)
                .icon("http://maps.google.com/mapfiles/kml/pal3/icon26.png");
                //^ Obtains the correct image for the marker.

        retailer.setMarker(new Marker(markerOptns));
        map.addMarker(retailer.getMarker());
    }

    
    /**
     * Method to find place of interest using the google maps API.
     * @param poi - Place of interest to find
     * @param map - Map to render the marker on
     */
    public static void findPoi(Poi poi, GoogleMap map) {
            LatLong latLong = new LatLong(poi.getLatitude(), poi.getLongitude());
            Marker marker = new Marker(new MarkerOptions()
                    .animation(Animation.DROP)
                    .position(latLong)
                    .icon("http://maps.google.com/mapfiles/kml/pal4/icon46.png"));

            poi.setMarker(marker);
            map.addMarker(poi.getMarker());
    }

    
    /**
     * Sets the route markers on the map.
     * @param route - Route object to be used
     * @param map 	- Map object to be used
     */
    public static void findRouteMarker(Route route, GoogleMap map) {
        if (route.getStartMarker() == null) {
            MarkerOptions startMarkOptns  = new MarkerOptions()
                    .position(new LatLong(route.getStart().getLatitude(), 
                    		route.getStart().getLongitude()))
                    .animation(Animation.DROP)
                    .title(route.getStartString())
                    .visible(routeStartVisible)
                    .icon("http://google.com/mapfiles/ms/micons/cycling.png");
            map.clearMarkers();
            route.setStartMarker(new Marker(startMarkOptns));

            MarkerOptions endMarkOptns = new MarkerOptions()
                    .position(new LatLong(route.getEnd().getLatitude(), 
                    		route.getEnd().getLongitude()))
                    .animation(Animation.DROP)
                    .title(route.getEndString())
                    .visible(false)
                    .icon("http://maps.google.com/mapsfiles/kml/pal2/icon13.png");

            route.setEndMarker(new Marker(endMarkOptns));

            map.addMarker(route.getStartMarker());
            map.addMarker(route.getEndMarker());
        }
    }

    
    /**
     * Method that finds every location near the locations for a route. Called by the 
     * directionsReceived method in MainScreenController.
     * @param locLat 	- Latitude of the location to find nearby places of
     * @param locLong 	- Longitude of the location to find nearby places of
     * @return 			- An arrayList of locations that are nearby
     */
    public static ArrayList<Location> findNearby(double locLat, double locLong) {
        ArrayList<Location> nearby = new ArrayList<Location>();

        // Loops through the retailers in the list and checks if the location is within 100 
        // metres of the retailer
        for (Retailer retailer : CurrentStorage.getRetailerArray()) {
            if (Map.getDistance(locLat, locLong,
                    retailer.getLatitude(), retailer.getLongitude() ) < 100) {
                nearby.add(retailer);
            }
        }

        // Loops through the wifi in the list and checks if the location is within 100 
        // metres of the wifi
        for (Wifi wifi : CurrentStorage.getWifiArray()) {
            if (Map.getDistance(locLat, locLong,
                    wifi.getLatitude(), wifi.getLongitude()) < 100) {
                nearby.add(wifi);
            }
        }

        // Loops through the POI in the list and checks if the location is within 100 
        // metres of the POI
        for (Poi poi : CurrentStorage.getPoiArray()) {
            if (Map.getDistance(locLat, locLong, poi.getLatitude(), poi.getLongitude()) < 100) {
                nearby.add(poi);
            }
        }

        // Loops through the toilets in the list and checks if the location is within 100 
        // metres of the toilet
        for (Toilet toilet : CurrentStorage.getToiletArray()) {
            if (Map.getDistance(toilet.getLatitude(), toilet.getLongitude(), locLat, locLong) 
            		< 100) {
                nearby.add(toilet);
            }
        }

        return nearby;
    }
}