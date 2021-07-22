package model;

import enums.CardId;
import javafx.scene.shape.Rectangle;
import model.informations.LevelInformation;
import model.informations.LevelValue;


/**
 * The type War object.
 */
public class WarObject {
    private CardId id;
    private String avatar;
    private String animation;
    private LevelInformation levelInformation;
    private LevelValue level1;
    private LevelValue level2;
    private LevelValue level3;
    private LevelValue level4;
    private LevelValue level5;

    /**
     * Upgrade.
     */
    public void upgrade(){
        switch (levelInformation.level){
            case LEVEL1:
                levelInformation=level2;
                break;
            case LEVEL2:
                levelInformation=level3;
                break;
            case LEVEL3:
                levelInformation=level4;
                break;
            case LEVEL4:
                levelInformation=level5;
                break;
            default:
                return;
        }
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public CardId getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(CardId id) {
        this.id = id;
    }

    /**
     * Gets level 1.
     *
     * @return the level 1
     */
    public LevelValue getLevel1() {
        return level1;
    }

    /**
     * Gets level 2.
     *
     * @return the level 2
     */
    public LevelValue getLevel2() {
        return level2;
    }

    /**
     * Gets level 3.
     *
     * @return the level 3
     */
    public LevelValue getLevel3() {
        return level3;
    }

    /**
     * Gets level 4.
     *
     * @return the level 4
     */
    public LevelValue getLevel4() {
        return level4;
    }

    /**
     * Gets level 5.
     *
     * @return the level 5
     */
    public LevelValue getLevel5() {
        return level5;
    }

    /**
     * Sets level 5.
     *
     * @param level5 the level 5
     */
    public void setLevel5(LevelValue level5) {
        this.level5 = level5;
    }

    /**
     * Sets level 1.
     *
     * @param level1 the level 1
     */
    public void setLevel1(LevelValue level1) {
        this.level1 = level1;
    }

    /**
     * Sets level 2.
     *
     * @param level2 the level 2
     */
    public void setLevel2(LevelValue level2) {
        this.level2 = level2;
    }

    /**
     * Sets level 3.
     *
     * @param level3 the level 3
     */
    public void setLevel3(LevelValue level3) {
        this.level3 = level3;
    }

    /**
     * Sets level 4.
     *
     * @param level4 the level 4
     */
    public void setLevel4(LevelValue level4) {
        this.level4 = level4;
    }

    /**
     * Sets level information.
     *
     * @param levelInformation the level information
     */
    public void setLevelInformation(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    /**
     * Gets level information.
     *
     * @return the level information
     */
    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    /**
     * Gets avatar.
     *
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets avatar.
     *
     * @param avatar the avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Gets animation.
     *
     * @return the animation
     */
    public String getAnimation() {
        return animation;
    }

    /**
     * Sets animation.
     *
     * @param animation the animation
     */
    public void setAnimation(String animation) {
        this.animation = animation;
    }

}
