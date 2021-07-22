package enums;

/**
 * The enum Speed.
 */
public enum Speed {
    /**
     * Slow speed.
     */
    SLOW(1)
    ,
    /**
     * Medium speed.
     */
    MEDIUM(1.5)
    ,
    /**
     * Fast speed.
     */
    FAST(2);
    /**
     * The Velocity.
     */
    public double velocity;
    Speed(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Sets velocity.
     *
     * @param velocity the velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public double getVelocity() {
        return velocity;
    }
}
