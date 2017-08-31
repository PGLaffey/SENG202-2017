package seng202.team5;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


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


    @FXML
    void signInPressed(ActionEvent event) {
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
