package seng202.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.omg.CORBA.Current;
import seng202.Model.CurrentStorage;
import seng202.Model.Route;
import javafx.scene.paint.Color;
import seng202.Model.User;


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
    private Button updateButton;

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

    @FXML
    private Button favouriteButton;

    @FXML
    private Label addedLabel;

    private Route route;
    private User user;


    /**
     * Method when the ok button is pressed, hides the pop up. routes
     * @param event Auto-generate event on button press
     */
    @FXML
    void okPressed(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.hide();
    }

    
    /**
     * Method when the update button is pressed, displays screen to change the selected route
     * @param event Auto-generate event on button press
     */
    @FXML
    void updatePressed(ActionEvent event) {
        Stage stage = (Stage) updateButton.getScene().getWindow();
        stage.hide();
    }

    
    /**
     * Method when the completed route button is pressed. Adds the distance of the route to the users statistics and change the button text
     * @param event Auto-generate event on button press
     */
    @FXML
    void completedRouteButtonPressed(ActionEvent event) {
        completedRouteButton.setVisible(false);
        addButton.setVisible(true);
        timeLabel.setVisible(true);
        timeText.setVisible(true);
    }

    
    /**
     * Method for when the add button is pressed. Verifies then adds the route
     * @param event Auto-generate event on button press
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
        } 
        catch (Exception e) {
            timeLabel.setTextFill(Color.RED);
        }

        if (add) {
            addedLabel.setVisible(true);
            addButton.setVisible(false);
            timeLabel.setVisible(false);
            timeText.setVisible(false);
            CurrentStorage.getUser().addDistance(route.getDistance());
            CurrentStorage.getUser().addRoute();
            CurrentStorage.getUser().addTime(Double.parseDouble(timeText.getText()));
        }
    }


    /**
     * TODO ADD docstring
     * @param event Auto-generate event on button press
     */
    @FXML
    void favouritePressed(ActionEvent event) {
        //CurrentStorage.addFavouriteRoute(route);
        //favouriteButton.setVisible(false);
        // TODO: PATRICK...
    }


    //TODO add docstring
    @FXML
    void initialize() {
        route = CurrentStorage.getRoute();
        user = CurrentStorage.getUser();

        if (CurrentStorage.getFavRoutes().contains(CurrentStorage.getRouteArray().indexOf(route))) {
            favouriteButton.setVisible(false);
        } else {
            favouriteButton.setVisible(true);
        }

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

