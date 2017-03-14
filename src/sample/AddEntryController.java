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
    Label ownerLabel, savedLabel;
    @FXML
    TextField entryTitleInput, entryDateInput;
    @FXML
    TextArea entryContentInput;
    Entry newEntry = new Entry("",ProgramController.getCurrentDate(),"");
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        savedLabel.setVisible(false);
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
        entryDateInput.setText(ProgramController.getCurrentDate());
        ProgramController.getCurrentUser().getJournal().add(newEntry);
        ProgramController.setCurrentEntry(newEntry);
    }


    public void handleBackAction(ActionEvent event) throws IOException{
        if(entryTitleInput.getText().equals("")){
            entryTitleInput.setText("Entry of "+ ProgramController.getCurrentDate());
        }
        newEntry = new Entry(entryTitleInput.getText(), entryDateInput.getText(), entryContentInput.getText());
        ProgramController.saveEntry(newEntry);
        controller.loadScene(event, "main", false, false);
    }

    public void handleSaveEntry(ActionEvent event)throws IOException{
        if(entryTitleInput.getText().equals("")){
            entryTitleInput.setText("Entry of "+ ProgramController.getCurrentDate());
        }
        newEntry = new Entry(entryTitleInput.getText(),entryDateInput.getText(), entryContentInput.getText());
        ProgramController.saveEntry(newEntry);
        savedLabel.setVisible(true);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        // your code here
                        savedLabel.setVisible(false);
                    }
                },
                1000
        );
        //controller.loadScene(event, "main", false, false);

    }





}
