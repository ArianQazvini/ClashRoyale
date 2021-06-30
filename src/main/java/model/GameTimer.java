package model;

import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer implements Runnable{
    TextField secondTimerText;
    TextField minuteTimerText;
    Timer myTimer = new Timer();
    int maxTime;
    int time;
    int sec,min;
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
