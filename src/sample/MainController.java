package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    //TODO create a dark theme for program
    @FXML
    Label ownerLabel;
    public void handleCloseAction() throws IOException{
        ProgramController.saveUser(ProgramController.getCurrentUser());
        Platform.exit();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
    }
}
