package model.Spell;

import enums.Level;
import model.informations.SpellLevelValue;

public class Arrows extends Spell{
    public Arrows(){
        setAvatar("arrows.png");
        setRadius(4);
        setCost(3);
        setLevel1(new SpellLevelValue(Level.LEVEL1,144));
        setLevel2(new SpellLevelValue(Level.LEVEL2,156));
        setLevel3(new SpellLevelValue(Level.LEVEL3,174));
        setLevel4(new SpellLevelValue(Level.LEVEL4,189));
        setLevel5(new SpellLevelValue(Level.LEVEL5,210));
        setLevelInformation(getLevel1());
    }
}
