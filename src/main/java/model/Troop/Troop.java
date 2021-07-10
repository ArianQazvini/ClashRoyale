package model.Troop;

import enums.Speed;
import enums.Target;
import model.AttackCard;
import model.PicHandler;
import model.informations.LevelInformation;
import model.informations.LevelValue;

import java.util.TreeMap;

public class Troop extends AttackCard {
    private int count;
    private Speed speed;
    private boolean isAreaSplash;
    private String WalkingPic;
    private PicHandler picHandler;
    private double x_destination;
    private double y_destination;
    private double x_Current;
    private double y_Current;

    public void setPicHandler(PicHandler picHandler) {
        this.picHandler = picHandler;
    }

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

    public PicHandler getPicHandler() {
        return picHandler;
    }
    public void Left()
    {
        x_Current = picHandler.getX()-20;
        picHandler.setX(x_Current);
    }
    public void Right()
    {
        x_Current=picHandler.getX()+20;
        picHandler.setX(x_Current);
    }
    public void Forward()
    {
        y_Current=picHandler.getY()-20;
        picHandler.setY(y_Current);
    }
    public void Backward()
    {
        y_Current=picHandler.getY()+20;
        picHandler.setY(y_Current);
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
    @Override
    public void setCurrent(double x,double y)
    {
        this.x_Current=x;
        this.y_Current=y;
        picHandler.setX(x);
        picHandler.setY(y);
    }
    public double getX_destination() {
        return x_destination;
    }
    public double getY_destination() {
        return y_destination;
    }
    public double getX_Current() {
        return x_Current;
    }
    public double getY_Current() {
        return y_Current;
    }

    @Override
    public void doAction() {
        getPicHandler().setWalkingMode();
    }
}
