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

/**
 * The type Player.
 */
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
    /**
     * The Anonymous.
     */
    boolean anonymous=false;

    /**
     * Instantiates a new Player.
     */
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

    /**
     * Set card level.
     */
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

    /**
     * Gets level.
     *
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Gets battle histories.
     *
     * @return the battle histories
     */
    public ArrayList<BattleHistory> getBattleHistories() {
        return battleHistories;
    }

    /**
     * Gets elixir.
     *
     * @return the elixir
     */
    public Elixir getElixir() {
        return elixir;
    }

    /**
     * Crown property integer property.
     *
     * @return the integer property
     */
    public IntegerProperty crownProperty() {
        return crownProperty;
    }

    /**
     * Sets elixir.
     *
     * @param value the value
     */
    public void setElixir(double value) {
        elixir.setElixir(value);
    }

    /**
     * Creat elixir.
     */
    public void creatElixir(){
        this.elixir=new Elixir();
    }

    /**
     * Gets attack cards on ground.
     *
     * @return the attack cards on ground
     */
    public ArrayList<AttackCard> getAttackCardsOnGround() {
        return attackCardsOnGround;
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Sets anonymous.
     *
     * @param anonymous the anonymous
     */
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    /**
     * Gets deck.
     *
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Is anonymous boolean.
     *
     * @return the boolean
     */
    public boolean isAnonymous() {
        return anonymous;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets troops.
     *
     * @return the troops
     */
    public ArrayList<Troop> getTroops() {
        return troops;
    }

    /**
     * Gets king tower.
     *
     * @return the king tower
     */
    public KingTower getKingTower() {
        return kingTower;
    }

    /**
     * Gets prince tower 1.
     *
     * @return the prince tower 1
     */
    public PrinceTower getPrinceTower1() {
        return princeTower1;
    }

    /**
     * Gets prince tower 2.
     *
     * @return the prince tower 2
     */
    public PrinceTower getPrinceTower2() {
        return princeTower2;
    }

    /**
     * Up pics.
     */
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

    /**
     * Down pics.
     */
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

    /**
     * Gets scores.
     *
     * @return the scores
     */
    public int getScores() {
        return scores;
    }

    /**
     * Sets scores.
     *
     * @param scores the scores
     */
    public void setScores(int scores) {
        this.scores = scores;
    }

    /**
     * Win.
     */
    public void win()
    {
        this.scores+=200;
    }

    /**
     * Lose.
     */
    public void lose()
    {
        this.scores +=70;
    }

    /**
     * Gets crowns won.
     *
     * @return the crowns won
     */
    public int getCrownsWon() {
        return crownsWon;
    }

    /**
     * Increment crowns won.
     */
    public void incrementCrownsWon()
    {
        crownsWon++;
    }

    /**
     * Sets crowns won.
     *
     * @param crownsWon the crowns won
     */
    public void setCrownsWon(int crownsWon) {
        this.crownsWon = crownsWon;
    }
}
