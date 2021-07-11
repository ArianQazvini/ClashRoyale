package model;

import enums.Speed;
import enums.Target;
import javafx.scene.shape.Rectangle;
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
    private AttackCard LockedTarget=null;
    private boolean isLocked=false;
    private Rectangle picHandler = new Rectangle();
    public void setLockedTarget(AttackCard lockedTarget) {
        if(lockedTarget==null)
        {
            isLocked=false;
            this.LockedTarget=null;
        }
        else
        {
            LockedTarget = lockedTarget;
            isLocked=true;
        }
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
    public void setCurrent(double x,double y)
    {
        this.setX_Current(x);
        this.setY_Current(y);
        picHandler.setX(x);
        picHandler.setY(y);
    }
    public double targetDistance()
    {
        double tempx = this.getLockedTarget().getX() - this.getX_Current();
        double tempy = this.getLockedTarget().getY() - this.getY_Current();
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.pow(sum,0.5);
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
    public Rectangle getPicHandler() {
        return picHandler;
    }
    public boolean isLocked() {
        return isLocked;
    }
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
