package seng202.Controller;

import com.lynden.gmapsfx.MapReadyListener;
import com.lynden.gmapsfx.javascript.object.Animation;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

/**
 * Controller class for the toilet information screen.
 */
public class ToiletInfoScreenController {

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
    private Label disabledLabel;

    @FXML
    private ChoiceBox<Boolean> disabledChoice;

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
    private Button saveButton;

    @FXML
    private Label unisexLabel;

    @FXML
    private ChoiceBox<Boolean> unisexChoice;

    @FXML
    private Button updateButton;

    @FXML
    private Button showOnMapBtn;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipText;

    @FXML
    private Button cancelButton;

    @FXML
    private Button deleteButton;

    private Toilet oldToilet;
    private Toilet newToilet;

    private boolean owner = false;


    /**
     * Method when the ok button is pressed, hides the pop up.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }
    
    
    /**
     * Method when the update button is pressed, displays text fields to update the selected 
     * toilet.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void updatePressed(ActionEvent event) {
        addressText.setVisible(true);
        addressText.setText(newToilet.getAddress());
    	boroughText.setVisible(true);
    	boroughText.setText(newToilet.getBorough());
    	unisexChoice.setVisible(true);
    	unisexChoice.setValue(newToilet.getUniSex());
    	disabledChoice.setVisible(true);
    	disabledChoice.setValue(newToilet.getForDisabled());
    	nameText.setVisible(true);
    	nameText.setText(newToilet.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(newToilet.getZip()));;
    	boroughLabel.setText("Borough: ");
    	unisexLabel.setText("Unisex: ");
    	disabledLabel.setText("Disabled: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	deleteButton.setVisible(false);
    	saveButton.setVisible(true);
    	cancelButton.setVisible(true);
    }

    
    /**
     * Checks the input is able to be parsed to an Integer.
     * @param s - String to be checked
     * @return  - True if Integer otherwise false
     */
    Boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } 
        catch (Exception e){
            return false;
        }
    }
    
    
    /**
     * Method for when the save button is pressed while a user is updating a toilet.
     * Checks that the inputed data is valid then updates the toilet in the list and database.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void savePressed(ActionEvent event) {

    	nameLabel.setTextFill(Color.BLACK);
        addressLabel.setTextFill(Color.BLACK);
        latLabel.setTextFill(Color.BLACK);
        longLabel.setTextFill(Color.BLACK);
        disabledLabel.setTextFill(Color.BLACK);
        unisexLabel.setTextFill(Color.BLACK);
        zipLabel.setTextFill(Color.BLACK);
        boroughLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (nameText.getText().equals("")) {
    		nameLabel.setTextFill(Color.RED);
    		allValid = false;
        }
        if(disabledChoice.getSelectionModel().isEmpty()) {
            disabledLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (unisexChoice.getSelectionModel().isEmpty()) {
            unisexLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!zipText.getText().equals("") && !isInt(zipText.getText())) {
            zipLabel.setTextFill(Color.RED);
            allValid = false;
        }

        if (allValid) {
    	    if (addressText.getText() != null && !addressText.getText().equals(oldToilet.getAddress())) {
    	        newToilet.setAddress(addressText.getText());
                double[] latLong = Map.getLatLong(addressText.getText());
                newToilet.setLatitude(latLong[0]);
                newToilet.setLongitude(latLong[1]);
            }
            newToilet.setBorough(boroughText.getText());
            newToilet.setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
        		newToilet.setZip(Integer.parseInt(zipText.getText()));
            }
            newToilet.setForDisabled(disabledChoice.getValue());
            newToilet.setUniSex(unisexChoice.getValue());
            DataFetcher exporter = new DataFetcher();
            try {
                exporter.connectDb();
                exporter.updateLocation(oldToilet, newToilet);
                exporter.closeConnection();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        	cancelPressed(event);
        }
    }

    
    /**
     * Method for when cancel is pressed while a user is updating a toilet.
     * Ignores changes, hides the text fields and re displays full labels.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void cancelPressed (ActionEvent event) {
        addressLabel.setText("Address: " + newToilet.getAddress());
        addressText.setVisible(false);
        latLabel.setText("Latitude: " + newToilet.getLatitude());
        longLabel.setText("Longitude: " + newToilet.getLongitude());
        disabledLabel.setText("Disabled: " + String.valueOf(newToilet.getForDisabled()));
        disabledChoice.setVisible(false);
        nameLabel.setText("Name: " + newToilet.getName());
        nameText.setVisible(false);
        unisexLabel.setText("Uni Sex: " + newToilet.getUniSex());
        unisexChoice.setVisible(false);
        zipLabel.setText("Zip: " + newToilet.getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + newToilet.getBorough());
        boroughText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
        deleteButton.setVisible(owner);
    }


    /**
     * Method for when a toilet is deleted. Removes it from the database, makes its index in 
     * toilet array list null and closes the pop up.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void deletePressed(ActionEvent event) {
        DataFetcher df = new DataFetcher();
        try {
            df.connectDb();
            df.deleteLocation(newToilet);
            df.closeConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        CurrentStorage.getToiletArray().set(CurrentStorage.getToiletIndex(), null);
        Stage stage = (Stage) deleteButton.getScene().getWindow();
        stage.hide();
    }

    
    /**
     * Shows all toilets on the map.
     * @param event - Auto-generate event on button press
     */
    @FXML
    void showToiletOnMap(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
        Parent root = loader.load();
        Toilet toilet = newToilet;

        Main.getStage().setScene(new Scene(root, Main.getStage().getScene().getWidth(),
        		Main.getStage().getScene().getHeight()));
        Main.getStage().setTitle("Map");

        MainScreenController controller = loader.getController();
        controller.getMapView().addMapReadyListener(new MapReadyListener() {
            @Override
            public void mapReady() {
                controller.getMapView().getMap().clearMarkers();
                Map.findToilets(toilet, controller.getMapView().getMap());
                controller.getMapView().getMap().setCenter(new LatLong(toilet.getLatitude(), 
                		toilet.getLongitude()));
                controller.getMapView().getMap().addMarker(
                        new Marker(
                                new MarkerOptions().animation(Animation.DROP).position(
                                		new LatLong(toilet.getLatitude(), toilet.getLongitude()))
                        ));
                ArrayList<Location> nearby = Map.findNearby(toilet.getLatitude(), 
                		toilet.getLongitude());

                for (Location loc : nearby) {
                    if (loc.getLocationType() == 0) {
                        Map.findToilets((Toilet) loc, controller.getMapView().getMap());
                    } 
                    else if (loc.getLocationType() == 1) {
                        Map.findPoi((Poi) loc, controller.getMapView().getMap());
                    } 
                    else if (loc.getLocationType() == 2) {
                        Map.findRetailers((Retailer) loc, controller.getMapView().getMap());
                    } 
                    else if (loc.getLocationType() == 3) {
                        Map.findWifi((Wifi) loc, controller.getMapView().getMap());
                    }
                }
            }
        });

        Main.getStage().show();
    }


    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {
        newToilet = CurrentStorage.getToiletArray().get(CurrentStorage.getToiletIndex());
        oldToilet = new Toilet(newToilet);

        DataFetcher df = new DataFetcher();
        try {
            df.connectDb();
            if (df.getLocationOwner(newToilet) != null && df.getLocationOwner(newToilet)
            		.equals(CurrentStorage.getUser().getUsername())) {
                owner = true;
            }
            df.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        deleteButton.setVisible(owner);

    	disabledLabel.setText("Disabled: " + String.valueOf(newToilet.getForDisabled()));
    	latLabel.setText("Latitude: " + newToilet.getLatitude());
    	longLabel.setText("Longitude " + newToilet.getLongitude());
    	nameLabel.setText("Name: " + newToilet.getName());
    	unisexLabel.setText("Uni Sex: " + newToilet.getUniSex());
    	zipLabel.setText("Zip: " + newToilet.getZip());
    	boroughLabel.setText("Borough: " + newToilet.getBorough());
    	addressLabel.setText("Address: " + newToilet.getAddress());
    	
    	ObservableList<Boolean> disabledOptions = FXCollections.observableArrayList(true, false);
    	disabledChoice.setItems(disabledOptions);
    	ObservableList<Boolean> unisexOptions = FXCollections.observableArrayList(true, false);
    	unisexChoice.setItems(unisexOptions);
    	
        assert disabledLabel != null : "fx:id=\"disabledLabel\" was not injected: check your"
        		+ " FXML file 'ToiletInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file"
        		+ " 'ToiletInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file"
        		+ " 'ToiletInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file"
        		+ " 'ToiletInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file "
        		+ "'ToiletInfoScreen.fxml'.";
        assert unisexLabel != null : "fx:id=\"unisexLabel\" was not injected: check your FXML"
        		+ " file 'ToiletInfoScreen.fxml'.";
    }
}



