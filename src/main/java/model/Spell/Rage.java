package model.Spell;

import enums.CardId;
import enums.Level;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.AttackCard;
import model.Tower.Tower;
import model.informations.SpellLevelValue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * The type Rage.
 */
public class Rage extends Spell{
    /**
     * The Damage boost.
     */
    float damageBoost;
    /**
     * The Speed boost.
     */
    float speedBoost;
    /**
     * The Hit speed boost.
     */
    float hitSpeedBoost;
    private float time ;

    private ArrayList<AttackCard> supplementary = new ArrayList<>();

    /**
     * Instantiates a new Rage.
     */
    public Rage(){
        setAvatar("rage.png");
        damageBoost=1.4F;
        speedBoost=1.4F;
        hitSpeedBoost=1.4F;
        setRadius(5);
        setCost(3);
        setLevel1(new SpellLevelValue(Level.LEVEL1,6));
        setLevel2(new SpellLevelValue(Level.LEVEL2,6.5F));
        setLevel3(new SpellLevelValue(Level.LEVEL3,7F));
        setLevel4(new SpellLevelValue(Level.LEVEL4,7.5F));
        setLevel5(new SpellLevelValue(Level.LEVEL5,8F));
        setLevelInformation(getLevel1());
        setId(CardId.rage);
    }
    private ArrayList<ImageView> groundImages = new ArrayList<>();

    /**
     * Sets ground images.
     *
     * @param groundImages the ground images
     */
    public void setGroundImages(ArrayList<ImageView> groundImages) {
        this.groundImages = groundImages;
    }

    /**
     * Gets ground images.
     *
     * @return the ground images
     */
    public ArrayList<ImageView> getGroundImages() {
        return groundImages;
    }

    /**
     * Rage thread.
     */
    public void rageThread()
    {
        time = getLevelInformation().getValue();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setUsed(true);
                rageTask();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * set rage thread to sleep
     */
    private void rageTask()
    {
//        Platform.runLater(new Runnable() {
//          //  Image purpleBlock = new Image(new File("src/main/resources/pics/purpleTile.png").toURI().toString());
//            @Override
//            public void run() {
//                for (int i = 0; i < getTowers().size(); i++) {
//                    getTowers().get(i).setRaged(true);
//                    getTowers().get(i).rageImpact();
//                }
//                for (int i = 0; i < getAttackCards().size(); i++) {
//                    getAttackCards().get(i).setRaged(true);
//                    getAttackCards().get(i).rageImpact();
//                }
//            }
//        });
        try {
            long delay = (long) (time + 1) *1000;
            System.out.println(delay);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setDone(true);
            }
        });
    }

    /**
     * counting distance between (x1,y1) , (x2,y2)
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return the distance
     */
    private double distance(double x1,double y1,double x2 , double y2)
    {
        double tempx = x1-x2;
        double tempy = y1-y2;
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.sqrt(sum);
    }
    private void removeAttckCard(AttackCard attackCard)
    {
        Iterator<AttackCard> iterator = getAttackCards().iterator();
        while (iterator.hasNext())
        {
            AttackCard help = iterator.next();
            if(help==attackCard)
            {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Gets supplementary.
     *
     * @return the supplementary
     */
    public ArrayList<AttackCard> getSupplementary() {
        return supplementary;
    }
}
