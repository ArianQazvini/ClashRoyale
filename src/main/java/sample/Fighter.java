package sample;

import enums.Speed;
import javafx.scene.shape.Rectangle;

public class Fighter extends Rectangle {
    private String name;
    private Speed speed;
    private String avatar;
    private String Walking;
    public Fighter(String name, Speed speed,String Avatar,String Walking) {
        this.name = name;
        this.speed = speed;
        this.avatar=Avatar;
        this.Walking=Walking;
    }

    public void setWalking(String walking) {
        Walking = walking;
    }

    public String getWalking() {
        return Walking;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public Speed getSpeed() {
        return speed;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
    @Override
    public String toString()
    {
        return this.avatar;
    }
}
