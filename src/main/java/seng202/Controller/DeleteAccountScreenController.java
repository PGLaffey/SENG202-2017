package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class DeleteAccountScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;


    @FXML
    void noButtonPressed(ActionEvent event) {
    	Stage stage = (Stage) noButton.getScene().getWindow(); 
    	stage.hide();	
    }

    @FXML
    void yesButtonPressed(ActionEvent event) {
    	Stage stage = (Stage) yesButton.getScene().getWindow(); 
    	stage.hide();	// TODO: Work out how to then change primaryStage to login screen
    }

    @FXML
    void initialize() {
        assert noButton != null : "fx:id=\"noButton\" was not injected: check your FXML file 'DeleteAccountScreen.fxml'.";
        assert yesButton != null : "fx:id=\"yesButton\" was not injected: check your FXML file 'DeleteAccountScreen.fxml'.";


    }

}
