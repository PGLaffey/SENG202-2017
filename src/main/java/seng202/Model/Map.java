package seng202.Model;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.DirectionsPane;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.service.directions.*;

public class Map implements DirectionsServiceCallback {
    protected Route currentRoute;
    protected Location currentLocation;
    protected DirectionsService directionsServiceHandler;
    protected DirectionsResult result;
    protected DirectionStatus status;
    protected LatLong start = new LatLong(-43.512390, 172.546751);
    protected LatLong end = new LatLong( -43.523538, 172.583923);

    public LatLong getStart() {
        return start;
    }

    public LatLong getEnd() {
        return end;
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status) {
        /*result = results;
        System.out.println(result);
        this.status = status;*/
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

    public double getDistance(double srcLat, double srcLong,
                                     double destLat, double destLong) {
        double distance = 0;
        LatLong start = new LatLong(srcLat, srcLong);
        LatLong end = new LatLong(destLat, destLong);
        DirectionsRequest request = new DirectionsRequest(start, end,
                TravelModes.BICYCLING);
        System.out.println("Request"+request);
        GoogleMapView mapView = new GoogleMapView();
        findRoute(request, mapView);
        for (DirectionsLeg dirLeg : result.getRoutes().get(0).getLegs()) {
            distance += dirLeg.getDistance().getValue();
        }

        return distance;
    }

    public double getDistance(LatLong start, LatLong end) {
        double distance = 0;
        DirectionsRequest request = new DirectionsRequest(start, end,
                TravelModes.BICYCLING);
        System.out.println("Request"+request);
        GoogleMapView mapView = new GoogleMapView();
        findRoute(request, mapView);
        for (DirectionsLeg dirLeg : result.getRoutes().get(0).getLegs()) {
            distance += dirLeg.getDistance().getValue();
        }

        return distance;
    }

    public void findRoute(DirectionsRequest request, GoogleMapView mapView) {
        GoogleMap map = mapView.createMap();

        DirectionsPane dirPane = mapView.getDirec();
        directionsServiceHandler.getRoute(request, this, new DirectionsRenderer(true, map, dirPane));
        directionsServiceHandler.processResponse(result, status);

    }

    public void loadLocation(Location location) {

    }

    public void applyFilter(String filter) {

    }

    public static void main(String[] argv){
        Map map = new Map();

        System.out.println(map.getDistance(map.getStart(), map.getEnd()));
    }
}
