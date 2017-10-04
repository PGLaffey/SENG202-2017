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
	 * Method for when the Sign In button is pressed. Checks the login, informing if information is wrong otherwise shows login screen.
	 * @throws Exception 
	 */
	public void signInButtonPressed() throws Exception {

		DataFetcher data = new DataFetcher();
		data.connectDb();
		//TODO: Jack to change
		if (!usernameText.getText().isEmpty() && !passwordText.getText().isEmpty()) {
			if (data.isUser(usernameText.getText()) == true) {
				if (data.fetchPassword(usernameText.getText()).isEmpty()) {
					incorrectPasswordLbl.setVisible(false);
					incorrectUserLbl.setVisible(true);
				} else if ((data.fetchPassword(usernameText.getText()).equals(passwordText.getText().toString()))) {

					ArrayList<String> userInfo;
					userInfo = data.fetchUserInfo(usernameText.getText());

					User user;
					//if (new File("./src/main/resources/data_files/" + usernameText.getText() + ".ser").exists()) {
					//	//user = new User("Courtney","Hoskin", "cgh", "2017-08-06","password");
					//    user = FileManager.userDeserialize("./src/main/resources/data_files/" + usernameText.getText() + ".ser");
					//} else {
					user = new User(userInfo.get(0), userInfo.get(1), usernameText.getText().toLowerCase(), userInfo.get(2), passwordText.getText().toString(),
							Integer.parseInt(userInfo.get(3)), Double.parseDouble(userInfo.get(4)), Double.parseDouble(userInfo.get(5)));
						//FileManager.userSerialize(user, "./src/main/resources/data_files/");
					CurrentStorage.setUser(user);
					System.out.println(userInfo.get(5));
					Stage primaryStage = (Stage) signInButton.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
					primaryStage.setTitle("Profile");
					primaryStage.setScene(new Scene(root));
				} else {
					incorrectUserLbl.setVisible(false);
					incorrectPasswordLbl.setVisible(true);
				}
			} else {
				incorrectPasswordLbl.setVisible(false);
				incorrectUserLbl.setVisible(true);
			}
		} else {
			incorrectPasswordLbl.setVisible(false);
			incorrectUserLbl.setVisible(false);
		}
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


}