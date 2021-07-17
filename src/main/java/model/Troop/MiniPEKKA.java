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

public class MiniPEKKA extends Troop{
    private int ShootingTimeTick =0;
    public MiniPEKKA(){
        setAvatar("mini-pekka.png");
        super.setCount(1);
        super.setCost(4);
        super.setRange(1);
        setSpeed(Speed.FAST);
        super.setHitSpeed(1.8F);
        super.setTarget(Target.GROUND);
        super.setAreaSplash(false);
        super.setLevel1(new ACLevelValue(600,new Damage<>(325), Level.LEVEL1));
        super.setLevel2(new ACLevelValue(660,new Damage<>(357),Level.LEVEL2));
        super.setLevel3(new ACLevelValue(726,new Damage<>(393),Level.LEVEL3));
        super.setLevel4(new ACLevelValue(798,new Damage<>(432),Level.LEVEL4));
        super.setLevel5(new ACLevelValue(876,new Damage<>(474),Level.LEVEL5));
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
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaWalk_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaWalk_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaWalk_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void WalkingDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaWalk_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitUpMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaHit_Up.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitDownMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaHit_Down.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitLeftMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaHit_Left.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }

    @Override
    public void HitRightMode() {
        Image image = new Image(new File("src/main/resources/pics/Characters/MiniPekkaHit_Right.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
}
