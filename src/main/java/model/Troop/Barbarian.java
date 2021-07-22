package model.Troop;

import enums.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

/**
 * The type Barbarian.
 */
public class Barbarian extends Troop{
    /**
     * Instantiates a new Barbarian.
     */
    public Barbarian(){
        setAvatar("barbarians.png");
        super.setCount(4);
        super.setCost(5);
        super.setHitSpeed(1.5F);
        setRange(1);
        super.setTarget(Target.GROUND);
        setSpeed(Speed.MEDIUM);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(300.0,new Damage<>(75.0),Level.LEVEL1));
        super.setLevel2(new ACLevelValue(330.0,new Damage<>(82.0),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(363.0,new Damage<>(90.0),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(438.0,new Damage<>(99.0),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(480.0,new Damage<>(109.0),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setId(CardId.barbarian);
    }

    @Override
    public void Hit() {
        if(this.isLocked())
        {
            if(super.getTowerTarget()!=null)
            {
              //  if(this.towerDistance()<= ((this.getRange()+1) * 20))
             //   {
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getTowerTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    }
            //    }
          //      else
             //   {
             //       super.setLockedTarget(null);
             //       super.setTowerTarget(null);
             //       setShootingTimeTick(0);
             //   }
            }
            else if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    }
                    //------------------------
                }
                else
                {
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                    setShootingTimeTick(0);
                }
            }
        }
        else
        {
            super.setLockedTarget(null);
            super.setTowerTarget(null);
            setShootingTimeTick(0);
        }
    }

    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BarbarianHit_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void resetTimeTick() {
        if(getShootingTimeTick()==getHitSpeed()*10)
        {
            setShootingTimeTick(0);
        }
    }

    @Override
    public void undoRage() {
        this.setHitSpeed(1.5F);
        this.getSpeed().setVelocity(1.5);
        if(this.getLevelInformation().level==Level.LEVEL1)
        {
            this.getLevelInformation().getDamage().setValue((Double)75.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL2)
        {
            this.getLevelInformation().getDamage().setValue((Double)82.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL3)
        {
            this.getLevelInformation().getDamage().setValue((Double)90.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL4)
        {
            this.getLevelInformation().getDamage().setValue((Double)99.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL5)
        {
            this.getLevelInformation().getDamage().setValue((Double)109.0);
        }
    }

    @Override
    public void Left()
    {
        super.Left();
        WalkingLeftMode();
    }
    @Override
    public void changePictoTarget()
    {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
                }
            }
            else if(super.getTowerTarget()!=null)
            {
            //    if(super.towerDistance()<= ((this.getRange()+1) * 20))
              //  {
                    if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.TOP)
                    {
                        HitUpMode();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.DOWN)
                    {
                        HitDownMode();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.RIGHT)
                    {
                        HitRightMode();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.LEFT)
                    {
                        HitLeftMode();
                    }
             //   }
            }
        }
    }
}
