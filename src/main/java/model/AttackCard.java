package model;

import enums.Speed;
import enums.Target;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class AttackCard extends Card {
    private int hp;
    private Damage damage;
    private float hitSpeed;
    private Target target;
    private float range;
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

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

}
