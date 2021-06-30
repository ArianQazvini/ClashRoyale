package model.informations;

import enums.Level;

public class ACLevelValue extends LevelValue {
    int hp;
    int damage;
    public ACLevelValue(int hp, int damage, Level level) {
        super(level);
        this.hp=hp;
        this.damage=damage;
    }
}
