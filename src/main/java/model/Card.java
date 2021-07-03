package model;

import model.informations.LevelInformation;
import model.informations.LevelValue;

import javafx.scene.shape.Rectangle;

public class Card extends WarObject {
    private int cost;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


}
