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
import org.omg.CORBA.Current;
import seng202.Model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class MainScreenController implements MapComponentInitializedListener, DirectionsServiceCallback{

    protected DirectionsService directionsService;
    protected DirectionsPane directionsPane;
    protected InfoWindow infoWindow;
    protected Marker currentMarker;
    
    private GoogleMap map;

    private GeocodingService geocodingService;

    private StringProperty address = new SimpleStringProperty();

    @FXML
    private GoogleMapView mapView;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    //Top menu bar
        
    @FXML
    private Button mapButton;
    
    @FXML
    private Button tableButton;
    
    @FXML
    private Button statButton;
    
    @FXML
    private Button accountButton;
    
    @FXML
    private Button logoutButton;
    
    //Side menu bar
    
    @FXML
    private Button showRouteButton;
    
    @FXML
    private MenuButton loadRouteMenu;
    
    @FXML
    private Menu favouriteRoutesMenu;
    
    @FXML
    private Menu savedRoutesMenu;
    
    @FXML
    private Button randomRouteButton;  
    
    @FXML
    private MenuButton addLocationsMenu;

    @FXML
    private MenuItem addOtherMenuButton;

    @FXML
    private MenuItem addPoiMenuButton;

    @FXML
    private MenuItem addRetailerMenuButton;

    @FXML 
    private MenuItem addToiletMenuButton;

    @FXML
    private MenuItem addWifiMenuButton;
    
    @FXML
    private Button shareButton;
    
    
    
    
    //Main map pane
    
    @FXML
    private AnchorPane mainMapPane;
    
    @FXML
    private Button bikeIconButton;
    
    @FXML
    private Button favouriteIconButton;
    
    @FXML
    private Button retailerIconButton;
    
    @FXML
    private Button shareIconButton;

    @FXML
    private Button toiletIconButton;

    @FXML
    private Button wifiIconButton;
    
    @FXML
    private Button saveRouteButton;
    
    @FXML
    private TextField searchText;
    
    //Random route pane
    
    @FXML
    private AnchorPane randomRoutePane;
    
    //Adding wifi pane
    
    @FXML
    private AnchorPane addWifiPane;
    
    @FXML
    private Button saveWifiButton;
    
    @FXML
    private TextField wifiAddressText;

    @FXML
    private TextField wifiBoroughText;

    @FXML
    private TextField wifiLatText;

    @FXML
    private TextField wifiLongText;

    @FXML
    private TextField wifiNameText;

    @FXML
    private TextField wifiProviderText;

    @FXML
    private TextField wifiTypeText;

    @FXML
    private TextField wifiZipText;
    
    //Adding poi pane
    
    @FXML
    private AnchorPane addPoiPane;
    
    @FXML
    private TextField poiAddressText;

    @FXML
    private TextField poiBoroughText;

    @FXML
    private TextField poiCostText;

    @FXML
    private TextArea poiDescriptionText;

    @FXML
    private TextField poiLatText;

    @FXML
    private TextField poiLongText;

    @FXML
    private TextField poiNameText;

    @FXML
    private TextField poiZipText;
    
    //Adding toilet pane
    
    @FXML
    private AnchorPane addToiletPane;

    @FXML
    private TextField toiletAddressText;

    @FXML
    private TextField toiletBoroughText;

    @FXML
    private ChoiceBox<?> toiletDisabledChoice;

    @FXML
    private TextField toiletLatText;

    @FXML
    private TextField toiletLongText;

    @FXML
    private TextField toiletNameText;

    @FXML
    private ChoiceBox<?> toiletUnisexChoice;

    @FXML
    private TextField toiletZipText;
    
    //Adding retailer pane
    
    @FXML
    private AnchorPane addRetailerPane;
    
    @FXML
    private TextField retailerAddressText;

    @FXML
    private TextField retailerBoroughText;

    @FXML
    private TextArea retailerDescriptionText;

    @FXML
    private TextField retailerLatText;

    @FXML
    private TextField retailerLongText;

    @FXML
    private TextField retailerNameText;

    @FXML
    private TextField retailerProductText;

    @FXML
    private TextField retailerZipText;
    
    //Adding other pane
    
    @FXML
    private AnchorPane addOtherPane;
    
    @FXML
    private TextField otherAddressText;

    @FXML
    private TextField otherBoroughText;

    @FXML
    private TextField otherLatText;

    @FXML
    private TextField otherLongText;

    @FXML
    private TextField otherNameText;

    @FXML
    private TextField otherZipText;


    
    
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
    	addWifiPane.setVisible(false);
    }

    @FXML
    void addLocationsPressed(ActionEvent event) {
    	addWifiPane.setVisible(true);
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
        for (Route route: CurrentStorage.getRouteArray()) {
            placeMarkerOnMap(route.getStart());
        }
    }

    @FXML
    void favouriteIconPressed(ActionEvent event) {
    }

    @FXML
    void retailerIconPressed(ActionEvent event) {
        for (Retailer retailer : CurrentStorage.getRetailerArray()) {
            placeRetailerOnMap(retailer);
            try {
                Thread.sleep(451);
            } catch (InterruptedException e) {
                continue;
            }
        }
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
    void toiletIconPressed(ActionEvent event) {
        ArrayList<Toilet> toilets = new ArrayList<Toilet>();
    }

    @FXML
    void wifiIconPressed(ActionEvent event) {
        for (Wifi wifi : CurrentStorage.getWifiArray()) {
            placeCircleOnMap(wifi);
        }
    }

    @FXML
    public void searchTextAction(ActionEvent event) {
        //Obtains a geocode location around latLong
        map.clearMarkers();
        Map.findLocation(address.get(), map, geocodingService);
    }
    
    @FXML
    void addOtherMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(true);
    	randomRoutePane.setVisible(false);
    }

    @FXML
    void addPoiMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(true);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    	randomRoutePane.setVisible(false);
    }

    @FXML
    void addRetailerMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(true);
    	addOtherPane.setVisible(false);
    	randomRoutePane.setVisible(false);
    }

    @FXML
    void addToiletMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(true);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    	randomRoutePane.setVisible(false);
    }

    @FXML
    void addWifiMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(true);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    	randomRoutePane.setVisible(false);
    }
    
    @FXML
    void saveOtherButtonPressed(ActionEvent event) {
    }

    @FXML
    void savePoiButtonPressed(ActionEvent event) {
    	if (!poiAddressText.getText().isEmpty()) {
    		Poi poi = new Poi(poiAddressText.getText(), poiNameText.getText(), poiDescriptionText.getText(), Double.parseDouble(poiCostText.getText()));
    		//CurrentStorage.addNewPoi(poi);
    	} else {
    		Poi poi = new Poi(Double.parseDouble(poiLatText.getText()), Double.parseDouble(poiLongText.getText()), poiNameText.getText(), poiDescriptionText.getText(), Double.parseDouble(poiCostText.getText()));
    		//CurrentStorage.addNewPoi(poi);
    	}
    }

    @FXML
    void saveRetailerButtonPressed(ActionEvent event) {
    	if (!retailerAddressText.getText().isEmpty()) {
    		Retailer retailer = new Retailer(retailerAddressText.getText(), retailerNameText.getText(), retailerProductText.getText(), retailerDescriptionText.getText(), Integer.parseInt(retailerZipText.getText()));
        	CurrentStorage.addNewRetailer(retailer);
        } else {
        	Retailer retailer = new Retailer(Double.parseDouble(retailerLatText.getText()), Double.parseDouble(retailerLongText.getText()), retailerNameText.getText(), retailerProductText.getText(), retailerDescriptionText.getText(), Integer.parseInt(retailerZipText.getText()));
        	CurrentStorage.addNewRetailer(retailer);

    	}
    }

    @FXML
    void saveToiletButtonPressed(ActionEvent event) {
    }

    @FXML
    void saveWifiButtonPressed(ActionEvent event) {
    	if (!wifiAddressText.getText().isEmpty()) {
        	//Wifi wifi = new Wifi(wifiAddressText.getText(), wifiNameText.getText(), wifiBoroughText.getText(), wifiTypeText.getText(), wifiProviderText.getText());
        	//CurrentStorage.addNewWifi(wifi);
    	}
    	else {
        	Wifi wifi = new Wifi(Double.parseDouble(wifiLatText.getText()), Double.parseDouble(wifiLongText.getText()), wifiNameText.getText(), wifiBoroughText.getText(), wifiTypeText.getText(), wifiProviderText.getText());
        	//CurrentStorage.addNewWifi(wifi);
    	}

    }

    
    @FXML
    void initialize() {
        mapView.addMapInializedListener(this);
        address.bind(searchText.textProperty());

    	loadRouteMenu.setPopupSide(Side.RIGHT);
    	
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(true);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    	randomRoutePane.setVisible(false);
    	
    	addLocationsMenu.setPopupSide(Side.RIGHT);

        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert addWifiPane != null : "fx:id=\"addWifiPane\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert bikeIconButton != null : "fx:id=\"bikeIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert favouriteIconButton != null : "fx:id=\"favouriteIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert favouriteRoutesMenu != null : "fx:id=\"favouriteRoutesMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert loadRouteMenu != null : "fx:id=\"loadRouteMenu\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert wifiNameText != null : "fx:id=\"locationNameText\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert mainMapPane != null : "fx:id=\"mainMapPane\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert randomRouteButton != null : "fx:id=\"randomRouteButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert randomRoutePane != null : "fx:id=\"randomRoutePane\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert retailerIconButton != null : "fx:id=\"retailerIconButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
        assert saveWifiButton != null : "fx:id=\"saveLocationButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
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

    public void placeCircleOnMap(Wifi wifi) {
        Map.findWifi(wifi, map);
    }

    public void placeMarkerOnMap(Location loc) {
        Map.findLocation(loc, map, geocodingService);
    }

    public void placeRetailerOnMap(Retailer retailer) {Map.findRetailers(retailer, map, geocodingService);}

    public void placeMarkerOnMap(Route route) {
        Map.findLocation(route.getStart(), map, geocodingService);
        Map.findLocation(route.getEnd(), map, geocodingService);
    }

    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(40.6971494, -74.2598728))
                .mapType(MapTypeIdEnum.ROADMAP)
                .mapTypeControl(false)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
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
