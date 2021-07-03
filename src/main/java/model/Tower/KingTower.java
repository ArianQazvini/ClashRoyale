package model.Tower;

import enums.Level;
import model.Damage;
import model.informations.ACLevelValue;

public class KingTower extends Tower{
    public KingTower(){
        setHitSpeed(1);
        setRange(7);
        setLevel1(new ACLevelValue(2400,new Damage(50), Level.LEVEL1));
        setLevel2(new ACLevelValue(2568,new Damage(53),Level.LEVEL2));
        setLevel3(new ACLevelValue(2736,new Damage(57),Level.LEVEL3));
        setLevel4(new ACLevelValue(2904,new Damage(60),Level.LEVEL4));
        setLevel5(new ACLevelValue(3096,new Damage(64),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
    }
}
