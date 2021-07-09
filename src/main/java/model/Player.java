package model;

import model.Tower.KingTower;
import model.Tower.PrinceTower;
import model.Troop.Troop;
import model.Troop.Giant;
import java.util.ArrayList;

public class Player {
    private ArrayList<Troop> troops = new ArrayList<>();
    private ArrayList<Card>cardsCollection=new ArrayList<>();
    private ArrayList<Card>cards=new ArrayList<>();
    private Deck deck=new Deck();
    private String name;
    private String password;
    private KingTower kingTower;
    private PrinceTower princeTower1;
    private PrinceTower princeTower2;
    boolean anonymous=false;
    private int elixir =0;

    public Player(){
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());
        cards.add(new Giant());

    }
    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }
}
