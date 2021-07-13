package Controller;

import javafx.fxml.FXML;
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
    Text message;

    public void initialize(){
        ViewService.setBackground(pageArea,"menubg1.jpg");
        message.setVisible(false);
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
}
