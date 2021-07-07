package model.informations;

import enums.Level;

public class SpellLevelValue extends LevelValue{
    float value;
    public SpellLevelValue(Level level, float value) {
        super(level);
        this.value=value;
    }
}
