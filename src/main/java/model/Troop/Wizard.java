package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import model.Damage;
import model.PicHandler;
import model.informations.ACLevelValue;

public class Wizard extends Troop{
    public Wizard(){
        setWalking("src/main/resources/pics/Wizard.jpg");
        setPicHandler(new PicHandler(this,getWalking()));
        super.setCount(1);
        super.setCost(5);
        super.setRange(5);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.7F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(340,new Damage(130), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(374,new Damage(143),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(411,new Damage(157),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(452,new Damage(172),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(496,new Damage(189),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
