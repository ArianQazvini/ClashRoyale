package model;

import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * The type Battle history.
 */
public class BattleHistory extends Text {
    /**
     * The Winner.
     */
    String winner;
    /**
     * The Date.
     */
    String date;

    /**
     * Instantiates a new Battle history.
     *
     * @param winner the winner
     * @param date   the date
     */
    public BattleHistory(String winner,String date){
        this.winner=winner;
        this.date=date;
        this.setText("Winner: "+winner+"    Date: "+date+"\n-------------------");
        setWrappingWidth(220);
        setTextAlignment(TextAlignment.CENTER);
        setFill(Color.WHITE);

    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }
}
