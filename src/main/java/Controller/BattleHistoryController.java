package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import sample.Main;
import services.DatabaseSaving;
import services.GameManager;

import java.sql.SQLException;

public class BattleHistoryController {
    GameManager gameManager= Main.gameManager;
    DatabaseSaving databaseSaving=Main.databaseSaving;
    @FXML
    VBox historyVBox;
    public void initialize(){
        try {
            databaseSaving.getBattleHistories();
            for (int i=0;i<gameManager.getPlayer().getBattleHistories().size();i++){
                historyVBox.getChildren().add(gameManager.getPlayer().getBattleHistories().get(i));
            }
        }catch (SQLException q){
            System.out.println(q);
        }

    }
}
