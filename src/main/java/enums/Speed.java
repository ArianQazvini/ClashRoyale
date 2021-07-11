package enums;

public enum Speed {
    SLOW(5)
    ,MEDIUM(10)
    ,FAST(15);
    public int velocity;
    Speed(int velocity) {
        this.velocity = velocity;
    }
    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }
    public int getVelocity() {
        return velocity;
    }
}
