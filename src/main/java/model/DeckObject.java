package model;

import Controller.DeckController;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Troop.*;
import sample.Main;
import services.GameManager;

import java.io.File;

public class DeckObject extends ImageView {
    String avatar;
    Image image;
    Card card;
    HBox hBox;
    HBox hBox1;
    HBox hBox2;
    GameManager gameManager= Main.gameManager;
    public DeckObject(HBox hBox,HBox hBox1,HBox hBox2,Card card) {
        this.avatar = card.getAvatar();
        image = new Image(new File("src/main/resources/pics/"+ avatar).toURI().toString());
        setImage(image);
        setFitHeight(100);
        setFitWidth(70);
        this.hBox=hBox;
        this.hBox1=hBox1;
        this.hBox2=hBox2;
        this.card=card;
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                fun();
            }
        });
    }
    public void fun(){
        ((HBox)getParent()).getChildren().remove(this);
        hBox.getChildren().add(new CollectionObject(hBox1,hBox2,card));
        gameManager.getPlayer().getDeck().getCards().remove(card);
    }
}
