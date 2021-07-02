package model.Tower;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Tower{
    private int hp;
    private int damage;
    private int hitSpeed;
    private int range;
    private ImageView[][] imageViews;

    public Tower(int hp, int damage, int hitSpeed, int range) {
        this.hp = hp;
        this.damage = damage;
        this.hitSpeed = hitSpeed;
        this.range = range;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHitSpeed() {
        return hitSpeed;
    }

    public void setHitSpeed(int hitSpeed) {
        this.hitSpeed = hitSpeed;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public ImageView[][] getImageViews() {
        return imageViews;
    }
}
