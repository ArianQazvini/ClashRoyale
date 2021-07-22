package model.Troop;

import enums.Commands;
import enums.Speed;
import model.AttackCard;
import enums.Directions;

import java.util.ArrayList;

/**
 * The type Troop.
 * @author ArianQazvini-NegarAnabestani
 * @version 1.0
 */
public abstract class Troop extends AttackCard {
    private int count;
    private Speed speed;
    private boolean isAreaSplash;
    private String WalkingPic;
    private double x_destination;
    private double y_destination;
    private ArrayList<Commands> path = new ArrayList<>();
    private Directions lastDirection;

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Is area splash boolean.
     *
     * @return the boolean
     */
    public boolean isAreaSplash() {
        return isAreaSplash;
    }

    /**
     * Sets area splash.
     *
     * @param areaSplash the area splash
     */
    public void setAreaSplash(boolean areaSplash) {
        isAreaSplash = areaSplash;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    /**
     * Left.
     */
    public void Left()
    {
        lastDirection = Directions.LEFT;
        super.setX_Current( super.getPicHandler().getX()-speed.getVelocity());
        super.getPicHandler().setX(super.getX_Current());
    }

    /**
     * Right.
     */
    public void Right()
    {
        lastDirection = Directions.RIGHT;
        super.setX_Current( super.getPicHandler().getX()+speed.getVelocity());
        super.getPicHandler().setX(super.getX_Current());
    }

    /**
     * Forward.
     */
    public void Forward()
    {
        lastDirection = Directions.TOP;
        super.setY_Current( super.getPicHandler().getY()-speed.getVelocity());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * Backward.
     */
    public void Backward()
    {
        lastDirection= Directions.DOWN;
        super.setY_Current( super.getPicHandler().getY()+speed.getVelocity());
        super.getPicHandler().setY(super.getY_Current());;
    }

    /**
     * Left.
     *
     * @param dist the dist
     */
    public void Left(double dist)
    {
        lastDirection = Directions.LEFT;
        super.setX_Current( super.getPicHandler().getX()-dist);
        super.getPicHandler().setX(super.getX_Current());
    }

    /**
     * Right.
     *
     * @param dist the dist
     */
    public void Right(double dist)
    {
        lastDirection = Directions.RIGHT;
        super.setX_Current( super.getPicHandler().getX()+dist);
        super.getPicHandler().setX(super.getX_Current());
    }

    /**
     * Forward.
     *
     * @param dist the dist
     */
    public void Forward(double dist)
    {
        lastDirection = Directions.TOP;
        super.setY_Current( super.getPicHandler().getY()-dist);
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * Backward.
     *
     * @param dist the dist
     */
    public void Backward(double dist)
    {
        lastDirection= Directions.DOWN;
        super.setY_Current( super.getPicHandler().getY()+dist);
        super.getPicHandler().setY(super.getY_Current());;
    }

    /**
     * Sets walking.
     *
     * @param walking the walking
     */
    public void setWalking(String walking) {
        this.WalkingPic = walking;
    }

    /**
     * Gets walking.
     *
     * @return the walking
     */
    public String getWalking() {
        return this.WalkingPic;
    }

    /**
     * Sets destination.
     *
     * @param x the x
     * @param y the y
     */
    public void setDestination(int x,int y)
    {
        this.x_destination=x;
        this.y_destination=y;
    }

    /**
     * Gets x destination.
     *
     * @return the x destination
     */
    public double getX_destination() {
        return x_destination;
    }

    /**
     * Gets y destination.
     *
     * @return the y destination
     */
    public double getY_destination() {
        return y_destination;
    }

    /**
     * Walking top mode.
     */
    public abstract void WalkingTopMode();

    /**
     * Walking left mode.
     */
    public abstract void WalkingLeftMode();

    /**
     * Walking right mode.
     */
    public abstract void WalkingRightMode();

    /**
     * Walking down mode.
     */
    public abstract void WalkingDownMode();

    /**
     * Hit up mode.
     */
    public abstract void HitUpMode();

    /**
     * Hit down mode.
     */
    public abstract void HitDownMode();

    /**
     * Hit left mode.
     */
    public abstract void HitLeftMode();

    /**
     * Hit right mode.
     */
    public abstract void HitRightMode();

    /**
     * change characters pic direction based on it's target closest direction
     */
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
        }
    }

    /**
     * Closest direction to directions.
     *
     * @param x_dist the x dist
     * @param y_dist the y dist
     * @return the directions
     */
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

    /**
     * Gets path.
     *
     * @return the path
     */
    public ArrayList<Commands> getPath() {
        return path;
    }

    /**
     * rage effect
     */
    @Override
    public void rageImpact()
    {
        this.setHitSpeed(this.getHitSpeed() -0.5F);
        this.getSpeed().setVelocity(this.getSpeed().getVelocity() +1.0);
        this.getLevelInformation().getDamage().setValue((Double)this.getLevelInformation().getDamage().getValue()*1.5F);
    }

}
