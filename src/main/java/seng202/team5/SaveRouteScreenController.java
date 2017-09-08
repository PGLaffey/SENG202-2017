package seng202.team5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class SaveRouteScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveLocallyButton;

    @FXML
    private Button savePubliclyButton;


    @FXML
    void cancelButtonPressed(ActionEvent event) {
    	Stage stage = (Stage) saveLocallyButton.getScene().getWindow(); 
    	stage.hide();
    }

    @FXML
    void saveLocallyPressed(ActionEvent event) {
    	Stage stage = (Stage) saveLocallyButton.getScene().getWindow(); 
    	stage.hide();
    }

    @FXML
    void savePubliclyPressed(ActionEvent event) {
    	Stage stage = (Stage) savePubliclyButton.getScene().getWindow(); 
    	stage.hide();
    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";
        assert saveLocallyButton != null : "fx:id=\"saveLocallyButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";
        assert savePubliclyButton != null : "fx:id=\"savePubliclyButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";


    }

}
