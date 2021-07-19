package model.Spell;

import enums.Level;
import model.informations.SpellLevelValue;

public class Rage extends Spell{
    float damageBoost;
    float speedBoost;
    float hitSpeedBoost;
    public Rage(){
        setAvatar("rage.png");
        damageBoost=1.4F;
        speedBoost=1.4F;
        hitSpeedBoost=1.4F;
        setRadius(5);
        setCost(3);
        setLevel1(new SpellLevelValue(Level.LEVEL1,6));
        setLevel2(new SpellLevelValue(Level.LEVEL2,6.5F));
        setLevel3(new SpellLevelValue(Level.LEVEL3,7F));
        setLevel4(new SpellLevelValue(Level.LEVEL4,7.5F));
        setLevel5(new SpellLevelValue(Level.LEVEL5,8F));
        setLevelInformation(getLevel1());
        setId(5);
    }
}
