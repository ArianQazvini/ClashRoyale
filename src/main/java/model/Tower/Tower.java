package model.Tower;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.AttackCard;
import model.Damage;
import model.Spell.Rage;
import model.WarObject;
import model.informations.ACLevelValue;
import model.informations.LevelInformation;
import model.informations.LevelValue;

import java.io.File;

/**
 * The type Tower.
 * @author ArianQazvini - NegarAnabestani
 */
public abstract class Tower extends WarObject {
    private float hp;
    private float hitSpeed;
    private float range;
    private ImageView[][] imageViews=new ImageView[3][3];
    private double x;
    private double y;
    private Circle CanonnBall = new Circle();
    private AttackCard LockedTarget=null;
    private boolean isLocked=false;
    private Damage damage;
    private int ShootingTimeTick=0;
    private String type ;
    private boolean gotHurt = false;
    private boolean isRaged =false;
    private Rage rage=null;
    private boolean isDead = false;

    /**
     * Gets hit speed.
     *
     * @return the hit speed
     */
    public float getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Sets dead.
     *
     * @param dead the dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Sets raged.
     *
     * @param raged the raged
     */
    public void setRaged(boolean raged) {
        isRaged = raged;
    }

    /**
     * Is raged boolean.
     *
     * @return the boolean
     */
    public boolean isRaged() {
        return isRaged;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets locked target.
     *
     * @return the locked target
     */
    public AttackCard getLockedTarget() {
        return LockedTarget;
    }

    /**
     * Sets locked target.
     *
     * @param lockedTarget the locked target
     */
    public void setLockedTarget(AttackCard lockedTarget) {
        LockedTarget = lockedTarget;
    }

    /**
     * Sets hit speed.
     *
     * @param hitSpeed the hit speed
     */
    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    /**
     * Gets range.
     *
     * @return the range
     */
    public float getRange() {
        return range;
    }

    /**
     * Sets range.
     *
     * @param range the range
     */
    public void setRange(float range) {
        this.range = range;
    }

    /**
     * Get image views image view [ ] [ ].
     *
     * @return the image view [ ] [ ]
     */
    public ImageView[][] getImageViews() {
        return imageViews;
    }

    /**
     * Hurt.
     *
     * @param damage the damage
     */
    public void Hurt(double damage)
    {
        gotHurt=true;
        double temp = this.getLevelInformation().getHp();
        this.getLevelInformation().setHp(temp-damage);
    }

    /**
     * Sets got hurt.
     *
     * @param gotHurt the got hurt
     */
    public void setGotHurt(boolean gotHurt) {
        this.gotHurt = gotHurt;
    }

    /**
     * Is got hurt boolean.
     *
     * @return the boolean
     */
    public boolean isGotHurt() {
        return gotHurt;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
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
     * Sets canonn ball.
     *
     * @param canonnBall the canonn ball
     */
    public void setCanonnBall(Circle canonnBall) {
        CanonnBall = canonnBall;
    }

    /**
     * Gets hp.
     *
     * @return the hp
     */
    public float getHp() {
        return hp;
    }

    /**
     * Sets hp.
     *
     * @param hp the hp
     */
    public void setHp(float hp) {
        this.hp = hp;
    }

    /**
     * Is locked boolean.
     *
     * @return the boolean
     */
    public boolean isLocked() {
        if(LockedTarget==null)
        {
            setLocked(false);
            return false;
        }
        else
        {
            setLocked(true);
            return true;
        }
    }

    /**
     * Sets locked.
     *
     * @param locked the locked
     */
    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    /**
     * Hit.
     */
    public abstract void Hit();

    /**
     * Target distance double.
     *
     * @return the double
     */
    public double targetDistance()
    {
        double tempx = this.getLockedTarget().getX_Current() - this.getX();
        double tempy = this.getLockedTarget().getY_Current() - this.getY();
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.pow(sum,0.5);
    }

    /**
     * Gets damage.
     *
     * @return the damage
     */
    public Damage getDamage() {
        return damage;
    }

    /**
     * Sets damage.
     *
     * @param damage the damage
     */
    public void setDamage(Damage damage) {
        this.damage = damage;
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
    @Override
    public ACLevelValue getLevelInformation() {
        return ((ACLevelValue)super.getLevelInformation());
    }

    /**
     * Sets cannon ball pic.
     */
    public void setCannonBallPic()
    {
            Image image = new Image(new File("src/main/resources/pics/Characters/CannonBall.png").toURI().toString());
            ImagePattern imagePattern = new ImagePattern(image);
            this.CanonnBall.setFill(imagePattern);
            this.CanonnBall.setRadius(5);
            this.CanonnBall.setCenterX(this.getX());
            this.CanonnBall.setCenterY(this.getY());
    }

    /**
     * Sets shooting time tick.
     *
     * @param shootingTimeTick the shooting time tick
     */
    public void setShootingTimeTick(int shootingTimeTick) {
        ShootingTimeTick = shootingTimeTick;
    }

    /**
     * Gets shooting time tick.
     *
     * @return the shooting time tick
     */
    public int getShootingTimeTick() {
        return ShootingTimeTick;
    }

    /**
     * Increment time tick.
     */
    public void incrementTimeTick()
    {
        ShootingTimeTick++;
    }

    /**
     * Reset time tick.
     */
    public void resetTimeTick()
    {
        this.CanonnBall.setCenterX(this.getX());
        this.CanonnBall.setCenterY(this.getY());
        setShootingTimeTick(0);
    }

    /**
     * Explosion pic.
     */
    public void explosionPic()
    {
        Image image = new Image(new File("src/main/resources/pics/Characters/Explosion.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        this.CanonnBall.setFill(imagePattern);
    }

    /**
     * Rage impact.
     */
    public void rageImpact()
    {
        this.setHitSpeed(this.getHitSpeed() - 0.2F);
        this.getLevelInformation().getDamage().setValue((Double)this.getLevelInformation().getDamage().getValue()*1.5);
    }

    /**
     * Undo rage.
     */
    public abstract void undoRage();

    /**
     * Sets rage.
     *
     * @param rage the rage
     */
    public void setRage(Rage rage) {
        this.rage = rage;
    }

    /**
     * Gets rage.
     *
     * @return the rage
     */
    public Rage getRage() {
        return rage;
    }
}
