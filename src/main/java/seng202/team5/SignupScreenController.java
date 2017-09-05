package seng202.team5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    private TextField birthDatePicker;

    @FXML
    private Button backBtn;

    @FXML
    private Button createBtn;

    public void createBtnPressed(ActionEvent event) throws Exception {
        String username = usernameLbl.getText();
        String firstName = firstNameLbl.getText();
        String lastName = lastNameLbl.getText();
        String birthDate = birthDatePicker.getText();

        Connection connect = DriverManager.getConnection("jdbc:mysql://222.152.179.135:3306/cyclrr","monitor","Team5Pass");
        testConnection(connect);
        connect.close();
        
        Stage primaryStage = (Stage) createBtn.getScene().getWindow(); 
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		Scene scene = new Scene(root); 
		primaryStage.setTitle("Log in");
		primaryStage.setScene(scene);
		primaryStage.show();
    }

    private void testConnection(Connection connect) throws Exception {
        Statement qrytest = connect.createStatement();
        ResultSet result = qrytest.executeQuery("show tables");
        while (result.next()) {
            System.out.println(result.getString(0));
        }
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
