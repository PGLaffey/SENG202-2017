package seng202.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controller class for the confirmation dialog.
 */
public class ConfirmationDialogController {
    @FXML
    Button yesButton;

    @FXML
    Button noButton;

    @FXML
    Label confirmLabel;

    /**
     * Ends the program
     * @param event auto generated event
     */
    @FXML
    void yesButtonPressed(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Hides the stage and continues if no is pressed.
     * @param event auto generated event
     */
    @FXML
    void noButtonPressed(ActionEvent event) {
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void initialize() {
        assert yesButton != null : "fx:id=\"yesButton\" was not injected: check your FXML file 'ConfirmationDialogScreen.fxml'.";
        assert noButton != null : "fx:id=\"noButton\" was not injected: check your FXML file 'ConfirmationDialogScreen.fxml'.";
        assert confirmLabel != null : "fx:id=\"confirmLabel\" was not injected: check your FXML file 'ConfirmationDialogScreen.fxml'.";
    }
}
