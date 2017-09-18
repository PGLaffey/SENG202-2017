package seng202.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;
import javafx.scene.control.Alert;
import netscape.javascript.JSObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.DirectoryNotEmptyException;

import static java.lang.Math.toRadians;

public class Map{
    private Route currentRoute;
    private Location currentLocation;
    private static GeocodingService geoService;


    public static double getLatitude(String address) {
        double latitude = 0;
        address = address.replaceAll(" ", "%20");
        try {
            URL mapsUrl = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=true");
            HttpURLConnection request = (HttpURLConnection) mapsUrl.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement thing = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject thingObj = thing.getAsJsonObject();

            JsonObject resultsObj = thingObj.get("results").getAsJsonArray().get(0).getAsJsonObject();
            JsonObject geometry = resultsObj.getAsJsonObject("geometry");
            JsonObject location = geometry.getAsJsonObject("location");
            latitude = location.get("lat").getAsDouble();

        } catch (Exception e){
            e.printStackTrace();
        }
        return latitude;
    }

    public static double getLongitude(String address) {

        double longitude = 0;
        address = address.replaceAll(" ", "%20");
        try {
            URL mapsUrl = new URL("http://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&sensor=true");
            HttpURLConnection request = (HttpURLConnection) mapsUrl.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement thing = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject thingObj = thing.getAsJsonObject();

            JsonObject resultsObj = thingObj.get("results").getAsJsonArray().get(0).getAsJsonObject();
            JsonObject geometry = resultsObj.getAsJsonObject("geometry");
            JsonObject location = geometry.getAsJsonObject("location");
            longitude = location.get("lng").getAsDouble();

        } catch (Exception e){
            e.printStackTrace();
        }
        return longitude;
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

    public static void findRoute(DirectionsRequest request, GoogleMapView mapView) {

    }

    /**
     * Locates an address on the maps in the application given a Location object.
     * @param givenLocation - A initialized Location object
     * @param map - The map to place the marker on
     * @param service - A GeocodingService from the GMapsFX API
     */
    public static void findLocation(Location givenLocation, GoogleMap map, GeocodingService service) {
        String locationName = givenLocation.getName();

        service.geocode(locationName, (GeocodingResult[] results, GeocoderStatus status) -> {
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

            MarkerOptions markOptns = new MarkerOptions()
                    .animation(Animation.DROP)
                    .position(latLong);
/*
            switch(givenLocation.getLocationType()) {
                case 0:
                    markOptns.icon(seng202.Model.Map.class.getResource("/images/toiletIcon.png").getPath());
                    break;
                case 2:
                    markOptns.icon(seng202.Model.Map.class.getResource("/images/retailIcon.png").getPath());
                    break;
                case 3:
                    markOptns.icon(seng202.Model.Map.class.getResource("/images/wifiIcon.png").getPath());
                    break;
                default:
                    break;
            }*/
            map.addMarker(new Marker(markOptns));
            map.setCenter(latLong);
        });
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
                    //.icon(seng202.Model.Map.class.getResource("/images/wifiIcon.png").getFile())));
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

    public static void findWifi(Wifi wifi, GoogleMap map) {
        CircleOptions circleOptns = new CircleOptions()
                .center(new LatLong(wifi.getLatitude(), wifi.getLongitude()))
                .radius(30)
                .draggable(false)
                .clickable(true)
                .fillOpacity(0.1)
                .fillColor("Blue")
                .strokeColor("Blue")
                .strokeWeight(0.2);
        Circle circle = new Circle(circleOptns);

        InfoWindowOptions cWindowOptns = new InfoWindowOptions().content("HelloWorld");
        InfoWindow cWindow = new InfoWindow(cWindowOptns);

        MarkerOptions cOptns = new MarkerOptions().position(circle.getCenter()).visible(false);
        Marker cCentre = new Marker(cOptns);
        cWindow.open(map, cCentre);

        map.addUIEventHandler(circle, UIEventType.click, (JSObject obj) -> {
            cCentre.setVisible(!cCentre.getVisible());
        });

        map.addMapShape(circle);
    }

    public static void main(String[] argv){
        System.out.println(Map.getDistance(-43.512390, 172.546751,-43.523538, 172.583923));
        System.out.println(Map.getLatitude("University of Canterbury"));
        System.out.println(Map.getLongitude("University of Canterbury"));
        Location loc = new Retailer("3 New York Plaza", "Test", "Thing", "BEGONE THOT", 4);
        System.out.println(loc.getLatitude());
        System.out.println(loc.getLongitude());
        System.out.println(seng202.Model.Map.class.getResource("/images/wifiIcon.png").getFile());
    }
}