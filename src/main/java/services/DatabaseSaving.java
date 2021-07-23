package services;

import model.BattleHistory;
import model.Card;
import sample.Main;

import java.sql.*;

/**
 * The type Database saving.
 */
public class DatabaseSaving {
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The Url.
     */
    String url="jdbc:mysql://localhost:3306";
    /**
     * The Username.
     */
    String username="root";
    //String password="1321an801123I";
    String password = "@rian09122155747";
    /**
     * The Password.
     */
// String password="1321an801123I";
    //String password = "@rian09122155747";
    /**
     * The Statement.
     */
    Statement statement;

    /**
     * Start connection.
     */
    public void startConnection(){
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
        }catch (SQLException q){
            System.out.println(q);
        }
    }

    /**
     * Save.
     *
     * @throws SQLException the sql exception
     */
    public void save() throws SQLException {
        String sets="";
        for (int i=0;i<8;i++){
            if (i==0)
            sets+="car"+(i+1)+"="+gameManager.getPlayer().getDeck().getCards().get(i).getId().id;
            else
                sets+=", car"+(i+1)+"="+gameManager.getPlayer().getDeck().getCards().get(i).getId().id;

        }
        String query="update clash_royal.player_profile set " +
                sets+" where name ="+"'"+gameManager.getPlayer().getName()+"'"+
                " and password="+"'"+gameManager.getPlayer().getPassword()+"'";
        statement.execute(query);
    }

    /**
     * Add battle history.
     *
     * @param battleHistory the battle history
     * @throws SQLException the sql exception
     */
    public void addBattleHistory(BattleHistory battleHistory) throws SQLException {
        String query="insert into clash_royal.battle_history values("+"'"+battleHistory.getWinner()+"'"+","+"'"
                +battleHistory.getDate()+"'"+","+"'"+gameManager.getPlayer().getName()
                +"'"+","+"'"+gameManager.getPlayer().getPassword()+"')";
        statement.execute(query);
        String query2="update clash_royal.player_profile set score="+gameManager.getPlayer().getScores()+" where name ="+"'"+gameManager.getPlayer().getName()+"'"+
                " and password="+"'"+gameManager.getPlayer().getPassword()+"'";
        statement.executeUpdate(query2);

    }

    /**
     * Gets battle histories.
     *
     * @throws SQLException the sql exception
     */
    public void getBattleHistories() throws SQLException {
        String query="select * from clash_royal.battle_history where name ="
                +"'"+gameManager.getPlayer().getName()+"'"+" and password="
                +"'"+gameManager.getPlayer().getPassword()+"'";
        statement.execute(query);
        ResultSet resultSet= statement.getResultSet();
        while (resultSet.next()){
            gameManager.getPlayer().getBattleHistories().add(new BattleHistory(resultSet.getString("winner"),resultSet.getString("date")));
        }
    }

    /**
     * Log in.
     *
     * @param name the name
     * @param pass the pass
     * @throws SQLException the sql exception
     */
    public void logIn(String name,String pass) throws SQLException {
        String query="select * from clash_royal.player_profile where name ="+"'"+name+"'"+" and password="+"'"+pass+"'";
        statement.execute(query);
    }

    /**
     * Sign up.
     *
     * @param name the name
     * @param pass the pass
     * @throws SQLException the sql exception
     */
    public void signUp(String name,String pass) throws SQLException {
        String query="insert into clash_royal.player_profile values("+"'"+name+"'"+","+"'"+pass+"'"+",0,0,0,0,0,0,0,0,300)";
        statement.execute(query);

    }

    /**
     * Import values boolean.
     *
     * @return the boolean
     * @throws SQLException the sql exception
     */
    public boolean importValues() throws SQLException {
        ResultSet resultSet= statement.getResultSet();
        if (!resultSet.isBeforeFirst()){
            System.out.println("is empty");
            return false;
        }else {
            while (resultSet.next()) {
                gameManager.getPlayer().setName(resultSet.getString("name"));
                gameManager.getPlayer().setPassword(resultSet.getString("password"));
                gameManager.getPlayer().setScores(resultSet.getInt("score"));
                for (int i = 3; i <= 10; i++) {
                    int id = resultSet.getInt(i);
                    Card card =findOnCards(id);
                    if (card!=null) {
                        gameManager.getPlayer().getDeck().getCards().add(card);
                        gameManager.getPlayer().getCards().remove(card);
                    }
                }

            }
        }
        return true;
    }
    private Card findOnCards(int id){
        for (Card c:gameManager.getPlayer().getCards()){
            if (c.getId().id==id)
                return c;
        }
        return null;
    }

}
