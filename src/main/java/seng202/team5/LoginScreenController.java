package seng202.team5;

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
	 */
    public void signInButtonPressed() {

    }
	

	/** 
	 * Method for when the Sign Up 
	 * button is pressed
	 */

    public void signUpButtonPressed() throws Exception {
        System.out.println("SignUp Pressed");
        Parent signUp = FXMLLoader.load(getClass().getResource("/SignUpScreen.fxml"));

    }


}