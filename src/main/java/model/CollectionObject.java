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

public class CollectionObject extends ImageView {
    String avatar;
    Image image;
    GameManager gameManager= Main.gameManager;
    HBox hBox1;
    HBox hBox2;
    Card card;
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
            ((HBox) getParent()).getChildren().remove(this);
        }
    }
}
