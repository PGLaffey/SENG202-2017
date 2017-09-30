package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Toilet;



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
    private TextField disabledText;

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
    private Button saveButton;

    @FXML
    private Label unisexLabel;

    @FXML
    private TextField unisexText;

    @FXML
    private Button updateButton;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipText;

    private Toilet toilet;


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
     * Method when the update button is pressed, displays screen to update the selected toilet
     * @param event
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	addressText.setVisible(true);
    	addressText.setText(toilet.getAddress());
    	boroughText.setVisible(true);
    	boroughText.setText(toilet.getBorough());
    	unisexText.setVisible(true);
    	unisexText.setText(String.valueOf(toilet.getUniSex()));
    	disabledText.setVisible(true);
    	disabledText.setText(String.valueOf(toilet.getForDisabled()));
    	latitudeText.setVisible(true);
    	latitudeText.setText(String.valueOf(toilet.getLatitude()));
    	longitudeText.setVisible(true);
    	longitudeText.setText(String.valueOf(toilet.getLongitude()));
    	nameText.setVisible(true);
    	nameText.setText(toilet.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(toilet.getZip()));;
    	addressLabel.setText("Address: ");
    	boroughLabel.setText("Borough: ");
    	unisexLabel.setText("Unisex: ");
    	disabledLabel.setText("Disabled: ");
    	latLabel.setText("Latitude: ");
    	longLabel.setText("Longitude: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	saveButton.setVisible(true);
    }
    
    /**
     * Method when the save button is pressed, updates the selected location
     * @param event
     */
    @FXML
    void savePressed(ActionEvent event) {
    }

    @FXML
    void initialize() {
    	toilet = CurrentStorage.getToilet();
    	disabledLabel.setText("Disabled: " + String.valueOf(toilet.getForDisabled()));
    	latLabel.setText("Latitude: " + toilet.getLatitude());
    	longLabel.setText("Longitude " + toilet.getLongitude());
    	nameLabel.setText("Name: " + toilet.getName());
    	unisexLabel.setText("Uni Sex: " + String.valueOf(toilet.getUniSex()));
    	zipLabel.setText("Zip: " + toilet.getZip());
    	boroughLabel.setText("Borough: " + toilet.getBorough());
    	addressLabel.setText("Address: " + toilet.getAddress());
    	
        assert disabledLabel != null : "fx:id=\"disabledLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert unisexLabel != null : "fx:id=\"unisexLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";


    }

}



