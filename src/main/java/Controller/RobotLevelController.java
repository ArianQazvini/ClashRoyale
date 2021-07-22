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


/**
 * The type Robot level controller.
 */
public class RobotLevelController {
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The Page area.
     */
    @FXML
    AnchorPane pageArea;
    /**
     * The Simple.
     */
    @FXML
    Button simple;

    /**
     * Initialize.
     */
    public void initialize() {
        ViewService.setBackground(pageArea, "menubg1.jpg");
    }

    /**
     * Press.
     *
     * @param actionEvent the action event
     * @throws Exception the exception
     */
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
