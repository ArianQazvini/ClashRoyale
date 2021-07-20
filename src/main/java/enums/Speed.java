package enums;

public enum Speed {
    SLOW(0.5)
    ,MEDIUM(0.75)
    ,FAST(1.5);
    public double velocity;
    Speed(double velocity) {
        this.velocity = velocity;
    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    public double getVelocity() {
        return velocity;
    }
}
