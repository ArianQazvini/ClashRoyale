package model.Building;

import enums.Target;
import model.AttackCard;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class Building extends AttackCard {
    private int lifeTime;

    public Building(int cost, String avatar, String animation, LevelInformation levelInformation, LevelValue level1, LevelValue level2, LevelValue level3, LevelValue level4, int hp, int damage, int hitSpeed, Target target, int range, int lifeTime) {
        super(cost, avatar, animation, levelInformation, level1, level2, level3, level4, hp, damage, hitSpeed, target, range);
        this.lifeTime = lifeTime;
    }
}
