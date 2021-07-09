package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class Archer extends Troop{
    public Archer(String WalkingPic){
        super(WalkingPic);
        super.setCount(2);
        super.setCost(3);
        super.setRange(5);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.2F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(125,new Damage(33), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(127,new Damage(44),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(151,new Damage(48),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(166,new Damage(53),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(182,new Damage(58),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
