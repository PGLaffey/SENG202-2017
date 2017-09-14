package seng202.Model;

import com.google.appengine.api.search.GeoPoint;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;

import static java.lang.Math.toRadians;

public class Map{
    private Route currentRoute;
    private Location currentLocation;
    private static GeocodingService geoService;


    public static double getLattitude(String address) {
        double latitude = 0;
        GeoPoint locationPoint = null;
        //Converting spaces into '%20' for URLs
        String locationAddress = address.replaceAll(" ", "%20");
        String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+locationAddress+"&sensor=true";

        //String ss = readWebService(url);

        return latitude;
    }

    public static double getLongitude(String address) {

        double longitude = 0;
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

    public void findRoute(DirectionsRequest request, GoogleMapView mapView) {

    }

    public void loadLocation(Location location) {

    }

    public void applyFilter(String filter) {

    }

    public static void main(String[] argv){
        System.out.println(Map.getDistance(-43.512390, 172.546751,-43.523538, 172.583923));
        System.out.println(Map.getLattitude("2 Brockhall Lane"));
    }
}
