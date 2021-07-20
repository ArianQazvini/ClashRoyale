package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Player;
import model.robot.SmartRobot;
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
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }catch (ClassNotFoundException c){
//            System.out.println(c);
//        }
//        String url="jdbc:mysql://localhost:3306";
//        String username="root";
//        String password="1321an801123I";
//        try{
//            Connection connection= DriverManager.getConnection(url,username,password);
//            Statement statement=connection.createStatement();
//            //String use="USE clash_royal";
//            String query="INSERT INTO clash_royal.player_profile VALUES ('negin','123456789',1,2,3,4,5,6,7,8)";
//            //boolean result =statement.execute(use);
//            statement.execute(query);
//            //connection.commit();
//            System.out.println("result");
//        }catch (SQLException q){
//            System.out.println(q);
//        }
        launch(args);

    }
}
