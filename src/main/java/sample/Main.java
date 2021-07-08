package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;
import services.GameManager;

import java.io.File;

public class Main extends Application {
    public static GameManager gameManager=new GameManager();
    @Override
    public void start(Stage primaryStage) throws Exception {
        File file;
        gameManager.init();
        gameManager.setStage(primaryStage);
        gameManager.setRoot("log_in");
        gameManager.getStage().show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
