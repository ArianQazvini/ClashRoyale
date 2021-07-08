package sample;

import enums.Speed;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;

public class Fighter {
    private String name;
    private Speed speed;
    private String avatar;
    private String Walking;
    private int x_destination;
    private int y_destination;
    private double x_Current;
    private double y_Current;
    private int seconds;
    private PicHandler picHandler;
    private Timeline timeline;
    //  private GameManager gameManager;
    public Fighter(String name, Speed speed,String Avatar,String Walking) {
        this.name = name;
        this.speed = speed;
        this.avatar = Avatar;
        this.Walking = Walking;
        this.picHandler = new PicHandler(this,"src/main/resources/pics/Wizard.jpg");
    }
    //    @Override
//    public void run()
//    {
//
////        KeyFrame keyFrame = new KeyFrame(Duration.seconds(seconds), event ->
////        {
////            if (y_Current > 0) {
////                Forward();
////            }
////        });
////        timeline=new Timeline(keyFrame);
////        timeline.setCycleCount(Timeline.INDEFINITE);
////        timeline.play();
//        while(y_Current>0)
//        {
//            Directions help = GameManager.CharacterExist(x_Current,y_Current,Directions.TOP);
//            if(help==null)
//            {
//                Forward();
//            }
//            else
//            {
//                Left();
//                try {
//                    Thread.sleep(seconds);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Forward();
//            }
//            try {
//                Thread.sleep(seconds);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
    public void Left()
    {
        x_Current = picHandler.getX()-20;
        picHandler.setX(x_Current);
    }
    public void Right()
    {
        x_Current=picHandler.getX()+20;
        picHandler.setX(x_Current);
    }
    public void Forward()
    {
        y_Current=picHandler.getY()-20;
        picHandler.setY(y_Current);
    }
    public void Backward()
    {
        y_Current=picHandler.getY()+20;
        picHandler.setY(y_Current);
    }
    public void setWalking(String walking) {
        Walking = walking;
    }
    public String getWalking() {
        return Walking;
    }

    public void SetName(String name) {
        this.name = name;
    }

    public void setSpeed(Speed speed) {
        this.speed = speed;
    }

    public String GetName() {
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
    public void setDestination(int x,int y)
    {
        this.x_destination=x;
        this.y_destination=y;
    }
    public void setCurrent(double x,double y)
    {
        this.x_Current=x;
        this.y_Current=y;
        picHandler.setX(x);
        picHandler.setY(y);
    }
    public int getX_destination() {
        return x_destination;
    }
    public int getY_destination() {
        return y_destination;
    }
    public double getX_Current() {
        return x_Current;
    }
    public double getY_Current() {
        return y_Current;
    }
    public PicHandler getPicHandler() {
        return picHandler;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }

}
