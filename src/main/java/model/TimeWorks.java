package model;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Building.Building;

/**
 * The type Time works.
 */
public class TimeWorks {
    private IntegerProperty min= new SimpleIntegerProperty(0);
    private IntegerProperty secs = new SimpleIntegerProperty(0);
    private boolean gameTimesUp  = false;
    private boolean doubleElxirTime  = false;

    /**
     * Building thread.
     *
     * @param building the building
     */
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

    /**
     * Game timer.
     */
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

    /**
     * Time stuff.
     */
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
               if(secs.get() ==59) {
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

    /**
     * Min property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty minProperty() {
        return min;
    }

    /**
     * Secs property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty secsProperty() {
        return secs;
    }

    /**
     * Is game times up boolean.
     *
     * @return the boolean
     */
    public boolean isGameTimesUp() {
        return gameTimesUp;
    }

    /**
     * Is double elxir time boolean.
     *
     * @return the boolean
     */
    public boolean isDoubleElxirTime() {
        return doubleElxirTime;
    }

    /**
     * Sets double elxir time.
     *
     * @param doubleElxirTime the double elxir time
     */
    public void setDoubleElxirTime(boolean doubleElxirTime) {
        this.doubleElxirTime = doubleElxirTime;
    }

    /**
     * Sets min.
     *
     * @param min the min
     */
    public void setMin(int min) {
        this.min.set(min);
    }

    /**
     * Sets secs.
     *
     * @param secs the secs
     */
    public void setSecs(int secs) {
        this.secs.set(secs);
    }

    /**
     * Sets game times up.
     *
     * @param gameTimesUp the game times up
     */
    public void setGameTimesUp(boolean gameTimesUp) {
        this.gameTimesUp = gameTimesUp;
    }
}
