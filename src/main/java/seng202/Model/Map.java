package seng202.Model;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.directions.*;

public class Map{
    private Route currentRoute;
    private Location currentLocation;

    public static double getLattitude(String address) {
        DirectionsRequest request = new DirectionsRequest(address, address, TravelModes.BICYCLING);
        double lattitude = 0;
        return lattitude;
    }

    public static double getLongitude(String address) {

        double longitude = 0;
        return longitude;
    }

    public static double getDistance(double srcLat, double srcLong,
                                     double destLat, double destLong) {
        LatLong start = new LatLong(srcLat, srcLong);
        LatLong end = new LatLong(destLat, destLong);
        return start.distanceFrom(end);
    }

    public void getDistance(LatLong start, LatLong end) {

    }

    public void findRoute(DirectionsRequest request, GoogleMapView mapView) {

    }

    public void loadLocation(Location location) {

    }

    public void applyFilter(String filter) {

    }

    public static void main(String[] argv){
        System.out.println(Map.getDistance(10, 10, 10.1, 10.1));
    }
}
