package seng202.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import seng202.Model.CurrentStorage;
import seng202.Model.DataFetcher;
import seng202.Model.User;


public class DeleteAccountScreenController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;


    @FXML
    void noButtonPressed(ActionEvent event) throws IOException {
/*    	Stage stage = (Stage) noButton.getScene().getWindow(); 
    	stage.hide();*/

    	Stage primaryStage = (Stage) noButton.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/ProfileScreen.fxml"));
		
		primaryStage.setTitle("Profile");
		primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
		primaryStage.show();
/*		
		Stage primaryStage = (Stage)signInButton.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource("/MainScreen.fxml"));
		primaryStage.setTitle("Profile");
		primaryStage.setScene(new Scene(root));*/
}

    @FXML
    void yesButtonPressed(ActionEvent event) throws IllegalAccessException, ClassNotFoundException, InstantiationException, IOException {
        DataFetcher data = new DataFetcher();
        data.connectDb();
        User user = CurrentStorage.getUser();
        data.deleteUser(user.getUsername());
        CurrentStorage.flush();
    	Stage stage = (Stage) yesButton.getScene().getWindow(); 
    	stage.hide();	// TODO: Work out how to then change primaryStage to login screen
        Stage primaryStage = (Stage) yesButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
        data.closeConnection();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }

    @FXML
    void initialize() {
        assert noButton != null : "fx:id=\"noButton\" was not injected: check your FXML file 'DeleteAccountScreen.fxml'.";
        assert yesButton != null : "fx:id=\"yesButton\" was not injected: check your FXML file 'DeleteAccountScreen.fxml'.";


    }

}
