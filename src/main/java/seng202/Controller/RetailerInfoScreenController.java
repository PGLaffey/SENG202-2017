package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label latLabel;

    @FXML
    private Label longLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label zipLabel;
    
    private Retailer retailer;


    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }

    @FXML
    void initialize() {
    	retailer = CurrentStorage.getRetailer();
    	nameLabel.setText("Name: " + retailer.getName());
    	addressLabel.setText("Address: " + retailer.getAddress());
    	latLabel.setText("Latitude: " + retailer.getLatitude());
    	longLabel.setText("Longitude: " + retailer.getLongitude());
    	zipLabel.setText("Zip: " + retailer.getZip());
    	
        assert addressLabel != null : "fx:id=\"addressLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert zipLabel != null : "fx:id=\"zipLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";


    }

}
