package model;

import model.informations.LevelInformation;
import model.informations.LevelValue;

import javafx.scene.shape.Rectangle;

public class Card extends Rectangle {
    private int cost;
    private String avatar;
    private String animation;
    private LevelInformation levelInformation;
    private LevelValue level1;
    private LevelValue level2;
    private LevelValue level3;
    private LevelValue level4;

    public Card(int cost, String avatar, String animation, LevelInformation levelInformation, LevelValue level1, LevelValue level2, LevelValue level3, LevelValue level4) {
        this.cost = cost;
        this.avatar = avatar;
        this.animation = animation;
        this.levelInformation = levelInformation;
        this.level1 = level1;
        this.level2 = level2;
        this.level3 = level3;
        this.level4 = level4;
    }
}
