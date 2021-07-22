package model;

import enums.Speed;
import enums.Target;
import javafx.scene.shape.Rectangle;
import model.Spell.Rage;
import model.Tower.Tower;
import model.informations.ACLevelValue;
import model.informations.LevelInformation;
import model.informations.LevelValue;

/**
 * The type Attack card.
 */
public abstract class AttackCard extends Card {
    private float hp;
    private Damage damage;
    private float hitSpeed;
    private Target target;
    private float range;
    private double x_Current;
    private double y_Current;
    private AttackCard LockedTarget=null;
    private Tower TowerTarget = null;
    private boolean isLocked=false;
    private Rectangle picHandler = new Rectangle();
    private int ShootingTimeTick =0;
    private boolean isRaged=false;
    private Rage rage = null;

    /**
     * Sets locked target.
     *
     * @param lockedTarget the locked target
     */
    public void setLockedTarget(AttackCard lockedTarget) {
            LockedTarget = lockedTarget;
    }

    /**
     * Gets locked target.
     *
     * @return the locked target
     */
    public AttackCard getLockedTarget() {
        return LockedTarget;
    }

    /**
     * Gets pic handler.
     * for movements we use this rectangle
     * @return the pic handler
     */
    public Rectangle getPicHandler() {
        return picHandler;
    }

    /**
     * Sets pic handler.
     *
     * @param picHandler the pic handler
     */
    public void setPicHandler(Rectangle picHandler) {
        this.picHandler = picHandler;
    }

    /**
     * Sets raged.
     *
     * @param raged the raged
     */
    public void setRaged(boolean raged) {
        isRaged = raged;
    }

    /**
     * Is raged boolean.
     *
     * @return the boolean
     */
    public boolean isRaged() {
        return isRaged;
    }

    /**
     * Gets hit speed.
     *
     * @return the hit speed
     */
    public float getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Sets hit speed.
     *
     * @param hitSpeed the hit speed
     */
    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * Gets target.
     *
     * @return the target
     */
    public Target getTarget() {
        return target;
    }

    /**
     * Sets target.
     *
     * @param target the target
     */
    public void setTarget(Target target) {
        this.target = target;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public float getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(float range) {
        this.range = range;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public Damage getDamage() {
        return damage;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public float getHp() {
        return hp;
    }

    /**
     * Sets y current.
     *
     * @param y_Current the y current
     */
    public void setY_Current(double y_Current) {
        this.y_Current = y_Current;
    }

    /**
     * Sets x current.
     *
     * @param x_Current the x current
     */
    public void setX_Current(double x_Current) {
        this.x_Current = x_Current;
    }

    /**
     * Gets x current.
     *
     * @return the x current
     */
    public double getX_Current() {
        return x_Current;
    }

    /**
     * set current location
     * @param x the x
     * @param y the y
     */
    public void setCurrent(double x,double y)
    {
        this.setX_Current(x);
        this.setY_Current(y);
        picHandler.setX(x);
        picHandler.setY(y);
    }

    /**
     * Target distance
     *
     * @return the distance
     */
    public double targetDistance()
    {
        double tempx = this.getLockedTarget().getX_Current() - this.getX_Current();
        double tempy = this.getLockedTarget().getY_Current() - this.getY_Current();
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.pow(sum,0.5);
    }

    /**
     * Gets y current.
     *
     * @return the y current
     */
    public double getY_Current() {
        return y_Current;
    }

    /**
     * Hurt.
     *
     * @param damage the damage value
     */
    public void Hurt(double damage)
    {
        double temp = this.getLevelInformation().getHp();
        this.getLevelInformation().setHp(temp-damage);
    }

    /**
     * Hit.
     */
//before using it -> call setLockedTarget
    public abstract void Hit();

    /**
     * Sets locked.
     *
     * @param locked the locked
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
    @Override
    public ACLevelValue getLevelInformation()
    {
        return (ACLevelValue) super.getLevelInformation();
    }

    /**
     * Sets tower target.
     *
     * @param towerTarget the tower target
     */
    public void setTowerTarget(Tower towerTarget) {
        TowerTarget = towerTarget;
    }

    /**
     * Is locked boolean.
     *
     * @return the boolean
     */
    public boolean isLocked() {
        if(this.getLockedTarget()!=null && this.TowerTarget==null)
        {
            this.setLocked(true);
            return true;
        }
        else if(this.TowerTarget!= null && this.getLockedTarget()==null)
        {
            this.setLocked(true);
            return true;
        }
        else if(this.TowerTarget==null && this.getLockedTarget()==null)
        {
            this.setLocked(false);
            return false;
        }
        else
        {
            this.setLocked(true);
            return true;
        }
    }

    /**
     * Gets tower target.
     *
     * @return the tower target
     */
    public Tower getTowerTarget() {
        return TowerTarget;
    }

    /**
     * Tower distance double.
     *
     * @return the double
     */
    public double towerDistance() {
        double tempx = this.getTowerTarget().getX() - this.getX_Current();
        double tempy = this.getTowerTarget().getY() - this.getY_Current();
        double sum = Math.pow(tempx, 2) + Math.pow(tempy, 2);
        return Math.pow(sum, 0.5);
    }

    /**
     * Sets shooting time tick.
     *
     * @param shootingTimeTick the shooting time tick
     */
    public void setShootingTimeTick(int shootingTimeTick) {
        ShootingTimeTick = shootingTimeTick;
    }

    /**
     * Gets shooting time tick.
     *
     * @return the shooting time tick
     */
    public int getShootingTimeTick() {
        return ShootingTimeTick;
    }

    /**
     * Increment time tick.
     */
    public void incrementTimeTick()
    {
        ShootingTimeTick++;
    }

    /**
     * Reset time tick.
     */
    public abstract void resetTimeTick();

    /**
     * Rage impact.
     */
    public abstract void rageImpact();

    /**
     * Undo rage.
     */
    public abstract void undoRage();

    /**
     * Sets rage.
     *
     * @param rage the rage
     */
    public void setRage(Rage rage) {
        this.rage = rage;
    }

    /**
     * Gets rage.
     *
     * @return the rage
     */
    public Rage getRage() {
        return rage;
    }
}
