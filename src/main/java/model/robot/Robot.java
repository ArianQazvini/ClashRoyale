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
import java.util.Random;

public class Robot extends Player {
    double x,y;
    public GameManager gameManager;
    public Robot(){
        super();
        gameManager=Main.gameManager;
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
        super.downPics();
        negativeType();
    }

    public void setBattleDeck(){
        Random random=new Random(0);
        for (int i=0;i<8;i++){
            Card tmp=getCards().get(random.nextInt(getCards().size()));
            getDeck().getCards().add(tmp);
            getCards().remove(tmp);
        }
    }

    public  Card chooseFromDeck(){return null;}
    public void chooseLocation(){}

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    public boolean isElixirEnough(){
        for (Card c:getDeck().getCards()){
            if (c.getCost()<= getElixir().getValue())
                return true;
        }
        return false;
    }
    private void negativeType()
    {
        for (int i = 0; i < getDeck().getCards().size(); i++) {
            getDeck().getCards().get(i).setType("-");
        }
    }
}
