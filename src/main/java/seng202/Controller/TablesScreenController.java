package seng202.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class TablesScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private TableView<?> allLocationsTable;

    @FXML
    private Button logoutButton;

    @FXML
    private Button mapButton;

    @FXML
    private TableView<?> poiTable;

    @FXML
    private TableView<?> retailersTable;

    @FXML
    private TableView<?> routesTable;

    @FXML
    private Button searchButton;

    @FXML
    private Button statButton;

    @FXML
    private Button tableButton;

    @FXML
    private ChoiceBox<String> tableOptions;

    @FXML
    private TableView<?> toiletsTable;

    @FXML
    private TableView<?> wifiTable;


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
    	ObservableList<String> locationTypes = FXCollections.observableArrayList("All locations","Retailers", "WiFi", "Toilets", "Points of interest", "Routes");
    	tableOptions.setItems(locationTypes);
		
        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert allLocationsTable != null : "fx:id=\"allLocationsTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert poiTable != null : "fx:id=\"poiTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert retailersTable != null : "fx:id=\"retailersTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert routesTable != null : "fx:id=\"routesTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert statButton != null : "fx:id=\"statButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert tableButton != null : "fx:id=\"tableButton\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert tableOptions != null : "fx:id=\"tableOptions\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert toiletsTable != null : "fx:id=\"toiletsTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";
        assert wifiTable != null : "fx:id=\"wifiTable\" was not injected: check your FXML file 'TablesScreen.fxml'.";


    }

}
