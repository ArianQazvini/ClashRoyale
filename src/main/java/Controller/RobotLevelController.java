package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import model.robot.FulSmartRobot;
import model.robot.SimpleRobot;
import model.robot.SmartRobot;
import sample.Main;
import services.GameManager;
import services.ViewService;


public class RobotLevelController {
    GameManager gameManager= Main.gameManager;
    @FXML
    AnchorPane pageArea;
    @FXML
    Button simple;

    public void initialize() {
        ViewService.setBackground(pageArea, "menubg1.jpg");
    }
    @FXML
    public void press(ActionEvent actionEvent) throws Exception {
        String value=((Button)actionEvent.getSource()).getText();
        if (value.equals("simple"))
            gameManager.setOpponent(new SimpleRobot());
        else if (value.equals("medium"))
            gameManager.setOpponent(new SmartRobot());
        else
            gameManager.setOpponent(new FulSmartRobot());
        gameManager.setRoot("sample");
    }
}
