package model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Building.Building;

public class TimeWorks {
    private IntegerProperty min= new SimpleIntegerProperty(0);
    private IntegerProperty secs = new SimpleIntegerProperty(0);
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
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       Platform.runLater(new Runnable() {
           @Override
           public void run() {
               if(secs.get() ==60) {
                   int temp = min.get();
                   temp++;
                   min.setValue(temp);
                   if(min.get()==3)
                   {
                       gameTimesUp=true;
                   }
                   else if(min.get()==2)
                   {
                       doubleElxirTime=true;
                   }
                   secs.set(0);
               }
               else
               {
                   int temp = secs.get();
                   temp++;
                   secs.set(temp);
               }
           }
       });
   }

    public IntegerProperty minProperty() {
        return min;
    }
    public IntegerProperty secsProperty() {
        return secs;
    }
    public boolean isGameTimesUp() {
        return gameTimesUp;
    }
    public boolean isDoubleElxirTime() {
        return doubleElxirTime;
    }

    public void setDoubleElxirTime(boolean doubleElxirTime) {
        this.doubleElxirTime = doubleElxirTime;
    }
    public void setMin(int min) {
        this.min.set(min);
    }
    public void setSecs(int secs) {
        this.secs.set(secs);
    }

    public void setGameTimesUp(boolean gameTimesUp) {
        this.gameTimesUp = gameTimesUp;
    }
}
