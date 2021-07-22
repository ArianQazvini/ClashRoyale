package model;

import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The type Game timer.
 */
public class GameTimer implements Runnable{
    /**
     * The Second timer text.
     */
    TextField secondTimerText;
    /**
     * The Minute timer text.
     */
    TextField minuteTimerText;
    /**
     * The My timer.
     */
    Timer myTimer = new Timer();
    /**
     * The Max time.
     */
    int maxTime;
    /**
     * The Time.
     */
    int time;
    /**
     * The Sec.
     */
    int sec, /**
     * The Min.
     */
    min;

    /**
     * Instantiates a new Game timer.
     *
     * @param secondTimerText the second timer text
     * @param minuteTimerText the minute timer text
     */
    public GameTimer(TextField secondTimerText,
            TextField minuteTimerText){
        maxTime=120;
        this.secondTimerText=secondTimerText;
        this.minuteTimerText=minuteTimerText;
        sec=min=0;
        time=0;
    }
    @Override
    public void run() {
        myTimer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                if (sec>60){
                    sec=0;
                    min++;
                }
                    if (sec<10)
                    secondTimerText.setText("0"+sec);
                    else
                        secondTimerText.setText(String.valueOf(sec));
                if (min<10)
                    minuteTimerText.setText("0"+min);
                else
                    minuteTimerText.setText(String.valueOf(min));

                if (time!=maxTime) {
                    time++;
                    sec++;
                }
            }
        },0, 1000);
    }
}
