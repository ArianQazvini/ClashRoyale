package model;

import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Date;

public class BattleHistory extends Text {
    String winner;
    String date;
    public BattleHistory(String winner,String date){
        this.winner=winner;
        this.date=date;
        this.setText("Winner: "+winner+"    Date: "+date);
        setWrappingWidth(180);
    }

    public String getWinner() {
        return winner;
    }

    public String getDate() {
        return date;
    }
}
