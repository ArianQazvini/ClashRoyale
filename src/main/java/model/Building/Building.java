package model.Building;

import enums.Target;
import model.AttackCard;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class Building extends AttackCard {
    private int lifeTime;

    public int getLifeTime() {
        return lifeTime;
    }
    public void setLifeTime(int lifeTime) {
        this.lifeTime = lifeTime;
    }
}
