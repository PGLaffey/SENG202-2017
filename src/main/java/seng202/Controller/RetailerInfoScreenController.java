package seng202.Controller;

import com.lynden.gmapsfx.MapReadyListener;
import com.lynden.gmapsfx.javascript.object.LatLong;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Map;
import seng202.Model.Retailer;
import seng202.team5.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RetailerInfoScreenController {

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
    private Label descriptionLabel;

    @FXML
    private TextField descriptionText;

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
    private Label productLabel;

    @FXML
    private TextField productText;

    @FXML
    private Button saveButton;
    
    @FXML
    private Button cancelButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button showOnMapButton;

    @FXML
    private Label zipLabel;

    @FXML
    private TextField zipText;
    
    private Retailer retailer;


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
     * Method when the update button is pressed, opens screen for user to update the selected poi
     * @param event
     */
    @FXML
    void updatePressed(ActionEvent event) {
    	boroughText.setVisible(true);
    	boroughText.setText(retailer.getBorough());
    	productText.setVisible(true);
    	productText.setText(retailer.getProduct());
    	descriptionText.setVisible(true);
    	descriptionText.setText(retailer.getDescription());
    	nameText.setVisible(true);
    	nameText.setText(retailer.getName());
    	zipText.setVisible(true);
    	zipText.setText(String.valueOf(retailer.getZip()));;
    	boroughLabel.setText("Borough: ");
    	productLabel.setText("Product: ");
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

    @FXML
    void showRetailerOnMap(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainScreen.fxml"));
        Parent root = loader.load();

        Main.getStage().setScene(new Scene(root, Main.getStage().getScene().getWidth(), Main.getStage().getScene().getHeight()));
        Main.getStage().setTitle("Map");

        MainScreenController controller = loader.getController();
        controller.getMapView().addMapReadyListener(new MapReadyListener() {
            @Override
            public void mapReady() {
                Map.findRetailers(retailer, controller.getMapView().getMap());
                controller.getMapView().getMap().setCenter(new LatLong(retailer.getLatitude(), retailer.getLongitude()));
            }
        });
        Main.getStage().show();
    }

    @FXML
    void savePressed(ActionEvent event) {
    	
    	nameLabel.setTextFill(Color.BLACK);
        addressLabel.setTextFill(Color.BLACK);
        latLabel.setTextFill(Color.BLACK);
        longLabel.setTextFill(Color.BLACK);
        productLabel.setTextFill(Color.BLACK);
        descriptionLabel.setTextFill(Color.BLACK);
        zipLabel.setTextFill(Color.BLACK);
        boroughLabel.setTextFill(Color.BLACK);
        
        boolean allValid = true;

    	if (nameText.getText().equals("")) {
    		nameLabel.setTextFill(Color.RED);
    		allValid = false;
    	}
        if (productText.getText().equals("")) {
            productLabel.setTextFill(Color.RED);
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

        if (allValid) {
        	retailer.setBorough(boroughText.getText());
        	retailer.setName(nameText.getText());
        	if (!zipText.getText().equals("")) {
            	retailer.setZip(Integer.parseInt(zipText.getText()));

        	}
        	retailer.setDescription(descriptionText.getText());
        	retailer.setProduct(productText.getText());

        	// TODO: Work out how to update the database

            cancelPressed(event);

        }

    }
    
    @FXML
    void cancelPressed(ActionEvent event) {
    	nameLabel.setText("Name: " + retailer.getName());
    	nameText.setVisible(false);
    	zipLabel.setText("Zip: " + retailer.getZip());
    	zipText.setVisible(false);
    	boroughLabel.setText("Borough: " + retailer.getBorough());
    	boroughText.setVisible(false);
    	productLabel.setText("Product: " + retailer.getProduct());
    	productText.setVisible(false);
    	descriptionLabel.setText("Description: " + retailer.getDescription());
    	descriptionText.setVisible(false);
    	okButton.setVisible(true);
    	updateButton.setVisible(true);
    	saveButton.setVisible(false);
    	cancelButton.setVisible(false);
    }
    

    @FXML
    void initialize() {
    	retailer = CurrentStorage.getRetailer();
    	nameLabel.setText("Name: " + retailer.getName());
    	addressLabel.setText("Address: " + retailer.getAddress());
    	latLabel.setText("Latitude: " + retailer.getLatitude());
    	longLabel.setText("Longitude: " + retailer.getLongitude());
    	zipLabel.setText("Zip: " + retailer.getZip());
    	boroughLabel.setText("Borough: " + retailer.getBorough());
    	productLabel.setText("Product: " + retailer.getProduct());
    	descriptionLabel.setText("Description: " + retailer.getDescription());
    	
        assert addressLabel != null : "fx:id=\"addressLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert latLabel != null : "fx:id=\"latLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert longLabel != null : "fx:id=\"longLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert nameLabel != null : "fx:id=\"nameLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert okButton != null : "fx:id=\"okButton\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";
        assert zipLabel != null : "fx:id=\"zipLabel\" was not injected: check your FXML file 'RetailerInfoScreen.fxml'.";


    }

}
