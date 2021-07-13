package model;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

public class Elixir extends ProgressBar {
   double value;
   double maxValue;
   Text valueText;
    public Elixir(){
        maxValue=10;
        value=maxValue;
        setRotate(270);
//        setHeight(30);
//        setWidth(90);
        setProgress(maxValue/maxValue);
        Thread increase=new Thread(new Runnable() {

            @Override
            public void run() {
                increaseTask();
            }

        });
        increase.start();
    }
    void increaseTask(){
        Platform.runLater(new Runnable() {
            Timeline timeline=null;
            @Override
            public void run() {
                KeyFrame keyFrame=new KeyFrame(Duration.seconds(2),actionEvent->{
                    if (value<10)
                    value+=2;
                    if (value>10)
                        value=10;
                    setElixir(value);
                });
                timeline=new Timeline(keyFrame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        });
    }
    public void setElixir(double value) {
        if (value>=0) {
            this.value = value;
            setProgress(value / maxValue);
            if (valueText!=null)
            valueText.setText(String.valueOf(value));
        }
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setValueText(Text valueText) {
        this.valueText = valueText;
    }

    public double getValue() {
        return value;
    }
}
