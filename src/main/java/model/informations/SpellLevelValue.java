package model.informations;

import enums.Level;

public class SpellLevelValue extends LevelValue{
    int value;
    public SpellLevelValue(Level level, int value) {
        super(level);
        this.value=value;
    }
}
