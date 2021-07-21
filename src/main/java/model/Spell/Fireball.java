package model.Spell;

import enums.CardId;
import enums.Level;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.AttackCard;
import model.Tower.Tower;
import model.informations.SpellLevelValue;

import java.io.File;
import java.util.ArrayList;

public class Fireball extends Spell{
    private Circle fireball = new Circle();
    public Fireball(){
        setAvatar("fireball.png");
        setRadius(2.5F);
        setCost(4);
        setLevel1(new SpellLevelValue(Level.LEVEL1,325));
        setLevel2(new SpellLevelValue(Level.LEVEL2,357));
        setLevel3(new SpellLevelValue(Level.LEVEL3,393));
        setLevel4(new SpellLevelValue(Level.LEVEL4,432));
        setLevel5(new SpellLevelValue(Level.LEVEL5,474));
        setLevelInformation(getLevel1());
        setId(CardId.fireball);
    }
    private void setPic()
    {
        Image arrows = new Image(new File("src/main/resources/pics/Characters/explosion2.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(arrows);
        this.fireball.setFill(imagePattern);
        this.fireball.setRadius(4*10);
        this.fireball.setCenterX(this.getX());
        this.fireball.setCenterY(this.getY());

    }
    public void Hit()
    {
        setPic();
        for (int i = 0; i < getAttackCards().size(); i++) {
            getAttackCards().get(i).Hurt(this.getLevelInformation().getValue());
        }
        for (int i = 0; i < getTowers().size(); i++) {
            getTowers().get(i).Hurt(this.getLevelInformation().getValue());
        }
    }

    public Circle getFireball() {
        return fireball;
    }
}
