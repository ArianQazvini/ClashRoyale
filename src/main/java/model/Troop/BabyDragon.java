package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Damage;
import model.Directions;
import model.informations.ACLevelValue;

import java.io.File;

public class BabyDragon extends Troop{
    private Circle fireball = new Circle();
    private int ShootingTimeTick =0;
    public BabyDragon(){
        setAvatar("baby-dragon.png");
        super.setCount(1);
        super.setCost(4);
        super.setRange(3);
        setSpeed(Speed.FAST);
        super.setHitSpeed(1.8F);
        super.setTarget(Target.AIR_GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(800,new Damage<>(100), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(880,new Damage<>(110),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(968,new Damage<>(121),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(1064,new Damage<>(133),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(1168,new Damage<>(146),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
        Image image = new Image(new File("src/main/java/resources/pics/Characters/FireBall.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        fireball.setFill(imagePattern);
        fireball.setRadius(10);
        fireball.setCenterX(super.getX_Current());
        fireball.setCenterY(super.getY_Current());
    }

    @Override
    public void Hit() {
        if(super.isLocked())
        {
            changePictoTarget();
            if(super.targetDistance()<= this.getRange() * 20)
            {
                ShootingTimeTick++;
                if(ShootingTimeTick== (super.getHitSpeed() *10))
                {
                    super.getLockedTarget().Hurt((Integer)super.getDamage().getValue());
                }
                double distPart= ShootingTimeTick/(super.getHitSpeed()*10);
                double x_Vector =super.getLockedTarget().getX_Current()-this.getPicHandler().getX();
                double y_Vector =super.getLockedTarget().getY_Current()-this.getPicHandler().getY();
                double xMoveVector = x_Vector/distPart;
                double yMoveVector = y_Vector/distPart;
                //------------------------
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
            fireball.setCenterX(super.getX_Current());
            fireball.setCenterY(super.getY_Current());
        }
    }
    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/BabyDragon_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/BabyDragon_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/BabyDragon_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/BabyDragon_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitUpMode() {
        WalkingTopMode();
    }

    @Override
    public void HitDownMode() {
        WalkingDownMode();
    }

    @Override
    public void HitLeftMode() {
        WalkingLeftMode();
    }

    @Override
    public void HitRightMode() {
        WalkingRightMode();
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
    @Override
    public void changePictoTarget() {
        if(super.isLocked())
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
