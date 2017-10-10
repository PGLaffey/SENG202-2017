package seng202.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.DataFetcher;
import seng202.Model.User;

import java.util.ArrayList;

/**
 * Controller class for the login screen.
 */
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
	private Label incorrectPasswordLbl;

	@FXML
	private Button ipChangedButton;

	@FXML
	private Button ipOkButton;

	@FXML
	private TextField ipText;

	
	/** 
	 * Method for when the Sign In button is pressed. Checks the login, informing if information 
	 * is wrong otherwise shows login screen.
	 */
	public void signInButtonPressed() throws Exception {
		DataFetcher data = new DataFetcher();
		data.connectDb();
		if (!usernameText.getText().isEmpty() && !passwordText.getText().isEmpty()) {
			if (data.fetchPassword(usernameText.getText()) == null) {
				incorrectPasswordLbl.setVisible(true);				
			}
			else if ((data.fetchPassword(usernameText.getText()).equals(passwordText.getText()
					.toString()))) {

				ArrayList<String> userInfo;
				userInfo = data.fetchUserInfo(usernameText.getText());

				User user = new User(userInfo.get(0), userInfo.get(1), usernameText.getText()
						.toLowerCase(), userInfo.get(2), passwordText.getText().toString(),
						Integer.parseInt(userInfo.get(3)), Double.parseDouble(userInfo.get(4)), 
						Double.parseDouble(userInfo.get(5)));

				CurrentStorage.setUser(user);
				Stage primaryStage = (Stage) signInButton.getScene().getWindow();
				Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
				primaryStage.setTitle("Profile");
				primaryStage.setScene(new Scene(root));
			} 
			else {
				incorrectPasswordLbl.setVisible(true);
			}
		
		} 
		else {
			incorrectPasswordLbl.setVisible(false);
		}
		data.closeConnection();
	}


	/** 
	 * Method for when the Sign Up button is pressed, shows the sign up screen.
	 */
    public void signUpButtonPressed() throws Exception {  	
    	Stage primaryStage = (Stage) signUpButton.getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/SignupScreen.fxml"));

    	primaryStage.setTitle("Sign Up");
    	primaryStage.setScene(new Scene(root));
    }

    
    /**
     * Method for when the ip changed button is pressed.
     */
	@FXML
	void ipChangedButtonPressed() {
    	ipOkButton.setVisible(true);
    	ipText.setVisible(true);
    	ipText.setPromptText("Enter new IP address");
	}

	
	/**
	 * Method for when the ip okay button is pressed.
	 */
	@FXML
	void ipOkButtonPressed() {
    	if (!ipText.getText().equals("")) {
			ipOkButton.setVisible(false);
			ipText.setVisible(false);
			DataFetcher df = new DataFetcher();
			try {
				df.connectDb();;
				df.setIP(ipText.getText());
				df.closeConnection();
			} 
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}