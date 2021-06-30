package model;

import enums.Target;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class AttackCard extends Card {
    private int hp;
    private int damage;
    private int hitSpeed;
    private Target target;
    private int range;

    public AttackCard(int cost, String avatar, String animation, LevelInformation levelInformation, LevelValue level1, LevelValue level2, LevelValue level3, LevelValue level4, int hp, int damage, int hitSpeed, Target target, int range) {
        super(cost, avatar, animation, levelInformation, level1, level2, level3, level4);
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.target = target;
        this.range = range;
    }
}
