package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
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
    private ChoiceBox disabledChoice;

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
    private ChoiceBox unisexChoice;

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
    	unisexChoice.setVisible(true);
    	unisexChoice.setValue(toilet.getUniSex());
    	disabledChoice.setVisible(true);
    	disabledChoice.setValue(toilet.getForDisabled());
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
     * Method when the save button is pressed, updates the selected location
     * @param event
     */
    @FXML
    void savePressed(ActionEvent event) {
    	// TODO: Check user has inputed the correct data
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
    	if (addressText.getText().equals("") && (latitudeText.getText().equals("") || longitudeText.getText().equals(""))) {
            addressLabel.setTextFill(Color.RED);
            latLabel.setTextFill(Color.RED);
            longLabel.setTextFill(Color.RED);
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
    	// TODO: Update the toilet instance and in database
        if (allValid) {
        	toilet.setAddress(addressText.getText());
        	toilet.setBorough(boroughText.getText());
        	toilet.setName(nameText.getText());
        	toilet.setZip(Integer.parseInt(zipText.getText()));
        }
    	// TODO: Create a whole heap of setters

    	// TODO: Redisplay the label and hide text fields and change buttons
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
    	
    	ObservableList<Boolean> disabledOptions = FXCollections.observableArrayList(true, false);
    	disabledChoice.setItems(disabledOptions);
    	ObservableList<Boolean> unisexOptions = FXCollections.observableArrayList(true, false);
    	unisexChoice.setItems(unisexOptions);
    	
        assert disabledLabel != null : "fx:id=\"disabledLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert unisexLabel != null : "fx:id=\"unisexLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";


    }

}



