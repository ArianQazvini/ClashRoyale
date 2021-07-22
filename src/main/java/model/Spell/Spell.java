package model.Spell;

import model.AttackCard;
import model.Card;
import model.Tower.Tower;
import model.informations.ACLevelValue;
import model.informations.LevelInformation;
import model.informations.LevelValue;
import model.informations.SpellLevelValue;

import java.util.ArrayList;

/**
 * The type Spell.
 */
public class Spell extends Card {
    private String ability;
    private float radius;
    private double x;
    private double y;
    private ArrayList<Tower> towers = new ArrayList<>();
    private ArrayList<AttackCard> attackCards = new ArrayList<>();
    private boolean isDone = false;
    private boolean isUsed =false;

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isUsed() {
        return isUsed;
    }

    /**
     * Sets ability.
     *
     * @param ability the ability
     */
    public void setAbility(String ability) {
        this.ability = ability;
    }

    /**
     * Sets towers.
     *
     * @param towers the towers
     */
    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    /**
     * Sets attack cards.
     *
     * @param attackCards the attack cards
     */
    public void setAttackCards(ArrayList<AttackCard> attackCards) {
        this.attackCards = attackCards;
    }

    /**
     * Gets attack cards.
     *
     * @return the attack cards
     */
    public ArrayList<AttackCard> getAttackCards() {
        return attackCards;
    }

    /**
     * Gets towers.
     *
     * @return the towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    /**
     * Sets radius.
     *
     * @param radius the radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Gets radius.
     *
     * @return the radius
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Gets ability.
     *
     * @return the ability
     */
    public String getAbility() {
        return ability;
    }
    @Override
    public SpellLevelValue getLevelInformation()
    {
        return (SpellLevelValue) super.getLevelInformation();
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
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
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }
}
