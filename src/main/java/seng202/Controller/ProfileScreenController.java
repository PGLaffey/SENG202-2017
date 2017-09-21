package seng202.Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.User;

public class ProfileScreenController {
	
	@FXML
	private Button mapButton;
	
    @FXML
    private Button tableButton;
	
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
	 * Method for when the map menu button is pressed, shows the main map screen. 
	 * @throws IOException 
	 */
	public void mapPressed() throws IOException {
		
		Stage primaryStage = (Stage) mapButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}
	
	/**
	 * Method for when the table menu buttonis pressed, shows the raw tables screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
    void tablePressed(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) tableButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/TablesScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		primaryStage.setTitle("Table");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	
	/**
	 * Method for when the statistics menu button is pressed, shows the statistics screen.
	 * @throws IOException 
	 */
	public void statPressed() throws IOException {
		
		Stage primaryStage = (Stage) statButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		primaryStage.setTitle("Statistics");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	/**
	 * Method for when the account menu button is pressed, shows the profile screen.
	 * @throws IOException 
	 */
	public void accountPressed() throws IOException {
		Stage primaryStage = (Stage) accountButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		primaryStage.setTitle("Profile");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	/**
	 * Method for when the Logout button is pressed, shows the login screen and flushes the current storage.
	 * @throws IOException 
	 */
	public void logoutPressed() throws IOException {
		CurrentStorage.flush();
		
		Stage primaryStage = (Stage) logoutButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
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
	 * Method for when the Change Password button is pressed, shows the change password pop up screen.
	 * @throws IOException 
	 */
	public void passwordPressed() throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/ChangePasswordScreen.fxml"));
		
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("Change Password");
		stage.setScene(scene);
		stage.show();
	}
	
	
	/**
	 * Method for when the Delete Account button is pressed, shows the delete account pop up screen.
	 * @throws IOException 
	 */
	public void deletePressed() throws IOException {
		Stage stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("/DeleteAccountScreen.fxml"));
		
		Scene scene = new Scene(root, stage.getWidth(), stage.getHeight());
		stage.setTitle("Delete Account");
		stage.setScene(scene);
		stage.show();
		
		
		Stage primaryStage = (Stage) accountButton.getScene().getWindow(); 
		primaryStage.hide();
	}
	
	@FXML
	void initialize() {
		user = CurrentStorage.getUser();
		usersNameLabel.setText(user.getName()); 
		usersBirthDateLabel.setText(user.getDob());
	}
	

}