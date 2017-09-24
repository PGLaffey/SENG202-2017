package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Route;


public class SaveRouteScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label routeNameLabel;

    @FXML
    private TextField routeNameText;




    /**
     * Method for when the cancel button is pressed, hides the pop up.
     * @param event
     */
    @FXML
    void cancelButtonPressed(ActionEvent event) {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.hide();
    }

    /**
     * Method when the save locally button is pressed, hides the pop up.
     * @param event
     */
    @FXML
    void savePressed(ActionEvent event) {
        if (routeNameText.getText().equals("")) {
            routeNameLabel.setTextFill(Color.RED);
        } else {
            Route route = new Route(CurrentStorage.getNewRouteStart(), CurrentStorage.getNewRouteEnd(), routeNameText.getText());
            CurrentStorage.addNewRoute(route);

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.hide();
        }



    }


    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";
        assert saveButton != null : "fx:id=\"saveLocallyButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";


    }

}
