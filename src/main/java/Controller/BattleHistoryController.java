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

/**
 * The type Battle history controller.
 */
public class BattleHistoryController {
    /**
     * The S c.
     */
    @FXML
    ScrollPane sC;
    /**
     * The Page area.
     */
    @FXML
    AnchorPane pageArea;
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The Database saving.
     */
    DatabaseSaving databaseSaving=Main.databaseSaving;
    /**
     * The History v box.
     */
    @FXML
    VBox historyVBox;

    /**
     * Initialize.
     */
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

    /**
     * Back.
     *
     * @throws Exception the exception
     */
    @FXML
    public void back() throws Exception {
            gameManager.setRoot("menu");
    }
}
