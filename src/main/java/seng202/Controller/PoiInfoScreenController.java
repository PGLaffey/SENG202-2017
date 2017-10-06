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
    
    private Integer poiIndex;
    private Poi oldPoi;


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
     * Method for when the update button is pressed, displays screen for updating selected poi
     * @param event Auto-generate event on button press
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	boroughText.setVisible(true);
    	boroughText.setText(CurrentStorage.getPoiArray().get(poiIndex).getBorough());
    	costText.setVisible(true);
    	costText.setText(String.valueOf(CurrentStorage.getPoiArray().get(poiIndex).getCost()));
    	descriptionText.setVisible(true);
    	descriptionText.setText(CurrentStorage.getPoiArray().get(poiIndex).getDescription());
    	nameText.setVisible(true);
    	nameText.setText(CurrentStorage.getPoiArray().get(poiIndex).getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(CurrentStorage.getPoiArray().get(poiIndex).getZip()));
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
    
    
    //TODO add docstring
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
            CurrentStorage.getPoiArray().get(poiIndex).setBorough(boroughText.getText());
            CurrentStorage.getPoiArray().get(poiIndex).setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
                CurrentStorage.getPoiArray().get(poiIndex).setZip(Integer.parseInt(zipText.getText()));
            }
            CurrentStorage.getPoiArray().get(poiIndex).setDescription(descriptionText.getText());
            CurrentStorage.getPoiArray().get(poiIndex).setCost(Double.parseDouble(costText.getText()));

        	// TODO: Update database

            DataFetcher exporter = new DataFetcher();
            try {
                exporter.connectDb();
                exporter.updateLocation(oldPoi, CurrentStorage.getPoiArray().get(poiIndex));
                exporter.closeConnection();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            cancelPressed(event);
        }
    }
    
    
    //TODO add docstring
    @FXML
    void cancelPressed(ActionEvent event) {
        costLabel.setText("Cost: $" + CurrentStorage.getPoiArray().get(poiIndex).getCost());
        costText.setVisible(false);
        descriptionLabel.setText("Description: " + CurrentStorage.getPoiArray().get(poiIndex).getDescription());
        descriptionText.setVisible(false);
        nameLabel.setText("Name: " + CurrentStorage.getPoiArray().get(poiIndex).getName());
        nameText.setVisible(false);
        zipLabel.setText("Zip: " + CurrentStorage.getPoiArray().get(poiIndex).getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + CurrentStorage.getPoiArray().get(poiIndex).getBorough());
        boroughText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
    }

    @FXML
    void showPoiOnMap(ActionEvent event) throws IOException {
        Poi poi = CurrentStorage.getPoiArray().get(poiIndex);
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
    
    
    //TODO add docstring
    @FXML
    void initialize() {
        oldPoi = new Poi(CurrentStorage.getPoiArray().get(CurrentStorage.getPoiIndex()));
        poiIndex = CurrentStorage.getPoiIndex();;
    	costLabel.setText("Cost: $" + CurrentStorage.getPoiArray().get(poiIndex).getCost());
    	descriptionLabel.setText("Description: " + CurrentStorage.getPoiArray().get(poiIndex).getDescription());
    	latLabel.setText("Latitude: " + CurrentStorage.getPoiArray().get(poiIndex).getLatitude());
    	longLabel.setText("Longitude: " + CurrentStorage.getPoiArray().get(poiIndex).getLongitude());
    	nameLabel.setText("Name: " + CurrentStorage.getPoiArray().get(poiIndex).getName());
    	zipLabel.setText("Zip: " + CurrentStorage.getPoiArray().get(poiIndex).getZip());
    	boroughLabel.setText("Borough: " + CurrentStorage.getPoiArray().get(poiIndex).getBorough());
    	addressLabel.setText("Address: " + CurrentStorage.getPoiArray().get(poiIndex).getAddress());
    	
        assert costLabel != null : "fx:id=\"costLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
    }
}





