package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Route;



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
    
    private Route route;


    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }
    
    @FXML
    void completedRouteButtonPressed(ActionEvent event) {
    	CurrentStorage.getUser().addDistance(route.getDistance());
    	completedRouteButton.setText("Added!");
    }

    @FXML
    void initialize() {
    	route = CurrentStorage.getRoute();
    	bikeidLabel.setText("Bike ID: "+ route.getBikeID());
    	distanceLabel.setText("Distance: " + route.getDistance());
    	endLabel.setText("End: " + route.getStartString());
    	startLabel.setText("Start: " + route.getEndString());
    	nameLabel.setText("Name: " + route.getName());
    	genderLabel.setText("Gender: " + route.getGender());
    	
    	completedRouteButton.setText("I have completed this route!");
    	
    	
        assert bikeidLabel != null : "fx:id=\"bikeidLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert distanceLabel != null : "fx:id=\"distanceLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert endLabel != null : "fx:id=\"endLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert genderLabel != null : "fx:id=\"genderLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";
        assert startLabel != null : "fx:id=\"startLabel\" was not injected: check your FXML file 'RouteInfoScreen.fxml'.";


    }

}

