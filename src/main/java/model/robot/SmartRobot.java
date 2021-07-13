package model.robot;

import model.Card;

import java.util.Random;

public class SmartRobot extends Robot{
    Random random=new Random();
    @Override
    public Card chooseFromDeck() {

        int index=random.nextInt(8);
        Card card=getDeck().getCards().get(index);
        while (card.getCost()> getElixir().getValue()){
            index=random.nextInt(8);
            card=getDeck().getCards().get(index);
        }
        return card;

    }

    @Override
    public void chooseLocation() {
        int a=random.nextInt(3);
        int mood=random.nextInt(2);
        if (mood==1) {
            if (a == 0)
                x = gameManager.getPlayer().getPrinceTower1().getX();
            else if (a == 1)
                x = gameManager.getPlayer().getKingTower().getX();
            else
                x = gameManager.getPlayer().getPrinceTower2().getX();
//        x=random.nextInt(360);
            y = random.nextInt(300);
        }

    }

}
