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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EditEntryController implements Initializable{
    private ProgramController controller = new ProgramController();

    @FXML
    Label ownerLabel, savedLabel;
    @FXML
    TextField entryTitleInput, entryDateInput;
    @FXML
    TextArea entryContentInput;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        savedLabel.setVisible(false);
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() + "'s Journal");
        entryTitleInput.setText(ProgramController.getCurrentEntry().getName());
        entryDateInput.setText(ProgramController.getCurrentEntry().getDateWritten());
        entryContentInput.setText(ProgramController.getCurrentEntry().getContent());
    }

    public void handleBackAction(ActionEvent event) throws IOException{
        controller.loadScene(event, "main", false, false);
    }

    public void handleSaveEntry()throws IOException {
        if(entryTitleInput.getText().equals("")){
            entryTitleInput.setText("Entry of "+ ProgramController.getCurrentEntry().getDateWritten());
        }
        Entry newEntry = new Entry(entryTitleInput.getText(),entryDateInput.getText(), entryContentInput.getText());
        //gets the index of entry being edited and replaces it with the "new" entry
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

    }


}
