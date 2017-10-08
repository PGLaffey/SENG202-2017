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

    @FXML
    void cancelPressed(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/ConfirmationDialogScreen.fxml"));

        stage.setScene(new Scene(root));
        stage.setTitle("Cancel upload");
        stage.show();
    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'ConfirmationDialogScreen.fxml'.";
    }
}
