package seng202.team5;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javafx.geometry.Side;


public class MainScreenController {

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
    
    @FXML
    void mapPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) mapButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
    
    @FXML
    void statPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) statButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
		
		primaryStage.setTitle("Statistics");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }


    @FXML
    void accountPressed(ActionEvent event) throws IOException {
    	Stage primaryStage = (Stage) accountButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
		
		primaryStage.setTitle("Profile");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
    }
    
    @FXML
    void logoutPressed(ActionEvent event) throws IOException {
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
    void initialize() {
    	ObservableList<String> locationTypes = FXCollections.observableArrayList("Wifi hotspot","Retailer","Toilets", "Point of interest", "Other");
    	loadRouteMenu.setPopupSide(Side.RIGHT);
    	locationTypeBox.setItems(locationTypes);
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

}