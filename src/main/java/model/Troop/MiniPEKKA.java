package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class MiniPEKKA extends Troop{
    public MiniPEKKA(String WalkingPic){
        super(WalkingPic);
        super.setCount(1);
        super.setCost(4);
        //super.setRange(melee);
        setSpeed(Speed.FAST);
        super.setHitSpeed(1.8F);
        super.setTarget(Target.GROUND);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(600,new Damage<>(325), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(660,new Damage<>(357),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(726,new Damage<>(393),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(798,new Damage<>(432),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(876,new Damage<>(474),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
