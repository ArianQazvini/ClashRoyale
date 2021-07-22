package model.informations;

import enums.Level;

/**
 * The type Spell level value.
 */
public class SpellLevelValue extends LevelValue{
    /**
     * The Value.
     */
    float value;

    /**
     * Instantiates a new Spell level value.
     *
     * @param level the level
     * @param value the value
     */
    public SpellLevelValue(Level level, float value) {
        super(level);
        this.value=value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public float getValue() {
        return value;
    }
}
