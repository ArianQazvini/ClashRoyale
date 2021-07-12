package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.AttackCard;
import model.Damage;
import model.Directions;
import model.Tower.Tower;
import model.informations.ACLevelValue;

import java.io.File;

public class Giant extends Troop{
    private int ShootingTimeTick =0;
    private Tower TowerTarget = null;
    public Giant(){
        super.setCount(1);
        super.setCost(5);
        super.setRange(1);
        setSpeed(Speed.SLOW);
        super.setHitSpeed(1.5F);
        super.setTarget(Target.BUILDING);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(2000,new Damage<>(126), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(2200,new Damage<>(138),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(2420,new Damage<>(152),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(2660,new Damage<>(167),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(2920,new Damage<>(183),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setAvatar("giant.png");
    }
    @Override
    public void Hit() {
        if(this.isLocked())
        {
            if(this.TowerTarget!=null)
            {
                changePictoTarget();
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    ShootingTimeTick++;
                    if(ShootingTimeTick== (super.getHitSpeed() *10))
                    {
                        this.TowerTarget.Hurt((Integer)super.getDamage().getValue());
                    }
                }
                else
                {
                    ShootingTimeTick=0;
                    if(super.closestDirectionTo(this.getTowerTarget().getX(),this.TowerTarget.getY())== Directions.TOP)
                    {
                        super.Forward();
                    }
                    else if(super.closestDirectionTo(this.getTowerTarget().getX(),this.TowerTarget.getY())== Directions.DOWN)
                    {
                        super.Backward();
                    }
                    else if(super.closestDirectionTo(this.getTowerTarget().getX(),this.TowerTarget.getY())== Directions.RIGHT)
                    {
                        super.Right();
                    }
                    else if(super.closestDirectionTo(this.getTowerTarget().getX(),this.TowerTarget.getY())== Directions.LEFT)
                    {
                        super.Left();
                    }
                }
            }
            else if(super.getLockedTarget()!=null)
            {
                changePictoTarget();
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    ShootingTimeTick++;
                    if(ShootingTimeTick== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((Integer)super.getDamage().getValue());
                    }
                    //------------------------
                }
                else
                {
                    ShootingTimeTick=0;
                    if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
                    {
                        super.Forward();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
                    {
                        super.Backward();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
                    {
                        super.Right();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
                    {
                        super.Left();
                    }
                }
            }
            else
            {
                super.setLockedTarget(null);
                ShootingTimeTick=0;
            }
        }
    }

    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/GiantHit_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void changePictoTarget() {
        if(this.isLocked())
        {
            if(this.TowerTarget!=null)
            {
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    if(super.closestDirectionTo(this.getTowerTarget().getX(),this.TowerTarget.getY())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
                else
                {
                    if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.TOP)
                    {
                        WalkingTopMode();
                    }
                    else if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(super.closestDirectionTo(this.TowerTarget.getX(),this.TowerTarget.getY())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
            }
            else if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
                else
                {
                    if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
                    {
                        WalkingTopMode();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(super.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
            }
        }

    }
    public double towerDistance()
    {
        double tempx = this.getTowerTarget().getX() - this.getX_Current();
        double tempy = this.getTowerTarget().getY() - this.getY_Current();
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.pow(sum,0.5);
    }

    public Tower getTowerTarget() {
        return TowerTarget;
    }

    public void setTowerTarget(Tower towerTarget) {
        TowerTarget = towerTarget;
    }
    @Override
    public boolean isLocked() {
        if(super.getLockedTarget()!=null && this.TowerTarget==null)
        {
            super.setLocked(true);
            return super.isLocked();
        }
        else if(this.TowerTarget!= null && super.getLockedTarget()==null)
        {
            super.setLocked(true);
            return super.isLocked();
        }
        else if(this.TowerTarget==null && super.getLockedTarget()==null)
        {
            super.setLocked(false);
            return super.isLocked();
        }
        else
        {
            super.setLocked(true);
            return super.isLocked();
        }
    }
}
