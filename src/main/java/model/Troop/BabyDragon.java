package model.Troop;

import enums.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;
import java.nio.file.Paths;

/**
 * The type Baby dragon.
 *  @author ArianQazvini - NegarAnabestani
 *  @version 1.0
 */
public class BabyDragon extends Troop{
    private Circle fireball = new Circle();

    /**
     * Instantiates a new Baby dragon.
     */
    public BabyDragon(){
        setAvatar("baby-dragon.png");
        super.setCount(1);
        super.setCost(4);
        super.setRange(3);
        setSpeed(Speed.FAST);
        super.setHitSpeed(1.8F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(800.0,new Damage<>(100.0), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(880.0,new Damage<>(110.0),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(968.0,new Damage<>(121.0),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(1064.0,new Damage<>(133.0),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(1168.0,new Damage<>(146.0),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        setId(CardId.dragon);
    }

    /**
     * Hit method
     */
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
    /**
     *  set Walking Top mode pic
     */
    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BabyDragon_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/FireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }
    /**
     *  set Walking Left mode pic
     */
    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BabyDragon_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/FireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }
    /**
     *  set Walking Right mode pic
     */
    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BabyDragon_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/FireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }
    /**
     *  set Walking Down mode pic
     */
    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/BabyDragon_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/FireBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        fireball.setFill(imagePattern2);
        fireball.setRadius(10);
        fireball.setCenterX(this.getX_Current());
        fireball.setCenterY(this.getY_Current());
    }
    /**
     *  set Hit Top mode pic
     */
    @Override
    public void HitUpMode() {
        WalkingTopMode();
    }
    /**
     *  set Hit Down mode pic
     */
    @Override
    public void HitDownMode() {
        WalkingDownMode();
    }
    /**
     *  set Hit Left mode pic
     */
    @Override
    public void HitLeftMode() {
        WalkingLeftMode();
    }
    /**
     *  set Hit Right mode pic
     */
    @Override
    public void HitRightMode() {
        WalkingRightMode();
    }
    /**
     *  set timetick to 0
     */
    @Override
    public void resetTimeTick() {
        if(getShootingTimeTick()==getHitSpeed()*10)
        {
            setShootingTimeTick(0);
            this.getFireball().setCenterX(this.getX_Current());
            this.getFireball().setCenterY(this.getY_Current());
        }
    }
    /**
     *  reset rage effect
     */
    @Override
    public void undoRage() {
        this.setHitSpeed(1.8F);
        this.getSpeed().setVelocity(2);
        if(this.getLevelInformation().level==Level.LEVEL1)
        {
            this.getLevelInformation().getDamage().setValue((Double)100.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL2)
        {
            this.getLevelInformation().getDamage().setValue((Double)110.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL3)
        {
            this.getLevelInformation().getDamage().setValue((Double)121.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL4)
        {
            this.getLevelInformation().getDamage().setValue((Double)133.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL5)
        {
            this.getLevelInformation().getDamage().setValue((Double)146.0);
        }
    }

    /**
     * Fire ball forward.
     *
     * @param dist the dist
     */
    public void FireBallForward(double dist)
    {
        this.fireball.setCenterY(this.fireball.getCenterY()-dist);
    }

    /**
     * Fire ball back ward.
     *
     * @param dist the dist
     */
    public void FireBallBackWard(double dist)
    {
        this.fireball.setCenterY(this.fireball.getCenterY()+dist);
    }

    /**
     * Fire ball left.
     *
     * @param dist the dist
     */
    public void FireBallLeft(double dist)
    {
        this.fireball.setCenterX(this.fireball.getCenterX()-dist);
    }

    /**
     * Fire ball right.
     *
     * @param dist the dist
     */
    public void FireBallRight(double dist)
    {
        this.fireball.setCenterX(this.fireball.getCenterX()+dist);
    }

    /**
     * Gets fireball.
     *
     * @return the fireball
     */
    public Circle getFireball() {
        return fireball;
    }

    /**
     * set explosion pic
     */
    private void explosionPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/Explosion.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        fireball.setFill(imagePattern);
    }
}
