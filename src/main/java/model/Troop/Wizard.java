package model.Troop;

import enums.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

public class Wizard extends Troop{
    private Circle fireball = new Circle();
    public Wizard(){
        setAvatar("wizard.png");
        super.setCount(1);
        super.setCost(5);
        super.setRange(5);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.7F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(340.0,new Damage(130.0), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(374.0,new Damage(143.0),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(411.0,new Damage(157.0),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(452.0,new Damage(172.0),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(496.0,new Damage(189.0),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setId(CardId.wizard);
    }
    @Override
    public void Hit() {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    super.incrementTimeTick();
                    if(super.getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                        explosionPic();
                    }
                    double distPart= super.getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getLockedTarget().getX_Current()-this.getPicHandler().getX();
                    double y_Vector =super.getLockedTarget().getY_Current()-this.getPicHandler().getY();
                    double xMoveVector = x_Vector*distPart;
                    double yMoveVector = y_Vector*distPart;
                    //------------------------
                    if(xMoveVector>0)
                    {
                        this.FireBallRight(xMoveVector);

                    }
                    else
                    {
                        this.FireBallLeft((-1)*xMoveVector);

                    }
                    //------------------------------
                    if(yMoveVector>0)
                    {
                        this.FireBallBackWard(yMoveVector);

                    }
                    else
                    {
                        this.FireBallForward((-1)*yMoveVector);

                    }
                }
                else
                {
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                    super.setShootingTimeTick(0);
                    fireball.setCenterX(super.getX_Current());
                    fireball.setCenterY(super.getY_Current());
                }
            }
            else if(super.getTowerTarget()!=null)
            {
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    super.incrementTimeTick();
                    if(super.getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getTowerTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    }
                    double distPart= super.getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getTowerTarget().getX()-this.getPicHandler().getX();
                    double y_Vector =super.getTowerTarget().getY()-this.getPicHandler().getY();
                    double xMoveVector = x_Vector*distPart;
                    double yMoveVector = y_Vector*distPart;
                    //------------------------
                    if(xMoveVector>0)
                    {
                        this.FireBallRight(xMoveVector);
                    }
                    else
                    {
                        this.FireBallLeft((-1)*xMoveVector);
                    }
                    //------------------------------
                    if(yMoveVector>0)
                    {
                        this.FireBallBackWard(yMoveVector);
                    }
                    else
                    {
                        this.FireBallForward((-1)*yMoveVector);
                    }
                }
                else
                {
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                    super.setShootingTimeTick(0);
                    fireball.setCenterX(super.getX_Current());
                    fireball.setCenterY(super.getY_Current());
                }
            }
        }
        else
        {
            super.setLockedTarget(null);
            super.setTowerTarget(null);
            super.setShootingTimeTick(0);
            fireball.setCenterX(super.getX_Current());
            fireball.setCenterY(super.getY_Current());
        }
    }
    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());

    }

    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //-------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/WizardFireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }

    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //-------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/WizardFireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }

    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //-------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/WizardFireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }

    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/WizardHit_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //-------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/WizardFireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }
    public void FireBallForward(double dist)
    {
        this.fireball.setCenterY(this.fireball.getCenterY()-dist);
    }
    public void FireBallBackWard(double dist)
    {
        this.fireball.setCenterY(this.fireball.getCenterY()+dist);
    }
    public void FireBallLeft(double dist)
    {
        this.fireball.setCenterX(this.fireball.getCenterX()-dist);
    }
    public void FireBallRight(double dist)
    {
        this.fireball.setCenterX(this.fireball.getCenterX()+dist);
    }

    public Circle getFireball() {
        return fireball;
    }
    @Override
    public void resetTimeTick() {
        if(getShootingTimeTick()==getHitSpeed()*10)
        {
            setShootingTimeTick(0);
            this.getFireball().setCenterX(this.getX_Current());
            this.getFireball().setCenterY(this.getY_Current());
        }
    }
    public void fireballLocationUpdate()
    {
        this.fireball.setCenterX(this.getX_Current());
        this.fireball.setCenterY(this.getY_Current());
    }
    private void explosionPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/Explosion.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        fireball.setFill(imagePattern);
    }
}
