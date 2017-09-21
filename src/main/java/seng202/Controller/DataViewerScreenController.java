package seng202.Controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;

public class DataViewerScreenController {
	
	
	@FXML
	private Button mapButton;
	
    @FXML
    private Button tableButton;
	
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
	 * Method for when the map menu button is pressed, shows the main map screen.
	 * @throws IOException 
	 */
	public void mapPressed() throws IOException {
		Stage primaryStage = (Stage) mapButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
		primaryStage.setTitle("Map");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
	
	
	/** 
	 * Method for when the table menu button is pressed, shows the raw tables screen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
    void tablePressed(ActionEvent event) throws IOException {
		Stage primaryStage = (Stage) tableButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/TablesScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
		primaryStage.setTitle("Table");
		primaryStage.setScene(scene);
		primaryStage.show();
    }
	
	
	/**
	 * Method for when the statistics menu button is pressed, shows the statistics screen.
	 * @throws IOException 
	 */
	public void statPressed() throws IOException {
		
		Stage primaryStage = (Stage) statButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/DataViewerScreen.fxml"));
		
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()); // I think we can add in window size here?
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
	
	@FXML
	void initialize() {
		ObservableList<String> filterTypes = FXCollections.observableArrayList("Distance travelled","Time spent cycling");
		filterGraphBox.setItems(filterTypes);
	}
}
