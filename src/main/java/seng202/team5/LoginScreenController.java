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
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LoginScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private CheckBox staySignedInCheck;

    @FXML
    private TextField usernameText;


    /**
     * Method for when the SignIn
     * Button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void signInPressed(ActionEvent event) throws IOException { // At the moment it doesn't check that the username and password fields are filled in correctly
    	
    	Stage stage = (Stage) signInButton.getScene().getWindow();
    	Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
    	
    	Scene scene = new Scene(root);
    	stage.setTitle("Profile");
    	stage.setScene(scene);
    	stage.show();    	
    }

    
    @FXML
    void signUpPressed(ActionEvent event) {
    }

    
    @FXML
    void initialize() {
        assert passwordText != null : "fx:id=\"passwordText\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert signInButton != null : "fx:id=\"signInButton\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert staySignedInCheck != null : "fx:id=\"staySignedInCheck\" was not injected: check your FXML file 'LoginScreen.fxml'.";
        assert usernameText != null : "fx:id=\"usernameText\" was not injected: check your FXML file 'LoginScreen.fxml'.";


    }

}
