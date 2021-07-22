package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Tower.KingTower;
import model.Tower.PrinceTower;
import model.Troop.*;

import java.util.ArrayList;

public class Player {
    private String level;
    private ArrayList<BattleHistory>battleHistories=new ArrayList<>();
    private ArrayList<Troop> troops = new ArrayList<>();
    private ArrayList<Card>cards=new ArrayList<>();
    private ArrayList<AttackCard>attackCardsOnGround=new ArrayList<>();
    private Deck deck=new Deck();
    private String name;
    private Elixir elixir;
    private String password;
    private KingTower kingTower= new KingTower();
    private PrinceTower princeTower1=new PrinceTower();
    private PrinceTower princeTower2= new PrinceTower();
    private int scores = 300;
    private int crownsWon = 0 ;
    private IntegerProperty crownProperty = new SimpleIntegerProperty(0);
    boolean anonymous=false;

    public Player(){
        cards.add(new Giant());
        cards.add(new Arrows());
        cards.add(new Archer());
        cards.add(new Barbarian());
        cards.add(new BabyDragon());
        cards.add(new MiniPEKKA());
        cards.add(new Valkyrie());
        cards.add(new Wizard());
        cards.add(new Cannon());
        cards.add(new InfernoTower());
        cards.add(new Rage());
        cards.add(new Fireball());
        this.upPics();
    }
    public void setCardLevel(){
        if (level.equals("1")){
            for (Card c:cards){
                c.setLevelInformation(c.getLevel1());
            }
            kingTower.setLevelInformation(kingTower.getLevel1());
            princeTower1.setLevelInformation(princeTower1.getLevel1());
            princeTower2.setLevelInformation(princeTower2.getLevel1());

        }else if (level.equals("2")){
            for (Card c:cards){
                c.setLevelInformation(c.getLevel2());
            }
            kingTower.setLevelInformation(kingTower.getLevel2());
            princeTower1.setLevelInformation(princeTower1.getLevel2());
            princeTower2.setLevelInformation(princeTower2.getLevel2());

        }else if (level.equals("3")){
            for (Card c:cards){
                c.setLevelInformation(c.getLevel3());
            }
            kingTower.setLevelInformation(kingTower.getLevel3());
            princeTower1.setLevelInformation(princeTower1.getLevel3());
            princeTower2.setLevelInformation(princeTower2.getLevel3());

        }else if (level.equals("4")){
            for (Card c:cards){
                c.setLevelInformation(c.getLevel4());
            }
            kingTower.setLevelInformation(kingTower.getLevel4());
            princeTower1.setLevelInformation(princeTower1.getLevel4());
            princeTower2.setLevelInformation(princeTower2.getLevel4());


        }else if (level.equals("5")){
            for (Card c:cards){
                c.setLevelInformation(c.getLevel5());
            }
            kingTower.setLevelInformation(kingTower.getLevel5());
            princeTower1.setLevelInformation(princeTower1.getLevel5());
            princeTower2.setLevelInformation(princeTower2.getLevel5());


        }
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<BattleHistory> getBattleHistories() {
        return battleHistories;
    }

    public Elixir getElixir() {
        return elixir;
    }
    public IntegerProperty crownProperty() {
        return crownProperty;
    }

    public void setElixir(double value) {
        elixir.setElixir(value);
    }
    public void creatElixir(){
        this.elixir=new Elixir();
    }

    public ArrayList<AttackCard> getAttackCardsOnGround() {
        return attackCardsOnGround;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public KingTower getKingTower() {
        return kingTower;
    }

    public PrinceTower getPrinceTower1() {
        return princeTower1;
    }

    public PrinceTower getPrinceTower2() {
        return princeTower2;
    }
    public void upPics()
    {
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i) instanceof Archer)
            {
                Archer temp = (Archer) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof BabyDragon)
            {
                BabyDragon temp = (BabyDragon) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof Barbarian)
            {
                Barbarian temp = (Barbarian) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof Giant)
            {
                Giant temp = (Giant) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof MiniPEKKA)
            {
                MiniPEKKA temp = (MiniPEKKA) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof Valkyrie)
            {
                Valkyrie temp = (Valkyrie) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof Wizard)
            {
                Wizard temp = (Wizard) cards.get(i);
                temp.WalkingTopMode();
            }
            else if(cards.get(i) instanceof Cannon)
            {
                Cannon temp = (Cannon) cards.get(i);
                temp.UpPic();
            }
        }
    }
    public void downPics()
    {
        for (int i = 0; i < cards.size(); i++) {
            if(cards.get(i) instanceof Archer)
            {
                Archer temp = (Archer) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof BabyDragon)
            {
                BabyDragon temp = (BabyDragon) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof Barbarian)
            {
                Barbarian temp = (Barbarian) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof Giant)
            {
                Giant temp = (Giant) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof MiniPEKKA)
            {
                MiniPEKKA temp = (MiniPEKKA) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof Valkyrie)
            {
                Valkyrie temp = (Valkyrie) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof Wizard)
            {
                Wizard temp = (Wizard) cards.get(i);
                temp.WalkingDownMode();
            }
            else if(cards.get(i) instanceof Cannon)
            {
                Cannon temp = (Cannon) cards.get(i);
                temp.DownPic();
            }
        }
    }
    private void positiveType()
    {
        for (int i = 0; i < getDeck().getCards().size(); i++) {
            getDeck().getCards().get(i).setType("+");
        }
    }

    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }
    public void win()
    {
        this.scores+=200;
    }
    public void lose()
    {
        this.scores +=70;
    }

    public int getCrownsWon() {
        return crownsWon;
    }
    public void incrementCrownsWon()
    {
        crownsWon++;
    }
    public void setCrownsWon(int crownsWon) {
        this.crownsWon = crownsWon;
    }
}
