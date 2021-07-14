package model.informations;

import enums.Level;
import model.Damage;

public class ACLevelValue extends LevelValue {
    int hp;
    Damage damage;
    public ACLevelValue(int hp, Damage damage, Level level) {
        super(level);
        this.hp=hp;
        this.damage=damage;
    }

    public Damage getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }
}
