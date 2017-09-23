package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Wifi;


public class WifiInfoScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label boroughLabel;

    @FXML
    private Label latLabel;

    @FXML
    private Label longLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label providerLabel;

    @FXML
    private Label zipLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Label ssidLabel;

    @FXML
    private Label typeLabel;
    
    private Wifi wifi;


    /**
     * Method when the ok button is pressed, hides the pop up.
     * @param event
     */
    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }

    @FXML
    void initialize() {
    	wifi = CurrentStorage.getWifi();
    	nameLabel.setText("Name: " + wifi.getName());
    	typeLabel.setText("Type: " + wifi.getType());
    	boroughLabel.setText("Borough: " + wifi.getBorough());
    	latLabel.setText("Latitude: " + wifi.getLatitude());
    	longLabel.setText("Longitude: " + wifi.getLongitude());
    	providerLabel.setText("Provider: " + wifi.getProvider());
    	zipLabel.setText("Zip: " + wifi.getZip());
    	addressLabel.setText("Address: " + wifi.getAddress());
    	ssidLabel.setText("Ssid: " + wifi.getSsid());
    	
        assert boroughLabel != null : "fx:id=\"boroughLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert providerLabel != null : "fx:id=\"providerLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert typeLabel != null : "fx:id=\"typeLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";


    }

}
