package seng202.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Route;

import java.net.URL;
import java.util.ResourceBundle;




public class RouteInfoScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label bikeidLabel;

    @FXML
    private Label distanceLabel;

    @FXML
    private Label endLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Button okButton;

    @FXML
    private Button completedRouteButton;

    @FXML
    private Label startLabel;

    @FXML
    private Label timeLabel;

    @FXML
    private TextField timeText;

    @FXML
    private Button addButton;
    
    private Route route;


    /**
     * Method when the ok button is pressed, hides the pop up.
     * @param event
     */
    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }

    /**
     * Method when the completed route button is pressed. Adds the distance of the route to the users statistics and change the button text
     * @param event
     */
    @FXML
    void completedRouteButtonPressed(ActionEvent event) {
        //boolean add = false;

        completedRouteButton.setVisible(false);
        addButton.setVisible(true);
        timeLabel.setVisible(true);
        timeText.setVisible(true);


    }

    /**
     * Method for when the add button is pressed. Verifies then adds the route
     * @param event
     */
    @FXML
    void addButtonPressed(ActionEvent event) {
        boolean add = false;

        if (timeText.getText().equals("")) {
            timeLabel.setTextFill(Color.RED);
        }
        try {
            Double.parseDouble(timeText.getText());
            add = true;
        } catch (Exception e) {
            timeLabel.setTextFill(Color.RED);
        }

        if (add) {
            completedRouteButton.setVisible(true);
            addButton.setVisible(false);
            timeLabel.setVisible(false);
            timeText.setVisible(false);
            CurrentStorage.getUser().addDistance(route.getDistance());
            CurrentStorage.getUser().addRoute();
            CurrentStorage.getUser().addTime(Double.parseDouble(timeText.getText()));
            completedRouteButton.setText("Added!");
        }
    }

    @FXML
    void showRouteBtnPressed(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	route = CurrentStorage.getRoute();
    	bikeidLabel.setText("Bike ID: "+ route.getBikeID());
    	distanceLabel.setText("Distance: " + route.getDistanceRound() + "m");
    	endLabel.setText("End: " + route.getStartString());
    	startLabel.setText("Start: " + route.getEndString());
    	nameLabel.setText("Name: " + route.getName());
    	genderLabel.setText("Gender: " + route.getGenderType());
    	
    	completedRouteButton.setText("I have completed this route!");
    	completedRouteButton.setVisible(true);
    	timeLabel.setVisible(false);
    	timeText.setVisible(false);
    	addButton.setVisible(false);
    	
    	
        assert bikeidLabel != null : "fx:id=\"bikeidLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert distanceLabel != null : "fx:id=\"distanceLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert endLabel != null : "fx:id=\"endLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert genderLabel != null : "fx:id=\"genderLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert startLabel != null : "fx:id=\"startLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";


    }

}

