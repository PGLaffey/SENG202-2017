package seng202.Controller;

import java.net.URL;
import java.util.DoubleSummaryStatistics;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.DataFetcher;
import seng202.Model.Poi;

public class PoiInfoScreenController {
	
    @FXML
    private TextField boroughText;

    @FXML
    private TextField costText;

    @FXML
    private TextField descriptionText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField zipText;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label costLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label latLabel;

    @FXML
    private Label longLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label zipLabel;

    @FXML
    private Label boroughLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private Button okButton;
    
    @FXML
    private Button updateButton;
    
    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;
    
    private Integer poiIndex;
    private Poi oldPoi;


    /**
     * Method when the ok button is pressed, hides the pop up.
     * @param event Auto-generate event on button press
     */
    @FXML
    void okPressed(ActionEvent event) {
    	Stage stage = (Stage) okButton.getScene().getWindow(); 
    	stage.hide();
    }
    
    
    /**
     * Method for when the update button is pressed, displays screen for updating selected poi
     * @param event Auto-generate event on button press
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	boroughText.setVisible(true);
    	boroughText.setText(CurrentStorage.getPoiArray().get(poiIndex).getBorough());
    	costText.setVisible(true);
    	costText.setText(String.valueOf(CurrentStorage.getPoiArray().get(poiIndex).getCost()));
    	descriptionText.setVisible(true);
    	descriptionText.setText(CurrentStorage.getPoiArray().get(poiIndex).getDescription());
    	nameText.setVisible(true);
    	nameText.setText(CurrentStorage.getPoiArray().get(poiIndex).getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(CurrentStorage.getPoiArray().get(poiIndex).getZip()));
    	boroughLabel.setText("Borough: ");
    	costLabel.setText("Cost: ");
    	descriptionLabel.setText("Description: ");
    	nameLabel.setText("Name: ");
    	zipLabel.setText("Zip: ");
    	okButton.setVisible(false);
    	updateButton.setVisible(false);
    	saveButton.setVisible(true);
    	cancelButton.setVisible(true);
    }
    
    
    /**
     * Checks the input is able to be parsed to a Double
     * @param s String to be checked
     * @return true if Double otherwise false
     */
    Boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e){
            return false;
        }
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
    
    
    //TODO add docstring
    @FXML
    void savePressed(ActionEvent event) {
    	nameLabel.setTextFill(Color.BLACK);
    	addressLabel.setTextFill(Color.BLACK);
    	longLabel.setTextFill(Color.BLACK);
    	latLabel.setTextFill(Color.BLACK);
    	costLabel.setTextFill(Color.BLACK);
    	descriptionLabel.setTextFill(Color.BLACK);
    	zipLabel.setTextFill(Color.BLACK);
    	boroughLabel.setTextFill(Color.BLACK);
    	
    	boolean allValid = true;
    	
    	if (nameText.getText().equals("")) {
    		nameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
        if(costText.getText().equals("")) {
            costLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (descriptionText.getText().equals("")) {
            descriptionLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!zipText.getText().equals("") && !isInt(zipText.getText())) {
            zipLabel.setTextFill(Color.RED);
            allValid = false;
        }
        if (!costText.getText().equals("") && !isDouble(costText.getText())) {
            costLabel.setTextFill(Color.RED);
            allValid = false;
    	}

        if (allValid) {
            CurrentStorage.getPoiArray().get(poiIndex).setBorough(boroughText.getText());
            CurrentStorage.getPoiArray().get(poiIndex).setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
                CurrentStorage.getPoiArray().get(poiIndex).setZip(Integer.parseInt(zipText.getText()));
            }
            CurrentStorage.getPoiArray().get(poiIndex).setDescription(descriptionText.getText());
            CurrentStorage.getPoiArray().get(poiIndex).setCost(Double.parseDouble(costText.getText()));

        	// TODO: Update database

            DataFetcher exporter = new DataFetcher();
            try {
                exporter.connectDb();
                exporter.updateLocation(oldPoi, CurrentStorage.getPoiArray().get(poiIndex));
                exporter.closeConnection();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
            cancelPressed(event);
        }
    }
    
    
    //TODO add docstring
    @FXML
    void cancelPressed(ActionEvent event) {
        costLabel.setText("Cost: $" + CurrentStorage.getPoiArray().get(poiIndex).getCost());
        costText.setVisible(false);
        descriptionLabel.setText("Description: " + CurrentStorage.getPoiArray().get(poiIndex).getDescription());
        descriptionText.setVisible(false);
        nameLabel.setText("Name: " + CurrentStorage.getPoiArray().get(poiIndex).getName());
        nameText.setVisible(false);
        zipLabel.setText("Zip: " + CurrentStorage.getPoiArray().get(poiIndex).getZip());
        zipText.setVisible(false);
        boroughLabel.setText("Borough: " + CurrentStorage.getPoiArray().get(poiIndex).getBorough());
        boroughText.setVisible(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        okButton.setVisible(true);
        updateButton.setVisible(true);
    }
    
    
    //TODO add docstring
    @FXML
    void initialize() {
        oldPoi = new Poi(CurrentStorage.getPoiArray().get(CurrentStorage.getPoiIndex()));
        poiIndex = CurrentStorage.getPoiIndex();;
    	costLabel.setText("Cost: $" + CurrentStorage.getPoiArray().get(poiIndex).getCost());
    	descriptionLabel.setText("Description: " + CurrentStorage.getPoiArray().get(poiIndex).getDescription());
    	latLabel.setText("Latitude: " + CurrentStorage.getPoiArray().get(poiIndex).getLatitude());
    	longLabel.setText("Longitude: " + CurrentStorage.getPoiArray().get(poiIndex).getLongitude());
    	nameLabel.setText("Name: " + CurrentStorage.getPoiArray().get(poiIndex).getName());
    	zipLabel.setText("Zip: " + CurrentStorage.getPoiArray().get(poiIndex).getZip());
    	boroughLabel.setText("Borough: " + CurrentStorage.getPoiArray().get(poiIndex).getBorough());
    	addressLabel.setText("Address: " + CurrentStorage.getPoiArray().get(poiIndex).getAddress());
    	
        assert costLabel != null : "fx:id=\"costLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert descriptionLabel != null : "fx:id=\"descriptionLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'PoiInfoScreen.fxml'.";
    }
}





