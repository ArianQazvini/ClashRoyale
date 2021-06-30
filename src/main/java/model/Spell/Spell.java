package model.Spell;

import model.Card;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class Spell extends Card {
    private String ability;
    private int radius;

    public Spell(int cost, String avatar, String animation, LevelInformation levelInformation, LevelValue level1, LevelValue level2, LevelValue level3, LevelValue level4, String ability, int radius) {
        super(cost, avatar, animation, levelInformation, level1, level2, level3, level4);
        this.ability = ability;
        this.radius = radius;
    }
}
