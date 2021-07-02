package model.Troop;

import enums.Level;
import enums.Target;
import model.informations.ACLevelValue;

public class Barbarian extends Troop{
    public Barbarian(){
        int test;
        super.setCount(4);
        super.setCost(5);
        super.setHitSpeed(1.5F);
        super.setTarget(Target.GROUND);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(300,75,Level.LEVEL1));
        super.setLevel2(new ACLevelValue(330,82,Level.LEVEL2));
        super.setLevel3(new ACLevelValue(363,90,Level.LEVEL3));
        super.setLevel4(new ACLevelValue(438,99,Level.LEVEL4));
        super.setLevel5(new ACLevelValue(480,109,Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }
}
