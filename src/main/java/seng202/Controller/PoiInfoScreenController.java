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
    private TextField addressText;

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

    @FXML
    private Button deleteButton;

    private Poi oldPoi;

    private Poi newPoi;

    private boolean owner = false;


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
     * Method for when the update button is pressed, displays text fields for updating selected poi
     * @param event Auto-generate event on button press
     */
    @FXML
    void updatePressed(ActionEvent event) {
        addressText.setVisible(true);
        addressText.setText(newPoi.getAddress());
    	boroughText.setVisible(true);
    	boroughText.setText(newPoi.getBorough());
    	costText.setVisible(true);
    	costText.setText(String.valueOf(newPoi.getCost()));
    	descriptionText.setVisible(true);
    	descriptionText.setText(newPoi.getDescription());
    	nameText.setVisible(true);
    	nameText.setText(newPoi.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(newPoi.getZip()));
        addressLabel.setText("Address:");
    	boroughLabel.setText("Borough: ");
    	costLabel.setText("Cost: ");
    	descriptionLabel.setText("Description: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	deleteButton.setVisible(false);
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
     * Method for when the save button is pressed while a user is updating a poi.
     * Checks that the inputted data is valid then updates the poi in the list and database.
     * @param event
     */
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
    	    if (addressText.getText() != null && !oldPoi.getAddress().equals(addressText.getText())) {
    	        newPoi.setAddress(addressText.getText());
                double[] latLong = Map.getLatLong(addressText.getText());
                newPoi.setLatitude(latLong[0]);
                newPoi.setLongitude(latLong[1]);
            }
            newPoi.setBorough(boroughText.getText());
            newPoi.setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
                newPoi.setZip(Integer.parseInt(zipText.getText()));
            }
            newPoi.setDescription(descriptionText.getText());
            newPoi.setCost(Double.parseDouble(costText.getText()));
            DataFetcher exporter = new DataFetcher();
            try {
                exporter.connectDb();
                exporter.updateLocation(oldPoi, newPoi);
                exporter.closeConnection();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            cancelPressed(event);
        }
    }


    /**
     * Method for when cancel is pressed while a user is updating a poi.
     * Ignores changes, hides the text fields and re displays full labels.
     * @param event
     */
    @FXML
    void cancelPressed(ActionEvent event) {
        addressLabel.setText("Address: " + newPoi.getAddress());
        addressText.setVisible(false);
        costLabel.setText("Cost: $" + newPoi.getCost());
        costText.setVisible(false);
        descriptionLabel.setText("Description: " + newPoi.getDescription());
        descriptionText.setVisible(false);
        nameLabel.setText("Name: " + newPoi.getName());
        nameText.setVisible(false);
        zipLabel.setText("Zip: " + newPoi.getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + newPoi.getBorough());
        boroughText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
        deleteButton.setVisible(owner);
    }

    /**
     * Method for when a poi is deleted.
     * Removes it from the database, makes its index in poi array list null and closes the pop up.
     * @param event
     */
    @FXML
    void deletePressed(ActionEvent event) {
        DataFetcher df = new DataFetcher();
        try {
            df.connectDb();
            df.deleteLocation(newPoi);
            df.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        CurrentStorage.getPoiArray().set(CurrentStorage.getPoiIndex(), null);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void showPoiOnMap(ActionEvent event) throws IOException {
        Poi poi = newPoi;
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
        newPoi = CurrentStorage.getPoiArray().get(CurrentStorage.getPoiIndex());
        oldPoi = new Poi(newPoi);

        DataFetcher df = new DataFetcher();
        try {
            df.connectDb();
            if (df.getLocationOwner(newPoi) != null && df.getLocationOwner(newPoi).equals(CurrentStorage.getUser().getUsername())) {
                owner = true;
            }
            df.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        deleteButton.setVisible(owner);

    	costLabel.setText("Cost: $" + newPoi.getCost());
    	descriptionLabel.setText("Description: " + newPoi.getDescription());
    	latLabel.setText("Latitude: " + newPoi.getLatitude());
    	longLabel.setText("Longitude: " + newPoi.getLongitude());
    	nameLabel.setText("Name: " + newPoi.getName());
    	zipLabel.setText("Zip: " + newPoi.getZip());
    	boroughLabel.setText("Borough: " + newPoi.getBorough());
    	addressLabel.setText("Address: " + newPoi.getAddress());
    	
        assert costLabel != null : "fx:id=\"costLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
    }
}





