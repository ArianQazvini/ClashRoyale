package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import sample.Main;
import services.GameManager;
import services.ViewService;

/**
 * The type Menu controller.
 */
public class MenuController {
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The Page area.
     */
    @FXML
    AnchorPane pageArea;
    @FXML
    Button music;

    /**
     * The Message.
     */
    @FXML
    Text message;

    /**
     * Initialize.
     */
    public void initialize(){
        gameManager.setPlayerLevel();
        gameManager.getPlayer().setCardLevel();
        ViewService.setBackground(pageArea,"menubg1.jpg");
        message.setVisible(false);
    }

    /**
     * Training camp.
     *
     * @throws Exception the exception
     */
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

    /**
     * Deck.
     *
     * @throws Exception the exception
     */
    @FXML
    public void deck() throws Exception {
        gameManager.setRoot("deck");
    }

    /**
     * History.
     *
     * @throws Exception the exception
     */
    @FXML
    public void history() throws Exception {
        gameManager.setRoot("battle_history");
    }

    /**
     * Profile.
     *
     * @throws Exception the exception
     */
    @FXML
    public void profile()throws Exception{
        gameManager.setRoot("profile");
    }

    /**
     * Back.
     *
     * @throws Exception the exception
     */
    @FXML
    public void back() throws Exception {
        gameManager.setRoot("log_in");
    }

}
