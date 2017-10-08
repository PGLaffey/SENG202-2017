package seng202.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the uploading screen
 */
public class UploadingScreenController {

    @FXML
    Button cancelButton;

    
    /**
     * Method to execute when the cancel button is pressed.
     * @param event - Auto generated event
     */
    @FXML
    void cancelPressed(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ConfirmationDialogScreen.fxml"));

        stage.setScene(new Scene(root));
        stage.setTitle("Cancel upload");
        stage.show();
    }

    
    /**
     * Initializes the controller.
     */
    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML"
        		+ " file 'ConfirmationDialogScreen.fxml'.";
    }
}
