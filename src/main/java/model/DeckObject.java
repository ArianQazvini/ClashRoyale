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

/**
 * The type Deck object.
 */
public class DeckObject extends ImageView {
    /**
     * The Avatar.
     */
    String avatar;
    /**
     * The Image.
     */
    Image image;
    /**
     * The Card.
     */
    Card card;
    /**
     * The H box.
     */
    HBox hBox;
    /**
     * The H box 1.
     */
    HBox hBox1;
    /**
     * The H box 2.
     */
    HBox hBox2;
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;

    /**
     * Instantiates a new Deck object.
     *
     * @param hBox  the h box
     * @param hBox1 the h box 1
     * @param hBox2 the h box 2
     * @param card  the card
     */
    public DeckObject(HBox hBox,HBox hBox1,HBox hBox2,Card card) {
        this.avatar = card.getAvatar();
        image = new Image(new File("src/main/resources/pics/cards/"+ avatar).toURI().toString());
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

    /**
     * Fun.
     */
    public void fun(){
        ((HBox)getParent()).getChildren().remove(this);
        hBox.getChildren().add(new CollectionObject(hBox1,hBox2,card));
        gameManager.getPlayer().getDeck().getCards().remove(card);
        gameManager.getPlayer().getCards().add(card);
    }
}
