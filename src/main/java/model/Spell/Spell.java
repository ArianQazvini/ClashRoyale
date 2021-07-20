package model.Spell;

import model.AttackCard;
import model.Card;
import model.Tower.Tower;
import model.informations.ACLevelValue;
import model.informations.LevelInformation;
import model.informations.LevelValue;
import model.informations.SpellLevelValue;

import java.util.ArrayList;

public class Spell extends Card {
    private String ability;
    private float radius;
    private double x;
    private double y;
    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<AttackCard> attackCards = new ArrayList<>();
    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public void setAttackCards(ArrayList<AttackCard> attackCards) {
        this.attackCards = attackCards;
    }

    public synchronized ArrayList<AttackCard> getAttackCards() {
        return attackCards;
    }
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    public String getAbility() {
        return ability;
    }
    @Override
    public SpellLevelValue getLevelInformation()
    {
        return (SpellLevelValue) super.getLevelInformation();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
