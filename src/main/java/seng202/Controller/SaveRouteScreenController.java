package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Location;
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

    @FXML
    private Label routeStartLabel;

    @FXML
    private TextField routeStartText;

    @FXML
    private Label routeEndLabel;

    @FXML
    private TextField routeEndText;

    @FXML
    private CheckBox saveRoutePrivateCheck;


    /**
     * Method for when the cancel button is pressed, hides the pop up.
     * @param event Auto-generate event on button press
     */
    @FXML
    void cancelButtonPressed(ActionEvent event) {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.hide();
    }


    /**
     * Method when the save button is pressed, saves the route and hides the pop up.
     * @param event Auto-generate event on button press
     */
    @FXML
    void savePressed(ActionEvent event) {
        boolean allValid = true;
        routeStartLabel.setTextFill(Color.BLACK);
        routeEndLabel.setTextFill(Color.BLACK);
        routeNameLabel.setTextFill(Color.BLACK);
        if (routeNameText.getText().equals("")) {
            routeNameLabel.setTextFill(Color.RED);
            allValid = false;
        } 
        if (routeEndText.getText().equals("")) {
            routeEndLabel.setTextFill(Color.RED);
            allValid = false;
        } 
        if (routeStartText.getText().equals("")) {
            routeStartLabel.setTextFill(Color.RED);
            allValid = false;
        } 
        if (allValid) {
            Location start = CurrentStorage.getNewRouteStart();
            Location end = CurrentStorage.getNewRouteEnd();
            start.setName(routeStartText.getText());
            end.setName(routeEndText.getText());

            Route route = new Route(start, end, routeNameText.getText());
            route.setSecret(saveRoutePrivateCheck.isSelected());

            CurrentStorage.addNewRoute(route);
            CurrentStorage.addSavedRoute(CurrentStorage.getRouteArray().indexOf(route));


            CurrentStorage.setNewRouteEnd(null);

            Stage stage = (Stage) saveButton.getScene().getWindow();
            stage.hide();

        }
    }


    //TODO add docstring
    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";
        assert saveButton != null : "fx:id=\"saveLocallyButton\" was not injected: check your FXML file 'SaveRouteScreen.fxml'.";
    }
}
