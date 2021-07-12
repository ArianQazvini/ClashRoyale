package model.Building;

import enums.Level;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Line;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

public class InfernoTower extends Building{
    //private Damage currentDamage;
    private int ShootingTimeTick=0;
    private int FireTimeTick=0;
    private Line fireLine = new Line();
    public InfernoTower(){
        setAvatar("inferno-tower.png");
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
        Image image = new Image(new File("src/main/java/resources/pics/Characters/InfernoTower.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        fireLine.setStroke(Color.ORANGERED);
        fireLine.setStrokeWidth(5);
    }
    @Override
    public void Hit()
    {
        if(super.isLocked())
        {
            ShootingTimeTick++;
            if(super.targetDistance()<= this.getRange() * 20)
            {
                ShootingTimeTick++;
                if(ShootingTimeTick== (super.getHitSpeed() *10))
                {
                    FireTimeTick++;
                    Damage();
                }
                double distPart= ShootingTimeTick/(super.getHitSpeed()*10);
                double x_Vector =super.getLockedTarget().getX_Current()-this.getPicHandler().getX();
                double y_Vector =super.getLockedTarget().getY_Current()-this.getPicHandler().getY();
                //*************************
                //------------------------
                double xMoveVector = x_Vector/distPart;
                double yMoveVector = y_Vector/distPart;
                //------------------------
                fireLine.setStartX(super.getX_Current());
                fireLine.setStartY(super.getY_Current());
                fireLine.setEndX(xMoveVector);
                fireLine.setEndY(yMoveVector);
            }
            else
            {
                ShootingTimeTick=0;
                FireTimeTick=0;
                super.setLockedTarget(null);
            }

        }
    }
    private void Damage()
    {
        DamageVary damage = (DamageVary) super.getDamage().getValue();
        double  range= (damage.max- damage.min);
        double harm = (this.FireTimeTick/40.0)*range + damage.min;
        super.getLockedTarget().Hurt(harm);
    }
    public void setShootingTimeTick(int shootingTimeTick) {
        ShootingTimeTick = shootingTimeTick;
    }
    public int getShootingTimeTick() {
        return ShootingTimeTick;
    }

    public void setFireTimeTick(int fireTimeTick) {
        FireTimeTick = fireTimeTick;
    }

    public int getFireTimeTick() {
        return FireTimeTick;
    }
    public Line getFireLine() {
        return fireLine;
    }
    public void setFireLine(Line fireLine) {
        this.fireLine = fireLine;
    }
}
class DamageVary {
    int min;
    int max;
    public DamageVary(int min,int max){
        this.min=min;
        this.max=max;
    }
    public int getMax() {
        return max;
    }
    public int getMin() {
        return min;
    }
}
