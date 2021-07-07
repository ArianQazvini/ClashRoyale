package model.Tower;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import model.Damage;
import model.WarObject;
import model.informations.LevelInformation;
import model.informations.LevelValue;

public class Tower extends WarObject {
    private float hitSpeed;
    private float range;
    private ImageView[][] imageViews;

//    public Tower(int hp, Damage damage, int hitSpeed, int range) {
//        this.hp = hp;
//        this.damage = damage;
//        this.hitSpeed = hitSpeed;
//        this.range = range;
//    }


    public float getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(float hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public ImageView[][] getImageViews() {
        return imageViews;
    }
}
