package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import enums.Directions;
import model.Tower.Tower;
import model.informations.ACLevelValue;

import java.io.File;

public class Giant extends Troop{
    private int ShootingTimeTick =0;
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
            if(super.getTowerTarget()!=null)
            {
                changePictoTarget();
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    ShootingTimeTick++;
                    if(ShootingTimeTick== (super.getHitSpeed() *10))
                    {
                        super.getTowerTarget().Hurt((double)super.getLevelInformation().getDamage().getValue());
                        ShootingTimeTick=0;
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
                        super.getLockedTarget().Hurt((double)super.getLevelInformation().getDamage().getValue());
                        ShootingTimeTick=0;
                    }
                    //------------------------
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
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/GiantHit_Right.png").toURI().toString());
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
            if(super.getTowerTarget()!=null)
            {
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    if(super.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(super.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(super.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(super.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.LEFT)
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
            }
        }

    }
}
