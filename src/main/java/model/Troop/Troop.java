package model.Troop;

import enums.Speed;
import enums.Target;
import javafx.scene.shape.Rectangle;
import model.AttackCard;
import model.PicHandler;
import model.informations.LevelInformation;
import model.informations.LevelValue;

import java.util.TreeMap;

public abstract class Troop extends AttackCard {
    private int count;
    private Speed speed;
    private boolean isAreaSplash;
    private String WalkingPic;
    private Rectangle picHandler = new Rectangle();
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

    public Rectangle getPicHandler() {
        return picHandler;
    }
    public void Left(double dist)
    {
        super.setX_Current( picHandler.getX()-dist);
        picHandler.setX(super.getX());
    }
    public void Right(double dist)
    {
        super.setX_Current(picHandler.getX()+dist);
        picHandler.setX(super.getX());
    }
    public void Forward(double dist)
    {
        super.setY_Current(picHandler.getY()-dist);
        picHandler.setY(super.getY());
    }
    public void Backward(double dist)
    {
        super.setY_Current(picHandler.getY()+dist);
        picHandler.setY(super.getY());
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
    public void setCurrent(double x,double y)
    {
        super.setX_Current(x);
        super.setY_Current(y);
        picHandler.setX(x);
        picHandler.setY(y);
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
}
