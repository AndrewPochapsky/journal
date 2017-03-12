package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgramController {

    private static User currentUser;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        ProgramController.currentUser = currentUser;
    }

    public static void saveUser(User user)throws IOException{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("users/user"+user.getId()+".bin"));
        objectOutputStream.writeObject(user);
        System.out.println("Successfully saved!");
    }

    public static void loadUser(int id)throws IOException, ClassNotFoundException{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("users/user"+id+".bin"));
        User user = (User)objectInputStream.readObject();
        ProgramController.setCurrentUser(user);
    }
    //doesn't work for loading from a menu
    public void loadScene(ActionEvent event, String name, boolean isMaximized, boolean isClosed) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(name+".fxml"));
        Scene scene = new Scene(parent, 1200, 800);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        if(isClosed){
            stage.hide();
        }

        stage.setScene(scene);
        stage.setMaximized(isMaximized);
        stage.setResizable(true);
        stage.show();
    }

    public static String getCurrentDate(){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date currentDate = new Date();
        return df.format(currentDate);
    }

}
