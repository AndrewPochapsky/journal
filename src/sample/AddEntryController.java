package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEntryController implements Initializable{
    ProgramController controller = new ProgramController();
    @FXML
    Label ownerLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
    }


    public void handleBackAction(ActionEvent event) throws IOException{
        controller.loadScene(event, "main", false, false);
    }


}
