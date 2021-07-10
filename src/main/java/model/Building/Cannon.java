package model.Building;

import enums.Level;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class Cannon extends Building{
    public Cannon(){
        setAvatar("cannon.png");
        setHitSpeed(0.8F);
        setTarget(Target.GROUND);
        setRange(5.5F);
        setLifeTime(30);
        setCost(6);
        setLevel1(new ACLevelValue(380,new Damage(60), Level.LEVEL1));
        setLevel2(new ACLevelValue(418,new Damage(66),Level.LEVEL2));
        setLevel3(new ACLevelValue(459,new Damage(72),Level.LEVEL3));
        setLevel4(new ACLevelValue(505,new Damage(79),Level.LEVEL4));
        setLevel5(new ACLevelValue(554,new Damage(87),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
    }
}
