package model;


import javafx.application.Platform;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Rectangle;

import java.util.Timer;
import java.util.TimerTask;

public class Elixir extends ProgressBar {
   double value;
   double maxValue;
    public Elixir(){
        maxValue=10;
        value=maxValue;
        setHeight(150);
        setWidth(30);
        setProgress(maxValue/maxValue);
        Thread increase=new Thread(new Runnable() {

            @Override
            public void run() {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                value+=2;
                                if (value>10)
                                    value=10;
                                setValue(value);
                            }
                        });
                    }
                };
                timer.schedule(timerTask,200);
            }
        });
        increase.start();
    }

    public void setValue(double value) {
        if (value>=0) {
            this.value = value;
            setProgress(value / maxValue);
        }
    }

    public double getValue() {
        return value;
    }
}
