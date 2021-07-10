package model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import sample.Main;
import services.GameManager;

import java.util.ArrayList;

public class GameDeck extends Thread{
    HBox hBox;

    private GameManager gameManager= Main.gameManager;
    private ArrayList<GameDeckObject>gameDeckObjects=new ArrayList<>();
    public GameDeck(HBox hBox){
        this.hBox=hBox;

        for (Card c:gameManager.getPlayer().getDeck().getCards()){
            gameDeckObjects.add(new GameDeckObject(c));
        }
    }
    @Override
    public void run() {
        hBox.setStyle("-fx-background-color:#341e16");

        while (true){
            while (hBox.getChildren().stream().count()<4){
                setNext();
            }
        }
    }
    public void setDeck(){
        for (int i=0;i<4;i++){
            setNext();
        }
    }
    private void setNext(){
        hBox.getChildren().add(gameDeckObjects.get(0));
        GameDeckObject tmp=gameDeckObjects.get(0);
        gameDeckObjects.remove(tmp);
        gameDeckObjects.add(tmp);
    }

    public ArrayList<GameDeckObject> getGameDeckObjects() {
        return gameDeckObjects;
    }
}
