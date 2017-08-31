package seng202.team5;

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
import javafx.stage.Stage;


public class ProfileScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mapButton;

    @FXML
    private Button statButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button editButton;

    @FXML
    private Button passwordButton;
    
    @FXML
    private Button deleteButton;

    @FXML
    private Label usersAgeLabel;

    @FXML
    private Label usersNameLabel;


    @FXML
    void mapPressed(ActionEvent event) {
    }
    
    
    /**
     * Method for when the Statistics
     * Button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void statPressed(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) statButton.getScene().getWindow(); // Here the window is the stage
    	Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
    	
    	Scene scene = new Scene(root); // I think we can add in window size here?
    	stage.setTitle("Statistics");
    	stage.setScene(scene);
    	stage.show();
    }
    
    
    /**
     * Method for when the Account
     * Button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void accountPressed(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) accountButton.getScene().getWindow();
    	Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
    	
    	Scene scene = new Scene(root);
    	stage.setTitle("Profile");
    	stage.setScene(scene);
    	stage.show();
    }
    
    
    /**
     * Method for when the Logout
     * Button is pressed.
     * @param event
     * @throws IOException 
     */
    @FXML
    void logoutPressed(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) logoutButton.getScene().getWindow();
    	Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
    	
    	Scene scene = new Scene(root);
    	stage.setTitle("Login");
    	stage.setScene(scene);
    	stage.show();
    }
    
    
    @FXML
    void editPressed(ActionEvent event) {
    }
    
    
    @FXML
    void passwordPressed(ActionEvent event) {
    }
    
    
    @FXML
    void deletePressed(ActionEvent event) {
    }
    
    
    @FXML
    void initialize() {
        assert accountButton != null : "fx:id=\"accountButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert editButton != null : "fx:id=\"editButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert logoutButton != null : "fx:id=\"logoutButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert mapButton != null : "fx:id=\"mapButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert passwordButton != null : "fx:id=\"passwordButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert statButton != null : "fx:id=\"statButton\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert usersAgeLabel != null : "fx:id=\"usersAgeLabel\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
        assert usersNameLabel != null : "fx:id=\"usersNameLabel\" was not injected: check your FXML file 'ProfileScreen.fxml'.";
    }

}
