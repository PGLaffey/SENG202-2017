package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Wifi;


public class WifiInfoScreenController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addressLabel;

    @FXML
    private TextField addressText;

    @FXML
    private Label boroughLabel;

    @FXML
    private TextField boroughText;

    @FXML
    private Label latLabel;

    @FXML
    private TextField latitudeText;

    @FXML
    private Label longLabel;

    @FXML
    private TextField longitudeText;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameText;

    @FXML
    private Button okButton;

    @FXML
    private Label providerLabel;

    @FXML
    private TextField providerText;

    @FXML
    private Button saveButton;

    @FXML
    private Label ssidLabel;

    @FXML
    private TextField ssidText;

    @FXML
    private Label typeLabel;

    @FXML
    private TextField typeText;

    @FXML
    private Button updateButton;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipText;

    
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
    
    /**
     * Method when the update button is pressed, displays screen to update selected wifi point
     * @param event
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	addressText.setVisible(true);
    	addressText.setText(wifi.getAddress());
    	boroughText.setVisible(true);
    	boroughText.setText(wifi.getBorough());
    	ssidText.setVisible(true);
    	ssidText.setText(wifi.getSsid());
    	typeText.setVisible(true);
    	typeText.setText(wifi.getType());
    	providerText.setVisible(true);
    	providerText.setText(wifi.getProvider());
    	latitudeText.setVisible(true);
    	latitudeText.setText(String.valueOf(wifi.getLatitude()));
    	longitudeText.setVisible(true);
    	longitudeText.setText(String.valueOf(wifi.getLongitude()));
    	nameText.setVisible(true);
    	nameText.setText(wifi.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(wifi.getZip()));;
    	addressLabel.setText("Address: ");
    	boroughLabel.setText("Borough: ");
    	ssidLabel.setText("Ssid: ");
    	typeLabel.setText("Type: ");
    	providerLabel.setText("Provider: ");
    	latLabel.setText("Latitude: ");
    	longLabel.setText("Longitude: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	saveButton.setVisible(true);
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
     * Method when the save button is pressed
     * @param event
     */
    @FXML
    void savePressed(ActionEvent event) {
    	// TODO: Check user has inputed the correct data
    	nameLabel.setTextFill(Color.BLACK);
        ssidLabel.setTextFill(Color.BLACK);
        addressLabel.setTextFill(Color.BLACK);
        latLabel.setTextFill(Color.BLACK);
        longLabel.setTextFill(Color.BLACK);
        boroughLabel.setTextFill(Color.BLACK);
        typeLabel.setTextFill(Color.BLACK);
        providerLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (nameText.getText().equals("")) {
            nameLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(ssidText.getText().equals("")) {
    	    ssidLabel.setTextFill(Color.RED);
    	    allValid = false;
    	}
    	if (addressText.getText().equals("") && (latitudeText.getText().equals("") || longitudeText.getText().equals(""))) {
            addressLabel.setTextFill(Color.RED);
            latLabel.setTextFill(Color.RED);
            longLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(boroughText.getText().equals("")) {
            boroughLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(typeText.getText().equals("")) {
            typeLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (providerText.getText().equals("")) {
            providerLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (addressText.getText().equals("") && !isDouble(latitudeText.getText())) {
            latLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (addressText.getText().equals("") && !isDouble(longitudeText.getText())) {
            longLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!zipText.getText().equals("") && !isInt(zipText.getText())) {
    	    zipLabel.setTextFill(Color.RED);
    	    allValid = false;
    	}
    	// TODO: Update the poi instance and in database
        
        if (allValid) {
        	wifi.setAddress(addressText.getText());
        	wifi.setBorough(boroughText.getText());
        	wifi.setName(nameText.getText());
        	wifi.setZip(Integer.parseInt(zipText.getText()));
        }
    	// TODO: Create a whole heap of setters

    	// TODO: Redisplay the label and hide text fields and change buttons
    	
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
