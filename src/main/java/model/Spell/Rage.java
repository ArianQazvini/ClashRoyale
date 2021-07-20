package model.Spell;

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

public class Rage extends Spell{
    float damageBoost;
    float speedBoost;
    float hitSpeedBoost;
    private double time ;
    private boolean isDone = false;
    private boolean isUsed =false;
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
    }
    private ArrayList<ImageView> groundImages = new ArrayList<>();

    public void setGroundImages(ArrayList<ImageView> groundImages) {
        this.groundImages = groundImages;
    }
    public ArrayList<ImageView> getGroundImages() {
        return groundImages;
    }
    public void rageThread()
    {
        time = getLevelInformation().getValue();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isUsed=true;
                rageTask();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    private void rageTask()
    {
        Platform.runLater(new Runnable() {
          //  Image purpleBlock = new Image(new File("src/main/resources/pics/purpleTile.png").toURI().toString());
            @Override
            public void run() {
                for (int i = 0; i < getTowers().size(); i++) {
                    getTowers().get(i).setRaged(true);
                   // getTowers().get(i).rageImpact();
                }
                for (int i = 0; i < getAttackCards().size(); i++) {
                    getAttackCards().get(i).setRaged(true);
                  //  getAttackCards().get(i).rageImpact();
                }
            }
        });
        while (time >=0)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time-= 0.5;
            for (int i = 0; i < getAttackCards().size(); i++) {
                if(distance(getAttackCards().get(i).getX_Current(),getAttackCards().get(i).getY_Current(),this.getX(),this.getY())> getRadius()*20)
                {
//                    getAttackCards().get(i).undoRage();
                    getAttackCards().get(i).setRaged(false);
//                    removeAttckCard(getAttackCards().get(i));
                }
            }
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                isDone=true;
            }
        });
    }
    public boolean isDone() {
        return isDone;
    }
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
    public void setUsed(boolean used) {
        isUsed = used;
    }
    public boolean isUsed() {
        return isUsed;
    }
}
