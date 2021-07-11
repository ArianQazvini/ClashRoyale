package model.Building;

import enums.Target;
import javafx.scene.shape.Rectangle;
import model.AttackCard;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public abstract class Building extends AttackCard {
    private double lifeTime;
    private double x_Current;
    private double y_Current;
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

    public void setLifeTime(double lifeTime) {
        this.lifeTime = lifeTime;
    }
    public void decrementLife(double value)
    {
        lifeTime-=value;
    }
}
