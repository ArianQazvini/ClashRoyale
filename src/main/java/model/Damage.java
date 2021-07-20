package model;

public class Damage<T>{
    T value;
    public Damage(T value){
        this.value=value;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }
}
