package model;

import enums.Target;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class AttackCard extends Card {
    private int hp;
    private int damage;
    private float hitSpeed;
    private Target target;
    private int range;

    public float getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
