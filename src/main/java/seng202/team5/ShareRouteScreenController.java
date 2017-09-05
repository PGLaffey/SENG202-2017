package seng202.team5;

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


    @FXML
    void initialize() {
        assert shareableLinkTextField != null : "fx:id=\"shareableLinkTextField\" was not injected: check your FXML file 'ShareRouteScreen.fxml'.";


    }

}
