package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEntryController implements Initializable{
    ProgramController controller = new ProgramController();
    @FXML
    Label ownerLabel;
    @FXML
    TextField entryTitleInput, entryDateInput;
    @FXML
    TextArea entryContentInput;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
        entryDateInput.setText(ProgramController.getCurrentDate());
    }


    public void handleBackAction(ActionEvent event) throws IOException{
        controller.loadScene(event, "main", false, false);
    }

    public void handleSaveEntry(ActionEvent event)throws IOException{
        if(entryTitleInput.getText().equals("")){
            entryTitleInput.setText("Entry of "+ ProgramController.getCurrentDate());
        }
        Entry newEntry = new Entry(entryTitleInput.getText(),entryDateInput.getText(), entryContentInput.getText());
        ProgramController.getCurrentUser().addEntry(newEntry);
        controller.loadScene(event, "main", false, false);

    }

}
