package seng202.Controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Map;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MainScreenController implements MapComponentInitializedListener, DirectionsServiceCallback{

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected InfoWindow infoWindow;

    @FXML
    private GoogleMapView mapView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private AnchorPane addLocationsPane;

    @FXML
    private AnchorPane mainMapPane;

    @FXML
    private AnchorPane randomRoutePane;

    @FXML
    private Button accountButton;
    
    @FXML
    private Button tableButton;

    @FXML
    private Button addLocationsButton;

    @FXML
    private Button bikeIconButton;

    @FXML
    private Button showRouteButton;

    @FXML
    private Button favouriteIconButton;

    @FXML
    private Button loadRouteButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button mapButton;

    @FXML
    private Button randomRouteButton;

    @FXML
    private Button retailerIconButton;

    @FXML
    private Button saveRouteButton;

    @FXML
    private TextField searchText;

    @FXML
    private Button shareButton;

    @FXML
    private Button shareIconButton;

    @FXML
    private Button statButton;

    @FXML
    private Button toiletIconButton;

    @FXML
    private Button wifiIconButton;
    
    @FXML
    private Button saveLocationButton;
    
    @FXML
    private MenuButton loadRouteMenu;
    
    @FXML
    private Menu favouriteRoutesMenu;
    
    @FXML
    private Menu savedRoutesMenu;
    
    @FXML
    private TextField locationNameText;

    @FXML
    private ChoiceBox<String> locationTypeBox;

    private GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();
    
    
    /** 
     * Method for when the map menu button is pressed, shows the main map screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void mapPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) mapButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    /**
     * Method for when the table menu button is pressed, shows the raw tables screen.
     * @param event
     * @throws IOException
     */
	@FXML
    void tablePressed(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) tableButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/TablesScreen.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Table");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
	/**
	 * Method for when the statistics menu button is pressed, shows the statistics screen.
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void statPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) statButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
		
		primaryStage.setTitle("Statistics");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }


    /** 
     * Method for when the account menu button is pressed, shows the account screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void accountPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) accountButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
		
		primaryStage.setTitle("Profile");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }
    
    /**
     * Method for when logout button is pressed, shows the login screen and flushes current storage.
     * @param event
     * @throws IOException
     */
    @FXML
    void logoutPressed(ActionEvent event) throws IOException {
    	CurrentStorage.flush();
    	
    	Stage primaryStage = (Stage) logoutButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		primaryStage.setTitle("Login");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }
    
    @FXML
    void showRoutePressed(ActionEvent event) {
    }
    
    @FXML
    void loadRoutePressed(ActionEvent event) {
    }

    @FXML
    void randomRoutePressed(ActionEvent event) {
    	randomRoutePane.setVisible(true);
    	mainMapPane.setVisible(false);
    	addLocationsPane.setVisible(false);
    }

    @FXML
    void addLocationsPressed(ActionEvent event) {
    	addLocationsPane.setVisible(true);
    	mainMapPane.setVisible(false);
    	randomRoutePane.setVisible(false);
    }
    
    /**
     * Method for when the share button pressed. Shows the pop up.
     * @param event
     * @throws IOException
     */
    @FXML
    void sharePressed(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	Parent root = FXMLLoader.load(getClass().getResource("/ShareRouteScreen.fxml"));

    	stage.setTitle("Share route");
    	stage.setScene(new Scene(root));
    	stage.show();
    }

    @FXML
    void bikeIconPressed(ActionEvent event) {
    }

    @FXML
    void favouriteIconPressed(ActionEvent event) {
    }

    @FXML
    void retailerIconPressed(ActionEvent event) {
    }

    /** 
     * Method for when the save route button is pressed, open the pop up.
     * @param event
     * @throws IOException
     */
    @FXML
    void saveRouteButtonPressed(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/SaveRouteScreen.fxml"));
		
		Scene scene = new Scene(root); 
		stage.setTitle("Save Route");
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    void saveLocationButtonPressed(ActionEvent event) throws IOException {
    	saveLocationButton.setText("Saved");
    }

    @FXML
    void toiletIconPressed(ActionEvent event) {
    }

    @FXML
    void wifiIconPressed(ActionEvent event) {
    }

    @FXML
    public void searchTextAction(ActionEvent event) {
        //Obtains a geocode location around latLong
//        geocodingService.geocode(address.get(), (GeocodingResult[] results, GeocoderStatus status) -> {
//            LatLong latLong = null;
//
//            if (status == GeocoderStatus.ZERO_RESULTS) {
//                Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
//                alert.show();
//                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
//                        results[0].getGeometry().getLocation().getLongitude());
//            } else {
//                latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(),
//                        results[0].getGeometry().getLocation().getLongitude());
//            }
//            map.addMarker(new Marker(new MarkerOptions()
//                    .animation(Animation.DROP)
//                    .position(latLong)));
//            map.setCenter(latLong);
//        });
        Map.findLocation(address.get(), map, geocodingService);
        DirectionsRequest request = new DirectionsRequest("2 brockhall lane", "University of Canterbury", TravelModes.DRIVING);
        directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
    }
    @FXML
    void initialize() {
    	ObservableList<String> locationTypes = FXCollections.observableArrayList("Wifi hotspot","Retailer","Toilets", "Point of interest", "Other");
        mapView.addMapInializedListener(this);
        address.bind(searchText.textProperty());

    	loadRouteMenu.setPopupSide(Side.RIGHT);
    	locationTypeBox.setItems(locationTypes);
    	addLocationsPane.setVisible(false);
    	mainMapPane.setVisible(true);
    	randomRoutePane.setVisible(false);

        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert addLocationsButton != null : "fx:id=\"addLocationsButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert addLocationsPane != null : "fx:id=\"addLocationsPane\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert bikeIconButton != null : "fx:id=\"bikeIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert favouriteIconButton != null : "fx:id=\"favouriteIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert favouriteRoutesMenu != null : "fx:id=\"favouriteRoutesMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert loadRouteMenu != null : "fx:id=\"loadRouteMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert locationNameText != null : "fx:id=\"locationNameText\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert locationTypeBox != null : "fx:id=\"locationTypeBox\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert mainMapPane != null : "fx:id=\"mainMapPane\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert randomRouteButton != null : "fx:id=\"randomRouteButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert randomRoutePane != null : "fx:id=\"randomRoutePane\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert retailerIconButton != null : "fx:id=\"retailerIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert saveLocationButton != null : "fx:id=\"saveLocationButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert saveRouteButton != null : "fx:id=\"saveRouteButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert savedRoutesMenu != null : "fx:id=\"savedRoutesMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert searchText != null : "fx:id=\"searchText\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert shareButton != null : "fx:id=\"shareButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert shareIconButton != null : "fx:id=\"shareIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert showRouteButton != null : "fx:id=\"showRouteButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert statButton != null : "fx:id=\"statButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert toiletIconButton != null : "fx:id=\"toiletIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert wifiIconButton != null : "fx:id=\"wifiIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";


    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(40.6971494, -74.2598728))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(false)
                .zoomControl(true)
                .zoom(12);

        map = mapView.createMap(mapOptions);
        directionsService = new DirectionsService();
        directionsPane = mapView.getDirec();
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status){
        infoWindow = new InfoWindow();
        Double totalDistance = 0.0;
        int step = results.getRoutes().get(0).getLegs().get(0).getSteps().size() / 2;
        for (DirectionsLeg leg : results.getRoutes().get(0).getLegs()) {
            totalDistance += leg.getDistance().getValue();
        }
        infoWindow.setContent(totalDistance.toString() + "km\n" + results.getRoutes().get(0).getLegs().get(0).getDuration().getText()+" minutes");


        infoWindow.setPosition(results.getRoutes().get(0).getLegs().get(0).getSteps().get(step).getEndLocation());
        infoWindow.open(map);
    }
}
