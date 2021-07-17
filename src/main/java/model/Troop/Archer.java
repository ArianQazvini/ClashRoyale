package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.Damage;
import enums.Directions;
import model.informations.ACLevelValue;

import java.io.File;

public class Archer extends Troop{
    private Rectangle Arrow = new Rectangle();
    public Archer(){
        setAvatar("archers.png");
        super.setCount(2);
        super.setCost(3);
        super.setRange(5);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.2F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(125,new Damage(33), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(127,new Damage(44),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(151,new Damage(48),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(166,new Damage(53),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(182,new Damage(58),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
//        ACLevelValue temp = (ACLevelValue) super.getLevelInformation();
//        temp.getHp();
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
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((Integer)super.getDamage().getValue());
                    }
                    double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getLockedTarget().getX_Current()-this.getPicHandler().getX();
                    double y_Vector =super.getLockedTarget().getY_Current()-this.getPicHandler().getY();
                    double xMoveVector = x_Vector/distPart;
                    double yMoveVector = y_Vector/distPart;
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
            }
            else if(super.getTowerTarget()!=null)
            {
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    incrementTimeTick();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getTowerTarget().Hurt((double)super.getLevelInformation().getDamage().getValue());
                    }
                }
                double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                double x_Vector =super.getTowerTarget().getX()-this.getPicHandler().getX();
                double y_Vector =super.getTowerTarget().getY()-this.getPicHandler().getY();
                double xMoveVector = x_Vector/distPart;
                double yMoveVector = y_Vector/distPart;
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
    public void ArrowForward(double dist)
    {
        this.Arrow.setY(this.Arrow.getY()-dist);
    }
    public void ArrowBackWard(double dist)
    {
        this.Arrow.setY(this.Arrow.getY()+dist);
    }
    public void ArrowLeft(double dist)
    {
        this.Arrow.setX(this.Arrow.getX()-dist);
    }
    public void ArrowRight(double dist)
    {
        this.Arrow.setX(this.Arrow.getX()+dist);
    }
}
