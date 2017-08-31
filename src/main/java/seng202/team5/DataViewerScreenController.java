package seng202.team5;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

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
	private ChoiceBox filterGraphBox;
	
	@FXML
	private RadioButton graphRadio;
	
	@FXML
	private RadioButton rawDataRadio;

	
	/**
	 * Method for when the Map
	 * button is pressed
	 */
	public void mapPressed() {
		mapPressedLabel.setVisible(true); //testing that the function works, but cant run it
	}
	
	
	/**
	 * Method for when the Statistics
	 * button is pressed
	 */
	public void statPressed() {
	}
	
	
	/**
	 * Method for when the Account
	 * button is pressed
	 */
	public void accountPressed() {
	}
	
	
	/**
	 * Method for when the Logout
	 * button is pressed
	 */
	public void logoutPressed() {
	}
	
	
}
