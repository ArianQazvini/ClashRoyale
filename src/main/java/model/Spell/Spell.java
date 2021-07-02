package model.Spell;

import model.Card;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class Spell extends Card {
    private String ability;
    private float radius;

    public void setAbility(String ability) {
        this.ability = ability;
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
}
