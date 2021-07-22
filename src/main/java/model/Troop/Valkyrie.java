package model.Troop;

import enums.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

/**
 * The type Valkyrie
 * @author ArianQazvini - NegarAnabestani
 * @version 1.0
 *
 */
public class Valkyrie extends Troop{
    /**
     * Instantiates a new Valkyrie.
     */
    public Valkyrie(){
        setAvatar("valkyrie.png");
        super.setCount(1);
        super.setCost(4);
        super.setRange(1);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.5F);
        super.setTarget(Target.GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(800.0,new Damage(120.0), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(968.0,new Damage(132.0),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(1064.0,new Damage(145.0),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(1170.0,new Damage(159.0),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(1284.0,new Damage(175.0),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setId(CardId.valkyrie);
    }

    /**
     * Hit method
     */
    @Override
    public void Hit() {
        if(this.isLocked())
        {
            if(super.getTowerTarget()!=null)
            {
               // if(this.towerDistance()<= ((this.getRange()+1) * 20))
              //  {
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getTowerTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    }
            //    }
//                else
//                {
//                    super.setLockedTarget(null);
//                    super.setTowerTarget(null);
//                    setShootingTimeTick(0);
//                }
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

    /**
     * set Walking top mode pic
     */
    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set Walking left mode pic
     */
    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set Walking right mode pic
     */
    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set walking down mode pic
     */
    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set hit up mode pic
     */
    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set hit down mode pic
     */
    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set hit left mode pic
     */
    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set hit right mode pic
     */
    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ValkyrieHit_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    /**
     * set timetick to 0
     */
    @Override
    public void resetTimeTick() {
        if(getShootingTimeTick()==getHitSpeed()*10)
        {
            setShootingTimeTick(0);
        }
    }

    /**
     * reset rage effect
     */
    @Override
    public void undoRage() {
        this.setHitSpeed(1.5F);
        this.getSpeed().setVelocity(1.5);
        if(this.getLevelInformation().level==Level.LEVEL1)
        {
            this.getLevelInformation().getDamage().setValue((Double)120.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL2)
        {
            this.getLevelInformation().getDamage().setValue((Double)132.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL3)
        {
            this.getLevelInformation().getDamage().setValue((Double)145.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL4)
        {
            this.getLevelInformation().getDamage().setValue((Double)159.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL5)
        {
            this.getLevelInformation().getDamage().setValue((Double)175.0);
        }
    }
    /**
     * change characters pic direction based on it's target closest direction
     */
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
             //   if(super.towerDistance()<= ((this.getRange()+1) * 20))
             //   {
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
