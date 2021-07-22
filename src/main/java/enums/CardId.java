package enums;

/**
 * The enum Card id.
 */
public enum CardId {
    /**
     * Cannon card id.
     */
    cannon(1),
    /**
     * Inferno card id.
     */
    inferno(2),
    /**
     * Arrows card id.
     */
    arrows(3),
    /**
     * Fireball card id.
     */
    fireball(4),
    /**
     * Rage card id.
     */
    rage(5),
    /**
     * Archer card id.
     */
    archer(6),
    /**
     * Dragon card id.
     */
    dragon(7),
    /**
     * Giant card id.
     */
    giant(8),
    /**
     * Mini card id.
     */
    mini(9),
    /**
     * Valkyrie card id.
     */
    valkyrie(10),
    /**
     * Wizard card id.
     */
    wizard(11),
    /**
     * Barbarian card id.
     */
    barbarian(12);
    /**
     * The Id.
     */
    public int id;
    CardId(int id){
        this.id=id;
    }

}
