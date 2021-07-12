package model.robot;

import model.Card;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

public class SimpleRobot extends Robot{
    Random random=new Random();
    @Override
    public Card chooseFromDeck() {

        int index=random.nextInt(4);
        Card card=deckOnGame.get(index);
        while (card.getCost()> getElixir().getValue()){
            index=random.nextInt(4);
            card=deckOnGame.get(index);
        }
        return card;

    }

    @Override
    public void chooseLocation() {
        x=random.nextInt(430);
        y=random.nextInt(346);
        while (x<70)
            x=random.nextInt(430);
        while (y<52)
            y=random.nextInt(346);

    }

}
