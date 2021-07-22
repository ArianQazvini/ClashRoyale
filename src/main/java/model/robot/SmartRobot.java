package model.robot;
import model.Card;
import model.Troop.Troop;

import java.util.Random;

public class SmartRobot extends Robot{
    Random random=new Random();
    int[][] coordinate=new int[361][1];
    public SmartRobot(){
        setName("smart robot");
        putSpeed=7000;
    }
    @Override
    public Card chooseFromDeck() {
        int index=random.nextInt(8);
        Card card=getDeck().getCards().get(index);
        while (card.getCost()> getElixir().getValue()){
            index=random.nextInt(8);
            card=getDeck().getCards().get(index);
        }
        chooseLocation();
        while (!gameManager.isValidLocation("robot",x,y))
            chooseLocation();
        return card;

    }

    @Override
    public void chooseLocation() {
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
    private void isAppropriate(){

    }
//    private boolean checkBabyDragonNumber(){
//        int num=0;
//        for (AttackCard at:gameManager.getPlayer().getAttackCardsOnGround()){
//            if (at instanceof BabyDragon){
//                num++;
//            }
//        }
//        if (num>)
//    }


}
