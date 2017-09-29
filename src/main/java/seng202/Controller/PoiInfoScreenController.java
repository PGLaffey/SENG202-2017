package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    	boroughLabel.setText("Label: ");
    	costLabel.setText("Cost: ");
    	descriptionLabel.setText("Description: ");
    	latLabel.setText("Latitude: ");
    	longLabel.setText("Longitude: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	saveButton.setVisible(true);
    }
    
    @FXML
    void savePressed(ActionEvent event) {
    	// TODO: Check user has inputed the correct data
    	// TODO: Update the poi
    	// TODO: Create a whole heap of setters
    	poi.setAddress(addressText.getText());
    	poi.setBorough(boroughText.getText());
    	poi.setName(nameText.getText());
    	poi.setZip(Integer.parseInt(zipText.getText()));
    	// TODO: Redisplay the label and hide text fields and change buttons
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





