package seng202.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
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
    private static LatLong startLoc = null;
    private static LatLong endLoc = null;

    private static Marker startMarker = null;
    private static Marker endMarker = null;

    /**
     * Sets the retailers to visible on the map
     */
    public static void setRetailerVisible(boolean value) {
        retailerVisible = value;
    }

    /**
     * Getter for whether the retailers are visible on the map
     */
    public static boolean getRetailerVisible() {
        return retailerVisible;
    }

    /**
     * Getter for whether the routes are visible on the map
     */
    public static boolean getRouteVisible() { return routeStartVisible; }

    /**
     * Sets the start of the route to visible on the map
     */
    public static void setRouteStartVisible(boolean value) { routeStartVisible = value; }

    /**
     * Getter for the start location on the map
     */
    public static LatLong getStartLoc() {
        return startLoc;
    }

    /**
     * Getter for the end location on the map
     */
    public static LatLong getEndLoc() {
        return endLoc;
    }

    /**
     * Repositions or creates the startMarker
     * @param latLong - A LatLong object of the mouse position
     * @param map - A googleMap object to place the startMarker on.
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
        } else {
            map.clearMarkers();
            startMarker = new Marker(routeMarkerOptns);
            map.addMarker(startMarker);
        }
    }

    /**
     * Repositions or sets the end marker
     * @param latLong - A LatLong object of the mouse position
     * @param map - A googleMap object to place the startMarker on.
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
        } else {
            endMarker = new Marker(routeMarkerOptns);
            map.addMarker(endMarker);
        }
    }

    /**
     * Sends a GET-request to the google geocoding service and parses the returned JSON to find the latitude and longitude.
     * @param address - Address to find.
     * @return An array of doubles containing the latitude and longitude.
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
            URL mapsUrl = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + address.replaceAll(" ", "%20") + "&sensor=true");
            HttpURLConnection request = (HttpURLConnection) mapsUrl.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            // Parses the main objects of the JSON
            JsonParser jp = new JsonParser();
            JsonElement thing = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject thingObj = thing.getAsJsonObject();

            // Slowly works down the JSON to find the correct field.
            JsonObject resultsObj = thingObj.get("results").getAsJsonArray().get(0).getAsJsonObject();
            JsonObject geometry = resultsObj.getAsJsonObject("geometry");
            JsonObject location = geometry.getAsJsonObject("location");
            latitude = location.get("lat").getAsDouble();
            longitude = location.get("lng").getAsDouble();

        } catch (Exception e){

            e.printStackTrace();
        }
        double[] latLong = {latitude, longitude};

        CurrentStorage.addCoords(new Coord(address, latitude, longitude));
        return latLong;
    }

    /** Calculates the displacement of two latitudes and longitudes by using the Haversine formula
     * @param srcLat - Source Latitude
     * @param srcLong - Source longitude
     * @param destLat - Destination latitude
     * @param destLong - Destination longitude
     * @return Displacement between the two points in metres.
     */
    public static double getDistance(double srcLat, double srcLong,
                                     double destLat, double destLong) {
        double earthRadius = 6371e3; // metres
        double lat1 = toRadians(srcLat);
        double lat2 = toRadians(destLat);
        double phi = toRadians(destLat-srcLat);
        double delta = toRadians(destLong-srcLong);

        double a = (Math.sin(phi/2) * Math.sin(phi/2)) + Math.cos(lat1) * Math.cos(lat2) * (Math.sin(delta/2) * Math.sin(delta/2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;
        return distance;
    }

    /**
     * Overloaded distance method to calculate the displacement between two points via the Haversine formula
     * @param start - Starting point of type LatLong
     * @param end - Ending point of type LatLong
     * @return - The displacement between the two points in metres.
     */
    public static double getDistance(LatLong start, LatLong end) {
        double srcLat = start.getLatitude();
        double srcLong = start.getLongitude();
        double destLat = end.getLatitude();
        double destLong = end.getLongitude();

        double earthRadius = 6371e3; // metres
        double lat1 = toRadians(srcLat);
        double lat2 = toRadians(destLat);
        double phi = toRadians(destLat-srcLat);
        double delta = toRadians(destLong-srcLong);

        double a = (Math.sin(phi/2) * Math.sin(phi/2)) + Math.cos(lat1) * Math.cos(lat2) * (Math.sin(delta/2) * Math.sin(delta/2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double distance = earthRadius * c;

        return distance;
    }

    /**
     * Locates an address on the maps in the application given a Location object.
     * @param givenLocation - A initialized Location object
     * @param map - The map to place the marker on
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
     * An overloaded method to handle a lone address and display it as a marker on a GoogleMap object
     * @param location - An address passed as a string
     * @param map - The map object to place the marker on.
     * @param service - A GeocodingService from the GMapsFX API
     */
    public static void findLocation(String location, GoogleMap map, GeocodingService service) {
        //Obtains a geocode location around latLong
        service.geocode(location, (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                alert.show();
                //parses the object returned by Google Maps APIs
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                        results[0].getGeometry().getLocation().getLongitude());
            } else {
                //parses the object returned by Google Maps APIs
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                        results[0].getGeometry().getLocation().getLongitude());
            }
            //Create a new marker on the map to identify the location
            map.addMarker(new Marker(new MarkerOptions()
                    .animation(Animation.DROP)
                    .position(latLong)));
            // Centres the map on the marker
            map.setCenter(latLong);
        });
    }

    /**
     * Method to find a route given two addresses
     * @param startAddress - Starting address of the route
     * @param endAddress - Destination address of the route.
     * @param mapView - The mapView for the renderer to draw on.
     * @param service - The DirectionsService object used to obtain the route
     * @param callback - The callback to return the object to.
     * @param pane - The DirectionsPane object used in the DirectionsRenderer for the service.
     */
    public static void findRoute(String startAddress, String endAddress, GoogleMapView mapView,
                          DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane, DirectionsRenderer directionsRenderer) {
        // Creates a new directions request for the Google Maps API
        DirectionsRequest request = new DirectionsRequest(startAddress, endAddress, TravelModes.BICYCLING);

        // Obtains the result of the request using the Directions Service.
        service.getRoute(request, callback, directionsRenderer);
    }

    /**
     * Overloaded method to find a route given two locations
     * @param startLoc - starting location
     * @param endLoc - Destination
     * @param mapView - MapView to render the route on.
     * @param service - DirectionsService Object to use/
     * @param callback - callback to return the results to
     * @param pane - Directions pane used for the directionsRenderer.
     */
    public static void findRoute(Location startLoc, Location endLoc, GoogleMapView mapView,
                          DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane) {

        DirectionsRequest request = new DirectionsRequest(new LatLong(startLoc.getLatitude(), startLoc.getLongitude()),
                new LatLong(endLoc.getLatitude(), endLoc.getLongitude()), TravelModes.BICYCLING);

        service.getRoute(request, callback, new DirectionsRenderer(true, mapView.getMap(), pane));
    }

    /**
     * Finds a given route on the map
     * @param route - The route to find
     * @param mapView - the mapView to render the route on.
     * @param service - The DirectionsService to use.
     * @param callback - The DirectionsServiceCallback to return the results to.
     * @param pane - The directionsPane to use in the DirectionsRenderer.
     */
    public static void findRoute(Route route, GoogleMapView mapView,
                          DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane) {

        DirectionsRequest request = new DirectionsRequest(new LatLong(route.getStart().getLatitude(), route.getStart().getLongitude()),
                new LatLong(route.getEnd().getLatitude(), route.getEnd().getLongitude()), TravelModes.BICYCLING);

        service.getRoute(request, callback, new DirectionsRenderer(true, mapView.getMap(), pane));
    }

    /**
     * Sets the pointer for the wifi location on the map
     * @param wifi Wifi object to use
     * @return the location of the pointer on the map
     */
    public static void findWifi(Wifi wifi, GoogleMap map) {
        //Creates a new circle and places it on a map.
        CircleOptions circleOptns = new CircleOptions()
                .center(new LatLong(wifi.getLatitude(), wifi.getLongitude()))
                .radius(70)
                .draggable(false)
                .clickable(false)
                .fillOpacity(0.01)
                .fillColor("Blue")
                .strokeColor("Blue")
                .strokeWeight(0.2);
        wifi.setCircle(new Circle(circleOptns));
        map.addMapShape(wifi.getCircle());

    }

    /**
     * Sets marker for the toilets on the map
     * @param toilet - Toilet object to find
     * @param map - map to place marker on
     */
    public static void findToilets(Toilet toilet, GoogleMap map) {
        MarkerOptions toiletMarkOptns = new MarkerOptions().animation(Animation.DROP)
                .label(toilet.getName() + "\n" + toilet.getAddress())
                .position(new LatLong(toilet.getLatitude(), toilet.getLongitude()))
                .visible(true);
        Marker toiletMark = new Marker(toiletMarkOptns);
        map.addMarker(toiletMark);
    }

    /**
     * Sets markers for the retailers on the map
     * @param retailer retailer object to be used
     */
    public static void findRetailers(Retailer retailer, GoogleMap map) {

        // Loops through a list of known addresses. This is to reduce the amount of requests that are made.
        if (retailer.getLatitude() == -91 || retailer.getLongitude() == -181) {
            for (Coord coord : CurrentStorage.getCoords()) {
                //TODO: check if the address of the new retailer has already been found.
                if (retailer.getAddress().equalsIgnoreCase(coord.getAddress()) ||
                        retailer.getAddress().toLowerCase().contains(coord.getAddress().toLowerCase()) ||
                        coord.getAddress().toLowerCase().contains(retailer.getAddress().toLowerCase())) {
                    retailer.setCoord(coord);
                    coord.getMarker().setTitle(coord.getMarker().getTitle() + "<br>" + retailer.getName());
                }
            }
        }

        if (retailer.getCoord() == null) {
            //Obtain the position for the marker and convert into the format required.
            LatLong latLong = new LatLong(retailer.getLatitude(), retailer.getLongitude());
            Coord newCoord = new Coord(retailer.getAddress(), retailer.getLatitude(), retailer.getLongitude());
            retailer.setCoord(newCoord);

            // Set up the marker options
            MarkerOptions markerOptns = new MarkerOptions()
                    .animation(Animation.DROP)
                    .position(latLong)
                    .title(retailer.getName())
                    .visible(retailerVisible) // sets it to false and inverts it.
                    .icon("http://maps.google.com/mapfiles/kml/pal3/icon26.png"); //Obtains the correct image for the marker.
            retailer.getCoord().setMarker(new Marker(markerOptns));

            retailer.getCoord().getMarker().setVisible(!retailerVisible);
            map.addMarker(retailer.getCoord().getMarker());
        } else {
            if (retailer.getCoord().getMarker().getVisible() == retailerVisible) {
                retailer.getCoord().getMarker().setVisible(!retailerVisible);
            }
        }

    }

    /**
     * Method to find place of interest using the google maps API
     * @param poi - Place of interest to find
     * @param map - Map to render the marker on.
     */
    public static void findPoi(Poi poi, GoogleMap map) {
        if (poi.getMarker() == null) {
                LatLong latLong = new LatLong(poi.getLatitude(), poi.getLongitude());
                Marker marker = new Marker(new MarkerOptions()
                        .animation(Animation.DROP)
                        .position(latLong)
                        .icon("http://labs.google.com/ridefinder/images/mm_20_yellow.png"));

                poi.setMarker(marker);
                map.addMarker(poi.getMarker());

                // Toggles the visibility of the place of interest.
                poi.getMarker().setVisible(!poi.getMarker().getVisible());

        } else {

            // If the place of interest already has a marker, then just toggle its visibility
            poi.getMarker().setVisible(!poi.getMarker().getVisible());
        }
    }

    /**
     * Sets the route markers on the map
     * @param route route object to be used
     * @param map map object to be used
     */
    public static void findRouteMarker(Route route, GoogleMap map) {
        if (route.getStartMarker() == null) {
            MarkerOptions startMarkOptns  = new MarkerOptions()
                    .position(new LatLong(route.getStart().getLatitude(), route.getStart().getLongitude()))
                    .animation(Animation.DROP)
                    .title(route.getStartString())
                    .visible(routeStartVisible)
                    .icon("http://google.com/mapfiles/ms/micons/cycling.png");
            map.clearMarkers();
            route.setStartMarker(new Marker(startMarkOptns));

            MarkerOptions endMarkOptns = new MarkerOptions()
                    .position(new LatLong(route.getEnd().getLatitude(), route.getEnd().getLongitude()))
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
     * Method that finds every location near the locations for a route. Called by the directionsReceived method in MainScreenController
     * @param locLat - Latitude of the location to find nearby places of.
     * @param locLong - longitude of the location to find nearby places of.
     * @return - An arrayList of locations that are nearby.
     */
    public static ArrayList<Location> findNearby(double locLat, double locLong) {
        ArrayList<Location> nearby = new ArrayList<Location>();

        // Loops through the retailers in the list and checks if the location is within 50 metres of the retailer
        for (Retailer retailer : CurrentStorage.getRetailerArray()) {
            if (Map.getDistance(locLat, locLong,
                    retailer.getLatitude(), retailer.getLongitude() ) < 50) {
                nearby.add(retailer);
            }
        }

        // Loops through the wifi in the list and checks if the location is within 50 metres of the wifi
        for (Wifi wifi : CurrentStorage.getWifiArray()) {
            if (Map.getDistance(locLat, locLong,
                    wifi.getLatitude(), wifi.getLongitude()) < 50) {
                nearby.add(wifi);
            }
        }


        // Loops through the POI in the list and checks if the location is within 50 metres of the POI
        for (Poi poi : CurrentStorage.getPoiArray()) {
            if (Map.getDistance(locLat, locLong, poi.getLatitude(), poi.getLongitude()) < 50) {
                nearby.add(poi);
            }
        }

        // Loops through the toilets in the list and checks if the location is within 50 metres of the toilet
        for (Toilet toilet : CurrentStorage.getToiletArray()) {
            if (Map.getDistance(toilet.getLatitude(), toilet.getLongitude(), locLat, locLong) < 50) {
                nearby.add(toilet);
            }
        }

        return nearby;
    }

}