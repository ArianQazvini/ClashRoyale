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

public class ProfileController {
    GameManager gameManager= Main.gameManager;
    @FXML
    ImageView cup;
    @FXML
    ImageView level;
    @FXML
    AnchorPane pageArea;
    @FXML
    Text cupText;
    @FXML
    Text levelText;
    @FXML
    HBox hBox1;
    @FXML
    HBox hBox2;
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
    @FXML
    public void back() throws Exception {
        gameManager.setRoot("menu");
    }
}
