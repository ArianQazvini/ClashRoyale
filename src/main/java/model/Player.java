package model;

import model.Tower.KingTower;
import model.Tower.PrinceTower;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> Deck;
    private String name;
    private String password;
    private KingTower kingTower;
    private PrinceTower princeTower1;
    private PrinceTower princeTower2;
    boolean anonymous=false;
    private int elixir =0;

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
}
