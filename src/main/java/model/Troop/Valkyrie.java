package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import model.Directions;
import model.informations.ACLevelValue;

import java.io.File;

public class Valkyrie extends Troop{
    private int ShootingTimeTick =0;
    public Valkyrie(){
        setAvatar("valkyrie.png");
        super.setCount(1);
        super.setCost(4);
        //super.setRange(melee);
        setSpeed(Speed.MEDIUM);
        super.setHitSpeed(1.5F);
        super.setTarget(Target.GROUND);
        super.setAreaSplash(true);
        super.setLevel1(new ACLevelValue(800,new Damage(120), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(968,new Damage(132),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(1064,new Damage(145),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(1170,new Damage(159),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(1284,new Damage(175),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
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

    @Override
    public void WalkingTopMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/ValkyrieHit_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
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
