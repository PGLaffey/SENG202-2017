package seng202.team5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;


public class DataViewerScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button accountButton;

    @FXML
    private ChoiceBox<?> filterGraphBox;

    @FXML
    private RadioButton graphRadio;

    @FXML
    private Button logoutButton;

    @FXML
    private Button mapButton;

    @FXML
    private Label mapPressedLabel;

    @FXML
    private RadioButton rawDataRadio;

    @FXML
    private Button statButton;


    @FXML
    void accountPressed(ActionEvent event) {
    }

    @FXML
    void logoutPressed(ActionEvent event) {
    }

    @FXML
    void mapPressed(ActionEvent event) {
    }

    @FXML
    void statPressed(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert filterGraphBox != null : "fx:id=\"filterGraphBox\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert graphRadio != null : "fx:id=\"graphRadio\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert mapPressedLabel != null : "fx:id=\"mapPressedLabel\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert rawDataRadio != null : "fx:id=\"rawDataRadio\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";
        assert statButton != null : "fx:id=\"statButton\" was not injected: check your FXML file 'DataViewerScreen.fxml'.";


    }

}
