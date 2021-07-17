package model.informations;

import enums.Level;
import model.Damage;

public class ACLevelValue extends LevelValue {
    double hp;
    Damage damage;
    public ACLevelValue(int hp, Damage damage, Level level) {
        super(level);
        this.hp=hp;
        this.damage=damage;
    }

    public Damage getDamage() {
        return damage;
    }

    public double getHp() {
        return hp;
    }

    public void setDamage(Damage damage) {
        this.damage = damage;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
}
