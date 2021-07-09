package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class Giant extends Troop{
    public Giant(){
        super.setCount(1);
        super.setCost(5);
        //super.setRange(melee);
        setSpeed(Speed.SLOW);
        super.setHitSpeed(1.5F);
        super.setTarget(Target.BUILDING);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(2000,new Damage<>(126), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(2200,new Damage<>(138),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(2420,new Damage<>(152),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(2660,new Damage<>(167),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(2920,new Damage<>(183),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setAvatar("giant.png");
    }
}
