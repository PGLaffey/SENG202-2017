package seng202.Controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class DataViewerScreenController {
	
	
	@FXML
	private Button mapButton;
	
	@FXML 
	private Label mapPressedLabel;
	
	@FXML
	private Button statButton;
	
	@FXML
	private Button accountButton;
	
	@FXML
	private Button logoutButton;	
	
	@FXML
	private ChoiceBox<String> filterGraphBox;
	
	@FXML
	private RadioButton graphRadio;
	
	@FXML
	private RadioButton rawDataRadio;

	
	/**
	 * Method for when the Map
	 * button is pressed
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
	 * Method for when the Statistics
	 * button is pressed
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
	 * Method for when the Account
	 * button is pressed
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
	 * Method for when the Logout
	 * button is pressed
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
	
	@FXML
	void initialize() {
		ObservableList<String> filterTypes = FXCollections.observableArrayList("Distance travelled","Time spent cycling");
		filterGraphBox.setItems(filterTypes);
	}
}