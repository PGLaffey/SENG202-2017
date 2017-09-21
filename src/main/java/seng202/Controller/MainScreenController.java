package seng202.Controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import seng202.Model.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import java.awt.Color;


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
    private Button fileChooserButton;
    
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

    Service<Void> backgroundThread;
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
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
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
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
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
		primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
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
		primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
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
		primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
		primaryStage.show();
    }
    
    @FXML
    void showRoutePressed(ActionEvent event) {
    }
    
    @FXML
    void loadRoutePressed(ActionEvent event) {
    }

    @FXML
    void fileChooserPressed(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Data Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String path = selectedFile.getPath();
        FileManager.readFile(path);
    }

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
            if (!retailer.hasNoMarker() && retailer.getMarker() != null) {
                retailer.getMarker().setVisible(!Map.getRetailerVisible());
                map.addMarker(retailer.getMarker());
            } else {
                locationMarkers.add(Map.findRetailers(retailer));
            }
        }
        Map.setRetailerVisible(!Map.getRetailerVisible());
        /*
        backgroundThread = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        return null;
                    }
                };
            }
        };
        backgroundThread.restart();*/
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
		
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
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
        Service<Void> wifiLoaderService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        for (Wifi wifi : CurrentStorage.getWifiArray()) {
                            if (wifi.getCircle() == null) {
                                wifiCircles.add(Map.findWifi(wifi));
                                map.addMapShape(wifi.getCircle());
                            } else {
                                wifi.getCircle().setVisible(!wifi.getCircle().getVisible());
                            }
                        }
                        return null;
                    }
                };
            }
        };

        wifiLoaderService.start();

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
    }

    @FXML
    void addPoiMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(true);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    }

    @FXML
    void addRetailerMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(true);
    	addOtherPane.setVisible(false);
    }

    @FXML
    void addToiletMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(false);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(true);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    }

    @FXML
    void addWifiMenuPressed(ActionEvent event) {
    	addWifiPane.setVisible(true);
    	mainMapPane.setVisible(false);
    	addPoiPane.setVisible(false);
    	addToiletPane.setVisible(false);
    	addRetailerPane.setVisible(false);
    	addOtherPane.setVisible(false);
    }
    
    @FXML
    void saveOtherButtonPressed(ActionEvent event) {
    	if (otherNameText.getText().isEmpty()) {
    		otherNameLabel.setTextFill(Color.RED);
    	} else if (otherAddressText.getText().isEmpty() && (otherLatText.getText().isEmpty() || otherLongText.getText().isEmpty())) {
    		otherNameLabel.setTextFill(Color.BLACK);
    		otherAddressLabel.setTextFill(Color.RED);
    		otherLatLabel.setTextFill(Color.RED);
    		otherLongLabel.setTextFill(Color.RED);
    	} else {
    		if (!otherAddressText.getText().isEmpty()) {
    			Location location = new Location(otherAddressText.getText(), otherNameText.getText(), 4);
    			if (!otherZipText.getText().isEmpty()) {
    				location.setZip(Integer.parseInt(otherZipText.getText()));
    			}
    			CurrentStorage.addNewGeneral(location);
    		} else {
    			Location location = new Location(Integer.parseInt(otherLatText.getText()), Integer.parseInt(otherLongLabel.getText()), otherNameText.getText(), 4);
    			if (!otherZipText.getText().isEmpty()) {
    				location.setZip(Integer.parseInt(otherZipText.getText()));
    			}
    			CurrentStorage.addNewGeneral(location);
    		}
    		otherNameLabel.setTextFill(Color.BLACK);
    		otherAddressLabel.setTextFill(Color.BLACK);
    		otherLatLabel.setTextFill(Color.BLACK);
    		otherLongLabel.setTextFill(Color.BLACK);
    		otherNameText.setText(null);
    		otherAddressText.setText(null);
    		otherLatText.setText(null);
    		otherLongText.setText(null);
    		otherZipText.setText(null);
    		otherBoroughText.setText(null);
    	}
    }

    @FXML
    void savePoiButtonPressed(ActionEvent event) {
    	if (poiNameText.getText().isEmpty()) {
    		poiNameLabel.setTextFill(Color.RED);
    	} else if (poiAddressText.getText().isEmpty() && (poiLatText.getText().isEmpty() || poiLongText.getText().isEmpty())) {
    		poiNameLabel.setTextFill(Color.BLACK);
    		poiAddressLabel.setTextFill(Color.RED);
    		poiLatLabel.setTextFill(Color.RED);
    		poiLongLabel.setTextFill(Color.RED);
    	} else {
    		if (!poiAddressText.getText().isEmpty()) {
        		Poi poi = new Poi(poiAddressText.getText(), poiNameText.getText(), poiDescriptionText.getText(), Double.parseDouble(poiCostText.getText()));
        		if (!poiZipText.getText().isEmpty()) {
        			poi.setZip(Integer.parseInt(poiZipText.getText()));
        		}
        		CurrentStorage.addNewPoi(poi);
        	} else {
        		Poi poi = new Poi(Double.parseDouble(poiLatText.getText()), Double.parseDouble(poiLongText.getText()), poiNameText.getText(), poiDescriptionText.getText(), Double.parseDouble(poiCostText.getText()));
        		if (!poiZipText.getText().isEmpty()) {
        			poi.setZip(Integer.parseInt(poiZipText.getText()));
        		}
        		CurrentStorage.addNewPoi(poi);
        	}
    		poiNameLabel.setTextFill(Color.BLACK);
    		poiAddressLabel.setTextFill(Color.BLACK);
    		poiLatLabel.setTextFill(Color.BLACK);
    		poiLongLabel.setTextFill(Color.BLACK);
    		poiNameText.setText(null);
    		poiAddressText.setText(null);
    		poiLatText.setText(null);
    		poiLongText.setText(null);
    		poiZipText.setText(null);
    		poiBoroughText.setText(null);
    		poiCostText.setText(null);
    		poiDescriptionText.setText(null);
    	}
    	
    }


    @FXML
    void saveRetailerButtonPressed(ActionEvent event) {
    	if (retailerNameText.getText().isEmpty()) {
    		retailerNameLabel.setTextFill(Color.RED);
    	} else if (retailerAddressText.getText().isEmpty() && (retailerLatText.getText().isEmpty() || retailerLongText.getText().isEmpty())) {
    		retailerNameLabel.setTextFill(Color.BLACK);
    		retailerAddressLabel.setTextFill(Color.RED);
    		retailerLatLabel.setTextFill(Color.RED);
    		retailerLongLabel.setTextFill(Color.RED);
    	} else {
    		if (!retailerAddressText.getText().isEmpty()) {
        		Retailer retailer = new Retailer(retailerAddressText.getText(), retailerNameText.getText(), retailerProductText.getText(), retailerDescriptionText.getText());
        		if (!retailerZipText.getText().isEmpty()) {
        			retailer.setZip(Integer.parseInt(retailerZipText.getText()));
        		}
            	CurrentStorage.addNewRetailer(retailer);
            } else {
            	Retailer retailer = new Retailer(Double.parseDouble(retailerLatText.getText()), Double.parseDouble(retailerLongText.getText()), retailerNameText.getText(), retailerProductText.getText(), retailerDescriptionText.getText());
            	if (!retailerZipText.getText().isEmpty()) {
        			retailer.setZip(Integer.parseInt(retailerZipText.getText()));
        		}
            	CurrentStorage.addNewRetailer(retailer);
        	}
    		retailerNameLabel.setTextFill(Color.BLACK);
    		retailerAddressLabel.setTextFill(Color.BLACK);
    		retailerLatLabel.setTextFill(Color.BLACK);
    		retailerLongLabel.setTextFill(Color.BLACK);
    		retailerNameText.setText(null);
    		retailerAddressText.setText(null);
    		retailerLatText.setText(null);
    		retailerLongText.setText(null);
    		retailerZipText.setText(null);
    		retailerBoroughText.setText(null);
    		retailerProductText.setText(null);
    		retailerDescriptionText.setText(null);
    	}
    	
    }

    @FXML
    void saveToiletButtonPressed(ActionEvent event) {
    	if (toiletNameText.getText().isEmpty()) {
    		toiletNameLabel.setTextFill(Color.RED);
    	} else if (toiletAddressText.getText().isEmpty() && (toiletLatText.getText().isEmpty() || toiletLongText.getText().isEmpty())) {
    		toiletNameLabel.setTextFill(Color.BLACK);
    		toiletAddressLabel.setTextFill(Color.RED);
    		toiletLatLabel.setTextFill(Color.RED);
    		toiletLongLabel.setTextFill(Color.RED);
    	} else {
    		if(!toiletAddressText.getText().isEmpty()) {
    			Toilet toilet = new Toilet(toiletAddressText.getText(), toiletNameText.getText(), toiletDisabledChoice.getValue(), toiletUnisexChoice.getValue());
    			if (!toiletZipText.getText().isEmpty()) {
    				toilet.setZip(Integer.parseInt(toiletZipText.getText()));
    			}
    			CurrentStorage.addNewToilet(toilet);
    		} else {
    			Toilet toilet = new Toilet(Double.parseDouble(toiletLatText.getText()), Double.parseDouble(toiletLongText.getText()), toiletNameText.getText(), toiletDisabledChoice.getValue(), toiletUnisexChoice.getValue());

    			if (!toiletZipText.getText().isEmpty()) {
    				toilet.setZip(Integer.parseInt(toiletZipText.getText()));
    			}
    			CurrentStorage.addNewToilet(toilet);
    		}
    		toiletNameLabel.setTextFill(Color.BLACK);
    		toiletAddressLabel.setTextFill(Color.BLACK);
    		toiletLatLabel.setTextFill(Color.BLACK);
    		toiletLongLabel.setTextFill(Color.BLACK);
    		toiletNameText.setText(null);
    		toiletAddressText.setText(null);
    		toiletLatText.setText(null);
    		toiletLongText.setText(null);
    		toiletZipText.setText(null);
    		toiletBoroughText.setText(null);
    		
    		
    			
    	}
    }

    @FXML
    void saveWifiButtonPressed(ActionEvent event) {
    	if (wifiNameText.getText().isEmpty()) {
    		wifiNameLabel.setTextFill(Color.RED);
    	} else if (wifiAddressText.getText().isEmpty() && (wifiLatText.getText().isEmpty() || wifiLongText.getText().isEmpty())) {
    		wifiNameLabel.setTextFill(Color.BLACK);
    		wifiAddressLabel.setTextFill(Color.RED);
    		wifiLatLabel.setTextFill(Color.RED);
    		wifiLongLabel.setTextFill(Color.RED);    		
    	} else {
    		if (!wifiAddressText.getText().isEmpty()) {
            	Wifi wifi = new Wifi(wifiAddressText.getText(), wifiNameText.getText(), wifiBoroughText.getText(), wifiTypeText.getText(), wifiProviderText.getText());
    			if (!wifiZipText.getText().isEmpty()) {
    				wifi.setZip(Integer.parseInt(wifiZipText.getText()));
    			}
            	CurrentStorage.addNewWifi(wifi);
        	} else {
            	Wifi wifi = new Wifi(Double.parseDouble(wifiLatText.getText()), Double.parseDouble(wifiLongText.getText()), wifiNameText.getText(), wifiBoroughText.getText(), wifiTypeText.getText(), wifiProviderText.getText());
            	if (!wifiZipText.getText().isEmpty()) {
    				wifi.setZip(Integer.parseInt(wifiZipText.getText()));
    			}
            	CurrentStorage.addNewWifi(wifi);
        	}
    		wifiNameLabel.setTextFill(Color.BLACK);
    		wifiAddressLabel.setTextFill(Color.BLACK);
    		wifiLatLabel.setTextFill(Color.BLACK);
    		wifiLongLabel.setTextFill(Color.BLACK);
    		wifiNameText.setText(null);
    		wifiAddressText.setText(null);
    		wifiLatText.setText(null);
    		wifiLongText.setText(null);
    		wifiZipText.setText(null);
    		wifiBoroughText.setText(null);
    		wifiTypeText.setText(null);
    		wifiProviderText.setText(null);
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
    	
    	addLocationsMenu.setPopupSide(Side.RIGHT);
    	
       	
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
        assert fileChooserButton != null : "fx:id=\"fileChooserButton\" was not injected: check your FXML file 'MainScreen.fxml'.";
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
        Map.findWifi(wifi);

    }

    public void placeMarkerOnMap(Location loc) {
        Map.findLocation(loc.getAddress(), map, geocodingService);
    }

    public void placeRetailerOnMap(Retailer retailer) {Map.findRetailers(retailer);}

    public void placeMarkerOnMap(Route route) {
        Map.findLocation(route.getStartString(), map, geocodingService);
        Map.findLocation(route.getEndString(), map, geocodingService);
    }

    @Override
    public void mapInitialized() {
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
