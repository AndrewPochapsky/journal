package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LogInController implements Initializable{

    @FXML
    Button logInButton, signUpButton;
    @FXML
    TextField newUserInput, newPassInput, returningUserInput, returningPassInput;
    @FXML
    Label invalidLogInLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invalidLogInLabel.setVisible(false);
    }

    public void handleLogInPress() throws SQLException{
        String databaseUsername = "";
        String databasePassword = "";
        String name = returningUserInput.getText();
        String password = returningPassInput.getText();

        String SQL = "SELECT * FROM users WHERE username='" + name + "' AND password='" + password+ "'";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery();
            ){
            boolean successfulLogin = false;
            while(rs.next()){
                databaseUsername = rs.getString("username");
                databasePassword = rs.getString("password");
                System.out.println("Successful Login!\n----");
                successfulLogin=true;
            }
            if(!successfulLogin){
                System.out.println("Incorrect Password or Username\n----");
                invalidLogInLabel.setVisible(true);
            }




        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void handleSignUpPress(){

    }




}
