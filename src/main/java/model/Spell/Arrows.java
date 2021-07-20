package model.Spell;

import enums.Level;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import model.AttackCard;
import model.Tower.Tower;
import model.informations.SpellLevelValue;

import java.io.File;
import java.util.ArrayList;

public class Arrows extends Spell{
    private Circle Arrows = new Circle();
    public Arrows(){
        setAvatar("arrows.png");
        setRadius(4);
        setCost(3);
        setLevel1(new SpellLevelValue(Level.LEVEL1,144));
        setLevel2(new SpellLevelValue(Level.LEVEL2,156));
        setLevel3(new SpellLevelValue(Level.LEVEL3,174));
        setLevel4(new SpellLevelValue(Level.LEVEL4,189));
        setLevel5(new SpellLevelValue(Level.LEVEL5,210));
        setLevelInformation(getLevel1());
    }
    private void setPic()
    {
        Image arrows = new Image(new File("src/main/resources/pics/Characters/Arrows.png").toURI().toString());
        ImagePattern imagePattern = new ImagePattern(arrows);
        this.Arrows.setFill(imagePattern);
        this.Arrows.setRadius(4*10);
        this.Arrows.setCenterX(this.getX());
        this.Arrows.setCenterY(this.getY());

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

    public Circle getArrows() {
        return Arrows;
    }
}
