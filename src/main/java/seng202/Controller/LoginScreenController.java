package seng202.Controller;

import seng202.Model.FileManager;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.DataFetcher;
import seng202.Model.User;

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
	 * @throws IOException 
	 */
    public void signInButtonPressed() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {

    	DataFetcher data = new DataFetcher();
		data.connectDb();
		if ((data.fetchPassword(usernameText.getText()).isEmpty())) {
			incorrectPasswordLbl.setVisible(false);
			incorrectUserLbl.setVisible(true);
		} else if ((data.fetchPassword(usernameText.getText()).get(0).get(0).toString().equals(passwordText.getText().toString()))) {
			//User user = new User(String first, String last, usernameText.getText().toString(), String doB, passwordText.getText().toString());
			//CurrentStorage.setUser(user); // TODO: Not sure how to access the users name and dob from the database
			// TODO: put the correct filenames in
			// TODO: or somehow load the objects into current storage
			FileManager.retailerRetriever( new File(getClass().getResource("/data_files/").getFile()).toString() + "/retailer_data1.csv");
			FileManager.wifiRetriever(new File(getClass().getResource("/data_files/").getFile()).toString() + "/wifi_data1.csv");
			//FileManager.routeRetriever(new File(getClass().getResource("/data_files/").getFile()).toString() + "/2014-01 - Citi Bike trip data.csv");
			//FileManager.routeRetriever(new File(getClass().getResource("/data_files/").getFile()).toString() + "/2014-02 - Citi Bike trip data.csv");
			User user = new User("Courtney", "Hoskin", "cgh31", "6 apr","password");
			CurrentStorage.setUser(user);
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
	 * Method for when the Sign Up button is pressed, shows the sign up screen.
	 */

    public void signUpButtonPressed() throws Exception {  	
    	Stage primaryStage = (Stage) signUpButton.getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/SignupScreen.fxml"));
    	
    	primaryStage.setTitle("Sign Up");
    	primaryStage.setScene(new Scene(root));
        

    }


}