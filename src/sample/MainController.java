package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    //TODO create a dark theme for program
    ProgramController controller = new ProgramController();
    @FXML
    Label ownerLabel;
    @FXML
    MenuBar menuBar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
    }

    public void handleCloseAction() throws IOException{
        ProgramController.saveUser(ProgramController.getCurrentUser());
        Platform.exit();
    }

    public void handleLogOutAction()throws IOException{
        //controller.loadScene(event, "logIn", false);
        ProgramController.saveUser(ProgramController.getCurrentUser());

        Parent parent = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene = new Scene(parent, 600, 400);
        Stage stage = (Stage)menuBar.getScene().getWindow();
        stage.hide();
        stage.setMaximized(false);
        stage.setScene(scene);

        stage.show();

    }



}
