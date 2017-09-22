package seng202.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.Location;

public class LocationInfoScreenController {

    @FXML
    private Label longLabel;

    @FXML
    private Label locationTypeLabel;

    @FXML
    private Button okButton;

    @FXML
    private Label addressLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label latLabel;

    private Location location;

    @FXML
    void okPressed(ActionEvent event) {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.hide();
    }

    @FXML
    void initialize() {
        location = CurrentStorage.getLocation();
        nameLabel.setText("Name: " + location.getName());
        latLabel.setText("Latitude: " + location.getLatitude());
        longLabel.setText("Longitude: " + location.getLongitude());
        addressLabel.setText("Address: " + location.getAddress());
        locationTypeLabel.setText("Location Type: " + location.getTypeString());
    }
}
