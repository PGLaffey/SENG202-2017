package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ObjectInfoScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private static Label objectInfoLabel;

    @FXML
    private Button okButton;


    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }
    
    public static void setInfoLabel(String retailer ) {
    	objectInfoLabel.setText(retailer);
    }

    @FXML
    void initialize() {
        assert objectInfoLabel != null : "fx:id=\"objectInfoLabel\" was not injected: check your FXML file 'ObjectInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ObjectInfoScreen.fxml'.";


    }

}
