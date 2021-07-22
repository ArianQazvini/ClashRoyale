package enums;

public enum Speed {
    SLOW(1)
    ,MEDIUM(1.5)
    ,FAST(2);
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
