package seng202.Controller;

import com.lynden.gmapsfx.MapReadyListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
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

import seng202.Model.*;
import seng202.team5.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

    private Integer wifiIndex;

    private Wifi oldWifi;



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
    	boroughText.setText(CurrentStorage.getWifiArray().get(wifiIndex).getBorough());
    	ssidText.setVisible(true);
    	ssidText.setText(CurrentStorage.getWifiArray().get(wifiIndex).getSsid());
    	typeText.setVisible(true);
    	typeText.setText(CurrentStorage.getWifiArray().get(wifiIndex).getType());
    	providerText.setVisible(true);
    	providerText.setText(CurrentStorage.getWifiArray().get(wifiIndex).getProvider());
    	nameText.setVisible(true);
    	nameText.setText(CurrentStorage.getWifiArray().get(wifiIndex).getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(CurrentStorage.getWifiArray().get(wifiIndex).getZip()));;
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
            CurrentStorage.getWifiArray().get(wifiIndex).setBorough(boroughText.getText());
            CurrentStorage.getWifiArray().get(wifiIndex).setName(nameText.getText());
            CurrentStorage.getWifiArray().get(wifiIndex).setSsid(ssidText.getText());
            CurrentStorage.getWifiArray().get(wifiIndex).setProvider(providerText.getText());
            CurrentStorage.getWifiArray().get(wifiIndex).setType(typeText.getText());
            if (!zipText.getText().equals("")) {
                CurrentStorage.getWifiArray().get(wifiIndex).setZip(Integer.parseInt(zipText.getText()));
            }
            // TODO: Update the database

            DataFetcher exporter = new DataFetcher();
            try {
                exporter.connectDb();
                exporter.updateLocation(oldWifi, CurrentStorage.getWifiArray().get(wifiIndex));
                exporter.closeConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }

            cancelPressed(event);
        }
    }

    @FXML
    void cancelPressed (ActionEvent event) {
        nameLabel.setText("Name: " + CurrentStorage.getWifiArray().get(wifiIndex).getName());
        nameText.setVisible(false);
        typeLabel.setText("Type: " + CurrentStorage.getWifiArray().get(wifiIndex).getType());
        typeText.setVisible(false);
        boroughLabel.setText("Borough: " + CurrentStorage.getWifiArray().get(wifiIndex).getBorough());
        boroughText.setVisible(false);
        providerLabel.setText("Provider: " + CurrentStorage.getWifiArray().get(wifiIndex).getProvider());
        providerText.setVisible(false);
        zipLabel.setText("Zip: " + CurrentStorage.getWifiArray().get(wifiIndex).getZip());
        zipText.setVisible(false);
        ssidLabel.setText("Ssid: " + CurrentStorage.getWifiArray().get(wifiIndex).getSsid());
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
                controller.getMapView().getMap().clearMarkers();
                Map.findWifi(wifi, controller.getMapView().getMap());
                controller.getMapView().getMap().setCenter(new LatLong(wifi.getLatitude(), wifi.getLongitude()));
                controller.getMapView().getMap().addMarker(
                        new Marker(
                                new MarkerOptions().animation(Animation.DROP)
                                        .position(new LatLong(wifi.getLatitude(), wifi.getLongitude()))
                        )
                );
                ArrayList<Location> nearby = Map.findNearby(wifi.getLatitude(), wifi.getLongitude());

                for (Location loc : nearby) {
                    if (loc.getLocationType() == 0) {
                        Map.findToilets((Toilet) loc, controller.getMapView().getMap());
                    } else if (loc.getLocationType() == 1) {
                        Map.findPoi((Poi) loc, controller.getMapView().getMap());
                    } else if (loc.getLocationType() == 2) {
                        Map.findRetailers((Retailer) loc, controller.getMapView().getMap());
                    } else if (loc.getLocationType() == 3) {
                        Map.findWifi((Wifi) loc, controller.getMapView().getMap());
                    }
                }
            }
        });
        Main.getStage().show();
    }

    @FXML
    void initialize() {
        oldWifi = new Wifi(CurrentStorage.getWifiArray().get(CurrentStorage.getWifiIndex()));
    	wifiIndex = CurrentStorage.getWifiIndex();
    	nameLabel.setText("Name: " + CurrentStorage.getWifiArray().get(wifiIndex).getName());
    	typeLabel.setText("Type: " + CurrentStorage.getWifiArray().get(wifiIndex).getType());
    	boroughLabel.setText("Borough: " + CurrentStorage.getWifiArray().get(wifiIndex).getBorough());
    	latLabel.setText("Latitude: " + CurrentStorage.getWifiArray().get(wifiIndex).getLatitude());
    	longLabel.setText("Longitude: " + CurrentStorage.getWifiArray().get(wifiIndex).getLongitude());
    	providerLabel.setText("Provider: " + CurrentStorage.getWifiArray().get(wifiIndex).getProvider());
    	zipLabel.setText("Zip: " + CurrentStorage.getWifiArray().get(wifiIndex).getZip());
    	addressLabel.setText("Address: " + CurrentStorage.getWifiArray().get(wifiIndex).getAddress());
    	ssidLabel.setText("Ssid: " + CurrentStorage.getWifiArray().get(wifiIndex).getSsid());
    	
        assert boroughLabel != null : "fx:id=\"boroughLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert providerLabel != null : "fx:id=\"providerLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";
        assert typeLabel != null : "fx:id=\"typeLabel\" was not injected: check your FXML file 'WifiInfoScreen.fxml'.";


    }

}
