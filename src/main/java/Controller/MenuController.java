package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import services.GameManager;
import services.ViewService;

public class MenuController {
    GameManager gameManager= Main.gameManager;
    @FXML
    AnchorPane pageArea;

    public void initialize(){
        ViewService.setBackground(pageArea,"menubg1.jpg");
    }
    @FXML
    public void trainingCamp() throws Exception {
        gameManager.setRoot("robot_level");
    }
    @FXML
    public void deck() throws Exception {
        gameManager.setRoot("deck");
    }
}
