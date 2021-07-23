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

/**
 * The type Robot.
 */
public class Robot extends Player {
    /**
     * The X.
     */
    double x, /**
     * The Y.
     */
    y;
    /**
     * The Max y.
     */
    double maxY, /**
     * The Max x.
     */
    maxX;
    /**
     * The Put speed.
     */
    public int putSpeed;
    /**
     * The Game manager.
     */
    public GameManager gameManager;

    /**
     * Instantiates a new Robot.
     */
    public Robot(){
        super();
        maxY=300;
        gameManager=Main.gameManager;
//        getCards().add(new Giant());
//        getCards().add(new Arrows());
//        getCards().add(new Archer());
//        getCards().add(new Barbarian());
//        getCards().add(new BabyDragon());
//        getCards().add(new MiniPEKKA());
//        getCards().add(new Valkyrie());
//        getCards().add(new Wizard());
//        getCards().add(new Cannon());
//        getCards().add(new InfernoTower());
//        getCards().add(new Rage());
//        getCards().add(new Fireball());
        super.downPics();
        negativeType();
        setCardLevel();
    }

    /**
     * Set battle deck.
     */
    public void setBattleDeck(){
        Random random=new Random(0);
        for (int i=0;i<8;i++){
            Card tmp=getCards().get(random.nextInt(getCards().size()));
            getDeck().getCards().add(tmp);
            getCards().remove(tmp);
           // System.out.println(getCards());
        }
       // System.out.println("-------------------------");
//        for (int i = 0; i < 8; i++) {
//           // System.out.println(getDeck().getCards().get(i));
//        }
    }

    /**
     * Choose from deck card.
     *
     * @return the card
     */
    public  Card chooseFromDeck(){return null;}

    /**
     * Choose location.
     */
    public void chooseLocation(){}

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * Is elixir enough boolean.
     *
     * @return the boolean
     */
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
