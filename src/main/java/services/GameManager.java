package services;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.Building.Building;
import model.Player;
import model.Tower.Tower;
import model.Troop.Troop;
import model.robot.Robot;
import model.robot.SimpleRobot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameManager {
    private final int blockSize=20;
    private Player player=new Player();
    private Robot opponent=new SimpleRobot();
    private Parent root;
    private Stage stage;
    private Scene scene;
    private final int rows =18;
    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[24][2];
    private ImageView[][] river = new ImageView[2][16];
    private ArrayList<Shape> bullets = new ArrayList<>();
    private ArrayList<Troop> troops = new ArrayList<>();
    private ArrayList<Tower>  towers= new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
//    public Directions CharacterExist(Troop troop, Directions directions)
//    {
//        if(directions==Directions.TOP)
//        {
//            for (int i=0;i<player.getTroops().size();i++)
//            {
//                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
//                {
//                    if(player.getTroops().get(i).getY_Current()- troop.getY_Current()== -20 && player.getTroops().get(i).getX_Current()==troop.getX_Current())
//                    {
//                        return Directions.TOP;
//                    }
//                }
//            }
//        }
//        else if(directions==Directions.LEFT)
//        {
//            for (int i=0;i<player.getTroops().size();i++)
//            {
//                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
//                {
//                    if(player.getTroops().get(i).getX_Current()- troop.getX_Current()== -20 && player.getTroops().get(i).getY_Current()==troop.getY_Current())
//                    {
//                        return Directions.LEFT;
//                    }
//                }
//            }
//        }
//        else if(directions==Directions.RIGHT)
//        {
//            for (int i=0;i<player.getTroops().size();i++)
//            {
//                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
//                {
//                    if(player.getTroops().get(i).getX_Current()- troop.getX_Current()== 20 && troop.getY_Current()==player.getTroops().get(i).getY_Current())
//                    {
//                        return Directions.RIGHT;
//                    }
//                }
//            }
//        }
//        else if(directions==Directions.DOWN)
//        {
//            for (int i=0;i<player.getTroops().size();i++)
//            {
//                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
//                {
//                    if(player.getTroops().get(i).getY_Current()- troop.getY_Current()== 20 && player.getTroops().get(i).getX_Current()==troop.getX_Current())
//                    {
//                        return Directions.DOWN;
//                    }
//                }
//            }
//        }
//        return null;
//    }

    public Robot getOpponent() {
        return opponent;
    }

    public void CreateMap()
    {
        double help_row=0.0;
        double help_col=0.0;
        Image grass = new Image(new File("src/main/resources/pics/terrainTile3.png").toURI().toString());
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                blocks[i][j]=new ImageView(grass);
                blocks[i][j].setFitWidth(20);
                blocks[i][j].setFitHeight(20);
                blocks[i][j].setY(help_row);
                blocks[i][j].setX(help_col);
                help_col+=blockSize;
            }
            help_row+=blockSize;
            help_col=0.0;
        }
        road();
        river();
        createPlayerTowers();
        createBotTowers();
    }
    private void road()
    {
        double y = 80.0;
        Image wat = new Image(new File("src/main/resources/pics/roadWater.png").toURI().toString());
        Image road = new Image(new File("src/main/resources/pics/roadTile.png").toURI().toString());
        for (int i = 0; i < 24; i++) {
            if(i==11 || i==12)
            {
               roads[i][0]= new ImageView(wat);
               roads[i][0].setFitWidth(20);
               roads[i][0].setFitHeight(20);
               roads[i][0].setX(40);
               roads[i][0].setY(y);
               y+=blockSize;
            }
            else
            {
                roads[i][0]= new ImageView(road);
                roads[i][0].setFitWidth(20);
                roads[i][0].setFitHeight(20);
                roads[i][0].setX(40);
                roads[i][0].setY(y);
                y+=blockSize;
            }
        }
        y=80.0;
        for (int i = 0; i < 24; i++) {
            if(i==11 || i==12)
            {
                roads[i][1]= new ImageView(wat);
                roads[i][1].setFitWidth(20);
                roads[i][1].setFitHeight(20);
                roads[i][1].setX(300);
                roads[i][1].setY(y);
                y+=blockSize;
            }
            else
            {
                roads[i][1]= new ImageView(road);
                roads[i][1].setFitWidth(20);
                roads[i][1].setFitHeight(20);
                roads[i][1].setX(300);
                roads[i][1].setY(y);
                y+=blockSize;
            }
        }
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 2; j++) {
                fixblocks(roads[i][j].getX(),roads[i][j].getY(),roads[i][j].getImage());
            }
        }
    }
    private void river()
    {
        double x = 0.0;
        int z = 0;
        Image wat = new Image(new File("src/main/resources/pics/terrainTile6.png").toURI().toString());
        for (int i = 0; i < 18; i++) {
            if(i==2 || i==15)
            {
                x+=blockSize;
            }
            else
            {
                river[0][z] = new ImageView(wat);
                river[0][z].setFitWidth(20);
                river[0][z].setFitHeight(20);
                river[0][z].setY(300);
                river[0][z].setX(x);
                x+=blockSize;
                z++;
            }
        }
        x=0.0;
        z=0;
        for (int i = 0; i < 18; i++) {
            if(i==2 || i==15)
            {
                x+=blockSize;
            }
            else
            {
                river[1][z] = new ImageView(wat);
                river[1][z].setFitWidth(20);
                river[1][z].setFitHeight(20);
                river[1][z].setY(320);
                river[1][z].setX(x);
                x+=blockSize;
                z++;
            }
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 16; j++) {
                fixblocks(river[i][j].getX(),river[i][j].getY(),river[i][j].getImage());
            }
        }
    }
    public void createPlayerTowers()
    {
        Image ground = new Image(new File("src/main/resources/pics/terrainTile4.png").toURI().toString());
        Image pCrown = new Image(new File("src/main/resources/pics/PrinceCrown.png").toURI().toString());
        Image kCrown = new Image(new File("src/main/resources/pics/KingCrown.png").toURI().toString());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==1 && j==1)
                {
                    player.getKingTower().getImageViews()[i][j]=new ImageView(kCrown);
                    player.getPrinceTower1().getImageViews()[i][j]= new ImageView(pCrown);
                    player.getPrinceTower2().getImageViews()[i][j]= new ImageView(pCrown);
                }
                else
                {
                    player.getKingTower().getImageViews()[i][j]=new ImageView(ground);
                    player.getPrinceTower1().getImageViews()[i][j]= new ImageView(ground);
                    player.getPrinceTower2().getImageViews()[i][j]= new ImageView(ground);
                }
                player.getKingTower().getImageViews()[i][j].setFitWidth(20);
                player.getKingTower().getImageViews()[i][j].setFitHeight(20);
                player.getPrinceTower1().getImageViews()[i][j].setFitWidth(20);
                player.getPrinceTower1().getImageViews()[i][j].setFitHeight(20);
                player.getPrinceTower2().getImageViews()[i][j].setFitWidth(20);
                player.getPrinceTower2().getImageViews()[i][j].setFitHeight(20);
            }
        }
        int row ;
        int col;
        row=580;
        col=140;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                player.getKingTower().getImageViews()[i][j].setY(row);
                player.getKingTower().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=140;
        }
        row=560;
        col=280;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                player.getPrinceTower2().getImageViews()[i][j].setY(row);
                player.getPrinceTower2().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=280;
        }
        row=560;
        col=20;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                player.getPrinceTower1().getImageViews()[i][j].setY(row);
                player.getPrinceTower1().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=20;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fixblocks(player.getKingTower().getImageViews()[i][j].getX(),player.getKingTower().getImageViews()[i][j].getY(),player.getKingTower().getImageViews()[i][j].getImage());
                fixblocks(player.getPrinceTower1().getImageViews()[i][j].getX(),player.getPrinceTower1().getImageViews()[i][j].getY(),player.getPrinceTower1().getImageViews()[i][j].getImage());
                fixblocks(player.getPrinceTower2().getImageViews()[i][j].getX(),player.getPrinceTower2().getImageViews()[i][j].getY(),player.getPrinceTower2().getImageViews()[i][j].getImage());
            }
        }
    }
    public void createBotTowers()
    {
        Image ground = new Image(new File("src/main/resources/pics/terrainTile4.png").toURI().toString());
        Image pCrown = new Image(new File("src/main/resources/pics/PrinceCrown.png").toURI().toString());
        Image kCrown = new Image(new File("src/main/resources/pics/KingCrown.png").toURI().toString());
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i==1 && j==1)
                {
                    opponent.getKingTower().getImageViews()[i][j]=new ImageView(kCrown);
                    opponent.getPrinceTower1().getImageViews()[i][j]= new ImageView(pCrown);
                    opponent.getPrinceTower2().getImageViews()[i][j]= new ImageView(pCrown);
                }
                else
                {
                    opponent.getKingTower().getImageViews()[i][j]=new ImageView(ground);
                    opponent.getPrinceTower1().getImageViews()[i][j]= new ImageView(ground);
                    opponent.getPrinceTower2().getImageViews()[i][j]= new ImageView(ground);
                }
                opponent.getKingTower().getImageViews()[i][j].setFitWidth(20);
                opponent.getKingTower().getImageViews()[i][j].setFitHeight(20);
                opponent.getPrinceTower1().getImageViews()[i][j].setFitWidth(20);
                opponent.getPrinceTower1().getImageViews()[i][j].setFitHeight(20);
                opponent.getPrinceTower2().getImageViews()[i][j].setFitWidth(20);
                opponent.getPrinceTower2().getImageViews()[i][j].setFitHeight(20);
            }
        }
        int row ;
        int col;
        row=0;
        col=140;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                opponent.getKingTower().getImageViews()[i][j].setY(row);
                opponent.getKingTower().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=140;
        }
        row=20;
        col=280;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                opponent.getPrinceTower2().getImageViews()[i][j].setY(row);
                opponent.getPrinceTower2().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=280;
        }
        row=20;
        col=20;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                opponent.getPrinceTower1().getImageViews()[i][j].setY(row);
                opponent.getPrinceTower1().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=20;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fixblocks(opponent.getKingTower().getImageViews()[i][j].getX(),opponent.getKingTower().getImageViews()[i][j].getY(),opponent.getKingTower().getImageViews()[i][j].getImage());
                fixblocks(opponent.getPrinceTower1().getImageViews()[i][j].getX(),opponent.getPrinceTower1().getImageViews()[i][j].getY(),opponent.getPrinceTower1().getImageViews()[i][j].getImage());
                fixblocks(opponent.getPrinceTower2().getImageViews()[i][j].getX(),opponent.getPrinceTower2().getImageViews()[i][j].getY(),opponent.getPrinceTower2().getImageViews()[i][j].getImage());
            }
        }
    }
    public void fixblocks(double x,double y,Image image)
    {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                if(blocks[i][j].getX()==x && blocks[i][j].getY()==y)
                {
                    blocks[i][j].setImage(image);
                    break;
                }
            }
        }
    }




    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public void init() throws IOException {
        root = FXMLLoader.load(new File("src/main/java/View/log_in.fxml").toURI().toURL());
        scene=new Scene(root);
    }
    public void setRoot(String viewName) throws Exception{
        root = FXMLLoader.load(new File("src/main/java/View/"+viewName+".fxml").toURI().toURL());
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
    }

    public Parent getRoot() {
        return root;
    }

    public Player getPlayer() {
        return player;
    }

    public ImageView[][] getBlocks() {
        return blocks;
    }
    public ImageView[][] getRiver() {
        return river;
    }
    public ImageView[][] getRoads() {
        return roads;
    }

    public ArrayList<Troop> getTroops() {
        return troops;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }
    public boolean isLocationUsable(double x,double y){
        return true;
    }
    public ArrayList<Shape> getBullets() {
        return bullets;
    }
    public ArrayList<Tower> getTowers() {
        return towers;
    }
    public void Step()
    {
        for (int i=0;i<this.getTroops().size();i++)
        {
            if(this.getTroops().get(i).getType().equals("+"))
            {
                if(this.getTroops().get(i).getY_Current()>=0 && this.getTroops().get(i).getX_Current()>=0 &&this.getTroops().get(i).getX_Current()<360)
                {
                    move(this.getTroops().get(i));
                }
            }
            else
            {
                if(this.getTroops().get(i).getY_Current()<640 && this.getTroops().get(i).getX_Current()>=0 &&this.getTroops().get(i).getX_Current()<360)
                {
                    move(this.getTroops().get(i));
                }
            }
        }

    }
    private void move(Troop troop)
    {
        if(troop.getType().equals("+"))
        {
            if(isBlockRiver(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                if(troop.getX_Current()>40 && troop.getX_Current() <=180)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
                else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() <40)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() > 320)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
            }
            else if(isBlockRoad(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                troop.WalkingTopMode();
                troop.Forward();
            }
            else if(isBlockTower(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                troop.WalkingTopMode();
            }
            else if(isBlockAttackCard(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                if(troop.getX_Current()>40 && troop.getX_Current() <=180)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
                else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() <40)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() > 320)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
            }
            else
            {
                troop.WalkingTopMode();
                troop.Forward();
            }
        }
        else
        {
            if(isBlockRiver(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                if(troop.getX_Current()>40 && troop.getX_Current() <=180)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
                else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() <40)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() > 320)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
            }
            else if(isBlockRoad(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                troop.WalkingDownMode();
                troop.Backward();
            }
            else if(isBlockTower(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                troop.WalkingDownMode();
            }
            else if(isBlockAttackCard(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                if(troop.getX_Current()>40 && troop.getX_Current() <=180)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
                else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() <40)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
                else if(troop.getX_Current() > 320)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
            }
            else
            {
                troop.WalkingDownMode();
                troop.Backward();
            }
        }
    }
    private boolean isBlockRiver(double x, double y)
    {
        if((y>300 && y<340 && x>=0 && x<40) || (y>300 && y<340 && x>=60&& x<300) ||(y>300 && y<340 && x>=320) )
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    private boolean isBlockRoad(double x, double y)
    {
        if((x>=40 && x<60 && y<=560 && y>=80) || (x>=300&& x<320&& y<=560 && y>=80))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean isBlockTower(double x, double y)
    {
        if( x<80 && x>=20 && y<=620 && y>=560)
        {
            return true;
        }
        else  if( x<340 && x>=280 && y<=620 && y>=560)
        {
            return true;
        }
        else  if( x<200 && x>=140 && y<=640 && y>=580) {
            return true;
        }
        else if( x<80 && x>=20 && y<=80 && y>=20)
        {
            return true;
        }
        else  if( x<340 && x>=280 && y<=80 && y>=20)
        {
            return true;
        }
        else if( x<200 && x>=140 && y<=60 && y>=0) {
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean isBlockAttackCard(double x, double y)
    {
        for (int i = 0; i < troops.size(); i++) {
            if(x> troops.get(i).getX_Current() && x< troops.get(i).getX_Current()+blockSize && y> troops.get(i).getY_Current() && y< troops.get(i).getY_Current()+blockSize)
            {
                return true;
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(x> troops.get(i).getX_Current() && x< troops.get(i).getX_Current()+blockSize && y>= troops.get(i).getY_Current() && y< troops.get(i).getY_Current()+blockSize)
            {
                return true;
            }
        }
        return false;
    }
}
