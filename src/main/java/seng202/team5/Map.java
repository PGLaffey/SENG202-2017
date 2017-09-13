package seng202.team5;

import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.directions.*;

public class Map {
    private Route currentRoute;
    private Location currentLocation;
    private DirectionsService directionsServiceHandler = new DirectionsService();

    public static double getLattitude(String address) {
        DirectionsRequest request = new DirectionsRequest(address, address, TravelModes.BICYCLING);
        directionsServiceHandler.getRoute(request, this, new DirectionsRenderer());
        double lattitude = 0;
        return lattitude;
    }

    public static double getLongitude(String address) {

        double longitude = 0;
        return longitude;
    }

    public static double getDistance(double srcLat, double srcLong,
                                     double destLat, double destLong) {
        DirectionsRoute route = new DirectionsRoute();
        System.out.println(route.getLegs());
        DirectionsLeg dirLeg = route.getLegs().get(0);


        return dirLeg.getDistance().getValue();
    }

    public void loadRoute(Route route) {

    }

    public void loadLocation(Location location) {

    }

    public void applyFilter(String filter) {

    }
}
