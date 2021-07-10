package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class BabyDragon extends Troop{
    public BabyDragon(){
        setAvatar("baby-dragon.png");
        super.setCount(1);
        super.setCost(4);
        super.setRange(3);
        setSpeed(Speed.FAST);
        super.setHitSpeed(1.8F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(800,new Damage<>(100), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(880,new Damage<>(110),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(968,new Damage<>(121),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(1064,new Damage<>(133),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(1168,new Damage<>(146),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
