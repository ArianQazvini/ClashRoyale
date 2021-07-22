package model;

import model.informations.LevelInformation;
import model.informations.LevelValue;

import javafx.scene.shape.Rectangle;

/**
 * The type Card.
 */
public class Card extends WarObject {
    private int cost;
    private String type ;

    /**
     * Gets cost.
     *
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets cost.
     *
     * @param cost the cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Do action.
     */
    public void doAction(){

    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set current.
     *
     * @param x the x
     * @param y the y
     */
    public void setCurrent(double x, double y){}
}
