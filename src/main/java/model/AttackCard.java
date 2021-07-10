package model;

import enums.Speed;
import enums.Target;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public abstract class AttackCard extends Card {
    private int hp;
    private Damage damage;
    private float hitSpeed;
    private Target target;
    private float range;
    private double x_Current;
    private double y_Current;
    private AttackCard LockedTarget;
    public void setLockedTarget(AttackCard lockedTarget) {
        LockedTarget = lockedTarget;
    }
    public AttackCard getLockedTarget() {
        return LockedTarget;
    }

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
    public Damage getDamage() {
        return damage;
    }
    public int getHp() {
        return hp;
    }

    public void setY_Current(double y_Current) {
        this.y_Current = y_Current;
    }

    public void setX_Current(double x_Current) {
        this.x_Current = x_Current;
    }

    public double getX_Current() {
        return x_Current;
    }

    public double getY_Current() {
        return y_Current;
    }
    public void Hurt(double damage)
    {
        this.hp -= damage;
    }

    //before using it -> call setLockedTarget
    public abstract void Hit();

}
