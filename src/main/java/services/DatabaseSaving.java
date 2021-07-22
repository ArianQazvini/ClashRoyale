package services;

import model.BattleHistory;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Card;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Troop.*;
import sample.Main;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseSaving {
    //        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        }catch (ClassNotFoundException c){
//            System.out.println(c);
//        }
    GameManager gameManager= Main.gameManager;
    String url="jdbc:mysql://localhost:3306";
    String username="root";
    String password="1321an801123I";
    Statement statement;
    public void startConnection(){
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            statement=connection.createStatement();
        }catch (SQLException q){
            System.out.println(q);
        }
    }
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
    public void addBattleHistory(BattleHistory battleHistory) throws SQLException {
        String query="insert into clash_royal.battle_history values("+"'"+battleHistory.getWinner()+"'"+","+"'"
                +battleHistory.getDate()+"'"+"'"+gameManager.getPlayer().getName()
                +"'"+","+"'"+gameManager.getPlayer().getPassword()+"')";
        statement.execute(query);

    }
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
    public void logIn(String name,String pass) throws SQLException {
        String query="select * from clash_royal.player_profile where name ="+"'"+name+"'"+" and password="+"'"+pass+"'";
        statement.execute(query);
    }
    public void signUp(String name,String pass) throws SQLException {
        String query="insert into clash_royal.player_profile values("+"'"+name+"'"+","+"'"+pass+"'"+",0,0,0,0,0,0,0,0,300)";
        statement.execute(query);

    }
    public boolean importValues() throws SQLException {
        ResultSet resultSet= statement.getResultSet();
        if (!resultSet.isBeforeFirst()){
            System.out.println("is empty");
            return false;
        }else {
            while (resultSet.next()) {
                gameManager.getPlayer().setName(resultSet.getString("name"));
                gameManager.getPlayer().setPassword(resultSet.getString("password"));
                for (int i = 3; i <= 10; i++) {
                    int id = resultSet.getInt(i);
                    Card card =findOnCards(id);
//                    if (id == 1)
                        gameManager.getPlayer().getDeck().getCards().add(card);
                        gameManager.getPlayer().getCards().remove(card);
//                    else if (id == 2)
//                        gameManager.getPlayer().getDeck().getCards().add(new InfernoTower());
//                    else if (id == 3)
//                        gameManager.getPlayer().getDeck().getCards().add(new Arrows());
//                    else if (id == 4)
//                        gameManager.getPlayer().getDeck().getCards().add(new Fireball());
//                    else if (id == 5)
//                        gameManager.getPlayer().getDeck().getCards().add(new Rage());
//                    else if (id == 6)
//                        gameManager.getPlayer().getDeck().getCards().add(new Archer());
//                    else if (id == 7)
//                        gameManager.getPlayer().getDeck().getCards().add(new BabyDragon());
//                    else if (id == 8)
//                        gameManager.getPlayer().getDeck().getCards().add(new Giant());
//                    else if (id == 9)
//                        gameManager.getPlayer().getDeck().getCards().add(new MiniPEKKA());
//                    else if (id == 10)
//                        gameManager.getPlayer().getDeck().getCards().add(new Valkyrie());
//                    else if (id == 11)
//                        gameManager.getPlayer().getDeck().getCards().add(new Wizard());
//                    else if (id == 12)
//                        gameManager.getPlayer().getDeck().getCards().add(new Barbarian());
//                }
//                for (int i=0;i<gameManager.getPlayer().getDeck().getCards().size();i++){
//
                }
                gameManager.getPlayer().setScores(resultSet.getInt("score"));

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
