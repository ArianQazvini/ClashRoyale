package model;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class BattleHistory extends Text {
    String winner;
    String date;
    public BattleHistory(String winner,String date){
        this.winner=winner;
        this.date=date;
        this.setText("Winner: "+winner+"    Date: "+date+"\n-------------------");
        setWrappingWidth(220);
        setTextAlignment(TextAlignment.CENTER);
        setFill(Color.WHITE);

    }

    public String getWinner() {
        return winner;
    }

    public String getDate() {
        return date;
    }
}
