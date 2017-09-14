package seng202.Model;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.directions.*;

public class Map implements DirectionsServiceCallback {
    private Route currentRoute;
    private Location currentLocation;
    private static DirectionsService directionsServiceHandler = new DirectionsService();
    private DirectionsResult result;
    private DirectionStatus status;

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        result = results;
        this.status = status;
    }

    public static double getLattitude(String address) {
        DirectionsRequest request = new DirectionsRequest(address, address, TravelModes.BICYCLING);
        //directionsServiceHandler.getRoute(request, this, new DirectionsRenderer());
        double lattitude = 0;
        return lattitude;
    }

    public static double getLongitude(String address) {

        double longitude = 0;
        return longitude;
    }

    public static double getDistance(double srcLat, double srcLong,
                                     double destLat, double destLong) {
        DirectionsRequest request = new DirectionsRequest(new LatLong(srcLat, srcLong),
                            new LatLong(destLat, destLong),
                TravelModes.BICYCLING);
        Map.findRoute(request);
        for (DirectionsRoute route : result.getRoutes()) {

        }
        DirectionsLeg dirLeg = new DirectionsLeg();

        return dirLeg.getDistance().getValue();
    }

    public void findRoute(DirectionsRequest request) {
        GoogleMapView mapView = new GoogleMapView();
        GoogleMap map = mapView.createMap();

        DirectionsPane dirPane = mapView.getDirec();
        directionsServiceHandler.getRoute(request, this, new DirectionsRenderer(true, map, dirPane));

    }

    public void loadLocation(Location location) {

    }

    public void applyFilter(String filter) {

    }
}
