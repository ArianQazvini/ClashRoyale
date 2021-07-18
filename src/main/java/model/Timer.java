package model;

import javafx.application.Platform;
import model.Building.Building;

public class Timer {
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
}
