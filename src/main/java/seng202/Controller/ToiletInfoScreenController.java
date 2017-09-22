package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Toilet;



public class ToiletInfoScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label disabledLabel;

    @FXML
    private Label latLabel;

    @FXML
    private Label longLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label unisexLabel;
    
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

    @FXML
    void initialize() {
    	toilet = CurrentStorage.getToilet();
    	disabledLabel.setText("Disabled: " + String.valueOf(toilet.getForDisabled()));
    	latLabel.setText("Latitude: " + toilet.getLatitude());
    	longLabel.setText("Longitude " + toilet.getLongitude());
    	nameLabel.setText("Name: " + toilet.getName());
    	unisexLabel.setText("Uni Sex: " + String.valueOf(toilet.getUniSex()));
    	
        assert disabledLabel != null : "fx:id=\"disabledLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert unisexLabel != null : "fx:id=\"unisexLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";


    }

}



