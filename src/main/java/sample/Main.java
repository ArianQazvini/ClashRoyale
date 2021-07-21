package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;
import model.robot.SmartRobot;
import services.DatabaseSaving;
import services.GameManager;

import java.io.File;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicReference;

public class Main extends Application {
    public static GameManager gameManager=new GameManager();
    public static DatabaseSaving databaseSaving=new DatabaseSaving();
    @Override
    public void start(Stage primaryStage) throws Exception {
        File file;
        gameManager.init();
        gameManager.setStage(primaryStage);
        gameManager.setRoot("log_in");
        gameManager.getStage().show();
        gameManager.setOpponent(new SmartRobot());
    }


    public static void main(String[] args)  {

        launch(args);
    }
}
