package model.robot;

import model.Card;
import model.Player;
import model.Troop.Troop;
import sample.Main;
import services.GameManager;

import java.util.ArrayList;

public abstract class Robot extends Player {
    ArrayList<Card>deckOnGame=new ArrayList<>();
    private GameManager gameManager= Main.gameManager;
    public void putDeck(){
        while (true){
            while (deckOnGame.size()<4){
                setNext();
            }
        }
    }
    private void setNext(){
        deckOnGame.add( getDeck().getCards().get(0));
        Card tmp=getDeck().getCards().get(0);
        getDeck().getCards().remove(tmp);
    }
    public void putCardOnGround(){
        Card card=chooseFromDeck();
        getDeck().getCards().add(card);
        //add card to one one 4 arraylists
    }
    public abstract Card chooseFromDeck();
    public abstract void chooseLocation();
}
