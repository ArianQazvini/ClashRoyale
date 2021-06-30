package model.Troop;

import enums.Speed;
import enums.Target;
import model.AttackCard;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class Troop extends AttackCard {
    private int count;
    private Speed speed;
    private boolean isAreaSplash;
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
}
