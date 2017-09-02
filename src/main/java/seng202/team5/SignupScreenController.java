package seng202.team5;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

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
    }

    private void testConnection(Connection connect) throws Exception {
        Statement qrytest = connect.createStatement();
        ResultSet result = qrytest.executeQuery("show tables");
        while (result.next()) {
            System.out.println(result.getString(0));
        }
    }
}
