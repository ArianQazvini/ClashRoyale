package model.informations;

import enums.Level;
import model.Damage;

/**
 * The type Ac level value.
 */
public class ACLevelValue extends LevelValue {
    /**
     * The Hp.
     */
    double hp;
    /**
     * The Damage.
     */
    Damage damage;

    /**
     * Instantiates a new Ac level value.
     *
     * @param hp     the hp
     * @param damage the damage
     * @param level  the level
     */
    public ACLevelValue(double hp, Damage damage, Level level) {
        super(level);
        this.hp=hp;
        this.damage=damage;
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public Damage getDamage() {
        return damage;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public double getHp() {
        return hp;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(Damage damage) {
        this.damage = damage;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(double hp) {
        this.hp = hp;
    }
}
