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
    private int FireTimeTick=0;
    private Line fireLine = new Line();
    public InfernoTower(){
        setAvatar("inferno-tower.png");
        setHitSpeed(0.4F);
        setTarget(Target.AIR_GROUND);
        setRange(6);
        setLifeTime(40);
        setCost(5);
        setLevel1(new ACLevelValue(800.0, new Damage<>(new DamageVary(20.0, 400.0)), Level.LEVEL1));
        setLevel2(new ACLevelValue(880.0,new Damage<>(new DamageVary(22.0,440.0)),Level.LEVEL2));
        setLevel3(new ACLevelValue(968.0,new Damage<>(new DamageVary(24.0,484.0)),Level.LEVEL3));
        setLevel4(new ACLevelValue(1064.0,new Damage<>(new DamageVary(26.0,532.0)),Level.LEVEL4));
        setLevel5(new ACLevelValue(1168.0,new Damage<>(new DamageVary(29.0,584.0)),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
        Image image = new Image(new File("src/main/resources/pics/Characters/InfernoTower.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        fireLine.setStroke(Color.ORANGERED);
        fireLine.setStrokeWidth(10);
    }
    @Override
    public void Hit()
    {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        FireTimeTick++;
                        Damage();
                    }
                    double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
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
                    setShootingTimeTick(0);
                    FireTimeTick=0;
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                }
            }
            else if(super.getTowerTarget()!=null)
            {
                if(super.towerDistance()<= this.getRange() * 20)
                {
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        FireTimeTick++;
                        Damage();
                    }
                    double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getTowerTarget().getX()-this.getPicHandler().getX();
                    double y_Vector =super.getTowerTarget().getY()-this.getPicHandler().getY();
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
                    setShootingTimeTick(0);
                    FireTimeTick=0;
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                }
            }
        }
        else
        {
                setShootingTimeTick(0);
                FireTimeTick=0;
                super.setLockedTarget(null);
                super.setTowerTarget(null);
        }

    }

    @Override
    public void resetTimeTick() {
        setShootingTimeTick(0);
        FireTimeTick=0;
    }

    private void Damage()
    {
        DamageVary damage = (DamageVary) super.getLevelInformation().getDamage().getValue();
        double  range= (damage.max- damage.min);
        double harm = (this.FireTimeTick/40.0)*range + damage.min;
        if(super.getTowerTarget()!=null)
        {
            super.getTowerTarget().Hurt(harm);
        }
        else if(super.getLockedTarget()!=null)
        {
            super.getLockedTarget().Hurt(harm);
        }
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
    double min;
    double max;
    public DamageVary(double min,double max){
        this.min=min;
        this.max=max;
    }
    public double getMax() {
        return max;
    }
    public double getMin() {
        return min;
    }
}
