package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.Main;
import services.GameManager;

import java.util.ArrayList;

public class GameDeck {
    HBox hBox;

    private GameManager gameManager= Main.gameManager;
    private ArrayList<GameDeckObject>gameDeckObjects=new ArrayList<>();
    public GameDeck(HBox hBox){
        this.hBox=hBox;

        for (Card c:gameManager.getPlayer().getDeck().getCards()){
            gameDeckObjects.add(new GameDeckObject(c));
        }
    }
    public void run() {
        //hBox.setStyle("-fx-background-color:#341e16");

        //while (true){
            while ((long) hBox.getChildren().size() <4){
                setNext();
            //}
        }
    }

    public void setNext(){
        //System.out.println(gameDeckObjects.size());
        GameDeckObject tmp=gameDeckObjects.get(0);
       // System.out.println(tmp.card.getClass());
        hBox.getChildren().add(gameDeckObjects.get(0));
        gameDeckObjects.remove(tmp);
    }
    public boolean isElixirEnough(){
        for (Node c:hBox.getChildren()){
            if (((GameDeckObject)c).getCard().getCost()<= gameManager.getPlayer().getElixir().getValue())
                return true;
        }
        return false;
    }

    public ArrayList<GameDeckObject> getGameDeckObjects() {
        return gameDeckObjects;
    }
}
