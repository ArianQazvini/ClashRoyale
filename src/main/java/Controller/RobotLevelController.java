package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import sample.Main;
import services.GameManager;
import services.ViewService;


public class RobotLevelController {
    GameManager gameManager= Main.gameManager;
    @FXML
    AnchorPane pageArea;

    public void initialize() {
        ViewService.setBackground(pageArea, "menubg1.jpg");
    }
    @FXML public void press() throws Exception {
        gameManager.setRoot("sample");
    }
}
