package model.Tower;

import enums.Level;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import model.Damage;
import model.informations.ACLevelValue;

import java.io.File;

/**
 * The type King tower.
 */
public class KingTower extends Tower{
    private boolean canShoot = false;

    /**
     * Instantiates a new King tower.
     */
    public KingTower(){
        setHitSpeed(1);
        setRange(7);
        setLevel1(new ACLevelValue(2400.0,new Damage(50.0), Level.LEVEL1));
        setLevel2(new ACLevelValue(2568.0,new Damage(53.0),Level.LEVEL2));
        setLevel3(new ACLevelValue(2736.0,new Damage(57.0),Level.LEVEL3));
        setLevel4(new ACLevelValue(2904.0,new Damage(60.0),Level.LEVEL4));
        setLevel5(new ACLevelValue(3096.0,new Damage(64.0),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
    }

    /**
     * Sets can shoot.
     *
     * @param canShoot the can shoot
     */
    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    /**
     * Is can shoot boolean.
     *
     * @return the boolean
     */
    public boolean isCanShoot() {
        return canShoot;
    }

    @Override
    public void Hit() {
        if(super.isLocked() && this.canShoot)
        {
            if(super.targetDistance()<= this.getRange()*20)
            {
                incrementTimeTick();
                setCannonBallPic();
                if(getShootingTimeTick()== (super.getHitSpeed()*10))
                {
                    super.getLockedTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                    explosionPic();
                }
                double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                double x_Vector =super.getLockedTarget().getX_Current()-super.getX();
                double y_Vector =super.getLockedTarget().getY_Current()-super.getY();
                double xMoveVector = x_Vector*distPart;
                double yMoveVector = y_Vector*distPart;
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
                if(getShootingTimeTick()== (super.getHitSpeed() *10))
                {
                    setShootingTimeTick(0);
                }
            }
            else
            {
                resetTimeTick();
            }
        }
        else
        {
            resetTimeTick();
            super.setLockedTarget(null);
        }
    }

    @Override
    public void undoRage() {
        this.setHitSpeed(1F);
        if(this.getLevelInformation().level==Level.LEVEL1)
        {
            this.getLevelInformation().getDamage().setValue((Double)50.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL2)
        {
            this.getLevelInformation().getDamage().setValue((Double)53.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL3)
        {
            this.getLevelInformation().getDamage().setValue((Double)57.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL4)
        {
            this.getLevelInformation().getDamage().setValue((Double)60.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL5)
        {
            this.getLevelInformation().getDamage().setValue((Double)64.0);
        }
    }
}
