package enums;

public enum Speed {
    SLOW(1)
    ,MEDIUM(2)
    ,FAST(2.5);
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
