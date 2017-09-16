package seng202.Controller;

import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Location;
import seng202.Model.Poi;
import seng202.Model.RawDataViewer;
import seng202.Model.Retailer;
import seng202.Model.Route;
import seng202.Model.Toilet;
import seng202.Model.User;
import seng202.Model.Wifi;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
/*import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;*/

import javafx.scene.control.*;


public class TablesScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private TableColumn<Location, Double> allLocLatCol;

    @FXML
    private TableColumn<Location, Double> allLocLongCol;

    @FXML
    private TableColumn<Location, String> allLocNameCol;

    @FXML
    private TableColumn<Location, String> allLocTypeCol;

    @FXML
    private TableView<Location> allLocationsTable;
    

    @FXML
    private Button logoutButton;

    @FXML
    private Button mapButton;

    @FXML
    private TableColumn<Poi, Double> poiCostCol;

    @FXML
    private TableColumn<Poi, String> poiDescCol;

    @FXML
    private TableColumn<Poi, Double> poiLatCol;

    @FXML
    private TableColumn<Poi, Double> poiLongCol;

    @FXML
    private TableColumn<Poi, String> poiNameCol;

    @FXML
    private TableView<Poi> poiTable;

    @FXML
    private TableColumn<Retailer, String> retDescCol;

    @FXML
    private TableColumn<Retailer, Double> retLatCol;

    @FXML
    private TableColumn<Retailer, Double> retLongCol;

    @FXML
    private TableColumn<Retailer, String> retNameCol;

    @FXML
    private TableColumn<Retailer, String> retProductCol;

    @FXML
    private TableView<Retailer> retailersTable;

    @FXML
    private TableColumn<Route, Double> routeDistCol;

    @FXML
    private TableColumn<Route, String> routeEndCol;

    @FXML
    private TableColumn<Route, String> routeNameCol;

    @FXML
    private TableColumn<Route, String> routeStartCol;

    @FXML
    private TableView<Route> routesTable;

    @FXML
    private Button searchButton;

    @FXML
    private Button statButton;

    @FXML
    private Button tableButton;

    @FXML
    private ChoiceBox<String> tableOptions;
    
    @FXML
    private TextField keywordText;

    @FXML
    private TableColumn<Toilet, Boolean> toiletDisCol;

    @FXML
    private TableColumn<Toilet, Double> toiletLatCol;

    @FXML
    private TableColumn<Toilet, Double> toiletLongCol;

    @FXML
    private TableColumn<Toilet, String> toiletNameCol;

    @FXML
    private TableColumn<Toilet, Boolean> toiletUniCol;

    @FXML
    private TableView<Toilet> toiletsTable;

    @FXML
    private TableColumn<Wifi, Double> wifiLatCol;

    @FXML
    private TableColumn<Wifi, Double> wifiLongCol;
    
    @FXML
    private TableColumn<Wifi, String> wifiBoroughCol;

    @FXML
    private TableColumn<Wifi, String> wifiNameCol;

    @FXML
    private TableColumn<Wifi, String> wifiProvCol;

    @FXML
    private TableView<Wifi> wifiTable;

    @FXML
    private TableColumn<Wifi, String> wifiTypeCol;


    @FXML
    void accountPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) accountButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Profile");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void logoutPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) logoutButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void mapPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) mapButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void searchPressed(ActionEvent event) {

    	String option = tableOptions.getValue().toString();
    	if (option == "All locations") {
    		// TODO: have a getLocationArray() in current storage, then uncomment the below
    		//ArrayList<Location> = locFiltered = RawDataViewer.searchLocations(CurrentStorage.getLocationArray(), keywordText.getText().toString());
    		//ObservableList<Location> locData = FXCollections.observableArrayList(locFiltered);
        	//allLocationsTable.setItems(locData);
    		allLocationsTable.setVisible(true);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "Retailers") {
    		ArrayList<Retailer> retFiltered = RawDataViewer.searchRetailer(CurrentStorage.getRetailerArray(), keywordText.getText().toString());
    		ObservableList<Retailer> retData = FXCollections.observableArrayList(retFiltered);
        	retailersTable.setItems(retData);
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(true);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "WiFi") {
    		ArrayList<Wifi> wifiFiltered = RawDataViewer.searchWifi(CurrentStorage.getWifiArray(), keywordText.getText().toString());
    		ObservableList<Wifi> wifiData = FXCollections.observableArrayList(wifiFiltered);
    		wifiTable.setItems(wifiData);
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(true);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "Toilets") {
    		ArrayList<Toilet> toiletFiltered = RawDataViewer.searchToilets(CurrentStorage.getToiletArray(), keywordText.getText().toString());
    		ObservableList<Toilet> toiletData = FXCollections.observableArrayList(toiletFiltered);
    		toiletsTable.setItems(toiletData);
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(true);
    		routesTable.setVisible(false);
    	} else if (option == "Points of interest")  {
    		ArrayList<Poi> poiFiltered = RawDataViewer.searchPoi(CurrentStorage.getPoiArray(), keywordText.getText().toString());
    		ObservableList<Poi> poiData = FXCollections.observableArrayList(poiFiltered);
    		poiTable.setItems(poiData);
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(true);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "Routes") {
    		ArrayList<Route> routeFiltered = RawDataViewer.searchRoutes(CurrentStorage.getRouteArray(), keywordText.getText().toString());
    		ObservableList<Route> routeData = FXCollections.observableArrayList(routeFiltered);
    		routesTable.setItems(routeData);
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(true);
    	}
    }

    @FXML
    void statPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) statButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("Statistics");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    @FXML
    void tablePressed(ActionEvent event) {
    }

    @FXML
    void initialize() {
    	
    	// Random objects for testing

    	CurrentStorage.addRetailer(new Retailer(23.45, 127.3, "Retailer new", "Bikes", "We sell bikes", 1));
    	CurrentStorage.addRetailer(new Retailer(5345.2, 765.3, "Retailer old", "Biks", "what do we", 1));
    	CurrentStorage.addRetailer(new Retailer(123.45, 127.3, "Retailer new", "Bikes", "rand", 1));
    	CurrentStorage.addRetailer(new Retailer(400.45, 127.3, "Retailer new", "Bikes", "We sell bikes", 1));
    	CurrentStorage.addRetailer(new Retailer(1235, 123, "Retaew", "CLothes", "We sell bikes", 1));
    	
    	// Originally     	
    	allLocationsTable.setVisible(false);
		poiTable.setVisible(false);
		retailersTable.setVisible(false);
		wifiTable.setVisible(false);
		toiletsTable.setVisible(false);
		routesTable.setVisible(false);
    	
    	ObservableList<String> filterOptions = FXCollections.observableArrayList("All locations","Routes", "Retailers", "WiFi", "Toilets", "Points of interest");
    	tableOptions.setItems(filterOptions);
    	
    	// Set up the all locations table
    	allLocNameCol.setCellValueFactory(new PropertyValueFactory<Location,String>("name"));
    	allLocLatCol.setCellValueFactory(new PropertyValueFactory<Location,Double>("latitude"));
    	allLocLongCol.setCellValueFactory(new PropertyValueFactory<Location,Double>("longitude"));
    	allLocTypeCol.setCellValueFactory(new PropertyValueFactory<Location,String>("typeString"));
    	
    	ObservableList<Location> locData = FXCollections.observableArrayList(CurrentStorage.getToiletArray()); // TODO: Make a list in Current Storage that has all the locations, getLocationArray()
    	allLocationsTable.setItems(locData);
    	
    	// Set up the toilets table
    	toiletDisCol.setCellValueFactory(new PropertyValueFactory<Toilet, Boolean>("forDisabled"));
    	toiletLatCol.setCellValueFactory(new PropertyValueFactory<Toilet, Double>("latitude"));
    	toiletLongCol.setCellValueFactory(new PropertyValueFactory<Toilet, Double>("longitude"));
    	toiletUniCol.setCellValueFactory(new PropertyValueFactory<Toilet, Boolean>("uniSex"));
    	toiletNameCol.setCellValueFactory(new PropertyValueFactory<Toilet, String>("name"));
    	
    	ObservableList<Toilet> toiletData = FXCollections.observableArrayList(CurrentStorage.getToiletArray());
    	toiletsTable.setItems(toiletData);
    	
    	// Set up the wifi table
    	wifiLatCol.setCellValueFactory(new PropertyValueFactory<Wifi, Double>("latitude"));
    	wifiProvCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("provider"));
    	wifiLongCol.setCellValueFactory(new PropertyValueFactory<Wifi, Double>("longitude"));
    	wifiTypeCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("type"));
    	wifiNameCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("name"));
    	wifiBoroughCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("borough"));
    	
    	ObservableList<Wifi> wifiData = FXCollections.observableArrayList(CurrentStorage.getWifiArray());
    	wifiTable.setItems(wifiData);
    	
    	// Set up the routes table
    	routeEndCol.setCellValueFactory(new PropertyValueFactory<Route, String>("endString"));
    	routeStartCol.setCellValueFactory(new PropertyValueFactory<Route, String>("startString"));
    	routeDistCol.setCellValueFactory(new PropertyValueFactory<Route, Double>("distance"));
    	routeNameCol.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));
    	
    	ObservableList<Route> routeData = FXCollections.observableArrayList(CurrentStorage.getRouteArray());
    	routesTable.setItems(routeData);
    	
    	// Set up the retailers table
    	retLatCol.setCellValueFactory(new PropertyValueFactory<Retailer, Double>("latitude"));
    	retDescCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("description"));
    	retLongCol.setCellValueFactory(new PropertyValueFactory<Retailer, Double>("longitude"));
    	retProductCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("product"));
    	retNameCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("name"));
    	
    	ObservableList<Retailer> retData = FXCollections.observableArrayList(CurrentStorage.getRetailerArray());
    	retailersTable.setItems(retData);
    	
    	// Set up the poi table
    	poiLatCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("latitude"));
    	poiDescCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("description"));
    	poiLongCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("longitude"));
    	poiNameCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("name"));
    	poiCostCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("cost"));
    	
    	ObservableList<Poi> poiData = FXCollections.observableArrayList(CurrentStorage.getPoiArray());
    	poiTable.setItems(poiData);   	
    	    	
    	assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocLatCol != null : "fx:id=\"allLocLatCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocLongCol != null : "fx:id=\"allLocLongCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocNameCol != null : "fx:id=\"allLocNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocTypeCol != null : "fx:id=\"allLocTypeCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocationsTable != null : "fx:id=\"allLocationsTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert keywordText != null : "fx:id=\"conditionText\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiCostCol != null : "fx:id=\"poiCostCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiDescCol != null : "fx:id=\"poiDescCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiLatCol != null : "fx:id=\"poiLatCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiLongCol != null : "fx:id=\"poiLongCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiNameCol != null : "fx:id=\"poiNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiTable != null : "fx:id=\"poiTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retDescCol != null : "fx:id=\"retDescCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retLatCol != null : "fx:id=\"retLatCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retLongCol != null : "fx:id=\"retLongCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retNameCol != null : "fx:id=\"retNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retProductCol != null : "fx:id=\"retProductCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retailersTable != null : "fx:id=\"retailersTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert routeDistCol != null : "fx:id=\"routeDistCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert routeEndCol != null : "fx:id=\"routeEndCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert routeNameCol != null : "fx:id=\"routeNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert routeStartCol != null : "fx:id=\"routeStartCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert routesTable != null : "fx:id=\"routesTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert statButton != null : "fx:id=\"statButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert tableButton != null : "fx:id=\"tableButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert tableOptions != null : "fx:id=\"tableOptions\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletDisCol != null : "fx:id=\"toiletDisCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletLatCol != null : "fx:id=\"toiletLatCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletLongCol != null : "fx:id=\"toiletLongCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletNameCol != null : "fx:id=\"toiletNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletUniCol != null : "fx:id=\"toiletUniCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletsTable != null : "fx:id=\"toiletsTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiLatCol != null : "fx:id=\"wifiLatCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiLongCol != null : "fx:id=\"wifiLongCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiNameCol != null : "fx:id=\"wifiNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiProvCol != null : "fx:id=\"wifiProvCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiTable != null : "fx:id=\"wifiTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiTypeCol != null : "fx:id=\"wifiTypeCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";


    }

}
