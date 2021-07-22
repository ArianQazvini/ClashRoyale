package model.Building;

import enums.CardId;
import enums.Directions;
import enums.Level;
import enums.Target;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.Damage;
import model.informations.ACLevelValue;
import java.io.File;
import java.util.Calendar;

/**
 * The type Cannon.
 * @author ArianQazvini - NegarAnabestani
 * @version 1.0
 */
public class Cannon extends Building{
    private Circle CanonnBall = new Circle();

    /**
     * Instantiates a new Cannon.
     */
    public Cannon(){
        setAvatar("cannon.png");
        setHitSpeed(0.8F);
        setTarget(Target.GROUND);
        setRange(5.5F);
        setLifeTime(30);
        setCost(3);
        setLevel1(new ACLevelValue(380.0,new Damage(60.0), Level.LEVEL1));
        setLevel2(new ACLevelValue(418.0,new Damage(66.0),Level.LEVEL2));
        setLevel3(new ACLevelValue(459.0,new Damage(72.0),Level.LEVEL3));
        setLevel4(new ACLevelValue(505.0,new Damage(79.0),Level.LEVEL4));
        setLevel5(new ACLevelValue(554.0,new Damage(87.0),Level.LEVEL5));
        setLevelInformation(super.getLevel1());
        setId(CardId.cannon);
    }

    /**
     * Up pic.
     */
    public void UpPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/CanonUp.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/CannonBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        CanonnBall.setFill(imagePattern2);
        CanonnBall.setRadius(10);
        CanonnBall.setCenterX(this.getX_Current());
        CanonnBall.setCenterY(this.getY_Current());
    }

    /**
     * Down pic.
     */
    public void DownPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/CanonDown.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/CannonBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        CanonnBall.setFill(imagePattern2);
        CanonnBall.setRadius(10);
        CanonnBall.setCenterX(this.getX_Current());
        CanonnBall.setCenterY(this.getY_Current());
    }

    /**
     * Left pic.
     */
    public void LeftPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/CanonLeft.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/CannonBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        CanonnBall.setFill(imagePattern2);
        CanonnBall.setRadius(10);
        CanonnBall.setCenterX(this.getX_Current());
        CanonnBall.setCenterY(this.getY_Current());
    }

    /**
     * Right pic.
     */
    public void RightPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/CanonRight.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.getPicHandler().setFill(imagePattern);
        super.getPicHandler().setHeight(20);
        super.getPicHandler().setWidth(20);
        super.getPicHandler().setX(super.getX_Current());
        super.getPicHandler().setY(super.getY_Current());
        //------------------------------------------------
        Image image2 = new Image(new File("src/main/resources/pics/Characters/CannonBall.png").toURI().toString());
        ImagePattern imagePattern2 = new ImagePattern(image2);
        CanonnBall.setFill(imagePattern2);
        CanonnBall.setRadius(10);
        CanonnBall.setCenterX(this.getX_Current());
        CanonnBall.setCenterY(this.getY_Current());
    }

    /**
     * Cannon ball forward.
     *
     * @param dist the dist
     */
    public void CannonBallForward(double dist)
    {
      this.CanonnBall.setCenterY(this.CanonnBall.getCenterY()-dist);
    }

    /**
     * Cannon ball back ward.
     *
     * @param dist the dist
     */
    public void CannonBallBackWard(double dist)
    {
        this.CanonnBall.setCenterY(this.CanonnBall.getCenterY()+dist);
    }

    /**
     * Cannon ball left.
     *
     * @param dist the dist
     */
    public void CannonBallLeft(double dist)
    {
        this.CanonnBall.setCenterX(this.CanonnBall.getCenterX()-dist);
    }

    /**
     * Cannon ball right.
     *
     * @param dist the dist
     */
    public void CannonBallRight(double dist)
    {
        this.CanonnBall.setCenterX(this.CanonnBall.getCenterX()+dist);
    }

    /**
     * Hit method
     */
    @Override
    public void Hit()
    {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    incrementTimeTick();
                    changePictoTarget();
                    if(getShootingTimeTick()== (super.getHitSpeed() *10))
                    {
                        super.getLockedTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                        explosionPic();
                    }
                    double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                    double x_Vector =super.getLockedTarget().getX_Current()-this.getPicHandler().getX();
                    double y_Vector =super.getLockedTarget().getY_Current()-this.getPicHandler().getY();
                    //*************************
                    //------------------------
                    double xMoveVector = x_Vector*distPart;
                    double yMoveVector = y_Vector*distPart;
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
            }
            else
                {
                    this.CanonnBall.setCenterX(super.getX_Current());
                    this.CanonnBall.setCenterY(super.getY_Current());
                    super.setLockedTarget(null);
                    super.setTowerTarget(null);
                    setShootingTimeTick(0);
                }
            }
            else if(super.getTowerTarget()!=null)
            {
                if(super.towerDistance()<= this.getRange() * 20)
                {
                        changePictoTarget();
                        incrementTimeTick();
                        if(getShootingTimeTick()== (super.getHitSpeed() *10))
                        {
                            super.getTowerTarget().Hurt((Double) super.getLevelInformation().getDamage().getValue());
                        }
                        double distPart= getShootingTimeTick()/(super.getHitSpeed()*10);
                        double x_Vector =super.getTowerTarget().getX()-this.getPicHandler().getX();
                        double y_Vector =super.getTowerTarget().getY()-this.getPicHandler().getY();
                        //*************************
                        //------------------------
                        double xMoveVector = x_Vector*distPart;
                        double yMoveVector = y_Vector*distPart;
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
                }
                else
                {
                        this.CanonnBall.setCenterX(super.getX_Current());
                        this.CanonnBall.setCenterY(super.getY_Current());
                        super.setLockedTarget(null);
                        super.setTowerTarget(null);
                        setShootingTimeTick(0);
                }
            }
        }
        else
        {
            this.CanonnBall.setCenterX(super.getX_Current());
            this.CanonnBall.setCenterY(super.getY_Current());
            super.setLockedTarget(null);
            super.setTowerTarget(null);
            setShootingTimeTick(0);
        }
    }

    /**
     * Gets canonn ball.
     *
     * @return the canonn ball
     */
    public Circle getCanonnBall() {
        return CanonnBall;
    }

    /**
     * set timetick to 0
     */
    @Override
    public void resetTimeTick()
    {
        this.CanonnBall.setCenterX(super.getX_Current());
        this.CanonnBall.setCenterY(super.getY_Current());
        setShootingTimeTick(0);
    }

    /**
     * change cannon's direction to it's target
     */
    private void changePictoTarget()
    {
        if(super.isLocked())
        {
            if(super.getLockedTarget()!=null)
            {
                if(super.targetDistance()<= this.getRange() * 20)
                {
                    if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.TOP)
                    {
                        UpPic();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.DOWN)
                    {
                        DownPic();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.RIGHT)
                    {
                        RightPic();
                    }
                    else if(this.closestDirectionTo(super.getLockedTarget().getX_Current(),super.getLockedTarget().getY_Current())== Directions.LEFT)
                    {
                        LeftPic();
                    }
                }
            }
            else if(super.getTowerTarget()!=null)
            {
                if(super.towerDistance()<= this.getRange() * 20)
                {
                    if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.TOP)
                    {
                        UpPic();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.DOWN)
                    {
                        DownPic();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.RIGHT)
                    {
                        RightPic();
                    }
                    else if(this.closestDirectionTo(super.getTowerTarget().getX(),super.getTowerTarget().getY())== Directions.LEFT)
                    {
                        LeftPic();
                    }
                }
            }
        }
    }

    /**
     * rage effect
     */
    @Override
    public void rageImpact()
    {
        this.setHitSpeed(this.getHitSpeed()-0.5F);
        this.getLevelInformation().getDamage().setValue((Double)this.getLevelInformation().getDamage().getValue()*1.5F);
    }

    /**
     * reset rage effect
     */
    @Override
    public void undoRage()
    {
        this.setHitSpeed(0.8F);
        if(this.getLevelInformation().level==Level.LEVEL1)
        {
            this.getLevelInformation().getDamage().setValue((Double)60.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL2)
        {
            this.getLevelInformation().getDamage().setValue((Double)66.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL3)
        {
            this.getLevelInformation().getDamage().setValue((Double)72.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL4)
        {
            this.getLevelInformation().getDamage().setValue((Double)79.0);
        }
        else if(this.getLevelInformation().level==Level.LEVEL5)
        {
            this.getLevelInformation().getDamage().setValue((Double)87.0);
        }
    }

    /**
     * set explosion pic
     */
    private void explosionPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/Explosion.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        this.CanonnBall.setFill(imagePattern);
    }
}
