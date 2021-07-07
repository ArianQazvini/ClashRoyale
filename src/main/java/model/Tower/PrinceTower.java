package model.Tower;

import enums.Level;
import model.Damage;
import model.informations.ACLevelValue;

public class PrinceTower extends Tower{
    public PrinceTower() {

        setHitSpeed(0.8F);
        setRange(7.5F);
        setLevel1(new ACLevelValue(1400,new Damage(50), Level.LEVEL1));
        setLevel2(new ACLevelValue(1512,new Damage(54),Level.LEVEL2));
        setLevel3(new ACLevelValue(1624,new Damage(58),Level.LEVEL3));
        setLevel4(new ACLevelValue(1750,new Damage(62),Level.LEVEL4));
        setLevel5(new ACLevelValue(1890,new Damage(69),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
    }
}
