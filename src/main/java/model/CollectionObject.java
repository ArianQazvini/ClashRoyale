package model;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Troop.BabyDragon;
import model.Troop.Barbarian;
import model.Troop.Troop;
import sample.Main;
import services.GameManager;

import java.io.File;

/**
 * The type Collection object.
 */
public class CollectionObject extends ImageView {
    /**
     * The Avatar.
     */
    String avatar;
    /**
     * The Image.
     */
    Image image;
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The H box 1.
     */
    HBox hBox1;
    /**
     * The H box 2.
     */
    HBox hBox2;
    /**
     * The Card.
     */
    Card card;

    /**
     * Instantiates a new Collection object.
     *
     * @param hBox1 the h box 1
     * @param hBox2 the h box 2
     * @param card  the card
     */
    public CollectionObject(HBox hBox1,HBox hBox2,Card card){
        this.avatar= card.getAvatar();
        image=new Image(new File("src/main/resources/pics/cards/"+avatar).toURI().toString());
        setImage(image);
        setFitHeight(100);
        setFitWidth(70);
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
        HBox hBox;
        if (hBox1.getChildren().stream().count()<4)
            hBox=hBox1;
        else
            hBox=hBox2;
        if (hBox.getChildren().stream().count()<4) {
            DeckObject deckObject=new DeckObject((HBox)getParent(),hBox1,hBox2,card);
            hBox.getChildren().add(deckObject);
            gameManager.getPlayer().getDeck().getCards().add(card);
            gameManager.getPlayer().getCards().remove(card);
            ((HBox) getParent()).getChildren().remove(this);
        }
    }
}
