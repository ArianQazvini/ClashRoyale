package model.Troop;

import enums.Commands;
import enums.Speed;
import model.AttackCard;
import enums.Directions;

import java.util.ArrayList;

public abstract class Troop extends AttackCard {
    private int count;
    private Speed speed;
    private boolean isAreaSplash;
    private String WalkingPic;
    private double x_destination;
    private double y_destination;
    private ArrayList<Commands> path = new ArrayList<>();
    private Directions lastDirection;
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
        lastDirection = Directions.LEFT;
        super.setX_Current( super.getPicHandler().getX()-speed.getVelocity());
        super.getPicHandler().setX(super.getX_Current());
    }
    public void Right()
    {
        lastDirection = Directions.RIGHT;
        super.setX_Current( super.getPicHandler().getX()+speed.getVelocity());
        super.getPicHandler().setX(super.getX_Current());
    }
    public void Forward()
    {
        lastDirection = Directions.TOP;
        super.setY_Current( super.getPicHandler().getY()-speed.getVelocity());
        super.getPicHandler().setY(super.getY_Current());
    }
    public void Backward()
    {
        lastDirection= Directions.DOWN;
        super.setY_Current( super.getPicHandler().getY()+speed.getVelocity());
        super.getPicHandler().setY(super.getY_Current());;
    }
    public void Left(double dist)
    {
        lastDirection = Directions.LEFT;
        super.setX_Current( super.getPicHandler().getX()-dist);
        super.getPicHandler().setX(super.getX_Current());
    }
    public void Right(double dist)
    {
        lastDirection = Directions.RIGHT;
        super.setX_Current( super.getPicHandler().getX()+dist);
        super.getPicHandler().setX(super.getX_Current());
    }
    public void Forward(double dist)
    {
        lastDirection = Directions.TOP;
        super.setY_Current( super.getPicHandler().getY()-dist);
        super.getPicHandler().setY(super.getY_Current());
    }
    public void Backward(double dist)
    {
        lastDirection= Directions.DOWN;
        super.setY_Current( super.getPicHandler().getY()+dist);
        super.getPicHandler().setY(super.getY_Current());;
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
    public void changePictoTarget()
    {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
            }
            else if(super.getTowerTarget()!=null)
            {
                if(super.towerDistance()<= this.getRange() * 20)
                {
                    if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
            }

//            else
//            {
//                if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
//                {
//                    WalkingTopMode();
//                }
//                else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
//                {
//                    HitDownMode();
//                }
//                else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
//                {
//                    HitRightMode();
//                }
//                else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
//                {
//                    HitLeftMode();
//                }
//            }
        }
    }
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

    public ArrayList<Commands> getPath() {
        return path;
    }

}
