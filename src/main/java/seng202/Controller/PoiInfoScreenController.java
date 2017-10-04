package seng202.Controller;

import java.net.URL;
import java.util.DoubleSummaryStatistics;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Poi;



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
    private TextField latitudeText;

    @FXML
    private TextField longitudeText;

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
    	addressText.setVisible(true);
    	addressText.setText(poi.getAddress());
    	boroughText.setVisible(true);
    	boroughText.setText(poi.getBorough());
    	costText.setVisible(true);
    	costText.setText(String.valueOf(poi.getCost()));
    	descriptionText.setVisible(true);
    	descriptionText.setText(poi.getDescription());
    	latitudeText.setVisible(true);
    	latitudeText.setText(String.valueOf(poi.getLatitude()));
    	longitudeText.setVisible(true);
    	longitudeText.setText(String.valueOf(poi.getLongitude()));
    	nameText.setVisible(true);
    	nameText.setText(poi.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(poi.getZip()));;
    	addressLabel.setText("Address: ");
    	boroughLabel.setText("Borough: ");
    	costLabel.setText("Cost: ");
    	descriptionLabel.setText("Description: ");
    	latLabel.setText("Latitude: ");
    	longLabel.setText("Longitude: ");
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
    	if (addressText.getText().equals("") && (latitudeText.getText().equals("") || longitudeText.getText().equals(""))) {
            addressLabel.setTextFill(Color.RED);
            latLabel.setTextFill(Color.RED);
            longLabel.setTextFill(Color.RED);
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
        if (!costText.getText().equals("") && !isDouble(costText.getText())) {
            costLabel.setTextFill(Color.RED);
            allValid = false;
    	}

        if (allValid) {
    	    if (addressText.getText().equals("")) {
    	        poi.setLongitude(Double.parseDouble(longitudeText.getText()));
    	        poi.setLatitude(Double.parseDouble(latitudeText.getText()));
            } else {
                poi.setAddress(addressText.getText());
            }
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
        latLabel.setText("Latitude: " + poi.getLatitude());
        latitudeText.setVisible(false);
        longLabel.setText("Longitude: " + poi.getLongitude());
        longitudeText.setVisible(false);
        nameLabel.setText("Name: " + poi.getName());
        nameText.setVisible(false);
        zipLabel.setText("Zip: " + poi.getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + poi.getBorough());
        boroughText.setVisible(false);
        addressLabel.setText("Address: " + poi.getAddress());
        addressText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
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





