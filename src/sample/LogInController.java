package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class LogInController implements Initializable{
    ProgramController controller = new ProgramController();
    @FXML
    Button logInButton, signUpButton, exitButton;
    @FXML
    TextField newUserInput, returningUserInput;
    @FXML
    PasswordField newPassInput, returningPassInput, newPassREInput;
    @FXML
    Label invalidLogInLabel, invalidSignUpLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        invalidLogInLabel.setVisible(false);
        invalidSignUpLabel.setVisible(false);
        logInButton.defaultButtonProperty().bind(logInButton.focusedProperty());
        signUpButton.defaultButtonProperty().bind(signUpButton.focusedProperty());
    }

    public void handleLogInPress(ActionEvent event) throws SQLException, IOException{
        //String databaseUsername = "";
        //String databasePassword = "";
        String username = returningUserInput.getText();
        String password = returningPassInput.getText();

        String SQL = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password+ "'";

        try(Connection con = DBConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(SQL);
            ResultSet rs = stmt.executeQuery()
            ){
            boolean successfulLogin = false;
            while(rs.next()){
                //databaseUsername = rs.getString("username");
                //databasePassword = rs.getString("password");
                System.out.println("Successful Login!\n----");
                successfulLogin=true;
                ProgramController.loadUser(rs.getInt("id"));
                controller.loadScene(event, "main", false, true);
            }
            if(!successfulLogin){
                System.out.println("Incorrect Password or Username\n----");
                invalidLogInLabel.setVisible(true);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void handleSignUpPress(ActionEvent event)throws SQLException, IOException{

        String username = newUserInput.getText();
        String password = newPassInput.getText();
        String passwordRetyped= newPassREInput.getText();
        String SQL = "INSERT into users(id, username, password) VALUES (?,?,?)";
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = con.prepareStatement(SQL);
        try{
            if(password.equals(passwordRetyped)){
                if(!username.trim().isEmpty() && !password.trim().isEmpty()){
                    stmt.setInt(1,getNextId());
                    stmt.setString(2,username);
                    stmt.setString(3,password);

                    stmt.execute();

                    User user = new User(getNextId()-1, username, password);
                    ProgramController.saveUser(user);
                    ProgramController.setCurrentUser(user);
                    controller.loadScene(event, "main", false, true);
                }else{
                    invalidSignUpLabel.setVisible(true);
                    invalidSignUpLabel.setText("*All fields must be \nfilled*");
                }
            }
            else{
                invalidSignUpLabel.setVisible(true);
                invalidSignUpLabel.setText("*Passwords must match*");
            }

        }catch(SQLIntegrityConstraintViolationException e){
            invalidSignUpLabel.setVisible(true);
            invalidSignUpLabel.setText("*Username or password \nalready exists*");
        }
    }

    private static int getNextId()throws SQLException{

        Connection con = DBConnection.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        rs.last();

        return rs.getRow()+1;
    }

    public void handleExitPress(){
        Platform.exit();
    }
}
