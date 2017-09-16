package seng202.Controller;

import javafx.stage.Stage;
import seng202.Model.Location;
import seng202.Model.Poi;
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
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


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
    private TableColumn<Location, Integer> allLocTypeCol;

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
    		allLocationsTable.setVisible(true);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "Retailers") {
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(true);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "WiFi") {
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(true);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "Toilets") {
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(false);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(true);
    		routesTable.setVisible(false);
    	} else if (option == "Points of interest")  {
    		allLocationsTable.setVisible(false);
    		poiTable.setVisible(true);
    		retailersTable.setVisible(false);
    		wifiTable.setVisible(false);
    		toiletsTable.setVisible(false);
    		routesTable.setVisible(false);
    	} else if (option == "Routes") {
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
    	
    	allLocationsTable.setVisible(false);
		poiTable.setVisible(false);
		retailersTable.setVisible(false);
		wifiTable.setVisible(false);
		toiletsTable.setVisible(false);
		routesTable.setVisible(false);
    	
    	ObservableList<String> filterOptions = FXCollections.observableArrayList("All locations","Routes", "Retailer", "WiFi", "Toilets", "Points of interest");
    	tableOptions.setItems(filterOptions);
    	
    	
    	// Set up the invoice table
    	allLocNameCol.setCellValueFactory(new PropertyValueFactory<Location,String>("name"));
    	allLocLatCol.setCellValueFactory(new PropertyValueFactory<Location,Double>("latitude"));
    	allLocLongCol.setCellValueFactory(new PropertyValueFactory<Location,Double>("longitude"));
    	allLocTypeCol.setCellValueFactory(new PropertyValueFactory<Location,Integer>("locationType"));
    	
    	ObservableList<Location> locData = FXCollections.observableArrayList(new Location(123.56, 456.789, "A location", 0), new Location(234.45,234.67, "B location", 2)); // create the data
    	allLocationsTable.setItems(locData);
    	
    	toiletDisCol.setCellValueFactory(new PropertyValueFactory<Toilet, Boolean>("forDisabled"));
    	toiletLatCol.setCellValueFactory(new PropertyValueFactory<Toilet, Double>("latitude"));
    	toiletLongCol.setCellValueFactory(new PropertyValueFactory<Toilet, Double>("longitude"));
    	toiletUniCol.setCellValueFactory(new PropertyValueFactory<Toilet, Boolean>("uniSex"));
    	toiletNameCol.setCellValueFactory(new PropertyValueFactory<Toilet, String>("name"));
    	
    	ObservableList<Toilet> toiletData = FXCollections.observableArrayList(new Toilet(123.56, 456.789, "A toilet", true, false), new Toilet(234.45, 234.67, "B toilet", false, true)); // create the data
    	toiletsTable.setItems(toiletData);
    	
    	wifiLatCol.setCellValueFactory(new PropertyValueFactory<Wifi, Double>("latitude"));
    	wifiProvCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("provider"));
    	wifiLongCol.setCellValueFactory(new PropertyValueFactory<Wifi, Double>("longitude"));
    	wifiTypeCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("type"));
    	wifiNameCol.setCellValueFactory(new PropertyValueFactory<Wifi, String>("name"));
    	
    	ObservableList<Wifi> wifiData = FXCollections.observableArrayList(new Wifi(123.56, 456.789, "A wifi", "A borough", "Free", "Provider A"), new Wifi(234.45, 234.67, "B wifi", "B borough", "Expensive", "Provider B")); // create the data
    	wifiTable.setItems(wifiData);
    	
    	routeEndCol.setCellValueFactory(new PropertyValueFactory<Route, String>("endString"));
    	routeStartCol.setCellValueFactory(new PropertyValueFactory<Route, String>("startString"));
    	routeDistCol.setCellValueFactory(new PropertyValueFactory<Route, Double>("distance"));
    	routeNameCol.setCellValueFactory(new PropertyValueFactory<Route, String>("name"));
    	
    	Location start = new Location(123.56, 456.789, "A location", 0);
    	Location end = new Location(234.45,234.67, "B location", 2);
    	
    	ObservableList<Route> routeData = FXCollections.observableArrayList(new Route("id 1", start, end, "A route", "female"), new Route("id 2", start, end, "B route", "male")); // create the data
    	routesTable.setItems(routeData);
    	
    	retLatCol.setCellValueFactory(new PropertyValueFactory<Retailer, Double>("latitude"));
    	retDescCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("desrciption"));
    	retLongCol.setCellValueFactory(new PropertyValueFactory<Retailer, Double>("longitude"));
    	retProductCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("product"));
    	retNameCol.setCellValueFactory(new PropertyValueFactory<Retailer, String>("name"));
    	
    	ObservableList<Retailer> retData = FXCollections.observableArrayList(new Retailer(123.56, 456.789, "A retailer", "A product", "A description", 1), new Retailer(234.45, 234.67, "B retailer", "B product", "B desription", 2)); // create the data
    	retailersTable.setItems(retData);
    	
    	poiLatCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("latitude"));
    	poiDescCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("description"));
    	poiLongCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("longitude"));
    	poiNameCol.setCellValueFactory(new PropertyValueFactory<Poi, String>("name"));
    	poiCostCol.setCellValueFactory(new PropertyValueFactory<Poi, Double>("cost"));
    	
    	ObservableList<Poi> poiData = FXCollections.observableArrayList(new Poi(123.56, 456.789, "A poi", "A description", 1.00), new Poi(234.45, 234.67, "B poi", "B desription", 2.00)); // create the data
    	poiTable.setItems(poiData);
    	    	
        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocLatCol != null : "fx:id=\"allLocLatCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocLongCol != null : "fx:id=\"allLocLongCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocNameCol != null : "fx:id=\"allLocNameCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocTypeCol != null : "fx:id=\"allLocTypeCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocationsTable != null : "fx:id=\"allLocationsTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
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
        assert routeDistCol != null : "fx:id=\"routeDIstCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
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
