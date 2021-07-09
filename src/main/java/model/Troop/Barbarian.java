package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class Barbarian extends Troop{
    public Barbarian(){
        super.setCount(4);
        super.setCost(5);
        super.setHitSpeed(1.5F);
        //setRange(melee);
        super.setTarget(Target.GROUND);
        setSpeed(Speed.MEDIUM);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(300,new Damage<>(75),Level.LEVEL1));
        super.setLevel2(new ACLevelValue(330,new Damage<>(82),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(363,new Damage<>(90),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(438,new Damage<>(99),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(480,new Damage<>(109),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
