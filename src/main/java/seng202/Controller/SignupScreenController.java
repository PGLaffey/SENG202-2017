package seng202.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import seng202.Model.DataFetcher;
import seng202.Model.User;

import java.io.IOException;

public class SignupScreenController {

    @FXML
    private TextField firstNameLbl;

    @FXML
    private TextField lastNameLbl;

    @FXML
    private TextField usernameLbl;

    @FXML
    private PasswordField passwordLbl;

    @FXML
    private PasswordField repeatPasswordLbl;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Button backButton;

    @FXML
    private Button createButton;

    @FXML
    private Label missingField;

    @FXML Label wrongPasswordLbl;
    /**
     * Creates an account if all fields are met
     * @throws IOException
     */
    public void createBtnPressed(ActionEvent event) throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        if (usernameLbl.getText().trim().isEmpty() || firstNameLbl.getText().trim().isEmpty() || lastNameLbl.getText().trim().isEmpty()){ //not sure why it's red
            missingField.setVisible(true);
        }
        else if (!passwordLbl.getText().equals(repeatPasswordLbl.getText())) {
            wrongPasswordLbl.setVisible(true);
        } else {
        String username = usernameLbl.getText().trim();
        String first = firstNameLbl.getText().trim();
        String last = lastNameLbl.getText().trim();
        String birthDate = birthDatePicker.getValue().toString();
        String password = passwordLbl.getText();

        User newUser = new User(first, last, username, birthDate, password); //create new user - need to change id

        DataFetcher data = new DataFetcher();
        data.connectDb();
        data.addUser(newUser);
            Stage primaryStage = (Stage)createButton.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));

            primaryStage.setTitle("Login");
            primaryStage.setScene(new Scene(root));
        }
    }
    
    public void backButtonPressed(ActionEvent event) throws IOException {
		
		Stage primaryStage = (Stage) backButton.getScene().getWindow(); // Here the window is the stage
		Parent root = FXMLLoader.load(getClass().getResource("/LoginScreen.fxml"));
		
		Scene scene = new Scene(root); // I think we can add in window size here?
		primaryStage.setTitle("Log in");
		primaryStage.setScene(scene);
		primaryStage.show();	
	}
}
