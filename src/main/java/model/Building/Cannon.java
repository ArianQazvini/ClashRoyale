package model.Building;

import enums.Level;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Damage;
import model.informations.ACLevelValue;
import java.io.File;
import java.util.Calendar;

public class Cannon extends Building{
    private Circle CanonnBall = new Circle();
    private int ShootingTimeTick=0;
    public Cannon(){
        setAvatar("cannon.png");
        setHitSpeed(0.8F);
        setTarget(Target.GROUND);
        setRange(5.5F);
        setLifeTime(30);
        setCost(6);
        setLevel1(new ACLevelValue(380,new Damage(60), Level.LEVEL1));
        setLevel2(new ACLevelValue(418,new Damage(66),Level.LEVEL2));
        setLevel3(new ACLevelValue(459,new Damage(72),Level.LEVEL3));
        setLevel4(new ACLevelValue(505,new Damage(79),Level.LEVEL4));
        setLevel5(new ACLevelValue(554,new Damage(87),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
        Image image = new Image(new File("src/main/java/resources/pics/Characters/CannonBall.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        CanonnBall.setFill(imagePattern);
        CanonnBall.setRadius(10);
        CanonnBall.setCenterX(super.getX_Current());
        CanonnBall.setCenterY(super.getY_Current());
    }
    public void UpPic()
    {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/CanonUp.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    public void DownPic()
    {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/CanonDown.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    public void LeftPic()
    {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/CanonLeft.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    public void RightPic()
    {
        Image image = new Image(new File("src/main/java/resources/pics/Characters/CanonRight.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
    }
    public void CannonBallForward(double dist)
    {
      this.CanonnBall.setCenterY(this.CanonnBall.getCenterY()-dist);
    }
    public void CannonBallBackWard(double dist)
    {
        this.CanonnBall.setCenterY(this.CanonnBall.getCenterY()+dist);
    }
    public void CannonBallLeft(double dist)
    {
        this.CanonnBall.setCenterX(this.CanonnBall.getCenterX()-dist);
    }
    public void CannonBallRight(double dist)
    {
        this.CanonnBall.setCenterX(this.CanonnBall.getCenterX()+dist);
    }
    @Override
    public void Hit()
    {
        if(super.isLocked())
        {
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
                //*************************
                if(Math.abs(x_Vector)>Math.abs(y_Vector))
                {
                    if(y_Vector>0)
                    {
                        DownPic();
                    }
                    else
                    {
                        UpPic();
                    }
                }
                else
                {
                    if(x_Vector>0)
                    {
                        RightPic();
                    }
                    else
                    {
                        LeftPic();
                    }
                }
                //------------------------
                double xMoveVector = x_Vector/distPart;
                double yMoveVector = y_Vector/distPart;
                //------------------------
                if(xMoveVector>0)
                {
                    this.CannonBallRight(xMoveVector);
                }
                else
                {
                    this.CannonBallLeft((-1)*xMoveVector);
                }
                //------------------------------
                if(yMoveVector>0)
                {
                    this.CannonBallBackWard(yMoveVector);
                }
                else
                {
                    this.CannonBallForward((-1)*yMoveVector);
                }
                if(ShootingTimeTick== (super.getHitSpeed() *10))
                {
                    ShootingTimeTick=0;
                }
            }
            else
            {
                ShootingTimeTick=0;
                super.setLockedTarget(null);
            }
        }
        else
        {
            this.CanonnBall.setCenterX(super.getX_Current());
            this.CanonnBall.setCenterY(super.getY_Current());
            super.setLockedTarget(null);
            ShootingTimeTick=0;
        }
    }
    public Circle getCanonnBall() {
        return CanonnBall;
    }
    public void setShootingTimeTick(int shootingTimeTick) {
        ShootingTimeTick = shootingTimeTick;
    }
    public int getShootingTimeTick() {
        return ShootingTimeTick;
    }
}
