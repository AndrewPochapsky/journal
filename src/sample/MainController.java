package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable{
    //TODO create a dark theme for program
    ProgramController controller = new ProgramController();
    @FXML
    Label ownerLabel, entryDate, entryTitle;
    @FXML
    MenuBar menuBar;
    @FXML
    TableView<Entry> entryTable;
    @FXML
    TableColumn<Entry, String> titleColumn, dateColumn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
        ProgramController.getCurrentUser().addEntry(new Entry("Testname", "Test Content"));
        ProgramController.getCurrentUser().addEntry(new Entry("Second", "SecondContent"));
        List<Entry>journal = ProgramController.getCurrentUser().getJournal();
        ObservableList<Entry> entries = FXCollections.observableArrayList();

        for(Entry entry:journal){
            entries.add(entry);
        }


        //entryTitle.textProperty().bind(selectedEntry.getName());
        titleColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("dateWritten"));
        entryTable.setItems(entries);

        //Entry entry = entryTable.getSelectionModel().getSelectedItem();
        entryTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entry>() {
            @Override
            public void changed(ObservableValue<? extends Entry> observable, Entry oldValue, Entry newValue) {
                if(entryTable.getSelectionModel().getSelectedItem() !=null){
                    entryTitle.setText("Title: "+ newValue.getName());
                    entryDate.setText("Date Written: "+newValue.getDateWritten());
                }
            }
        });
        //entryTitle.setText(entry.getName());

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
