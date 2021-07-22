package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.Main;
import services.GameManager;

import java.io.File;
import java.util.ArrayList;

/**
 * The type Game deck.
 */
public class GameDeck {
    /**
     * The H box.
     */
    HBox hBox;
    private ImageView nextCardImageView;
    private GameManager gameManager= Main.gameManager;
    private ArrayList<GameDeckObject>gameDeckObjects=new ArrayList<>();

    /**
     * Instantiates a new Game deck.
     *
     * @param hBox              the h box
     * @param nextCardImageView the next card image view
     */
    public GameDeck(HBox hBox,ImageView nextCardImageView){
        this.hBox=hBox;
        this.nextCardImageView=nextCardImageView;
        for (Card c:gameManager.getPlayer().getDeck().getCards()){
            gameDeckObjects.add(new GameDeckObject(c));
        }
    }

    /**
     * Run.
     */
    public void run() {
        //hBox.setStyle("-fx-background-color:#341e16");

        //while (true){
            while ((long) hBox.getChildren().size() <4){
                setNext();
            //}
        }
    }

    /**
     * Set next.
     */
    public void setNext(){
        GameDeckObject tmp=gameDeckObjects.get(0);
        hBox.getChildren().add(gameDeckObjects.get(0));
        gameDeckObjects.remove(tmp);
        nextCardImageView.setImage(new Image(new File("src/main/resources/pics/cards/"+ gameDeckObjects.get(0).getCard().getAvatar()).toURI().toString()));
    }

    /**
     * Is elixir enough boolean.
     *
     * @return the boolean
     */
    public boolean isElixirEnough(){
        for (Node c:hBox.getChildren()){
            if (((GameDeckObject)c).getCard().getCost()<= gameManager.getPlayer().getElixir().getValue())
                return true;
        }
        return false;
    }

    /**
     * Gets game deck objects.
     *
     * @return the game deck objects
     */
    public ArrayList<GameDeckObject> getGameDeckObjects() {
        return gameDeckObjects;
    }
}
