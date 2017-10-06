package seng202.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.ResourceBundle;

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
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.*;
import seng202.team5.Main;


public class PoiInfoScreenController {

    @FXML
    private TextField boroughText;

    @FXML
    private TextField costText;

    @FXML
    private TextField descriptionText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField zipText;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label costLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label latLabel;

    @FXML
    private Label longLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label zipLabel;

    @FXML
    private Label boroughLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Button okButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button showOnMapBtn;
    
    private Poi poi;


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
     * Method for when the update button is pressed, displays screen for updating selected poi
     * @param event
     */
    @FXML
    void updatePressed(ActionEvent event) {

    	boroughText.setVisible(true);
    	boroughText.setText(poi.getBorough());
    	costText.setVisible(true);
    	costText.setText(String.valueOf(poi.getCost()));
    	descriptionText.setVisible(true);
    	descriptionText.setText(poi.getDescription());
    	nameText.setVisible(true);
    	nameText.setText(poi.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(poi.getZip()));
    	boroughLabel.setText("Borough: ");
    	costLabel.setText("Cost: ");
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
    void savePressed(ActionEvent event) {

    	nameLabel.setTextFill(Color.BLACK);
    	addressLabel.setTextFill(Color.BLACK);
    	longLabel.setTextFill(Color.BLACK);
    	latLabel.setTextFill(Color.BLACK);
    	costLabel.setTextFill(Color.BLACK);
    	descriptionLabel.setTextFill(Color.BLACK);
    	zipLabel.setTextFill(Color.BLACK);
    	boroughLabel.setTextFill(Color.BLACK);
    	
    	boolean allValid = true;
    	
    	if (nameText.getText().equals("")) {
    		nameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
        if(costText.getText().equals("")) {
            costLabel.setTextFill(Color.RED);
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
        if (!costText.getText().equals("") && !isDouble(costText.getText())) {
            costLabel.setTextFill(Color.RED);
            allValid = false;
    	}

        if (allValid) {
        	poi.setBorough(boroughText.getText());
        	poi.setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
                poi.setZip(Integer.parseInt(zipText.getText()));
            }
            poi.setDescription(descriptionText.getText());
        	poi.setCost(Double.parseDouble(costText.getText()));

        	// TODO: Update database

        	cancelPressed(event);
        }
    }
    
    @FXML
    void cancelPressed(ActionEvent event) {
        costLabel.setText("Cost: $" + poi.getCost());
        costText.setVisible(false);
        descriptionLabel.setText("Description: " + poi.getDescription());
        descriptionText.setVisible(false);
        nameLabel.setText("Name: " + poi.getName());
        nameText.setVisible(false);
        zipLabel.setText("Zip: " + poi.getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + poi.getBorough());
        boroughText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
    }

    @FXML
    void showPoiOnMap(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
        Parent root = loader.load();

        Main.getStage().setScene(new Scene(root, Main.getStage().getScene().getWidth(), Main.getStage().getScene().getHeight()));
        Main.getStage().setTitle("Map");

        MainScreenController controller = loader.getController();
        controller.getMapView().addMapReadyListener(new MapReadyListener() {
            @Override
            public void mapReady() {
                controller.getMapView().getMap().clearMarkers();
                Map.findPoi(poi, controller.getMapView().getMap());
                controller.getMapView().getMap().setCenter(new LatLong(poi.getLatitude(), poi.getLongitude()));
                controller.getMapView().getMap().addMarker(
                        new Marker(
                        new MarkerOptions().animation(Animation.DROP)
                        .position(new LatLong(poi.getLatitude(), poi.getLongitude()))
                        )
                );
                ArrayList<Location> nearby = Map.findNearby(poi.getLatitude(), poi.getLongitude());

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
    	poi = CurrentStorage.getPoi();
    	costLabel.setText("Cost: $" + poi.getCost());
    	descriptionLabel.setText("Description: " + poi.getDescription());
    	latLabel.setText("Latitude: " + poi.getLatitude());
    	longLabel.setText("Longitude: " + poi.getLongitude());
    	nameLabel.setText("Name: " + poi.getName());
    	zipLabel.setText("Zip: " + poi.getZip());
    	boroughLabel.setText("Borough: " + poi.getBorough());
    	addressLabel.setText("Address: " + poi.getAddress());
    	
        assert costLabel != null : "fx:id=\"costLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";


    }

}





