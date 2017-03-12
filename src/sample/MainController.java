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
import javafx.stage.Modality;
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
    @FXML
    TextArea contentArea;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ownerLabel.setText(ProgramController.getCurrentUser().getUserName() +"'s Journal");
        //test entries
        //ProgramController.getCurrentUser().addEntry(new Entry("Testname", "     Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur?"));
        //ProgramController.getCurrentUser().addEntry(new Entry("Second", "SecondContent"));
        List<Entry>journal = ProgramController.getCurrentUser().getJournal();
        ObservableList<Entry> entries = FXCollections.observableArrayList();

        for(Entry entry:journal){
            entries.add(entry);
        }
        titleColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Entry, String>("dateWritten"));
        entryTable.setItems(entries);

        entryTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Entry>() {
            @Override
            public void changed(ObservableValue<? extends Entry> observable, Entry oldValue, Entry newValue) {
                if(entryTable.getSelectionModel().getSelectedItem() !=null){
                    entryTitle.setText("Title: "+ newValue.getName());
                    entryDate.setText("Date Written: "+newValue.getDateWritten());
                    contentArea.setText(newValue.getContent());
                }
            }
        });

    }

    public void handleCloseAction() throws IOException{
        ProgramController.saveUser(ProgramController.getCurrentUser());
        Platform.exit();
    }

    public void handleLogOutAction()throws IOException{
        ProgramController.saveUser(ProgramController.getCurrentUser());

        Parent parent = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        Scene scene = new Scene(parent, 600, 400);
        Stage stage = (Stage)menuBar.getScene().getWindow();
        stage.hide();
        stage.setMaximized(false);
        stage.setScene(scene);

        stage.show();

    }

    public void handleAddEntry() throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource("addEntry.fxml"));
        //TODO set size of new scene equal to the size of the last scene(for a smoother transition
        Scene scene = new Scene(parent, 1200, 800);
        Stage stage = (Stage)menuBar.getScene().getWindow();

        stage.setMaximized(false);
        stage.setScene(scene);

        stage.show();
    }

    public void handleDeleteEntry(ActionEvent event) throws IOException{
        Entry entryToRemove = entryTable.getSelectionModel().getSelectedItem();
        if(entryToRemove!=null){
            AlertBox box = new AlertBox("Confirmation", "Are you sure?");
            box.display();
            if(box.isDeleteEntry()){
                ProgramController.getCurrentUser().removeEntry(entryToRemove);
                //"refresh" the window
                controller.loadScene(event, "main", false, false);
            }

        }

    }

    public void handleEditEntry(){

    }


}
