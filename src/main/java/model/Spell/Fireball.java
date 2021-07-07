package model.Spell;

import enums.Level;
import model.informations.SpellLevelValue;

public class Fireball extends Spell{
    public Fireball(){
        setRadius(2.5F);
        setCost(4);
        setLevel1(new SpellLevelValue(Level.LEVEL1,325));
        setLevel2(new SpellLevelValue(Level.LEVEL2,357));
        setLevel3(new SpellLevelValue(Level.LEVEL3,393));
        setLevel4(new SpellLevelValue(Level.LEVEL4,432));
        setLevel5(new SpellLevelValue(Level.LEVEL5,474));
        setLevelInformation(getLevel1());
    }
}
