package model;

/**
 * The type Damage.
 *
 * @param <T> the type parameter
 */
public class Damage<T>{
    /**
     * The Value.
     */
    T value;

    /**
     * Instantiates a new Damage.
     *
     * @param value the value
     */
    public Damage(T value){
        this.value=value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(T value) {
        this.value = value;
    }
}
