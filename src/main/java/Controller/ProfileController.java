package Controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import sample.Main;
import services.GameManager;
import services.ViewService;

import java.io.File;

/**
 * The type Profile controller.
 */
public class ProfileController {
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The Cup.
     */
    @FXML
    ImageView cup;
    /**
     * The Level.
     */
    @FXML
    ImageView level;
    /**
     * The Page area.
     */
    @FXML
    AnchorPane pageArea;
    /**
     * The Cup text.
     */
    @FXML
    Text cupText;
    /**
     * The Level text.
     */
    @FXML
    Text levelText;
    /**
     * The H box 1.
     */
    @FXML
    HBox hBox1;
    /**
     * The H box 2.
     */
    @FXML
    HBox hBox2;

    /**
     * Initialize.
     */
    public void initialize(){
        cup.setImage(new Image(new File("src/main/resources/pics/trophies.png").toURI().toString()));
        level.setImage(new Image(new File("src/main/resources/pics/playerlevel.png").toURI().toString()));
        cupText.setText(String.valueOf(gameManager.getPlayer().getScores()));
        levelText.setText(gameManager.getPlayer().getLevel());
        ViewService.setBackground(pageArea,"menubg2.jpg");
        setDeck();

    }
    private void setDeck(){
        for (int i=0;i<gameManager.getPlayer().getDeck().getCards().size();i++){
            ImageView imageView=new ImageView(new Image(new File("src/main/resources/pics/cards/"+ gameManager.getPlayer().getDeck().getCards().get(i).getAvatar()).toURI().toString()));
            imageView.setFitHeight(90);
            imageView.setFitWidth(60);
            if (hBox1.getChildren().size()<4){
                hBox1.getChildren().add(imageView);
            }else {
                hBox2.getChildren().add(imageView);
            }
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
