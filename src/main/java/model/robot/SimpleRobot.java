package model.robot;

import model.Card;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

public class SimpleRobot extends Robot{
    Random random=new Random();
    public SimpleRobot(){
        setName("simple robot");
        putSpeed=10000;
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
        x=random.nextInt(360);
        y=random.nextInt(300);
        while (!checkValidity(x,y))
        {
            x=random.nextInt(360);
            y=random.nextInt(300);
        }
    }
    private boolean checkValidity(double x,double y)
    {
        if( x<=80 && x>=20 && y<=80 && y>=20)
        {
            return false;
        }
        else  if( x<=340 && x>=280 && y<=80 && y>=20)
        {
            return false;
        }
        else  if( x<=200 && x>=140 && y<=60 && y>=0) {
            return false;
        }
        else
        {
            return true;
        }
    }


}
