package model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.io.File;

public class GameDeckObject extends Button {
    Card card;
    public GameDeckObject(Card card){
        this.card=card;
        ImageView imageView=new ImageView(new Image(new File("src/main/resources/pics/cards/"+ card.getAvatar()).toURI().toString()));
         imageView.setFitHeight(90);
         imageView.setFitWidth(60);
        setGraphic(imageView);
        setHeight(90);
        setWidth(60);
    }

    public Card getCard() {
        return card;
    }
}
