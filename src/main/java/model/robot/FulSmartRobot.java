package model.robot;

import enums.CardId;
import model.AttackCard;
import model.Building.Building;
import model.Card;
import model.Troop.BabyDragon;
import model.Troop.Troop;
import java.util.Random;

/**
 * The type Ful smart robot.
 */
public class FulSmartRobot extends Robot{
    /**
     * The Coordinate.
     */
    int[][] coordinate=new int[361][1];

    /**
     * Instantiates a new Ful smart robot.
     */
    public FulSmartRobot(){
        setName("ful smart robot");
        putSpeed=5000;
    }
    @Override
    public void setCardLevel() {
        for (Card c:getCards()){
            c.setLevelInformation(c.getLevel5());
        }
    }
    @Override
    public Card chooseFromDeck() {
        Random random=new Random();
        if (findCardOnDeck(CardId.giant)!=null){
            if (checkBuilding()) {
                buildingChooseLocation();
                while (!gameManager.isValidLocation("robot",x,y))
                    buildingChooseLocation();
            }
                return findCardOnDeck(CardId.giant);
        }else if (findCardOnDeck(CardId.archer)!=null){
            if (checkCardOnGround(CardId.dragon)){
                mainChooseLocation(CardId.dragon);
                while (!gameManager.isValidLocation("robot",x,y))
                    mainChooseLocation(CardId.dragon);
                return findCardOnDeck(CardId.archer);
            }
        }else if (findCardOnDeck(CardId.wizard)!=null){
            if (checkCardOnGround(CardId.dragon)){
                mainChooseLocation(CardId.dragon);
                while (!gameManager.isValidLocation("robot",x,y))
                    mainChooseLocation(CardId.dragon);
                    return findCardOnDeck(CardId.wizard);
            }
        }else {
            int index=random.nextInt(8);
            Card card=getDeck().getCards().get(index);
            while (card.getCost()> getElixir().getValue()){
                index=random.nextInt(8);
                card=getDeck().getCards().get(index);
            }
            mainChooseLocation(CardId.wizard);
            while (!gameManager.isValidLocation("robot",x,y))
                mainChooseLocation(CardId.wizard);
            return card;
        }
        return null;
    }

    private void mainChooseLocation(CardId id){
        Random random=new Random();
        if (id.equals(CardId.dragon)){
            x=findClosestDragon();
            y=random.nextInt(300);
        }else {
            chooseLocation();
        }
    }
    private void buildingChooseLocation(){
        Random random=new Random();
        Building building=null;
        for (Building b:gameManager.getBuildings()){
            if (b.getType().equals("+")){
                if (building==null)
                    building=b;
                else if (b.getY_Current()>building.getY_Current()){
                    building=b;
                }

            }
        }
        x=building.getX_Current();
        y=random.nextInt(300);
    }

    @Override
    public void chooseLocation() {
        Random random=new Random();
        int a=random.nextInt(3);
        int moodX=random.nextInt(2);
        int moodY=random.nextInt(2);
        initCoordinate();
        if (moodX==1) {
            if (a == 0)
                x = gameManager.getPlayer().getPrinceTower1().getX();
            else if (a == 1)
                x = gameManager.getPlayer().getKingTower().getX();
            else
                x = gameManager.getPlayer().getPrinceTower2().getX();
            x=random.nextInt(360);
        }else {
            //System.out.println(gameManager);
            if (gameManager.getTroops().size()!=0) {
                x = findCrowdedPlace();
            }
            else {
                x = random.nextInt(360);
            }

        }
        if (moodY==0){
            y=random.nextInt(150);
        }else {
            y=random.nextInt(300);
            while (y<150){
                y=random.nextInt(300);
            }
        }
    }

    private double findClosestDragon(){
        AttackCard biggest=null;
        for (AttackCard at:gameManager.getPlayer().getAttackCardsOnGround()){
            if (at instanceof BabyDragon){
                if (biggest==null)
                    biggest=at;
                else if (at.getY_Current()>biggest.getY_Current())
                    biggest=at;
            }
        }
        return biggest.getX_Current();
        
    }
    private boolean checkBuilding(){
        for (Building b:gameManager.getBuildings()){
            if (b.getType().equals("+"))
                return true;
        }
        return false;
    }
    private Card findCardOnDeck(CardId id){
        for (int i=0;i<4;i++){
            if (getDeck().getCards().get(i).getId()==id)
                return getDeck().getCards().get(i);
        }
        return null;
    }
    private boolean checkCardOnGround(CardId id){
        for (AttackCard at:gameManager.getPlayer().getAttackCardsOnGround()){
            if (at.getType().equals("+"))
                if (at.getId().equals(id))
                    return true;
        }
        return false;
    }
    private void initCoordinate(){
        for (int[] c:coordinate){
            c[0]=0;
        }
    }
    private int findCrowdedPlace(){
        for (int i=0;i<361;i++){
            for (Troop t:gameManager.getTroops()){
                if (t.getType().equals("+")){
                    coordinate[((int) t.getX_Current())][0]++;
                }
            }
        }
        return findBiggestOnCoordinate();

    }
    private int findBiggestOnCoordinate(){
        int biggest=0;
        for (int i=0;i<361;i++){
            if (coordinate[i][0]>coordinate[biggest][0]){
                biggest=i;
            }
        }
        return biggest;
    }

}
