package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class GameDeckObject extends ImageView {
    Card card;
    public GameDeckObject(Card card){
        this.card=card;
        setImage(new Image(new File("src/main/resources/pics/cards/"+ card.getAvatar()).toURI().toString()));
        setFitHeight(90);
        setFitWidth(60);
    }

    public Card getCard() {
        return card;
    }
}
