package model;

import enums.CardId;
import javafx.scene.shape.Rectangle;
import model.informations.LevelInformation;
import model.informations.LevelValue;


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

    public CardId getId() {
        return id;
    }

    public void setId(CardId id) {
        this.id = id;
    }

    public LevelValue getLevel1() {
        return level1;
    }

    public LevelValue getLevel2() {
        return level2;
    }

    public LevelValue getLevel3() {
        return level3;
    }

    public LevelValue getLevel4() {
        return level4;
    }

    public LevelValue getLevel5() {
        return level5;
    }

    public void setLevel5(LevelValue level5) {
        this.level5 = level5;
    }

    public void setLevel1(LevelValue level1) {
        this.level1 = level1;
    }

    public void setLevel2(LevelValue level2) {
        this.level2 = level2;
    }

    public void setLevel3(LevelValue level3) {
        this.level3 = level3;
    }

    public void setLevel4(LevelValue level4) {
        this.level4 = level4;
    }

    public void setLevelInformation(LevelInformation levelInformation) {
        this.levelInformation = levelInformation;
    }

    public LevelInformation getLevelInformation() {
        return levelInformation;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAnimation() {
        return animation;
    }

    public void setAnimation(String animation) {
        this.animation = animation;
    }

}
