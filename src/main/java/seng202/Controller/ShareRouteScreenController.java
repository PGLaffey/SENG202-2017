package seng202.Controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ShareRouteScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField shareableLinkTextField;


    //TODO add docstring
    @FXML
    void initialize() {
        assert shareableLinkTextField != null : "fx:id=\"shareableLinkTextField\" was not injected: check your FXML file 'ShareRouteScreen.fxml'.";
    }
}
