package models.informations;

import enums.Level;

public class AELevelValue extends LevelValue {
    int hp;
    int damage;
    public AELevelValue(int hp, int damage, Level level) {
        super(level);
        this.hp=hp;
        this.damage=damage;
    }
}
