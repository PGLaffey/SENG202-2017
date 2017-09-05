package seng202.team5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;

import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class SignupScreenController {

    @FXML
    private TextField firstNameLbl;

    @FXML
    private TextField lastNameLbl;

    @FXML
    private TextField usernameLbl;

    @FXML
    private TextField passwordLbl;

    @FXML
    private TextField repeatPasswordLbl;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Button backBtn;

    @FXML
    private Button createBtn;

    @FXML
    private Label missingField;

    /**
     * Creates an account if all fields are met
     * @throws IOException
     */
    public void createBtnPressed(ActionEvent event) throws IOException {
//        if (usernameLbl.getText().trim().isEmpty() || firstNameLbl.getText().trim().isEmpty() || lastNameLbl.getText().trim().isEmpty()
//                || birthDatePicker.getValue() == null){ //not sure why it's red
//            missingField.setVisible(true);
//        }
//        else {
//        String username = usernameLbl.getText().trim();
//        String name = firstNameLbl.getText().trim() + " " + lastNameLbl.getText().trim();
//        String birthDate = birthDatePicker.getValue().toString();
//
//        User newUser = new User(name, 1, birthDate); //create new user - need to change id
//        newUser.setUsername(username);
//
//
//
//
//
//        Stage primaryStage = (Stage) createBtn.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
//
//        Scene scene = new Scene(root);
//        primaryStage.setTitle("Log in");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        }
    }
    
    public void backBtnPressed(ActionEvent event) throws IOException {
		
		Stage primaryStage = (Stage) backBtn.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Log in");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
}
