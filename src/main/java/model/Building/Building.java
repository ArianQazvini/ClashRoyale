package model.Building;

import enums.Directions;
import enums.Target;
import javafx.scene.shape.Rectangle;
import model.AttackCard;
import model.informations.LevelInformation;
import model.informations.LevelValue;

/**
 * The type Building.
 * @author ArianQazvini - NegarAnabestani
 * @version 1.0
 */
public abstract class Building extends AttackCard {
    private double lifeTime;
    private double x_Current;
    private double y_Current;

    /**
     * Gets life time.
     *
     * @return the life time
     */
    public double getLifeTime() {
        return lifeTime;
    }

    public double getX_Current() {
        return x_Current;
    }

    public double getY_Current() {
        return y_Current;
    }
    public void setX_Current(double x_Current) {
        this.x_Current = x_Current;
    }
    public void setY_Current(double y_Current) {
        this.y_Current = y_Current;
    }

    /**
     * Sets life time.
     *
     * @param lifeTime the life time
     */
    public void setLifeTime(double lifeTime) {
        this.lifeTime = lifeTime;
    }

    /**
     * Decrement life time of building .
     *
     * @param value the value
     */
    public void decrementLife(double value)
    {
        lifeTime-=value;
    }

    /**
     * Closest direction to directions.
     *
     * @param x_dist the x dist
     * @param y_dist the y dist
     * @return the directions
     */
    public Directions closestDirectionTo(double x_dist, double y_dist)
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
