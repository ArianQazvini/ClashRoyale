package model.robot;

import model.Card;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

public class SimpleRobot extends Robot{
    @Override
    public Card chooseFromDeck() {
        Random random=new Random();
        int index=random.nextInt(4);
        return deckOnGame.get(index);
    }

    @Override
    public void chooseLocation() {

    }
}
