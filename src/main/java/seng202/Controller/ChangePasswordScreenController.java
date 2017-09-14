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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ChangePasswordScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private PasswordField newPasswordText;

    @FXML
    private PasswordField newPasswordTextAgain;

    @FXML
    private TextField oldPasswordText;


    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {  	
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.hide();
    }

    @FXML
    void changePasswordButtonPressed(ActionEvent event) throws IOException {
    	Stage stage = (Stage) changePasswordButton.getScene().getWindow();
    	stage.hide();	
    }

    @FXML
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'ChangePasswordScreen.fxml'.";
        assert changePasswordButton != null : "fx:id=\"changePasswordButton\" was not injected: check your FXML file 'ChangePasswordScreen.fxml'.";
        assert newPasswordText != null : "fx:id=\"newPasswordText\" was not injected: check your FXML file 'ChangePasswordScreen.fxml'.";
        assert newPasswordTextAgain != null : "fx:id=\"newPasswordTextAgain\" was not injected: check your FXML file 'ChangePasswordScreen.fxml'.";
        assert oldPasswordText != null : "fx:id=\"oldPasswordText\" was not injected: check your FXML file 'ChangePasswordScreen.fxml'.";


    }

}
