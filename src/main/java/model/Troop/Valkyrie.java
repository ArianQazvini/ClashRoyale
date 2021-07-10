package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class Valkyrie extends Troop{
    public Valkyrie(){
        setAvatar("valkyrie.png");
        super.setCount(1);
        super.setCost(4);
        //super.setRange(melee);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.5F);
        super.setTarget(Target.GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(800,new Damage(120), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(968,new Damage(132),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(1064,new Damage(145),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(1170,new Damage(159),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(1284,new Damage(175),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
