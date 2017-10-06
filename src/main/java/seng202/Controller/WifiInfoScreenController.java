package seng202.Controller;

import com.lynden.gmapsfx.MapReadyListener;
import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Map;
import seng202.Model.Wifi;
import seng202.team5.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class WifiInfoScreenController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addressLabel;

    @FXML
    private Label boroughLabel;

    @FXML
    private TextField boroughText;

    @FXML
    private Label latLabel;

    @FXML
    private Label longLabel;

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

    @FXML
    private Button cancelButton;

    @FXML
    private Button showOnMapBtn;

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
    	boroughText.setVisible(true);
    	boroughText.setText(wifi.getBorough());
    	ssidText.setVisible(true);
    	ssidText.setText(wifi.getSsid());
    	typeText.setVisible(true);
    	typeText.setText(wifi.getType());
    	providerText.setVisible(true);
    	providerText.setText(wifi.getProvider());
    	nameText.setVisible(true);
    	nameText.setText(wifi.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(wifi.getZip()));;
    	boroughLabel.setText("Borough: ");
    	ssidLabel.setText("Ssid: ");
    	typeLabel.setText("Type: ");
    	providerLabel.setText("Provider: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	saveButton.setVisible(true);
    	cancelButton.setVisible(true);
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

    	nameLabel.setTextFill(Color.BLACK);
        ssidLabel.setTextFill(Color.BLACK);
        addressLabel.setTextFill(Color.BLACK);
        latLabel.setTextFill(Color.BLACK);
        longLabel.setTextFill(Color.BLACK);
        boroughLabel.setTextFill(Color.BLACK);
        typeLabel.setTextFill(Color.BLACK);
        providerLabel.setTextFill(Color.BLACK);
        zipLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (nameText.getText().equals("")) {
            nameLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if(ssidText.getText().equals("")) {
    	    ssidLabel.setTextFill(Color.RED);
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
        if (!zipText.getText().equals("") && !isInt(zipText.getText())) {
    	    zipLabel.setTextFill(Color.RED);
    	    allValid = false;
    	}
        
        if (allValid) {
            wifi.setBorough(boroughText.getText());
            wifi.setName(nameText.getText());
            wifi.setSsid(ssidText.getText());
            wifi.setProvider(providerText.getText());
            wifi.setType(typeText.getText());
            if (!zipText.getText().equals("")) {
                wifi.setZip(Integer.parseInt(zipText.getText()));
            }
            // TODO: Update the database

            cancelPressed(event);
        }
    }

    @FXML
    void cancelPressed (ActionEvent event) {
        nameLabel.setText("Name: " + wifi.getName());
        nameText.setVisible(false);
        typeLabel.setText("Type: " + wifi.getType());
        typeText.setVisible(false);
        boroughLabel.setText("Borough: " + wifi.getBorough());
        boroughText.setVisible(false);
        providerLabel.setText("Provider: " + wifi.getProvider());
        providerText.setVisible(false);
        zipLabel.setText("Zip: " + wifi.getZip());
        zipText.setVisible(false);
        ssidLabel.setText("Ssid: " + wifi.getSsid());
        ssidText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
    }

    @FXML
    void showWifiOnMap(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
        Parent root = loader.load();

        Main.getStage().setScene(new Scene(root, Main.getStage().getScene().getWidth(), Main.getStage().getScene().getHeight()));
        Main.getStage().setTitle("Map");

        MainScreenController controller = loader.getController();
        controller.getMapView().addMapReadyListener(new MapReadyListener() {
            @Override
            public void mapReady() {
                Map.findWifi(wifi, controller.getMapView().getMap());
                controller.getMapView().getMap().setCenter(new LatLong(wifi.getLatitude(), wifi.getLongitude()));
            }
        });
        Main.getStage().show();
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
