package model.Tower;

import enums.Level;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

public class KingTower extends Tower{
    private boolean canShoot = false;
    private int ShootingTimeTick=0;
    public KingTower(){
        setHitSpeed(1);
        setRange(7);
        setLevel1(new ACLevelValue(2400,new Damage(50), Level.LEVEL1));
        setLevel2(new ACLevelValue(2568,new Damage(53),Level.LEVEL2));
        setLevel3(new ACLevelValue(2736,new Damage(57),Level.LEVEL3));
        setLevel4(new ACLevelValue(2904,new Damage(60),Level.LEVEL4));
        setLevel5(new ACLevelValue(3096,new Damage(64),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
        Image image = new Image(new File("src/main/java/resources/pics/Characters/CannonBall.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getCanonnBall().setFill(imagePattern);
        super.getCanonnBall().setRadius(10);
        super.getCanonnBall().setCenterX(super.getX());
        super.getCanonnBall().setCenterY(super.getY());
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
    public boolean isCanShoot() {
        return canShoot;
    }

    @Override
    public void Hit() {
        if(super.isLocked() && this.canShoot)
        {
            if(super.targetDistance()<= this.getRange()*20)
            {
                ShootingTimeTick++;
                if(ShootingTimeTick== (super.getHitSpeed()*10))
                {
                    super.getLockedTarget().Hurt((Double) super.getDamage().getValue());
                }
                double distPart= ShootingTimeTick/(super.getHitSpeed()*10);
                double x_Vector =super.getLockedTarget().getX_Current()-super.getX();
                double y_Vector =super.getLockedTarget().getY_Current()-super.getY();
                double xMoveVector = x_Vector/distPart;
                double yMoveVector = y_Vector/distPart;
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
        }
        else
        {
            ShootingTimeTick=0;
            super.setLockedTarget(null);
        }
    }
}
