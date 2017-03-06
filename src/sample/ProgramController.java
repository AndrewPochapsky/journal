package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

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

    public void loadScene(ActionEvent event, String name) throws IOException{
        Parent parent = FXMLLoader.load(getClass().getResource(name+".fxml"));
        Scene scene = new Scene(parent, 800, 500);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }




}
