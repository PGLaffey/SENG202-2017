package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Retailer;


public class RetailerInfoScreenController {

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
    private Label descriptionLabel;

    @FXML
    private TextField descriptionText;

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
    private Label zipLabel;

    @FXML
    private TextField zipText;
    
    private Retailer retailer;


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
     * Method when the update button is pressed, opens screen for user to update the selected poi
     * @param event
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	addressText.setVisible(true);
    	addressText.setText(retailer.getAddress());
    	boroughText.setVisible(true);
    	boroughText.setText(retailer.getBorough());
    	productText.setVisible(true);
    	productText.setText(retailer.getProduct());
    	descriptionText.setVisible(true);
    	descriptionText.setText(retailer.getDescription());
    	latitudeText.setVisible(true);
    	latitudeText.setText(String.valueOf(retailer.getLatitude()));
    	longitudeText.setVisible(true);
    	longitudeText.setText(String.valueOf(retailer.getLongitude()));
    	nameText.setVisible(true);
    	nameText.setText(retailer.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(retailer.getZip()));;
    	addressLabel.setText("Address: ");
    	boroughLabel.setText("Borough: ");
    	productLabel.setText("Product: ");
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
    	// TODO: Check user has inputed the correct data
    	
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
    	if (addressText.getText().equals("") && (latitudeText.getText().equals("") || longitudeText.getText().equals(""))) {
            addressLabel.setTextFill(Color.RED);
            latLabel.setTextFill(Color.RED);
            longLabel.setTextFill(Color.RED);
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
        
    	// TODO: Update the retailer instance and in database
        if (allValid) {
        	if (addressText.getText().equals("")) {
        		retailer.setLatitude(Double.parseDouble(latitudeText.getText()));
        		retailer.setLongitude(Double.parseDouble(longitudeText.getText()));
        	} else {
        		retailer.setAddress(addressText.getText());
        	}
        	retailer.setAddress(addressText.getText());
        	retailer.setBorough(boroughText.getText());
        	retailer.setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
            	retailer.setZip(Integer.parseInt(zipText.getText()));

        	}
        }
        cancelPressed(event);
    }
    
    @FXML
    void cancelPressed(ActionEvent event) {
    	nameLabel.setText("Name: " + retailer.getName());
    	nameText.setVisible(false);
    	addressLabel.setText("Address: " + retailer.getAddress());
    	addressText.setVisible(false);
    	latLabel.setText("Latitude: " + retailer.getLatitude());
    	latitudeText.setVisible(false);
    	longLabel.setText("Longitude: " + retailer.getLongitude());
    	longitudeText.setVisible(false);
    	zipLabel.setText("Zip: " + retailer.getZip());
    	zipText.setVisible(false);
    	boroughLabel.setText("Borough: " + retailer.getBorough());
    	boroughText.setVisible(false);
    	productLabel.setText("Product: " + retailer.getProduct());
    	productText.setVisible(false);
    	descriptionLabel.setText("Description: " + retailer.getDescription());
    	descriptionText.setVisible(false);
    	okButton.setVisible(true);
    	updateButton.setVisible(true);
    	saveButton.setVisible(false);
    	cancelButton.setVisible(false);
    }
    

    @FXML
    void initialize() {
    	retailer = CurrentStorage.getRetailer();
    	nameLabel.setText("Name: " + retailer.getName());
    	addressLabel.setText("Address: " + retailer.getAddress());
    	latLabel.setText("Latitude: " + retailer.getLatitude());
    	longLabel.setText("Longitude: " + retailer.getLongitude());
    	zipLabel.setText("Zip: " + retailer.getZip());
    	boroughLabel.setText("Borough: " + retailer.getBorough());
    	productLabel.setText("Product: " + retailer.getProduct());
    	descriptionLabel.setText("Description: " + retailer.getDescription());
    	
        assert addressLabel != null : "fx:id=\"addressLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert zipLabel != null : "fx:id=\"zipLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";


    }

}
