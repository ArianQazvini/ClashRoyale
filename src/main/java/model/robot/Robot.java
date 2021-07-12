package model.robot;

import model.Building.Building;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Card;
import model.Player;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Troop.*;
import sample.Main;
import services.GameManager;

import java.util.ArrayList;

public class Robot extends Player {
    ArrayList<Card>deckOnGame=new ArrayList<>();
    int x,y;
    private GameManager gameManager= Main.gameManager;
    public Robot(){
        getCards().add(new Giant());
        getCards().add(new Arrows());
        getCards().add(new Archer());
        getCards().add(new Barbarian());
        getCards().add(new BabyDragon());
        getCards().add(new MiniPEKKA());
        getCards().add(new Valkyrie());
        getCards().add(new Wizard());
        getCards().add(new Cannon());
        getCards().add(new InfernoTower());
        getCards().add(new Rage());
        getCards().add(new Fireball());
    }
    public void putDeck(){

            while (deckOnGame.size()<4){
                setNext();

        }
    }
    private void setNext(){
        deckOnGame.add( getDeck().getCards().get(0));
        Card tmp=getDeck().getCards().get(0);
        getDeck().getCards().remove(tmp);
    }
    public void putCardOnGround(Card card){
        getDeck().getCards().add(card);
        if (card instanceof Troop)
            gameManager.getTroops().add((Troop) card);
        else if (card instanceof Building)
            gameManager.getBuildings().add((Building) card);
        setElixir(getElixir().getValue()- card.getCost());
        //add spell

    }
    public  Card chooseFromDeck(){return null;}
    public void chooseLocation(){}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public boolean isElixirEnough(){
        for (Card c:deckOnGame){
            if (c.getCost()<= getElixir().getValue())
                return true;
        }
        return false;
    }
}
