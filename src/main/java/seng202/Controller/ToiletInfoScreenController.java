package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Toilet;



public class ToiletInfoScreenController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label addressLabel;

    @FXML
    private Label boroughLabel;

    @FXML
    private TextField boroughText;

    @FXML
    private Label disabledLabel;

    @FXML
    private ChoiceBox<Boolean> disabledChoice;

    @FXML
    private Label latLabel;

    @FXML
    private Label longLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField nameText;

    @FXML
    private Button okButton;

    @FXML
    private Button saveButton;

    @FXML
    private Label unisexLabel;

    @FXML
    private ChoiceBox<Boolean> unisexChoice;

    @FXML
    private Button updateButton;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipText;

    @FXML
    private Button cancelButton;

    private Integer toiletIndex;


    /**
     * Method when the ok button is pressed, hides the pop up.
     * @param event
     */
    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }
    
    /**
     * Method when the update button is pressed, displays screen to update the selected toilet
     * @param event
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	boroughText.setVisible(true);
    	boroughText.setText(CurrentStorage.getToiletArray().get(toiletIndex).getBorough());
    	unisexChoice.setVisible(true);
    	unisexChoice.setValue(CurrentStorage.getToiletArray().get(toiletIndex).getUniSex());
    	disabledChoice.setVisible(true);
    	disabledChoice.setValue(CurrentStorage.getToiletArray().get(toiletIndex).getForDisabled());
    	nameText.setVisible(true);
    	nameText.setText(CurrentStorage.getToiletArray().get(toiletIndex).getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(CurrentStorage.getToiletArray().get(toiletIndex).getZip()));;
    	boroughLabel.setText("Borough: ");
    	unisexLabel.setText("Unisex: ");
    	disabledLabel.setText("Disabled: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	saveButton.setVisible(true);
    	cancelButton.setVisible(true);
    }

    /**
     * Checks the input is able to be parsed to an Integer
     * @param s String to be checked
     * @return true if Integer otherwise false
     */
    Boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    
    /**
     * Method when the save button is pressed, updates the selected location
     * @param event
     */
    @FXML
    void savePressed(ActionEvent event) {

    	nameLabel.setTextFill(Color.BLACK);
        addressLabel.setTextFill(Color.BLACK);
        latLabel.setTextFill(Color.BLACK);
        longLabel.setTextFill(Color.BLACK);
        disabledLabel.setTextFill(Color.BLACK);
        unisexLabel.setTextFill(Color.BLACK);
        zipLabel.setTextFill(Color.BLACK);
        boroughLabel.setTextFill(Color.BLACK);

        boolean allValid = true;

    	if (nameText.getText().equals("")) {
    		nameLabel.setTextFill(Color.RED);
    		allValid = false;
        }
        if(disabledChoice.getSelectionModel().isEmpty()) {
            disabledLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (unisexChoice.getSelectionModel().isEmpty()) {
            unisexLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!zipText.getText().equals("") && !isInt(zipText.getText())) {
            zipLabel.setTextFill(Color.RED);
            allValid = false;
        }

        if (allValid) {
    	    CurrentStorage.getToiletArray().get(toiletIndex).setBorough(boroughText.getText());
        	CurrentStorage.getToiletArray().get(toiletIndex).setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
        		CurrentStorage.getToiletArray().get(toiletIndex).setZip(Integer.parseInt(zipText.getText()));
            }
        	CurrentStorage.getToiletArray().get(toiletIndex).setForDisabled(disabledChoice.getValue());
            CurrentStorage.getToiletArray().get(toiletIndex).setUniSex(unisexChoice.getValue());

        	// TODO: Update the database

        	cancelPressed(event);
        }
    }

    @FXML
    void cancelPressed (ActionEvent event) {
        disabledLabel.setText("Disabled: " + String.valueOf(CurrentStorage.getToiletArray().get(toiletIndex).getForDisabled()));
        disabledChoice.setVisible(false);
        nameLabel.setText("Name: " + CurrentStorage.getToiletArray().get(toiletIndex).getName());
        nameText.setVisible(false);
        unisexLabel.setText("Uni Sex: " + String.valueOf(CurrentStorage.getToiletArray().get(toiletIndex).getUniSex()));
        unisexChoice.setVisible(false);
        zipLabel.setText("Zip: " + CurrentStorage.getToiletArray().get(toiletIndex).getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + CurrentStorage.getToiletArray().get(toiletIndex).getBorough());
        boroughText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
    }


    @FXML
    void initialize() {
    	toiletIndex = CurrentStorage.getToiletIndex();
    	disabledLabel.setText("Disabled: " + String.valueOf(CurrentStorage.getToiletArray().get(toiletIndex).getForDisabled()));
    	latLabel.setText("Latitude: " + CurrentStorage.getToiletArray().get(toiletIndex).getLatitude());
    	longLabel.setText("Longitude " + CurrentStorage.getToiletArray().get(toiletIndex).getLongitude());
    	nameLabel.setText("Name: " + CurrentStorage.getToiletArray().get(toiletIndex).getName());
    	unisexLabel.setText("Uni Sex: " + String.valueOf(CurrentStorage.getToiletArray().get(toiletIndex).getUniSex()));
    	zipLabel.setText("Zip: " + CurrentStorage.getToiletArray().get(toiletIndex).getZip());
    	boroughLabel.setText("Borough: " + CurrentStorage.getToiletArray().get(toiletIndex).getBorough());
    	addressLabel.setText("Address: " + CurrentStorage.getToiletArray().get(toiletIndex).getAddress());
    	
    	ObservableList<Boolean> disabledOptions = FXCollections.observableArrayList(true, false);
    	disabledChoice.setItems(disabledOptions);
    	ObservableList<Boolean> unisexOptions = FXCollections.observableArrayList(true, false);
    	unisexChoice.setItems(unisexOptions);
    	
        assert disabledLabel != null : "fx:id=\"disabledLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";
        assert unisexLabel != null : "fx:id=\"unisexLabel\" was not injected: check your FXML file 'ToiletInfoScreen.fxml'.";


    }

}



