package model.Building;

import enums.Level;
import enums.Target;
import model.Damage;
import model.informations.ACLevelValue;

public class InfernoTower extends Building{
    private Damage currentDamage;
    public InfernoTower(){
        setHitSpeed(0.4F);
        setTarget(Target.AIR_GROUND);
        setRange(6);
        setLifeTime(40);
        setCost(5);
        setLevel1(new ACLevelValue(800, new Damage<>(new DamageVary(20, 400)), Level.LEVEL1));
        setLevel2(new ACLevelValue(880,new Damage<>(new DamageVary(22,440)),Level.LEVEL2));
        setLevel3(new ACLevelValue(968,new Damage<>(new DamageVary(24,484)),Level.LEVEL3));
        setLevel4(new ACLevelValue(1064,new Damage<>(new DamageVary(26,532)),Level.LEVEL4));
        setLevel5(new ACLevelValue(1168,new Damage<>(new DamageVary(29,584)),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
    }
}
class DamageVary {
    int min;
    int max;
    public DamageVary(int min,int max){
        this.min=min;
        this.max=max;
    }
}
