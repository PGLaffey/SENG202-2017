package seng202.team5;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginScreenController {
	
	@FXML
	private TextField usernameText;
	
	@FXML
	private PasswordField passwordText;
	
	@FXML
	private Button signInButton;
	
	@FXML
	private Button signUpButton;
	
	@FXML
	private CheckBox staySignedInCheck;
	

	/** 
	 * Method for when the Sign In 
	 * button is pressed
	 * @throws IOException 
	 */
    public void signInButtonPressed() throws IOException {
    	
    	Stage primaryStage = (Stage)signInButton.getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
    	
    	primaryStage.setTitle("Profile");
    	primaryStage.setScene(new Scene(root));
    	

    }
	

	/** 
	 * Method for when the Sign Up 
	 * button is pressed
	 */

    public void signUpButtonPressed(ActionEvent event) throws Exception {
//        FXMLLoader signUp = FXMLLoader.load(Main.class.getResource("/SignUpScreen.fxml"));
//        AnchorPane login = (AnchorPane) signUp.load();
//        Scene scene = new Scene(login);
    	
    	Stage primaryStage = (Stage) signUpButton.getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/SignupScreen.fxml"));
    	
    	primaryStage.setTitle("Sign Up");
    	primaryStage.setScene(new Scene(root));
        

    }


}