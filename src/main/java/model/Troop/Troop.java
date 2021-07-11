package model.Troop;

import enums.Speed;
import enums.Target;
import javafx.scene.shape.Rectangle;
import model.AttackCard;
import model.Directions;
import model.PicHandler;
import model.informations.LevelInformation;
import model.informations.LevelValue;

import javax.print.attribute.standard.Destination;
import java.util.TreeMap;

public abstract class Troop extends AttackCard {
    private int count;
    private Speed speed;
    private boolean isAreaSplash;
    private String WalkingPic;
    private double x_destination;
    private double y_destination;
    public int getCount() {
        return count;
    }
    public boolean isAreaSplash() {
        return isAreaSplash;
    }

    public void setAreaSplash(boolean areaSplash) {
        isAreaSplash = areaSplash;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }
    public void Left()
    {
        super.setX_Current( super.getPicHandler().getX()-speed.getVelocity());
        super.getPicHandler().setX(super.getX());
    }
    public void Right()
    {
        super.setX_Current( super.getPicHandler().getX()+speed.getVelocity());
        super.getPicHandler().setX(super.getX());
    }
    public void Forward()
    {
        super.setY_Current( super.getPicHandler().getY()-speed.getVelocity());
        super.getPicHandler().setY(super.getY());
    }
    public void Backward()
    {
        super.setY_Current( super.getPicHandler().getY()+speed.getVelocity());
        super.getPicHandler().setY(super.getY());;
    }
    public void setWalking(String walking) {
        this.WalkingPic = walking;
    }
    public String getWalking() {
        return this.WalkingPic;
    }
    public void setDestination(int x,int y)
    {
        this.x_destination=x;
        this.y_destination=y;
    }
    public double getX_destination() {
        return x_destination;
    }
    public double getY_destination() {
        return y_destination;
    }
    public abstract void WalkingTopMode();
    public abstract void WalkingLeftMode();
    public abstract void WalkingRightMode();
    public abstract void WalkingDownMode();
    public abstract void HitUpMode();
    public abstract void HitDownMode();
    public abstract void HitLeftMode();
    public abstract void HitRightMode();
    public abstract void changePictoTarget();

    public Directions closestDirectionTo(double x_dist,double y_dist)
    {
        double x_Vector =x_dist-this.getPicHandler().getX();
        double y_Vector =y_dist-this.getPicHandler().getY();
        if(Math.abs(x_Vector)>Math.abs(y_Vector))
        {
            if(y_Vector>0)
            {
                return Directions.DOWN;
            }
            else
            {
                return Directions.TOP;
            }
        }
        else
        {
            if(x_Vector>0)
            {
                return Directions.RIGHT;
            }
            else
            {
                return Directions.LEFT;
            }
        }
    }
}
