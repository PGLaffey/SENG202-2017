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
    private TableColumn<?, ?> poiCostCol;

    @FXML
    private TableColumn<?, ?> poiDescCol;

    @FXML
    private TableColumn<?, ?> poiLatCol;

    @FXML
    private TableColumn<?, ?> poiLongCol;

    @FXML
    private TableColumn<?, ?> poiNameCol;

    @FXML
    private TableView<Poi> poiTable;

    @FXML
    private TableColumn<?, ?> retDescCol;

    @FXML
    private TableColumn<?, ?> retLatCol;

    @FXML
    private TableColumn<?, ?> retLongCol;

    @FXML
    private TableColumn<?, ?> retNameCol;

    @FXML
    private TableColumn<?, ?> retProductCol;

    @FXML
    private TableView<Retailer> retailersTable;

    @FXML
    private TableColumn<?, ?> routeDIstCol;

    @FXML
    private TableColumn<?, ?> routeEndCol;

    @FXML
    private TableColumn<?, ?> routeNameCol;

    @FXML
    private TableColumn<?, ?> routeStartCol;

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
    private TableColumn<?, ?> toiletDisCol;

    @FXML
    private TableColumn<?, ?> toiletLatCol;

    @FXML
    private TableColumn<?, ?> toiletLongCol;

    @FXML
    private TableColumn<?, ?> toiletNameCol;

    @FXML
    private TableColumn<?, ?> toiletUniCol;

    @FXML
    private TableView<Toilet> toiletsTable;

    @FXML
    private TableColumn<?, ?> wifiLatCol;

    @FXML
    private TableColumn<?, ?> wifiLongCol;

    @FXML
    private TableColumn<?, ?> wifiNameCol;

    @FXML
    private TableColumn<?, ?> wifiProvCol;

    @FXML
    private TableView<Wifi> wifiTable;

    @FXML
    private TableColumn<?, ?> wifiTypeCol;


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
    	
    	ObservableList<String> filterOptions = FXCollections.observableArrayList("All locations","Routes");
    	tableOptions.setItems(filterOptions);
    	
    	
    	// Set up the invoice table
    	allLocNameCol.setCellValueFactory(new PropertyValueFactory<Location,String>("name"));
    	allLocLatCol.setCellValueFactory(new PropertyValueFactory<Location,Double>("latitude"));
    	allLocLongCol.setCellValueFactory(new PropertyValueFactory<Location,Double>("longitude"));
    	allLocTypeCol.setCellValueFactory(new PropertyValueFactory<Location,Integer>("locationType"));
    	
    	ObservableList<Location> data = FXCollections.observableArrayList(new Location(123.56, 456.789, "A location", 0), new Location(234.45,234.67, "B location", 2)); // create the data
    	allLocationsTable.setItems(data);
    	
    	
    	
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
        assert routeDIstCol != null : "fx:id=\"routeDIstCol\" was not injected: check your FXML file 'TablesScreen.fxml'.";
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
