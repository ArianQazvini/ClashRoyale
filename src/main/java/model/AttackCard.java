package model;

import enums.Speed;
import enums.Target;
import javafx.scene.shape.Rectangle;
import model.Tower.Tower;
import model.informations.ACLevelValue;
import model.informations.LevelInformation;
import model.informations.LevelValue;

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
    public void setLockedTarget(AttackCard lockedTarget) {
            LockedTarget = lockedTarget;
    }
    public AttackCard getLockedTarget() {
        return LockedTarget;
    }

    public Rectangle getPicHandler() {
        return picHandler;
    }

    public void setPicHandler(Rectangle picHandler) {
        this.picHandler = picHandler;
    }
    public void setRaged(boolean raged) {
        isRaged = raged;
    }
    public boolean isRaged() {
        return isRaged;
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
    public float getHp() {
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
        double tempx = this.getLockedTarget().getX_Current() - this.getX_Current();
        double tempy = this.getLockedTarget().getY_Current() - this.getY_Current();
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.pow(sum,0.5);
    }
    public double getY_Current() {
        return y_Current;
    }
    public void Hurt(double damage)
    {
        double temp = this.getLevelInformation().getHp();
        this.getLevelInformation().setHp(temp-damage);
    }

    //before using it -> call setLockedTarget
    public abstract void Hit();
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
    @Override
    public ACLevelValue getLevelInformation()
    {
        return (ACLevelValue) super.getLevelInformation();
    }
    public void setTowerTarget(Tower towerTarget) {
        TowerTarget = towerTarget;
    }
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
    public Tower getTowerTarget() {
        return TowerTarget;
    }
    public double towerDistance() {
        double tempx = this.getTowerTarget().getX() - this.getX_Current();
        double tempy = this.getTowerTarget().getY() - this.getY_Current();
        double sum = Math.pow(tempx, 2) + Math.pow(tempy, 2);
        return Math.pow(sum, 0.5);
    }

    public void setShootingTimeTick(int shootingTimeTick) {
        ShootingTimeTick = shootingTimeTick;
    }

    public int getShootingTimeTick() {
        return ShootingTimeTick;
    }
    public void incrementTimeTick()
    {
        ShootingTimeTick++;
    }
    public abstract void resetTimeTick();
    public abstract void rageImpact();
    public abstract void undoRage();
}
