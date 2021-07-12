package model.Tower;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.AttackCard;
import model.Damage;
import model.WarObject;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public abstract class Tower extends WarObject {
    private float hp;
    private float hitSpeed;
    private float range;
    private ImageView[][] imageViews;
    private double x;
    private double y;
    private Circle CanonnBall = new Circle();
    private AttackCard LockedTarget=null;
    private boolean isLocked=false;
    public float getHitSpeed() {
        return hitSpeed;
    }

    public AttackCard getLockedTarget() {
        return LockedTarget;
    }

    public void setLockedTarget(AttackCard lockedTarget) {
        LockedTarget = lockedTarget;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public ImageView[][] getImageViews() {
        return imageViews;
    }
    public void Hurt(double damage)
    {
        this.hp -= damage;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Circle getCanonnBall() {
        return CanonnBall;
    }

    public void setCanonnBall(Circle canonnBall) {
        CanonnBall = canonnBall;
    }

    public float getHp() {
        return hp;
    }
    public void setHp(float hp) {
        this.hp = hp;
    }
    public boolean isLocked() {
        if(LockedTarget==null)
        {
            setLocked(false);
        }
        else
        {
            setLocked(true);
        }
        return isLocked;
    }
    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
