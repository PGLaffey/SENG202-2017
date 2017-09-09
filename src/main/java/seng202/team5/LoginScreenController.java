package seng202.team5;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
	
	@FXML
	private Label incorrectPasswordLbl;

	@FXML
	private Label incorrectUserLbl;
	/** 
	 * Method for when the Sign In 
	 * button is pressed
	 * @throws IOException 
	 */
    public void signInButtonPressed() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {

    	DataFetcher data = new DataFetcher();
		data.connectDb();
		if ((data.fetchPassword(usernameText.getText()).isEmpty())) {
			incorrectPasswordLbl.setVisible(false);
			incorrectUserLbl.setVisible(true);
		} else if ((data.fetchPassword(usernameText.getText()).get(0).get(0).toString().equals(passwordText.getText().toString()))) {
			Stage primaryStage = (Stage)signInButton.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
			primaryStage.setTitle("Profile");
			primaryStage.setScene(new Scene(root));
		} else {
			incorrectUserLbl.setVisible(false);
			incorrectPasswordLbl.setVisible(true);
		}
    	

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