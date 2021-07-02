package model;


import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Rectangle;

public class Elixir {
    Rectangle tank;
    DoubleProperty percentage;
    DoubleBinding b;
    public Elixir(Rectangle tank){
        this.tank=tank;
        percentage=new SimpleDoubleProperty(10);
        b=tank.widthProperty().multiply(percentage);
        tank.widthProperty().bind(b);
    }
}
