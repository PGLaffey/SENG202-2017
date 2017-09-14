package seng202.Model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.DirectionsRequest;
import com.lynden.gmapsfx.service.geocoding.GeocoderStatus;
import com.lynden.gmapsfx.service.geocoding.GeocodingResult;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.scene.control.Alert;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
     * @return - The distance between the two points in metres.
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

    public static void findLocation(Location location, GoogleMap map, GeocodingService service) {
        String locationName = location.getName();
        int locationType = location.getLocationType();

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

            switch(locationType) {
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
            }
            map.addMarker(new Marker(markOptns));
            map.setCenter(latLong);Anyone remember the location type numbers?
        });
    }

    public static void findLocation(String location, GoogleMap map, GeocodingService service) {
        //Obtains a geocode location around latLong
        service.geocode(location, (GeocodingResult[] results, GeocoderStatus status) -> {
            LatLong latLong = null;

            if (status == GeocoderStatus.ZERO_RESULTS) {Anyone remember the location type numbers?
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


    public static void main(String[] argv){
        System.out.println(Map.getDistance(-43.512390, 172.546751,-43.523538, 172.583923));
        System.out.println(Map.getLatitude("University of Canterbury"));
        System.out.println(Map.getLongitude("University of Canterbury"));
    }
}
