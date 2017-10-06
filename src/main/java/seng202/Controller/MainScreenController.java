package seng202.Controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import com.lynden.gmapsfx.service.directions.*;
import com.lynden.gmapsfx.service.geocoding.GeocodingService;
import com.lynden.gmapsfx.shapes.Circle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.omg.CORBA.Current;
import seng202.Model.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

//import java.awt.Color;


public class MainScreenController implements MapComponentInitializedListener, DirectionsServiceCallback {

    private DirectionsService directionsService;
    private DirectionsPane directionsPane;

    private static DataFetcher data = new DataFetcher();

    private static boolean isStart = true;
    
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
    private ProgressBar progressBar;
    
    @FXML
    private Button showRouteButton;
    
    @FXML
    private MenuButton loadRouteMenu;
    
    @FXML
    private Menu favouriteRoutesMenu;
    
    @FXML
    private Menu savedRoutesMenu;
    
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
    private Label latLongDistanceLabel;
    
    @FXML
    private TextField searchText;
    
    //Adding wifi pane
    
    @FXML
    private AnchorPane addWifiPane;
    
    @FXML
    private Button saveWifiButton;
    
    @FXML
    private Label wifiAddressLabel;

    @FXML
    private TextField wifiAddressText;

    @FXML
    private Label wifiBoroughLabel;

    @FXML
    private TextField wifiBoroughText;

    @FXML
    private Label wifiLatLabel;

    @FXML
    private TextField wifiLatText;

    @FXML
    private Label wifiLongLabel;

    @FXML
    private TextField wifiLongText;

    @FXML
    private Label wifiNameLabel;

    @FXML
    private TextField wifiNameText;

    @FXML
    private Label wifiSsidLabel;

    @FXML
    private TextField wifiSsidText;

    @FXML
    private Label wifiProviderLabel;

    @FXML
    private TextField wifiProviderText;

    @FXML
    private Label wifiTypeLabel;

    @FXML
    private TextField wifiTypeText;

    @FXML
    private Label wifiZipLabel;

    @FXML
    private TextField wifiZipText;

    @FXML
    private CheckBox wifiPrivateCheck;
    
    //Adding poi pane
    
    @FXML
    private AnchorPane addPoiPane;
    
    @FXML
    private Button savePoiButton;
    
    @FXML
    private Label poiAddressLabel;

    @FXML
    private TextField poiAddressText;

    @FXML
    private Label poiBoroughLabel;

    @FXML
    private TextField poiBoroughText;

    @FXML
    private Label poiCostLabel;

    @FXML
    private TextField poiCostText;

    @FXML
    private Label poiDescriptionLabel;

    @FXML
    private TextArea poiDescriptionText;

    @FXML
    private Label poiLatLabel;

    @FXML
    private TextField poiLatText;

    @FXML
    private Label poiLongLabel;

    @FXML
    private TextField poiLongText;

    @FXML
    private Label poiNameLabel;

    @FXML
    private TextField poiNameText;

    @FXML
    private Label poiZipLabel;

    @FXML
    private TextField poiZipText;

    @FXML
    private CheckBox poiPrivateCheck;
    
    //Adding toilet pane
    
    @FXML
    private AnchorPane addToiletPane;
    
    @FXML
    private Label toiletAddressLabel;

    @FXML
    private TextField toiletAddressText;

    @FXML
    private Label toiletBoroughLabel;

    @FXML
    private TextField toiletBoroughText;

    @FXML
    private ChoiceBox<Boolean> toiletDisabledChoice;

    @FXML
    private Label toiletDisabledLabel;

    @FXML
    private Label toiletLatLabel;

    @FXML
    private TextField toiletLatText;

    @FXML
    private Label toiletLongLabel;

    @FXML
    private TextField toiletLongText;

    @FXML
    private Label toiletNameLabel;

    @FXML
    private TextField toiletNameText;

    @FXML
    private ChoiceBox<Boolean> toiletUnisexChoice;

    @FXML
    private Label toiletUnisexLabel;

    @FXML
    private Label toiletZipLabel;

    @FXML
    private TextField toiletZipText;

    @FXML
    private CheckBox toiletPrivateCheck;
    
    //Adding retailer pane
    
    @FXML
    private AnchorPane addRetailerPane;
    
    @FXML
    private Label retailerAddressLabel;

    @FXML
    private TextField retailerAddressText;

    @FXML
    private Label retailerBoroughLabel;

    @FXML
    private TextField retailerBoroughText;

    @FXML
    private Label retailerDescriptionLabel;

    @FXML
    private TextArea retailerDescriptionText;

    @FXML
    private Label retailerLatLabel;

    @FXML
    private TextField retailerLatText;

    @FXML
    private Label retailerLongLabel;

    @FXML
    private TextField retailerLongText;

    @FXML
    private Label retailerNameLabel;

    @FXML
    private TextField retailerNameText;

    @FXML
    private Label retailerProductLabel;

    @FXML
    private TextField retailerProductText;

    @FXML
    private Label retailerZipLabel;

    @FXML
    private TextField retailerZipText;

    @FXML
    private CheckBox retailerPrivateCheck;
    
    //Adding other pane
    
    @FXML
    private AnchorPane addOtherPane;
    
    @FXML
    private Label otherAddressLabel;

    @FXML
    private TextField otherAddressText;

    @FXML
    private Label otherBoroughLabel;

    @FXML
    private TextField otherBoroughText;

    @FXML
    private Label otherLatLabel;

    @FXML
    private TextField otherLatText;

    @FXML
    private Label otherLongLabel;

    @FXML
    private TextField otherLongText;

    @FXML
    private Label otherNameLabel;

    @FXML
    private TextField otherNameText;

    @FXML
    private Label otherZipLabel;

    @FXML
    private TextField otherZipText;
    
    @FXML
    private Button saveOtherButton;

    @FXML
    private CheckBox otherPrivateCheck;

    // File chooser

    @FXML
    private MenuButton fileChooserMenu;

    @FXML
    private MenuItem uploadWifiMenuBtn;

    @FXML
    private MenuItem uploadRetailerMenuBtn;

    @FXML
    private MenuItem uploadRouteMenuBtn;

    @FXML
    private MenuItem uploadToiletMenuBtn;

    @FXML
    private MenuItem uploadPoiMenuBtn;

    @FXML
    private Label progressBarLabel;

    private DirectionsRenderer directionsRenderer;

    private WebEngine mapEngine;

    ArrayList<Circle> wifiCircles = new ArrayList<Circle>();
    ArrayList<Marker> locationMarkers = new ArrayList<Marker>();
    
    /** 
     * Method for when the map menu button is pressed, shows the main map screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void mapPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) mapButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));

        Scene currentScene = primaryStage.getScene();
        Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), primaryStage.getMinHeight())
                : new Scene(root, currentScene.getWidth(), currentScene.getHeight()));

        //Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
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

	    try {
            Scene currentScene = primaryStage.getScene();
            Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), primaryStage.getMinHeight())
                    : new Scene(root, currentScene.getWidth(), currentScene.getHeight()));

//            Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
            primaryStage.setTitle("Table");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            Stage stage = new Stage();
            Parent loadingRoot = FXMLLoader.load(getClass().getResource("/DataLoadingScreen.fxml"));

            Scene scene = new Scene(loadingRoot, stage.getWidth(), stage.getHeight());
            stage.setTitle("Still Loading");
            stage.setScene(scene);
            stage.show();
        }

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


        Scene currentScene = primaryStage.getScene();
        Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), primaryStage.getMinHeight())
                : new Scene(root, currentScene.getWidth(), currentScene.getHeight()));

        //Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
		
		primaryStage.setTitle("Statistics");
		primaryStage.setScene(scene);
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


        Scene currentScene = primaryStage.getScene();
        Scene scene = (currentScene == null ? new Scene(root, primaryStage.getMinWidth(), primaryStage.getMinHeight())
                : new Scene(root, currentScene.getWidth(), currentScene.getHeight()));

        //Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
		
		primaryStage.setTitle("Profile");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    /**
     * Method for when logout button is pressed, shows the login screen and flushes current storage.
     * @param event
     * @throws IOException
     */
    @FXML
    void logoutPressed(ActionEvent event) throws IOException {
        DataFetcher exporter = new DataFetcher();
        try {
            exporter.connectDb();
            exporter.storeCurrentStorage();
            exporter.closeConnection();
            CurrentStorage.flush();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Places the route markers on the map
     * @param event
     */
    @FXML
    void showRoutePressed(ActionEvent event) {
        for (Route route : CurrentStorage.getRouteArray()) {
            DirectionsRequest request = new DirectionsRequest(new LatLong(route.getStart().getLatitude(),
                    route.getStart().getLongitude()),
                    new LatLong(route.getEnd().getLatitude(), route.getEnd().getLongitude()), TravelModes.DRIVING);
            directionsService.getRoute(request, this, new DirectionsRenderer(true, mapView.getMap(), directionsPane));
        }
    }

    public void displayRouteOnMap(Route route) {
        Map.findRoute(route, mapView, directionsService, this, directionsPane);
    }


    @FXML
    void loadRoutePressed(ActionEvent event) {
    }

    /**
     * Displays the file chooser pane
     * @param event
     */
    @FXML
    void fileChooserPressed(ActionEvent event) {
       /* FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String path = selectedFile.getPath();
        FileManager.readFile(path);*/
        addWifiPane.setVisible(false);
        mainMapPane.setVisible(false);
        addPoiPane.setVisible(false);
        addToiletPane.setVisible(false);
        addRetailerPane.setVisible(false);
        addOtherPane.setVisible(false);
    }

    /**
     * Open the file chooser to load a wifi file
     * @param event
     */
    @FXML
    void uploadWifiBtnPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            Thread wifiThread = new Thread(new WifiImporterThread(path));
            wifiThread.start();
        }
    }

    /**
     * Open the file chooser to load a retailer file
     * @param event
     */
    @FXML
    void uploadRetailerBtnPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            Thread retailerThread = new Thread(new RetailerImporterThread(path));
            retailerThread.start();
        }
    }

    /**
     * Open the file chooser to load a route file
     * @param event
     */
    @FXML
    void uploadRouteBtnPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            Thread t = new Thread(new RouteImporterThread(path));
            t.start();

        }
    }

    /**
     * Open the file chooser to load a toilet file
     * @param event
     */
    @FXML
    void uploadToiletBtnPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            FileManager.toiletRetriever(path);
        }
    }

    /**
     * Open the file chooser to load a poi file
     * @param event
     */
    @FXML
    void uploadPOIBtnPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            String path = selectedFile.getPath();
            FileManager.poiRetriever(path);
        }
    }


    /**
     * Displays the add wifi pane
     * @param event
     */
    @FXML
    void addLocationsPressed(ActionEvent event) {
    	addWifiPane.setVisible(true);
    	mainMapPane.setVisible(false);
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
    	stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
    	stage.show();
    }

    /**
     * Method for when the bike button is pressed, displays all the routes on the map.
     * @param event
     */
    @FXML
    void bikeIconPressed(ActionEvent event) {
//        int initCount = count;
//        Map.setRouteStartVisible(!Map.getRouteVisible());
//        // Places 50 route icons on the map.
//        while (count < CurrentStorage.getRouteArray().size() && count < initCount + 50) {
//            Route route = CurrentStorage.getRouteArray().get(count);
//            if (!(route.getStartMarker() == null) || !(route.getEndMarker() == null)) {
//                route.getStartMarker().setVisible(Map.getRouteVisible());
//                map.addMarker(route.getStartMarker());
//
//            } else {
//                Map.findRouteMarker(route, map);
//                route.getStartMarker().setVisible(Map.getRouteVisible());
//            }
//            count++;
//        }
    }

    /**
     * Method for when the favourite button is pressed, displays all the
     * users favourite routes on the map.
     * @param event
     */
    @FXML
    void favouriteIconPressed(ActionEvent event) {

    }

    /**
     * Method for when the retailer button is pressed, displays all the
     * retailers on the map
     * @param event
     */
    @FXML
    void retailerIconPressed(ActionEvent event) {
        map.clearMarkers();
        directionsRenderer.clearDirections();
        for (Retailer retailer : CurrentStorage.getRetailerArray()) {
                Map.findRetailers(retailer, map);
        }


        Map.setRetailerVisible(!Map.getRetailerVisible());
    }

    /** 
     * Method for when the save route button is pressed, opens the pop up.
     * @param event
     * @throws IOException
     */
    @FXML
    void saveRouteButtonPressed(ActionEvent event) throws IOException {
        if (CurrentStorage.getNewRouteEnd() != null) {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/SaveRouteScreen.fxml"));

            Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
            stage.setTitle("Save Route");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Method for when the toilet button is pressed, displays all the toilet
     * locations on the map
     * @param event
     */
    @FXML
    void toiletIconPressed(ActionEvent event) {
        map.clearMarkers();
        for (Toilet toilet : CurrentStorage.getToiletArray()) {
            Map.findToilets(toilet, map);
        }
    }

    /**
     * Method for when the wifi button is pressed, displays all the wifi
     * locations on the map
     * @param event
     */
    @FXML
    void wifiIconPressed(ActionEvent event) {
        map.clearMarkers();
        directionsRenderer.clearDirections();
        for (Wifi wifi : CurrentStorage.getWifiArray()) {
            Map.findWifi(wifi, map);
        }
    }

    /**
     * Method for when a search is performed on the map.
     * @param event
     */
    @FXML
    public void searchTextAction(ActionEvent event) {
        //Obtains a geocode location around latLong
        map.clearMarkers();
        directionsRenderer.clearDirections();
        Map.findLocation(address.get(), map, geocodingService);
    }

    /**
     * Displays the pane for user to add an other location
     * @param event
     */
    @FXML
    void addOtherMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(true);
    }

    /**
     * Displays the pane for user to add a point of interest location
     * @param event
     */
    @FXML
    void addPoiMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(true);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    }

    /**
     * Displays the pane for user to add a retailer location
     * @param event
     */
    @FXML
    void addRetailerMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(true);
    	addOtherPane.setVisible(false);
    }

    /**
     * Displays the pane for user to add a toilet location
     * @param event
     */
    @FXML
    void addToiletMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(true);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    }

    /**
     * Displays the pane for user to add a wifi location
     * @param event
     */
    @FXML
    void addWifiMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(true);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    }

    /**
     * Checks the input is able to be parsed to a Double
     * @param s String to be checked
     * @return true if Double otherwise false
     */
    Boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Checks the input is able to be parsed to an Integer
     * @param s String to be checked
     * @return true if Integer otherwise false
     */
    Boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    /**
     * Save an other location if all the required fields have the correct text - otherwise labels turn red
     * @param event
     */
    @FXML
    void saveOtherButtonPressed(ActionEvent event) {

        otherNameLabel.setTextFill(Color.BLACK);
        otherAddressLabel.setTextFill(Color.BLACK);
        otherLatLabel.setTextFill(Color.BLACK);
        otherLongLabel.setTextFill(Color.BLACK);
        otherZipLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (otherNameText.getText().equals("")) {
    		otherNameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
    	if (otherAddressText.getText().equals("") && (otherLatText.getText().equals("") || otherLongText.getText().equals(""))) {
            otherAddressLabel.setTextFill(Color.RED);
            otherLatLabel.setTextFill(Color.RED);
            otherLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (otherAddressText.getText().equals("") && !isDouble(otherLatText.getText())) {
            otherLatLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (otherAddressText.getText().equals("") && !isDouble(otherLongText.getText())) {
            otherLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!otherZipText.getText().equals("") && !isInt(otherZipText.getText())) {
            otherZipLabel.setTextFill(Color.RED);
            allValid = false;
    	}

    	if (allValid) {
    	    Location location;
    		if (!otherAddressText.getText().equals("")) {
    			location = new Location(otherAddressText.getText(), otherNameText.getText(), 4);
    		} else {
    			location = new Location(Double.parseDouble(otherLatText.getText()), Double.parseDouble(otherLongText.getText()), otherNameText.getText(), 4);
    		}

            if (!otherZipText.getText().equals("")) {
                location.setZip(Integer.parseInt(otherZipText.getText()));
            }
            if (!otherBoroughText.getText().equals("")) {
    		    location.setBorough(otherBoroughText.getText());
            }
            location.setSecret(otherPrivateCheck.isSelected());
            CurrentStorage.addNewGeneral(location);

    		otherNameLabel.setTextFill(Color.BLACK);
    		otherAddressLabel.setTextFill(Color.BLACK);
    		otherLatLabel.setTextFill(Color.BLACK);
    		otherLongLabel.setTextFill(Color.BLACK);
    		otherNameText.setText("");
    		otherAddressText.setText("");
    		otherLatText.setText("");
    		otherLongText.setText("");
    		otherZipText.setText("");
    		otherBoroughText.setText("");
    	}
    }

    /**
     * Save a point of interest location if all the required fields have the correct text - otherwise labels turn red
     * @param event
     */
    @FXML
    void savePoiButtonPressed(ActionEvent event) {

        poiNameLabel.setTextFill(Color.BLACK);
        poiAddressLabel.setTextFill(Color.BLACK);
        poiLatLabel.setTextFill(Color.BLACK);
        poiLongLabel.setTextFill(Color.BLACK);
        poiCostLabel.setTextFill(Color.BLACK);
        poiDescriptionLabel.setTextFill(Color.BLACK);
        poiZipLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (poiNameText.getText().equals("")) {
    		poiNameLabel.setTextFill(Color.RED);
            allValid = false;
        }
    	if (poiAddressText.getText().equals("") && (poiLatText.getText().equals("") || poiLongText.getText().equals(""))) {
            poiAddressLabel.setTextFill(Color.RED);
            poiLatLabel.setTextFill(Color.RED);
            poiLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(poiCostText.getText().equals("")) {
            poiCostLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (poiDescriptionText.getText().equals("")) {
            poiDescriptionLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (poiAddressText.getText().equals("") && !isDouble(poiLatText.getText())) {
            poiLatLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (poiAddressText.getText().equals("") && !isDouble(poiLongText.getText())) {
            poiLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!poiZipText.getText().equals("") && !isInt(poiZipText.getText())) {
            poiZipLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!poiCostText.getText().equals("") && !isDouble(poiCostText.getText())) {
            poiCostLabel.setTextFill(Color.RED);
            allValid = false;
    	}

    	if (allValid) {
            Poi poi;
            if (!poiAddressText.getText().equals("")) {
                poi = new Poi(poiAddressText.getText(), poiNameText.getText(), poiDescriptionText.getText(), Double.parseDouble(poiCostText.getText()));
            } else {
                poi = new Poi(Double.parseDouble(poiLatText.getText()), Double.parseDouble(poiLongText.getText()), poiNameText.getText(), poiDescriptionText.getText(), Double.parseDouble(poiCostText.getText()));
            }
            if (!poiZipText.getText().equals("")) {
                poi.setZip(Integer.parseInt(poiZipText.getText()));
            }
            if (!poiBoroughText.getText().equals("")) {
                poi.setBorough(poiBoroughText.getText());
            }
            poi.setSecret(poiPrivateCheck.isSelected());
            CurrentStorage.addNewPoi(poi);

            poiNameLabel.setTextFill(Color.BLACK);
            poiAddressLabel.setTextFill(Color.BLACK);
            poiLatLabel.setTextFill(Color.BLACK);
            poiLongLabel.setTextFill(Color.BLACK);
            poiCostLabel.setTextFill(Color.BLACK);
            poiDescriptionLabel.setTextFill(Color.BLACK);
            poiZipLabel.setTextFill(Color.BLACK);
            poiNameText.setText("");
            poiAddressText.setText("");
            poiLatText.setText("");
            poiLongText.setText("");
            poiZipText.setText("");
            poiBoroughText.setText("");
            poiCostText.setText("");
            poiDescriptionText.setText("");
        }
    }


    /**
     *Save a retailer location if all the required fields have the correct text - otherwise labels turn red
     * @param event
     */
    @FXML
    void saveRetailerButtonPressed(ActionEvent event) {
        retailerNameLabel.setTextFill(Color.BLACK);
        retailerAddressLabel.setTextFill(Color.BLACK);
        retailerLatLabel.setTextFill(Color.BLACK);
        retailerLongLabel.setTextFill(Color.BLACK);
        retailerProductLabel.setTextFill(Color.BLACK);
        retailerDescriptionLabel.setTextFill(Color.BLACK);
        retailerZipLabel.setTextFill(Color.BLACK);


        boolean allValid = true;

    	if (retailerNameText.getText().equals("")) {
    		retailerNameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
    	if (retailerAddressText.getText().equals("") && (retailerLatText.getText().equals("") || retailerLongText.getText().equals(""))) {
            retailerAddressLabel.setTextFill(Color.RED);
            retailerLatLabel.setTextFill(Color.RED);
            retailerLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (retailerProductText.getText().equals("")) {
            retailerProductLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (retailerDescriptionText.getText().equals("")) {
            retailerDescriptionLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (retailerAddressText.getText().equals("") && !isDouble(retailerLatText.getText())) {
            retailerLatLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (retailerAddressText.getText().equals("") && !isDouble(retailerLongText.getText())) {
            retailerLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!retailerZipText.getText().equals("") && !isInt(retailerZipText.getText())) {
            retailerZipLabel.setTextFill(Color.RED);
            allValid = false;
        }

        if (allValid) {
    	    Retailer retailer;
    		if (!retailerAddressText.getText().equals("")) {
        		retailer = new Retailer(retailerAddressText.getText(), retailerNameText.getText(), retailerProductText.getText(), retailerDescriptionText.getText());
            } else {
            	retailer = new Retailer(Double.parseDouble(retailerLatText.getText()), Double.parseDouble(retailerLongText.getText()), retailerNameText.getText(), retailerProductText.getText(), retailerDescriptionText.getText());
        	}

            if (!retailerZipText.getText().equals("")) {
                retailer.setZip(Integer.parseInt(retailerZipText.getText()));
            }
            if (!retailerBoroughText.getText().equals("")) {
    		    retailer.setBorough(retailerBoroughText.getText());
            }
            retailer.setSecret(retailerPrivateCheck.isSelected());
            CurrentStorage.addNewRetailer(retailer);

            retailerNameLabel.setTextFill(Color.BLACK);
            retailerAddressLabel.setTextFill(Color.BLACK);
            retailerLatLabel.setTextFill(Color.BLACK);
            retailerLongLabel.setTextFill(Color.BLACK);
            retailerProductLabel.setTextFill(Color.BLACK);
            retailerDescriptionLabel.setTextFill(Color.BLACK);
            retailerZipLabel.setTextFill(Color.BLACK);
    		retailerNameText.setText("");
    		retailerAddressText.setText("");
    		retailerLatText.setText("");
    		retailerLongText.setText("");
    		retailerZipText.setText("");
    		retailerBoroughText.setText("");
    		retailerProductText.setText("");
    		retailerDescriptionText.setText("");
    	}
    	
    }

    /**
     * Save a toilet location if all the required fields have the correct text - otherwise labels turn red
     * @param event
     */
    @FXML
    void saveToiletButtonPressed(ActionEvent event) {
        toiletNameLabel.setTextFill(Color.BLACK);
        toiletAddressLabel.setTextFill(Color.BLACK);
        toiletLatLabel.setTextFill(Color.BLACK);
        toiletLongLabel.setTextFill(Color.BLACK);
        toiletDisabledLabel.setTextFill(Color.BLACK);
        toiletUnisexLabel.setTextFill(Color.BLACK);
        toiletZipLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (toiletNameText.getText().equals("")) {
    		toiletNameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
    	if (toiletAddressText.getText().equals("") && (toiletLatText.getText().equals("") || toiletLongText.getText().equals(""))) {
            toiletAddressLabel.setTextFill(Color.RED);
            toiletLatLabel.setTextFill(Color.RED);
            toiletLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(toiletDisabledChoice.getSelectionModel().isEmpty()) {
            toiletDisabledLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (toiletUnisexChoice.getSelectionModel().isEmpty()) {
            toiletUnisexLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (toiletAddressText.getText().equals("") && !isDouble(toiletLatText.getText())) {
            toiletLatLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (toiletAddressText.getText().equals("") && !isDouble(toiletLongText.getText())) {
            toiletLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!toiletZipText.getText().equals("") && !isInt(toiletZipText.getText())) {
            toiletZipLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (allValid) {
    	    Toilet toilet;
    		if(!toiletAddressText.getText().equals("")) {
    			toilet = new Toilet(toiletAddressText.getText(), toiletNameText.getText(), toiletDisabledChoice.getValue(), toiletUnisexChoice.getValue());
    		} else {
    			toilet = new Toilet(Double.parseDouble(toiletLatText.getText()), Double.parseDouble(toiletLongText.getText()), toiletNameText.getText(), toiletDisabledChoice.getValue(), toiletUnisexChoice.getValue());
    		}

            if (!toiletZipText.getText().equals("")) {
                toilet.setZip(Integer.parseInt(toiletZipText.getText()));
            }
            if(!toiletBoroughText.getText().equals("")) {
    		    toilet.setBorough(toiletBoroughText.getText());
            }
            toilet.setSecret(toiletPrivateCheck.isSelected());
            CurrentStorage.addNewToilet(toilet);

            toiletNameLabel.setTextFill(Color.BLACK);
            toiletAddressLabel.setTextFill(Color.BLACK);
            toiletLatLabel.setTextFill(Color.BLACK);
            toiletLongLabel.setTextFill(Color.BLACK);
            toiletDisabledLabel.setTextFill(Color.BLACK);
            toiletUnisexLabel.setTextFill(Color.BLACK);
            toiletZipLabel.setTextFill(Color.BLACK);
    		toiletNameText.setText("");
    		toiletAddressText.setText("");
    		toiletLatText.setText("");
    		toiletLongText.setText("");
    		toiletZipText.setText("");
    		toiletBoroughText.setText("");
    	}
    }

    /**
     * Save a wifi location if all the required fields have the correct text - otherwise labels turn red
     * @param event
     */
    @FXML
    void saveWifiButtonPressed(ActionEvent event) {
        wifiNameLabel.setTextFill(Color.BLACK);
        wifiSsidLabel.setTextFill(Color.BLACK);
        wifiAddressLabel.setTextFill(Color.BLACK);
        wifiLatLabel.setTextFill(Color.BLACK);
        wifiLongLabel.setTextFill(Color.BLACK);
        wifiBoroughLabel.setTextFill(Color.BLACK);
        wifiTypeLabel.setTextFill(Color.BLACK);
        wifiProviderLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (wifiNameText.getText().equals("")) {
            wifiNameLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(wifiSsidText.getText().equals("")) {
    	    wifiSsidLabel.setTextFill(Color.RED);
    	    allValid = false;
    	}
    	if (wifiAddressText.getText().equals("") && (wifiLatText.getText().equals("") || wifiLongText.getText().equals(""))) {
            wifiAddressLabel.setTextFill(Color.RED);
            wifiLatLabel.setTextFill(Color.RED);
            wifiLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(wifiBoroughText.getText().equals("")) {
            wifiBoroughLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(wifiTypeText.getText().equals("")) {
            wifiTypeLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (wifiProviderText.getText().equals("")) {
            wifiProviderLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (wifiAddressText.getText().equals("") && !isDouble(wifiLatText.getText())) {
            wifiLatLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (wifiAddressText.getText().equals("") && !isDouble(wifiLongText.getText())) {
            wifiLongLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!wifiZipText.getText().equals("") && !isInt(wifiZipText.getText())) {
    	    wifiZipLabel.setTextFill(Color.RED);
    	    allValid = false;
    	}

    	if (allValid) {
    	    Wifi wifi;
    		if (!wifiAddressText.getText().equals("")) {
            	wifi = new Wifi(wifiAddressText.getText(), wifiNameText.getText(), wifiSsidText.getText(), wifiTypeText.getText(), wifiProviderText.getText());
        	} else {
            	wifi = new Wifi(Double.parseDouble(wifiLatText.getText()), Double.parseDouble(wifiLongText.getText()), wifiNameText.getText(), wifiSsidText.getText(), wifiTypeText.getText(), wifiProviderText.getText());
        	}
            if (!wifiZipText.getText().equals("")) {
                wifi.setZip(Integer.parseInt(wifiZipText.getText()));
            }
            if (!wifiBoroughText.getText().equals("")) {
    		    wifi.setBorough(wifiBoroughText.getText());
            }
            wifi.setSecret(wifiPrivateCheck.isSelected());
            CurrentStorage.addNewWifi(wifi);


            wifiNameLabel.setTextFill(Color.BLACK);
            wifiSsidLabel.setTextFill(Color.BLACK);
            wifiAddressLabel.setTextFill(Color.BLACK);
            wifiLatLabel.setTextFill(Color.BLACK);
            wifiLongLabel.setTextFill(Color.BLACK);
            wifiBoroughLabel.setTextFill(Color.BLACK);
            wifiTypeLabel.setTextFill(Color.BLACK);
            wifiProviderLabel.setTextFill(Color.BLACK);
    		wifiNameText.setText("");
    		wifiAddressText.setText("");
    		wifiLatText.setText("");
    		wifiLongText.setText("");
    		wifiZipText.setText("");
    		wifiBoroughText.setText("");
    		wifiTypeText.setText("");
    		wifiProviderText.setText("");
    	}
    	

    }

    
    @FXML
    void initialize() {
        //mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        mapEngine = mapView.getWebview().getEngine();
        address.bind(searchText.textProperty());

    	loadRouteMenu.setPopupSide(Side.RIGHT);
    	
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(true);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);

    	fileChooserMenu.setPopupSide(Side.RIGHT);
    	addLocationsMenu.setPopupSide(Side.RIGHT);

    	progressBar.setVisible(true);

    	ArrayList<Integer> favRoutes = CurrentStorage.getFavRoutes();
    	ArrayList<Integer> savedRoutes = CurrentStorage.getSavedRoutes();

    	for (Integer index : favRoutes) {
    	    Route route = CurrentStorage.getRouteArray().get(index);
            MenuItem menuItem = new MenuItem(route.getName());
            menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //TODO: display route on map
                    displayRouteOnMap(route);
                }
            });
            favouriteRoutesMenu.getItems().add(menuItem);
        }

        for (Integer index : savedRoutes) {
    	    Route route = CurrentStorage.getRouteArray().get(index);
    	    MenuItem menuItem = new MenuItem(route.getName());
    	    menuItem.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    //TODO: display route on map
                    displayRouteOnMap(route);
                }
            });
            savedRoutesMenu.getItems().add(menuItem);
        }

    	if (!data.isHasImported()) {
            Service service = new Service() {
                @Override
                protected Task createTask() {
                    return new Task() {
                        @Override
                        protected Object call() throws Exception {
                            data.setHasImported(true);

                            int progress = 0;
                            Integer[] oldOffsets = {0, 0, 0, 0, 0};
                            progressBarLabel.setVisible(true);
                            updateMessage("Connecting to database...");
                            data.connectDb();
                            progressBar.setVisible(true);
                            updateProgress(progress, 100);
                            //progressBar.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
                            updateMessage("Retrieving Base Locations...");
                            data.loadNextLocations();
                            progress += 15;
                            updateProgress(progress, 100);
                            updateMessage("Retrieving Base Routes...");
                            data.loadAllRoutes();
                            progress += 20;
                            updateProgress(progress, 100);
                            Integer[] newOffsets = data.getOffsets();
                            while (!Arrays.deepEquals(oldOffsets, newOffsets)) {
                                data.loadNextLocations();
                                progress += 10;
                                updateProgress(progress, 100);
                                updateMessage("Loading remaining data...");
                                data.loadAllRoutes();
                                progress += 10;
                                updateProgress(progress, 100);
                                oldOffsets = newOffsets.clone();
                                newOffsets = data.getOffsets();
                            }
                            updateProgress(100, 100);
                            updateMessage("Done!");

                            data.closeConnection();
                            return null;
                        }
                    };
                }
            };

            progressBar.progressProperty().bind(service.progressProperty());
            progressBarLabel.textProperty().bind(service.messageProperty());
            service.start();
        }

    	ObservableList<Boolean> disabledOptions = FXCollections.observableArrayList(true, false);
    	toiletDisabledChoice.setItems(disabledOptions);
    	ObservableList<Boolean> unisexOptions = FXCollections.observableArrayList(true, false);
    	toiletUnisexChoice.setItems(unisexOptions);

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

    @Override
    public void mapInitialized() {

        CurrentStorage currentStorage = new CurrentStorage();
        geocodingService = new GeocodingService();
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(40.7128, -74.0059))
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
        directionsRenderer = new DirectionsRenderer(true, map, directionsPane);
        map.addMouseEventHandler(UIEventType.click, (GMapMouseEvent event) -> {
            // Sets up an actionEvent where it will create a marker at the clicked location.
            LatLong latLong = event.getLatLong();
            if (isStart) {
                Location location = new Location(latLong.getLatitude(), latLong.getLongitude(), 4);
                currentStorage.setNewRouteStart(location);
                map.clearMarkers();
                directionsRenderer.clearDirections();
                directionsRenderer = new DirectionsRenderer(true, map, directionsPane);
                Map.setStartMarker(latLong, map);
                isStart = false;
            } else {
                Location location = new Location(latLong.getLatitude(), latLong.getLongitude(), 4);
                currentStorage.setNewRouteEnd(location);
                Map.setEndMarker(latLong, map);
                isStart = true;
            }

            if (Map.getStartLoc() != null && Map.getEndLoc() != null) {

                Map.findRoute(Map.getStartLoc().getLatitude() + ", " + Map.getStartLoc().getLongitude(),
                        Map.getEndLoc().getLatitude() + ", " + Map.getEndLoc().getLongitude(),
                        mapView, directionsService, this, directionsPane, directionsRenderer);

                updateDistanceLabel(Map.getDistance(CurrentStorage.getNewRouteStart().getLatitude(), CurrentStorage.getNewRouteStart().getLongitude(),
                        CurrentStorage.getNewRouteEnd().getLatitude(),
                         CurrentStorage.getNewRouteEnd().getLongitude())/1000);
                Map.resetStartMarker();
                Map.resetEndMarker();
            }
        });
    }

    public WebEngine getMapWebEngine() { return mapEngine; }

    public GoogleMapView getMapView() { return mapView; }

    public DirectionsService getDirectionsService() { return directionsService; }

    public void updateDistanceLabel(double distance) {
        latLongDistanceLabel.setText(String.format("Distance: %.2fkm", distance));
    }

    @Override
    public void directionsReceived(DirectionsResult results, DirectionStatus status){
        // This is called when a route is created.

        //This section finds the nearby locations.
        ArrayList<Location> nearby = new ArrayList<Location>();
        map.clearMarkers();
        double minDistance = Double.MAX_VALUE;
        int minIndex = 0;
        int index = 0;
        for (DirectionsRoute route : results.getRoutes()) {
            double distance = 0;
            for (DirectionsLeg leg : route.getLegs()) {
                distance += leg.getDistance().getValue();
            }
            if (distance < minDistance) {
                minDistance = distance;
                minIndex = index;
            }
            index++;
        }

        for (LatLong latLong : results.getRoutes().get(minIndex).getOverviewPath()) {
            nearby.addAll(Map.findNearby(latLong.getLatitude(), latLong.getLongitude()));
        }

        for (Location loc : nearby) {
            if (loc.getLocationType() == 0) {
                Map.findToilets((Toilet) loc, map);
            } else if (loc.getLocationType() == 1) {
                Map.findPoi((Poi) loc, map);
            } else if (loc.getLocationType() == 2) {
                Map.findRetailers((Retailer) loc, map);
            } else if (loc.getLocationType() == 3) {
                Map.findWifi((Wifi) loc, map);
            }
        }

    }
}
