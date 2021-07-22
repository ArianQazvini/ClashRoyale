package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sample.Main;
import services.DatabaseSaving;
import services.GameManager;
import services.ViewService;

import java.sql.SQLException;

public class BattleHistoryController {
    @FXML
    ScrollPane sC;
    @FXML
    AnchorPane pageArea;
    GameManager gameManager= Main.gameManager;
    DatabaseSaving databaseSaving=Main.databaseSaving;
    @FXML
    VBox historyVBox;
    public void initialize(){
        sC.setStyle("-fx-background-color:#071c3b");
        ViewService.setBackground(historyVBox,"menubg2.jpg");
        ViewService.setBackground(pageArea,"menubg2.jpg");
        ViewService.setBackground(pageArea,"menubg2.jpg");
        try {
            databaseSaving.getBattleHistories();
            for (int i=0;i<gameManager.getPlayer().getBattleHistories().size();i++){
                historyVBox.getChildren().add(gameManager.getPlayer().getBattleHistories().get(i));
            }
        }catch (SQLException q){
            System.out.println(q);
        }

    }
    @FXML
    public void back() throws Exception {
            gameManager.setRoot("menu");
    }
}
