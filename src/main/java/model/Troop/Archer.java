package model.Troop;

import enums.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

/**
 * The type Archer.
 * @author ArianQazvini - NegarAnabestani
 * @version 1.0
 */
public class Archer extends Troop{
    private Rectangle Arrow = new Rectangle();

    /**
     * Instantiates a new Archer.
     */
    public Archer(){
        setAvatar("archers.png");
        super.setCount(2);
        super.setCost(3);
        super.setRange(5);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.2F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(125.0,new Damage(33.0), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(127.0,new Damage(44.0),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(151.0,new Damage(48.0),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(166.0,new Damage(53.0),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(182.0,new Damage(58.0),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setId(CardId.archer);
    }

    /**
     * Hit method
     */
    @Override
    public void Hit()
    {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    }
                    double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getLockedTarget().getX_Current()-this.getPicHandler().getX();
                    double y_Vector =super.getLockedTarget().getY_Current()-this.getPicHandler().getY();
                    double xMoveVector = x_Vector*distPart;
                    double yMoveVector = y_Vector*distPart;
                    //------------------------
                    if(xMoveVector>0)
                    {
                        this.ArrowRight(xMoveVector);
                    }
                    else
                    {
                        this.ArrowLeft((-1)*xMoveVector);
                    }
                    //------------------------------
                    if(yMoveVector>0)
                    {
                        this.ArrowBackWard(yMoveVector);
                    }
                    else
                    {
                        this.ArrowForward((-1)*yMoveVector);
                    }
                }
                else
                {
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                    setShootingTimeTick(0);
                    Arrow.setX(super.getX_Current());
                    Arrow.setY(super.getY_Current());
                }
            }
            else if(super.getTowerTarget()!=null)
            {
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getTowerTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    }
                    double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getTowerTarget().getX()-this.getPicHandler().getX();
                    double y_Vector =super.getTowerTarget().getY()-this.getPicHandler().getY();
                    double xMoveVector = x_Vector*distPart;
                    double yMoveVector = y_Vector*distPart;
                    //------------------------
                    if(xMoveVector>0)
                    {
                        this.ArrowRight(xMoveVector);
                    }
                    else
                    {
                        this.ArrowLeft((-1)*xMoveVector);
                    }
                    //------------------------------
                    if(yMoveVector>0)
                    {
                        this.ArrowBackWard(yMoveVector);
                    }
                    else
                    {
                        this.ArrowForward((-1)*yMoveVector);
                    }
                }
                else
                {
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                    setShootingTimeTick(0);
                    Arrow.setX(super.getX_Current());
                    Arrow.setY(super.getY_Current());
                }
            }
        }
        else
        {
            super.setLockedTarget(null);
            super.setTowerTarget(null);
            setShootingTimeTick(0);
            Arrow.setX(super.getX_Current());
            Arrow.setY(super.getY_Current());
        }
    }

    /**
     *  set Walking top mode pic
     */
    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    /**
     *  set Walking left mode pic
     */
    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    /**
     *  set Walking right mode pic
     */
    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    /**
     *  set Walking down mode pic
     */
    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    /**
     *  set Hit top mode pic
     */
    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherHit_Up.png").toURI().toString());
        Image image2 = new Image(new File("src/main/resources/pics/Characters/ArrowUp.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        ImagePattern imagePattern2 = new ImagePattern(image2);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //----------------------------------------------
        this.Arrow.setFill(imagePattern2);
        this.Arrow.setHeight(20);
        this.Arrow.setWidth(20);
        this.Arrow.setX(super.getX_Current());
        this.Arrow.setY(super.getY_Current());
    }
    /**
     *  set Hit down mode pic
     */
    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherHit_Down.png").toURI().toString());
        Image image2 = new Image(new File("src/main/resources/pics/Characters/ArrowDown.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        ImagePattern imagePattern2 = new ImagePattern(image2);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //----------------------------------------------
        this.Arrow.setFill(imagePattern2);
        this.Arrow.setHeight(20);
        this.Arrow.setWidth(20);
        this.Arrow.setX(super.getX_Current());
        this.Arrow.setY(super.getY_Current());
    }
    /**
     *  set Hit left mode pic
     */
    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherHit_Left.png").toURI().toString());
        Image image2 = new Image(new File("src/main/resources/pics/Characters/ArrowLeft.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        ImagePattern imagePattern2 = new ImagePattern(image2);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //----------------------------------------------
        this.Arrow.setFill(imagePattern2);
        this.Arrow.setHeight(20);
        this.Arrow.setWidth(20);
        this.Arrow.setX(super.getX_Current());
        this.Arrow.setY(super.getY_Current());
    }
    /**
     *  set Hit right mode pic
     */
    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/ArcherHit_Right.png").toURI().toString());
        Image image2 = new Image(new File("src/main/resources/pics/Characters/ArrowRight.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        ImagePattern imagePattern2 = new ImagePattern(image2);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //----------------------------------------------
        this.Arrow.setFill(imagePattern2);
        this.Arrow.setHeight(20);
        this.Arrow.setWidth(20);
        this.Arrow.setX(super.getX_Current());
        this.Arrow.setY(super.getY_Current());
    }
    /**
     *  set timetick to 0
     */
    @Override
    public void resetTimeTick() {
        if(getShootingTimeTick()==getHitSpeed()*10)
        {
            setShootingTimeTick(0);
            this.Arrow.setX(this.getX_Current());
            this.Arrow.setY(this.getY_Current());
        }
    }
    /**
     *  reset rage effect
     */
    @Override
    public void undoRage() {
        this.getSpeed().setVelocity(1.5);
        this.setHitSpeed(1.2F);
        if(this.getLevelInformation().level==Level.LEVEL1)
        {
            this.getLevelInformation().getDamage().setValue((Double)33.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL2)
        {
            this.getLevelInformation().getDamage().setValue((Double)44.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL3)
        {
            this.getLevelInformation().getDamage().setValue((Double)48.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL4)
        {
            this.getLevelInformation().getDamage().setValue((Double)53.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL5)
        {
            this.getLevelInformation().getDamage().setValue((Double)58.0);
        }

    }

    /**
     * Arrow forward.
     *
     * @param dist the dist
     */
    public void ArrowForward(double dist)
    {
        this.Arrow.setY(this.Arrow.getY()-dist);
    }

    /**
     * Arrow back ward.
     *
     * @param dist the dist
     */
    public void ArrowBackWard(double dist)
    {
        this.Arrow.setY(this.Arrow.getY()+dist);
    }

    /**
     * Arrow left.
     *
     * @param dist the dist
     */
    public void ArrowLeft(double dist)
    {
        this.Arrow.setX(this.Arrow.getX()-dist);
    }

    /**
     * Arrow right.
     *
     * @param dist the dist
     */
    public void ArrowRight(double dist)
    {
        this.Arrow.setX(this.Arrow.getX()+dist);
    }

    /**
     * Gets arrow.
     *
     * @return the arrow
     */
    public Rectangle getArrow() {
        return Arrow;
    }
}
