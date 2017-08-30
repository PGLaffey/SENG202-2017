package seng202.team5;

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

	public void mapPressed() {
		mapPressedLabel.setVisable(true); //testing that the function works, but cant run it
	}
}
