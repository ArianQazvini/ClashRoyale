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
import java.lang.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Elixir.
 */
public class Elixir extends ProgressBar {
    /**
     * The Value.
     */
    double value;
    /**
     * The Max value.
     */
    double maxValue;
    /**
     * The Value text.
     */
    Text valueText;
   private boolean gameFinished=false;
   private int sleep = 2000;

    /**
     * Instantiates a new Elixir.
     */
    public Elixir(){
        maxValue=10;
        value=4;
        setRotate(270);
//        setHeight(30);
//        setWidth(90);
        setProgress(4/maxValue);
        Thread increase=new Thread(new Runnable() {
            @Override
            public void run() {
                value=4;
                while (!gameFinished)
                {
                    increaseTask();
                }

            }

        });
        increase.setDaemon(true);
        increase.start();
    }

    /**
     * Increase task.
     */
    void increaseTask(){
        Platform.runLater(new Runnable() {
           // Timeline timeline=null;
            @Override
            public void run() {
                if (value<10)
                    value+=1;
                    if (value>10)
                        value=10;
                    setElixir(value);
            }
        });
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets elixir.
     *
     * @param value the value
     */
    public void setElixir(double value) {
        if (value>=0) {
            this.value = value;
            setProgress(value / maxValue);
            if (valueText!=null)
            valueText.setText(String.valueOf(value));
        }
    }

    /**
     * Sets game finished.
     *
     * @param gameFinished the game finished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Is game finished boolean.
     *
     * @return the boolean
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets value text.
     *
     * @param valueText the value text
     */
    public void setValueText(Text valueText) {
        this.valueText = valueText;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets sleep.
     *
     * @param sleep the sleep
     */
    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    /**
     * Gets sleep.
     *
     * @return the sleep
     */
    public int getSleep() {
        return sleep;
    }
}
