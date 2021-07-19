package model;

import javafx.application.Platform;
import model.Building.Building;

public class Timer {
    private int min=0;
    private int secs = 0;
    private boolean gameTimesUp  = false;
    private boolean doubleElxirTime  = false;
   public void buildingThread(Building building)
   {
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               buildingTask(building);
           }
       });
       thread.setDaemon(true);
       thread.start();
   }
   private void buildingTask(Building building)
   {
       try {
           Thread.sleep((long) building.getLifeTime() * 1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       Platform.runLater(new Runnable() {
           @Override
           public void run() {
               building.setLifeTime(0);
           }
       });
   }
   public void gameTimer()
   {
       Thread thread = new Thread(new Runnable() {
           @Override
           public void run() {
               while (!isGameTimesUp())
               {
                   timeStuff();
               }
           }
       });
       thread.setDaemon(true);
       thread.start();
   }
   public void timeStuff()
   {
       try {
           Thread.sleep(1000);
           if(secs ==60) {
               min++;
               if(min==3)
               {
                   gameTimesUp=true;
               }
               else if(min==2)
               {
                   doubleElxirTime=true;
               }
               secs=0;
           }
           else
           {
               secs++;
           }
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
    public int getMin() {
        return min;
    }
    public int getSecs() {
        return secs;
    }
    public boolean isGameTimesUp() {
        return gameTimesUp;
    }
    public boolean isDoubleElxirTime() {
        return doubleElxirTime;
    }
}
