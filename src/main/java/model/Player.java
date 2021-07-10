package model;

import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Tower.KingTower;
import model.Tower.PrinceTower;
import model.Troop.*;

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
        cards.add(new Arrows());
        cards.add(new Archer());
        cards.add(new Barbarian());
        cards.add(new BabyDragon());
        cards.add(new MiniPEKKA());
        cards.add(new Valkyrie());
        cards.add(new Wizard());
        cards.add(new Cannon());
        cards.add(new InfernoTower());
        cards.add(new Rage());
        cards.add(new Fireball());

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
