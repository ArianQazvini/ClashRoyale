package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;

/**
 * The type Game deck object.
 */
public class GameDeckObject extends Button {
    /**
     * The Card.
     */
    Card card;

    /**
     * Instantiates a new Game deck object.
     *
     * @param card the card
     */
    public GameDeckObject(Card card){
        this.card=card;
        ImageView imageView=new ImageView(new Image(new File("src/main/resources/pics/cards/"+ card.getAvatar()).toURI().toString()));
         imageView.setFitHeight(90);
         imageView.setFitWidth(60);
        setGraphic(imageView);
        setHeight(90);
        setWidth(60);
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
        return card;
    }
}
