package model.Troop;

import enums.Level;
import enums.Speed;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import enums.Directions;
import model.informations.ACLevelValue;

import java.io.File;

public class Barbarian extends Troop{
    private int ShootingTimeTick =0;
    public Barbarian(){
        setAvatar("barbarians.png");
        super.setCount(4);
        super.setCost(5);
        super.setHitSpeed(1.5F);
        setRange(1);
        super.setTarget(Target.GROUND);
        setSpeed(Speed.MEDIUM);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(300,new Damage<>(75),Level.LEVEL1));
        super.setLevel2(new ACLevelValue(330,new Damage<>(82),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(363,new Damage<>(90),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(438,new Damage<>(99),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(480,new Damage<>(109),Level.LEVEL5));
        super.setLevelInformation(super.getLevel1());
    }

    @Override
    public void Hit() {
        if(this.isLocked())
        {
            if(super.getTowerTarget()!=null)
            {
                if(this.towerDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
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
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    changePictoTarget();
                    ShootingTimeTick++;
                    if(ShootingTimeTick== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((double)super.getLevelInformation().getDamage().getValue());
                        ShootingTimeTick=0;
                    }
                    //------------------------
                }
            }
        }
        else
        {
            super.setLockedTarget(null);
            super.setTowerTarget(null);
            ShootingTimeTick=0;
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
    public void Left()
    {
        super.Left();
        WalkingLeftMode();
    }
}
