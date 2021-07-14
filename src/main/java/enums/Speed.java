package enums;

public enum Speed {
    SLOW(1)
    ,MEDIUM(2)
    ,FAST(3);
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
