package seng202.Controller;


import com.lynden.gmapsfx.MapReadyListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import java.net.URL;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.RET;
import com.sun.org.apache.regexp.internal.RE;
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



public class RetailerInfoScreenController {

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
    private Label descriptionLabel;

    @FXML
    private TextField descriptionText;

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
    private Label productLabel;

    @FXML
    private TextField productText;

    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button showOnMapButton;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipText;
    
    private Integer retailerIndex;
    private Retailer oldRetailer;


    /**
     * Method when the ok button is pressed, hides the pop up.
     * @param event Auto-generate event on button press
     */
    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }
    
    
    /** 
     * Method when the update button is pressed, opens screen for user to update the selected poi
     * @param event Auto-generate event on button press
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	boroughText.setVisible(true);
    	boroughText.setText(CurrentStorage.getRetailerArray().get(retailerIndex).getBorough());
    	productText.setVisible(true);
    	productText.setText(CurrentStorage.getRetailerArray().get(retailerIndex).getProduct());
    	descriptionText.setVisible(true);
    	descriptionText.setText(CurrentStorage.getRetailerArray().get(retailerIndex).getDescription());
    	nameText.setVisible(true);
    	nameText.setText(CurrentStorage.getRetailerArray().get(retailerIndex).getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(CurrentStorage.getRetailerArray().get(retailerIndex).getZip()));;
    	boroughLabel.setText("Borough: ");
    	productLabel.setText("Product: ");
    	descriptionLabel.setText("Description: ");
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

    @FXML
    void showRetailerOnMap(ActionEvent event) throws IOException{
        Retailer retailer = CurrentStorage.getRetailerArray().get(retailerIndex);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
        Parent root = loader.load();

        Main.getStage().setScene(new Scene(root, Main.getStage().getScene().getWidth(), Main.getStage().getScene().getHeight()));
        Main.getStage().setTitle("Map");

        MainScreenController controller = loader.getController();
        controller.getMapView().addMapReadyListener(new MapReadyListener() {
            @Override
            public void mapReady() {
                controller.getMapView().getMap().clearMarkers();
                Map.findRetailers(retailer, controller.getMapView().getMap());
                controller.getMapView().getMap().setCenter(new LatLong(retailer.getLatitude(), retailer.getLongitude()));
                controller.getMapView().getMap().addMarker(
                        new Marker(
                                new MarkerOptions().animation(Animation.DROP)
                                        .position(new LatLong(retailer.getLatitude(), retailer.getLongitude()))
                        )
                );
                ArrayList<Location> nearby = Map.findNearby(retailer.getLatitude(), retailer.getLongitude());

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


    
    
    /**
     * TODO add docstring
     * @param event Auto-generate event on button press
     */
    @FXML
    void savePressed(ActionEvent event) {
    	
    	nameLabel.setTextFill(Color.BLACK);
        addressLabel.setTextFill(Color.BLACK);
        latLabel.setTextFill(Color.BLACK);
        longLabel.setTextFill(Color.BLACK);
        productLabel.setTextFill(Color.BLACK);
        descriptionLabel.setTextFill(Color.BLACK);
        zipLabel.setTextFill(Color.BLACK);
        boroughLabel.setTextFill(Color.BLACK);
        
        boolean allValid = true;

    	if (nameText.getText().equals("")) {
    		nameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
        if (productText.getText().equals("")) {
            productLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (descriptionText.getText().equals("")) {
            descriptionLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!zipText.getText().equals("") && !isInt(zipText.getText())) {
            zipLabel.setTextFill(Color.RED);
            allValid = false;
        }

        if (allValid) {
            CurrentStorage.getRetailerArray().get(retailerIndex).setBorough(boroughText.getText());
            CurrentStorage.getRetailerArray().get(retailerIndex).setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
                CurrentStorage.getRetailerArray().get(retailerIndex).setZip(Integer.parseInt(zipText.getText()));

        	}
            CurrentStorage.getRetailerArray().get(retailerIndex).setDescription(descriptionText.getText());
            CurrentStorage.getRetailerArray().get(retailerIndex).setProduct(productText.getText());

        	// TODO: Work out how to update the database

            DataFetcher exporter = new DataFetcher();
            try {
                exporter.connectDb();
                exporter.updateLocation(oldRetailer, CurrentStorage.getRetailerArray().get(retailerIndex));
                exporter.closeConnection();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            cancelPressed(event);
        }
    }
    
    
    //TODO Add docstring
    @FXML
    void cancelPressed(ActionEvent event) {
    	nameLabel.setText("Name: " + CurrentStorage.getRetailerArray().get(retailerIndex).getName());
    	nameText.setVisible(false);
    	zipLabel.setText("Zip: " + CurrentStorage.getRetailerArray().get(retailerIndex).getZip());
    	zipText.setVisible(false);
    	boroughLabel.setText("Borough: " + CurrentStorage.getRetailerArray().get(retailerIndex).getBorough());
    	boroughText.setVisible(false);
    	productLabel.setText("Product: " + CurrentStorage.getRetailerArray().get(retailerIndex).getProduct());
    	productText.setVisible(false);
    	descriptionLabel.setText("Description: " + CurrentStorage.getRetailerArray().get(retailerIndex).getDescription());
    	descriptionText.setVisible(false);
    	okButton.setVisible(true);
    	updateButton.setVisible(true);
    	saveButton.setVisible(false);
    	cancelButton.setVisible(false);
    }
    

    //TODO add docstring
    @FXML
    void initialize() {
        oldRetailer = new Retailer(CurrentStorage.getRetailerArray().get(CurrentStorage.getRetailerIndex()));
        retailerIndex = CurrentStorage.getRetailerIndex();

    	nameLabel.setText("Name: " + CurrentStorage.getRetailerArray().get(retailerIndex).getName());
    	addressLabel.setText("Address: " + CurrentStorage.getRetailerArray().get(retailerIndex).getAddress());
    	latLabel.setText("Latitude: " + CurrentStorage.getRetailerArray().get(retailerIndex).getLatitude());
    	longLabel.setText("Longitude: " + CurrentStorage.getRetailerArray().get(retailerIndex).getLongitude());
    	zipLabel.setText("Zip: " + CurrentStorage.getRetailerArray().get(retailerIndex).getZip());
    	boroughLabel.setText("Borough: " + CurrentStorage.getRetailerArray().get(retailerIndex).getBorough());
    	productLabel.setText("Product: " + CurrentStorage.getRetailerArray().get(retailerIndex).getProduct());
    	descriptionLabel.setText("Description: " + CurrentStorage.getRetailerArray().get(retailerIndex).getDescription());
    	
        assert addressLabel != null : "fx:id=\"addressLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert zipLabel != null : "fx:id=\"zipLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
    }
}
