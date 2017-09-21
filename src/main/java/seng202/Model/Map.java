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

import static java.lang.Math.toRadians;

public class Map {
    private Route currentRoute;
    private Location currentLocation;
    private static GeocodingService geoService;
    private static boolean retailerVisible = false;

    public static void setRetailerVisible(boolean value) {
        retailerVisible = value;
    }

    public static boolean getRetailerVisible() {
        return retailerVisible;
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
            URL mapsUrl = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=\"" + address.replaceAll(" ", "%20") + ",%20NY\"&sensor=true");
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
                    .position(latLong);

            switch(givenLocation.getLocationType()) {
                case 0:
                    markOptns.icon("http://google.com/mapfiles/ms/micons/green-dot");
                    break;
                //case 2:
                    //markOptns.icon("http://google.com/mapfiles/ms/micons/green-dot");
                    //break;
                case 3:
                    markOptns.icon("http://google.com/mapfiles/ms/micons/green-dot");
                    break;
                default:
                    break;
            }
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
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                        results[0].getGeometry().getLocation().getLongitude());
            } else {
                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
                        results[0].getGeometry().getLocation().getLongitude());
            }
            map.addMarker(new Marker(new MarkerOptions()
                    .animation(Animation.DROP)
                    .position(latLong)));
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
    public void findRoute(String startAddress, String endAddress, GoogleMapView mapView,
                          DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane) {
        DirectionsRequest request = new DirectionsRequest(startAddress, endAddress, TravelModes.BICYCLING);
        service.getRoute(request, callback, new DirectionsRenderer(true, mapView.getMap(), pane));
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
    public void findRoute(Location startLoc, Location endLoc, GoogleMapView mapView,
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
    public void findRoute(Route route, GoogleMapView mapView,
                          DirectionsService service, DirectionsServiceCallback callback, DirectionsPane pane) {

        DirectionsRequest request = new DirectionsRequest(new LatLong(route.getStart().getLatitude(), route.getStart().getLongitude()),
                new LatLong(route.getEnd().getLatitude(), route.getEnd().getLongitude()), TravelModes.BICYCLING);

        service.getRoute(request, callback, new DirectionsRenderer(true, mapView.getMap(), pane));
    }

    public static Circle findWifi(Wifi wifi) {

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
        return wifi.getCircle();
            //map.addMapShape(wifi.getCircle());
            /*MarkerOptions markOptns = new MarkerOptions()
                    .position(new LatLong(wifi.getLatitude(), wifi.getLongitude()))
                    .animation(Animation.DROP)
                    .title(wifi.getName())
                    .visible(false)
                    .icon("http://labs.google.com/ridefinder/images/mm_20_blue.png");
            wifi.setCircle(new Marker(markOptns));
            wifi.getCircle().setVisible(!wifi.getCircle().getVisible());
            map.addMarker(wifi.getCircle());*/

    }

    public static Marker findRetailers(Retailer retailer) {

        //Checks if there is already a marker on the same location
        for (Coord coord : CurrentStorage.getCoords()) {
            if (retailer.getAddress().equalsIgnoreCase(coord.getAddress())) {
                if (coord.hasMarker()) {
                    retailer.setNoMarker(true);
                    retailer.setCoord(coord);
                }
            }
        }

        //Obtain the position for the marker and convert into the format required.
        LatLong latLong = new LatLong(retailer.getLatitude(), retailer.getLongitude());

        // Set up the marker options
        MarkerOptions markerOptns = new MarkerOptions()
                .animation(Animation.DROP)
                .position(latLong)
                .title(retailer.toString())
                .visible(retailerVisible) // sets it to false and inverts it.
                .icon("http://maps.google.com/mapfiles/kml/pal3/icon26.png"); //Obtains the correct image for the marker.
        retailer.setMarker(new Marker(markerOptns));

        retailer.getMarker().setVisible(!retailerVisible);

        // Sets the corresponding coord so that it has a marker.
        for (Coord coord : CurrentStorage.getCoords()) {
            if (retailer.getAddress().equalsIgnoreCase(coord.getAddress())) {
                retailer.setCoord(coord);
                coord.setHasMarker(true);
            }
        }

        return retailer.getMarker();
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
                poi.getMarker().setVisible(!poi.getMarker().getVisible());

        } else {
            poi.getMarker().setVisible(!poi.getMarker().getVisible());
        }
    }

}