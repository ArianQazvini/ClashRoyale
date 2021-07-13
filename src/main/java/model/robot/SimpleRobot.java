package model.robot;

import model.Card;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

public class SimpleRobot extends Robot{
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
        x=random.nextInt(360);
        y=random.nextInt(300);
//        while (x<70)
//            x=random.nextInt(430);
//        while (y<52)
//            y=random.nextInt(330);

    }

}
