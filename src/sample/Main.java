package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        window.setTitle("Journal");
        window.setScene(new Scene(root, 600, 400));
        window.setResizable(false);
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}

