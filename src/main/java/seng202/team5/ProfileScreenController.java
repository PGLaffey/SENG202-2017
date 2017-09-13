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
	private Label usersNameLabel;
	
	@FXML 
	private Label usersBirthDateLabel;
	
	private User user;
	
	
	/**
	 * Method for when the Map button is pressed
	 * @throws IOException 
	 */
	public void mapPressed() throws IOException {
		
		Stage primaryStage = (Stage) mapButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	
	
	/**
	 * Method for when the Statistics button is pressed
	 * @throws IOException 
	 */
	public void statPressed() throws IOException {
		
		Stage primaryStage = (Stage) statButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Statistics");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	/**
	 * Method for when the Account button is pressed
	 * @throws IOException 
	 */
	public void accountPressed() throws IOException {
		Stage primaryStage = (Stage) accountButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Profile");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	/**
	 * Method for when the Logout button is pressed
	 * @throws IOException 
	 */
	public void logoutPressed() throws IOException {
		Stage primaryStage = (Stage) logoutButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Login");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	/**
	 * Method for when the Edit Account button is pressed
	 */
	public void editPressed() {
	}
	
	
	/**
	 * Method for when the Change Password button is pressed
	 * @throws IOException 
	 */
	public void passwordPressed() throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ChangePasswordScreen.fxml"));
		
		Scene scene = new Scene(root); 
		stage.setTitle("Change Password");
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * Method for when the Delete Account button is pressed
	 * @throws IOException 
	 */
	public void deletePressed() throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/DeleteAccountScreen.fxml"));
		
		Scene scene = new Scene(root); 
		stage.setTitle("Delete Account");
		stage.setScene(scene);
		stage.show();
	}
	
	private
	
	@FXML
	void initialize() {
		// TODO: uncomment the below once the user object is created properly in the login fxml
		/*user = CurrentStorage.getUser();
		usersNameLabel.setText(CurrentStorage.getUser().getName()); 
		usersBirthDateLabel.setText(CurrentStorage.getUser().getDob()); */
	}
	

}