package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.Main;
import services.GameManager;
import services.ViewService;

public class MenuController {
    GameManager gameManager= Main.gameManager;
    @FXML
    AnchorPane pageArea;
    @FXML
    Button music;

    @FXML
    Text message;

    public void initialize(){
        gameManager.setPlayerLevel();
        gameManager.getPlayer().setCardLevel();
        ViewService.setBackground(pageArea,"menubg1.jpg");
        message.setVisible(false);
    }
    @FXML
    public void music(){
        if (gameManager.isMusicPlaying)
            gameManager.pause();
        else
            gameManager.resume();
    }
    @FXML
    public void trainingCamp() throws Exception {
        if (gameManager.getPlayer().getDeck().getCards().size()==0){
            message.setText("set your battle deck!");
            message.setVisible(true);
        }else
        gameManager.setRoot("robot_level");
    }
    @FXML
    public void deck() throws Exception {
        gameManager.setRoot("deck");
    }
    @FXML
    public void history() throws Exception {
        gameManager.setRoot("battle_history");
    }
    @FXML
    public void profile()throws Exception{
        gameManager.setRoot("profile");
    }
    @FXML
    public void back() throws Exception {
        gameManager.setRoot("log_in");
    }

}
