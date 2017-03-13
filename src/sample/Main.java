package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main extends Application {
    public static Stage getWindow() {
        return window;
    }

    private static Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        //TODO change icon for program
        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("logIn.fxml"));
        primaryStage.setTitle("Journal");
        Scene scene = new Scene(root, 600, 400);
        window.setScene(scene);
        window.setResizable(false);
        window.show();

    }


    public static void main(String[] args) {
        launch(args);
    }


}

