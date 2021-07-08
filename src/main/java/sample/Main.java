package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;

import java.io.File;

public class Main extends Application {
    public static Player player=new Player();
    @Override
    public void start(Stage primaryStage) throws Exception{
        File file;
        Parent root = FXMLLoader.load(new File("src/main/java/View/deck.fxml").toURI().toURL());
        Scene scene = new Scene(root);
        primaryStage.setTitle("ClashRoyale");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
