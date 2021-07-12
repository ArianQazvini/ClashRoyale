package model;


import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Rectangle;

public class Elixir extends ProgressBar {
   double value;
   double maxValue;
    public Elixir(){
        maxValue=10;
        value=maxValue;
        setHeight(150);
        setWidth(30);
    }

    public void setValue(double value) {
        this.value = value;
        setProgress(value);
    }

    public double getValue() {
        return value;
    }
}
