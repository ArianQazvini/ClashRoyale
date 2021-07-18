package services;
import enums.Target;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.AttackCard;
import model.Building.Building;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Player;
import model.Tower.Tower;
import model.Troop.Archer;
import model.Troop.BabyDragon;
import model.Troop.Troop;
import model.Troop.Wizard;
import model.robot.Robot;
import model.robot.SimpleRobot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class GameManager {
    private final int blockSize=20;
    private final double Renedering=0.1;
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
        checkBuildingsLife();
        checkTroopsLife();
        buildingsLifeDecrement();
        for (int i = 0; i < troops.size(); i++) {
            prepareTargetFor(troops.get(i));
            addBullet(troops.get(i));
            troops.get(i).Hit();
            areaSplash(troops.get(i),troops.get(i).getLockedTarget());
            if(troops.get(i).getShootingTimeTick()== troops.get(i).getHitSpeed()*10)
            {
                troops.get(i).setShootingTimeTick(0);
            }
            checkBulletsLife(troops.get(i));
            checkBuildingsLife();
            checkTroopsLife();
        }
        for (int i = 0; i < buildings.size(); i++) {
            prepareTargetFor(buildings.get(i));
            addBullet(buildings.get(i));
            buildings.get(i).Hit();
            if(buildings.get(i).getShootingTimeTick()== buildings.get(i).getHitSpeed()*10)
            {
                buildings.get(i).setShootingTimeTick(0);
            }
            checkBulletsLife(buildings.get(i));
            checkBuildingsLife();
            checkTroopsLife();
        }
        for (int i=0;i<this.getTroops().size();i++)
        {
            if(checkPalace(troops.get(i)) && !troops.get(i).isLocked() )
            {
                move(troops.get(i));
            }

        }
    }
    private boolean checkPalace(Troop troop)
    {
        if(troop.getX_Current() >= 0 && troop.getX_Current() <360 && troop.getY_Current()>0 && troop.getY_Current()<640) {
            return true;
        }
        else
        {
            return false;
        }
    }
    private void move(Troop troop)
    {
        if(troop.getType().equals("+"))
        {
            if(riverHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                riverMove(troop);
            }
            else if(roadHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                roadMove(troop);
            }
            else if(towerHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                towerMove(troop);
            }
            else
            {
//                troop.WalkingTopMode();
//                troop.Forward();
                smartUp(troop,troop.getSpeed().getVelocity());
                //collision(troop);
            }
        }
        else
        {
            if(riverHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                riverMove(troop);
            }
            else if(roadHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                roadMove(troop);
            }
            else if(towerHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
               towerMove(troop);
            }
            else
            {
//                troop.WalkingDownMode();
//                troop.Backward();
//                collision(troop);
                smartDown(troop,troop.getSpeed().getVelocity());
            }
        }
    }
    private boolean riverHit(double x, double y)
    {
        if((y>300.0 && y<340.0 && x>=0.0 && x<40.0) || (y>300.0 && y<340.0 && x>=60.0&& x<300.0) ||(y>300.0 && y<340.0 && x>=320.0) )
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    private boolean roadHit(double x, double y)
    {
        if((x>=40.0 && x<60.0 && y<=560.0 && y>=80.0) || (x>=300.0&& x<320.0&& y<=560.0 && y>=80.0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    private boolean towerHit(double x, double y)
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
    private boolean attackCardHit(Troop curr)
    {
        if(curr instanceof BabyDragon)
        {
            return false;
        }
        else
        {
            if((curr.getX_Current()>=40.0 && curr.getX_Current()<60.0 && curr.getY_Current()>300 && curr.getY_Current()<340) ||(curr.getX_Current()>=300 && curr.getX_Current()<320 && curr.getY_Current()>300 && curr.getY_Current()<340))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < troops.size(); i++) {
                    if(troops.get(i)!= curr && !(troops.get(i) instanceof BabyDragon) )
                    {
                        if(Math.abs(troops.get(i).getY_Current()-curr.getY_Current())< blockSize && Math.abs(troops.get(i).getX_Current()-curr.getX_Current())<blockSize)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
    private boolean upCollisionChance(Troop curr, double dist)
    {
        if(curr instanceof BabyDragon)
        {
            return false;
        }
        else
        {
            if((curr.getX_Current()>=40.0 && curr.getX_Current()<60.0 && curr.getY_Current()>300 && curr.getY_Current()<340) ||(curr.getX_Current()>=300 && curr.getX_Current()<320 && curr.getY_Current()>300 && curr.getY_Current()<340))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < troops.size(); i++) {
                    if(troops.get(i)!= curr && !(troops.get(i) instanceof BabyDragon) )
                    {
                        if(Math.abs(troops.get(i).getY_Current()-(curr.getY_Current()-dist))< blockSize && Math.abs(troops.get(i).getX_Current()-curr.getX_Current())<blockSize)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
    private boolean downCollisionChance(Troop curr, double dist)
    {
        if(curr instanceof BabyDragon)
        {
            return false;
        }
        else
        {
            if((curr.getX_Current()>=40.0 && curr.getX_Current()<60.0 && curr.getY_Current()>300 && curr.getY_Current()<340) ||(curr.getX_Current()>=300 && curr.getX_Current()<320 && curr.getY_Current()>300 && curr.getY_Current()<340))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < troops.size(); i++) {
                    if(troops.get(i)!= curr && !(troops.get(i) instanceof BabyDragon) )
                    {
                        if(Math.abs(troops.get(i).getY_Current()-(curr.getY_Current()+dist))< blockSize && Math.abs(troops.get(i).getX_Current()-curr.getX_Current())<blockSize)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
    private boolean leftCollisionChance(Troop curr, double dist)
    {
        if(curr instanceof BabyDragon)
        {
            return false;
        }
        else
        {
            if((curr.getX_Current()>=40.0 && curr.getX_Current()<60.0 && curr.getY_Current()>300 && curr.getY_Current()<340) ||(curr.getX_Current()>=300 && curr.getX_Current()<320 && curr.getY_Current()>300 && curr.getY_Current()<340))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < troops.size(); i++) {
                    if(troops.get(i)!= curr && !(troops.get(i) instanceof BabyDragon) )
                    {
                        if(Math.abs(troops.get(i).getY_Current()-curr.getY_Current())< blockSize && Math.abs(troops.get(i).getX_Current()-(curr.getX_Current()-dist))<blockSize)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
    private boolean rightCollisionChance(Troop curr, double dist)
    {
        if(curr instanceof BabyDragon)
        {
            return false;
        }
        else
        {
            if((curr.getX_Current()>=40.0 && curr.getX_Current()<60.0 && curr.getY_Current()>300 && curr.getY_Current()<340) ||(curr.getX_Current()>=300 && curr.getX_Current()<320 && curr.getY_Current()>300 && curr.getY_Current()<340))
            {
                return false;
            }
            else
            {
                for (int i = 0; i < troops.size(); i++) {
                    if(troops.get(i)!= curr && !(troops.get(i) instanceof BabyDragon) )
                    {
                        if(Math.abs(troops.get(i).getY_Current()-curr.getY_Current())< blockSize && Math.abs(troops.get(i).getX_Current()-(curr.getX_Current()+dist))<blockSize)
                        {
                            return true;
                        }
                    }
                }
                return false;
            }
        }
    }
    private double distance(double x1,double y1,double x2 , double y2)
    {
        double tempx = x1-x2;
        double tempy = y1-y2;
        double sum = Math.pow(tempx,2)+Math.pow(tempy,2);
        return Math.sqrt(sum);
    }
//    private void collision(Troop troop)
//    {
//        if(attackCardHit(troop))
//        {
//            if (troop.getX_Current()<=180)
//            {
//                troop.WalkingRightMode();
//                troop.Right(blockSize);
//            }
//            else
//            {
//                troop.WalkingLeftMode();
//                troop.Left();
//            }
//        }
//    }
    private void riverMove(Troop troop)
    {
        if(!(troop instanceof BabyDragon))
        {
            if(troop.getType().equals("+"))
            {
                if(riverHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
                {
                    if(troop.getX_Current()>=60 && troop.getX_Current() <=80)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                       // smartLeft(troop,20);
                    }
                    else if(troop.getX_Current()>=80 && troop.getX_Current() <=180)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(troop.getSpeed().getVelocity());
                       // smartLeft(troop,troop.getSpeed().velocity);
                    }
                    else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                    {
//                        troop.WalkingRightMode();
//                        troop.Right();
                        smartRight(troop,troop.getSpeed().velocity);
                    }
                    else if(troop.getX_Current() <40)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                        //smartRight(troop,troop.getSpeed().getVelocity());
                    }
                    else if(troop.getX_Current() >= 320 && troop.getX_Current()<340)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                        //smartLeft(troop,20);
                    }
                    else if (troop.getX_Current()>=340)
                    {
                        troop.WalkingLeftMode();
                        troop.Left();
                       // smartLeft(troop,troop.getSpeed().velocity);
                    }
                }
            }
            else {
                if(riverHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
                {
                    if(troop.getX_Current()>=60 && troop.getX_Current() <=80)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                   //     smartLeft(troop,20);
                    }
                    else if(troop.getX_Current()>=80 && troop.getX_Current() <=180)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(troop.getSpeed().getVelocity());
                       // smartLeft(troop,troop.getSpeed().velocity);
                    }
                    else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                 //       smartRight(troop,troop.getSpeed().velocity);
                    }
                    else if(troop.getX_Current() <40)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                      //  smartRight(troop,troop.getSpeed().getVelocity());
                    }
                    else if(troop.getX_Current() >= 320 && troop.getX_Current()<340)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                       // smartLeft(troop,20);
                    }
                    else if (troop.getX_Current()>=340)
                    {
                        troop.WalkingLeftMode();
                        troop.Left();
                     //   smartLeft(troop,troop.getSpeed().velocity);
                    }
                }
            }
        }
        else
        {
            if(troop.getType().equals("+"))
            {
                troop.WalkingTopMode();
                troop.Forward();
            }
            else
            {
                troop.WalkingDownMode();
                troop.Backward();
            }
        }
      // collision(troop);
    }
    private void roadMove(Troop troop)
    {
        if(troop.getType().equals("+"))
        {
            if(roadHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                troop.WalkingTopMode();
                troop.Forward();
               // smartUp(troop,troop.getSpeed().getVelocity());
            }
        }
        else
        {
            if(roadHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                troop.WalkingDownMode();
                troop.Backward();
//                smartDown(troop,troop.getSpeed().getVelocity());
            }
        }
        //collision(troop);
    }
    private void towerMove(Troop troop)
    {
        if (troop.getType().equals("+"))
        {
            if(towerHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
            {
                troop.WalkingTopMode();
//                troop.Forward();
               // smartUp(troop,troop.getSpeed().getVelocity());
            }
        }
        else
        {
            if(towerHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize))
            {
                troop.WalkingDownMode();
//                troop.Backward();
               // smartDown(troop,troop.getSpeed().getVelocity());
            }
        }
       // collision(troop);
    }
    private void smartLeft(Troop troop,double dist)
    {
        if(!leftCollisionChance(troop,dist))
        {
            troop.WalkingLeftMode();
            troop.Left(dist);
        }
        else
        {
            if(!upCollisionChance(troop,dist))
            {
                troop.WalkingTopMode();
                troop.Forward(blockSize);
            }
            else
            {
                troop.WalkingDownMode();
                troop.Backward(blockSize);
            }
        }
    }
    private void smartUp(Troop troop,double dist)
    {
            if(!upCollisionChance(troop,dist))
            {
                troop.WalkingTopMode();
                troop.Forward(dist);
            }
            else
            {
                if(!leftCollisionChance(troop,dist))
                {
                    troop.WalkingLeftMode();
                    troop.Left(blockSize);
                }
                else
                {
                    troop.WalkingRightMode();
                    troop.Right(blockSize);
                }
            }
    }
    private void smartRight(Troop troop,double dist)
    {
            if(!rightCollisionChance(troop,dist))
            {
                troop.WalkingRightMode();
                troop.Right(dist);
            }
            else
            {
                if(!upCollisionChance(troop,dist))
                {
                    troop.WalkingTopMode();
                    troop.Forward(blockSize);
                }
                else
                {
                    troop.WalkingDownMode();
                    troop.Backward(blockSize);
                }
            }
    }
    private void smartDown(Troop troop,double dist)
    {
            if(!downCollisionChance(troop,dist))
            {
                troop.WalkingDownMode();
                troop.Backward(dist);
            }
            else
            {
                if(!leftCollisionChance(troop,dist))
                {
                    troop.WalkingLeftMode();
                    troop.Left(blockSize);
                }
                else
                {
                    troop.WalkingRightMode();
                    troop.Right(blockSize);
                }
            }
    }
    private void buildingsLifeDecrement()
    {
        for (int i = 0; i < buildings.size(); i++) {
            buildings.get(i).decrementLife(Renedering);
        }
    }
    private void removeBuilding(Building building)
    {
        Iterator<Shape> iterator = bullets.iterator();
        if(building instanceof Cannon)
        {
            Cannon temp = (Cannon) building;
            while (iterator.hasNext())
            {
                Shape shape = iterator.next();
                if(shape==temp.getCanonnBall())
                {
                    iterator.remove();
                    break;
                }
            }
        }
        else if(building instanceof InfernoTower)
        {
            InfernoTower temp = (InfernoTower) building;
            while (iterator.hasNext())
            {
                Shape shape = iterator.next();
                if(shape==temp.getFireball())
                {
                    iterator.remove();
                    break;
                }
            }
        }
        Iterator<Building> it = buildings.iterator();
        while (it.hasNext())
        {
            Building temp = it.next();
            if(temp==building)
            {
                it.remove();
                break;
            }
        }
        for (int i = 0; i < troops.size(); i++) {
            if(troops.get(i).getLockedTarget()==building)
            {
                troops.get(i).setLockedTarget(null);
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).getLockedTarget()==building)
            {
                buildings.get(i).setLockedTarget(null);
            }
        }
        towerTargetRemove(building,player.getPrinceTower1());
        towerTargetRemove(building,player.getPrinceTower2());
        towerTargetRemove(building,opponent.getPrinceTower2());
        towerTargetRemove(building,opponent.getPrinceTower2());
        towerTargetRemove(building,player.getKingTower());
        towerTargetRemove(building,opponent.getKingTower());
    }
    private void towerTargetRemove(AttackCard attackCard ,Tower tower)
    {
        if(tower.getLockedTarget()== attackCard)
        {
            tower.setLockedTarget(null);
        }
    }
    private void checkBuildingsLife()
    {
        for (int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).getLifeTime()<=0 || buildings.get(i).getLevelInformation().getHp()<=0)
            {
                removeBuilding(buildings.get(i));
            }
        }
    }
    private void checkTroopsLife()
    {
        for (int i = 0; i < troops.size(); i++) {
            if(troops.get(i).getLevelInformation().getHp()<=0)
            {
                removeTroops(troops.get(i));
            }
        }
    }
    private void removeTroops(Troop troop)
    {
        Iterator<Shape> it= bullets.iterator();
        if(troop instanceof Wizard)
        {
            Wizard temp = (Wizard) troop;
            while (it.hasNext())
            {
                Shape shape = it.next();
                if(shape== temp.getFireball())
                {
                    it.remove();
                    break;
                }
            }
        }
        else if(troop instanceof  BabyDragon)
        {
            BabyDragon temp = (BabyDragon) troop;
            while (it.hasNext())
            {
                Shape shape = it.next();
                if(shape== temp.getFireball())
                {
                    it.remove();
                    break;
                }
            }
        }
        else if(troop instanceof Archer)
        {
            Archer temp = (Archer) troop;
            while (it.hasNext())
            {
                Shape shape = it.next();
                if(shape== temp.getArrow())
                {
                    it.remove();
                    break;
                }
            }
        }
        Iterator<Troop> it2 = troops.iterator();
        while (it2.hasNext())
        {
            Troop temp = it2.next();
            if(temp==troop)
            {
                it2.remove();
                break;
            }
        }
        for (int i = 0; i < troops.size(); i++) {
            if(troops.get(i).getLockedTarget()==troop)
            {
                troops.get(i).setLockedTarget(null);
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).getLockedTarget()==troop)
            {
                buildings.get(i).setLockedTarget(null);
            }
        }
        towerTargetRemove(troop,player.getPrinceTower1());
        towerTargetRemove(troop,player.getPrinceTower2());
        towerTargetRemove(troop,opponent.getPrinceTower2());
        towerTargetRemove(troop,opponent.getPrinceTower2());
        towerTargetRemove(troop,player.getKingTower());
        towerTargetRemove(troop,opponent.getKingTower());
    }
    private ArrayList<AttackCard> attackCardsInArea(double x,double y, double radius)
    {
        ArrayList<AttackCard> temp = new ArrayList<>();
        for (int i = 0; i < troops.size(); i++) {
            if(distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current())<radius)
            {
                temp.add(troops.get(i));
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current())<radius)
            {
                temp.add(buildings.get(i));
            }
        }
        return temp;
    }
    private void areaSplash(Troop shooter,AttackCard target)
    {
        if(shooter.isAreaSplash() && target!=null)
        {
            if(shooter.getShootingTimeTick()== shooter.getHitSpeed()*10)
            {
                if(shooter instanceof Wizard)
                {
                    Wizard wizard = (Wizard) shooter;
                    ArrayList<AttackCard> temp = attackCardsInArea(wizard.getFireball().getCenterX(),wizard.getFireball().getCenterY(),shooter.getRange()*blockSize);
                    for (int i = 0; i < temp.size(); i++) {
                        if(shooter.getTarget()== Target.AIR)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target && temp.get(i) instanceof BabyDragon)
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                        else if(shooter.getTarget()==Target.AIR_GROUND)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target)
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                        else if(shooter.getTarget()==Target.GROUND)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target && !(temp.get(i) instanceof BabyDragon))
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                        else if(shooter.getTarget()==Target.BUILDING)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target && temp.get(i) instanceof Building)
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                    }
                }
                else if(shooter instanceof BabyDragon)
                {
                    BabyDragon babyDragon = (BabyDragon) shooter;
                    ArrayList<AttackCard> temp = attackCardsInArea(babyDragon.getFireball().getCenterX(),babyDragon.getFireball().getCenterY(),shooter.getRange()*blockSize);
                    for (int i = 0; i < temp.size(); i++) {
                        if(shooter.getTarget()== Target.AIR)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target && temp.get(i) instanceof BabyDragon)
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                        else if(shooter.getTarget()==Target.AIR_GROUND)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target)
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                        else if(shooter.getTarget()==Target.GROUND)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target && !(temp.get(i) instanceof BabyDragon))
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                        else if(shooter.getTarget()==Target.BUILDING)
                        {
                            if(temp.get(i)!= shooter && temp.get(i).getType().equals(shooter.getType()) && temp.get(i)!=target && temp.get(i) instanceof Building)
                            {
                                temp.get(i).Hurt((double) shooter.getLevelInformation().getDamage().getValue());
                            }
                        }
                    }
                }
            }
        }
    }
    private void prepareTargetFor(AttackCard attacker)
    {
        if(!attacker.isLocked())
        {
            AttackCard target = closestAttackCardinArea(attacker,attacker.getX_Current(),attacker.getY_Current(),attacker.getRange()*blockSize);
            if(target!=null)
            {
                attacker.setLockedTarget(target);
            }
        }
    }
    private AttackCard closestAttackCardinArea(AttackCard attacker,double x,double y , double radius)
    {
        ArrayList<Double> dists= new ArrayList<>();
        if(attacker instanceof Troop)
        {
            for (int i = 0; i < troops.size(); i++) {
                if(attacker.getTarget()==Target.AIR)
                {
                    if(troops.get(i) instanceof BabyDragon)
                    {
                        if(troops.get(i)!= attacker && !troops.get(i).getType().equals(attacker.getType()) && distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current())<=radius)
                        {
                            dists.add(distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()));
                        }
                    }
                }
                else if(attacker.getTarget()==Target.GROUND)
                {
                    if(!(troops.get(i) instanceof BabyDragon))
                    {
                        if(troops.get(i)!= attacker && !troops.get(i).getType().equals(attacker.getType()) && distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current())<=radius)
                        {
                            dists.add(distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()));
                        }
                    }
                }
                else if(attacker.getTarget()==Target.AIR_GROUND)
                {
                    if(troops.get(i)!= attacker && !troops.get(i).getType().equals(attacker.getType()) && distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current())<=radius)
                    {
                        dists.add(distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()));
                    }
                }
            }
            for (int i = 0; i < buildings.size(); i++) {
                if(!buildings.get(i).getType().equals(attacker.getType()) && distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current())<=radius)
                {
                    dists.add(distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current()));
                }
            }
            if(dists.size()!=0)
            {
                double min = Collections.min(dists);
                for (int i = 0; i < troops.size(); i++) {
                    if(min== distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()))
                    {
                        return troops.get(i);
                    }
                }
                for (int i = 0; i < buildings.size(); i++) {
                    if(min== distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current()))
                    {
                        return buildings.get(i);
                    }
                }
            }
        }
        else if(attacker instanceof Building)
        {
            for (int i = 0; i < buildings.size(); i++) {
                if(buildings.get(i)!= attacker && !buildings.get(i).getType().equals(attacker.getType()) && distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current())<=radius)
                {
                    dists.add(distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current()));
                }
            }
            for (int i = 0; i < troops.size(); i++) {
                if(attacker.getTarget()==Target.AIR)
                {
                    if(troops.get(i) instanceof  BabyDragon)
                    {
                        if(!troops.get(i).getType().equals(attacker.getType()) && distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current())<=radius)
                        {
                            dists.add(distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()));
                        }
                    }
                }
                else if(attacker.getTarget()== Target.AIR_GROUND)
                {
                    if(!troops.get(i).getType().equals(attacker.getType()) && distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current())<=radius)
                    {
                        dists.add(distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()));
                    }
                }
                else if(attacker.getTarget()==Target.GROUND) {
                    if (!(troops.get(i) instanceof BabyDragon)) {
                        if (!troops.get(i).getType().equals(attacker.getType()) && distance(x, y, troops.get(i).getX_Current(), troops.get(i).getY_Current()) <= radius) {
                            dists.add(distance(x, y, troops.get(i).getX_Current(), troops.get(i).getY_Current()));
                        }
                    }
                }
            }
            if(dists.size()!=0)
            {
                double min = Collections.min(dists);
                for (int i = 0; i < troops.size(); i++) {
                    if(min== distance(x,y,troops.get(i).getX_Current(),troops.get(i).getY_Current()))
                    {
                        return troops.get(i);
                    }
                }
                for (int i = 0; i < buildings.size(); i++) {
                    if(min== distance(x,y,buildings.get(i).getX_Current(),buildings.get(i).getY_Current()))
                    {
                        return buildings.get(i);
                    }
                }
            }
        }
        return null;
    }
    private boolean checkBulletExistence(AttackCard attacker)
    {
        Shape shape = null;
        if(attacker instanceof Wizard)
        {
            Wizard temp = (Wizard) attacker;
            shape = temp.getFireball();
        }
        else if(attacker instanceof  BabyDragon)
        {
            BabyDragon temp = (BabyDragon) attacker;
            shape = temp.getFireball();
        }
        else if(attacker instanceof Cannon)
        {
            Cannon temp = (Cannon) attacker;
            shape = temp.getCanonnBall();
        }
        else if(attacker instanceof InfernoTower)
        {
            InfernoTower temp = (InfernoTower) attacker;
            shape = temp.getFireball();
        }
        else if(attacker instanceof Archer)
        {
            Archer temp = (Archer) attacker;
            shape = temp.getArrow();
        }
        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i)== shape)
            {
                return true;
            }
        }
        return false;
    }
    private void addBullet(AttackCard attacker)
    {
        if(attacker.isLocked() && !checkBulletExistence(attacker))
        {
            if(attacker instanceof Wizard)
            {
                Wizard temp = (Wizard) attacker;
                bullets.add(temp.getFireball());
            }
            else if(attacker instanceof  BabyDragon)
            {
                BabyDragon temp = (BabyDragon) attacker;
                bullets.add(temp.getFireball());
            }
            else if(attacker instanceof Cannon)
            {
                Cannon temp = (Cannon) attacker;
                bullets.add(temp.getCanonnBall());
            }
            else if(attacker instanceof InfernoTower)
            {
                InfernoTower temp = (InfernoTower) attacker;
                bullets.add(temp.getFireball());
            }
            else if(attacker instanceof Archer)
            {
                Archer temp = (Archer) attacker;
                bullets.add(temp.getArrow());
            }
        }
    }
    private void checkBulletsLife(AttackCard attacker)
    {
        if(attacker.getShootingTimeTick()==attacker.getHitSpeed()*10 || !attacker.isLocked())
        {
            Iterator<Shape> it= bullets.iterator();
            if(attacker instanceof Wizard)
            {
                Wizard temp = (Wizard) attacker;
                while (it.hasNext())
                {
                    Shape shape = it.next();
                    if(shape== temp.getFireball())
                    {
                        it.remove();
                        break;
                    }
                }
            }
            else if(attacker instanceof  BabyDragon)
            {
                BabyDragon temp = (BabyDragon) attacker;
                while (it.hasNext())
                {
                    Shape shape = it.next();
                    if(shape== temp.getFireball())
                    {
                        it.remove();
                        break;
                    }
                }
            }
            else if(attacker instanceof Cannon)
            {
                Cannon temp = (Cannon) attacker;
                while (it.hasNext())
                {
                    Shape shape = it.next();
                    if(shape== temp.getCanonnBall())
                    {
                        it.remove();
                        break;
                    }
                }
            }
            else if(attacker instanceof InfernoTower)
            {
                InfernoTower temp = (InfernoTower) attacker;
                while (it.hasNext())
                {
                    Shape shape = it.next();
                    if(shape== temp.getFireball())
                    {
                        it.remove();
                        break;
                    }
                }
            }
            else if(attacker instanceof Archer)
            {
                Archer temp = (Archer) attacker;
                while (it.hasNext())
                {
                    Shape shape = it.next();
                    if(shape== temp.getArrow())
                    {
                        it.remove();
                        break;
                    }
                }
            }
            attacker.resetTimeTick();
        }
    }
//    public void updateBulletsLocation()
//    {
//        for (int i = 0; i < troops.size(); i++) {
//            if(troops.get(i) instanceof Wizard)
//            {
//                Wizard temp = (Wizard) troops.get(i);
//                temp.getFireball().setCenterX(troops.get(i).getX_Current());
//                temp.getFireball().setCenterY(troops.get(i).getY_Current());
//            }
//            else if(troops.get(i) instanceof BabyDragon)
//            {
//                BabyDragon temp = (BabyDragon) troops.get(i);
//                temp.getFireball().setCenterX(troops.get(i).getX_Current());
//                temp.getFireball().setCenterY(troops.get(i).getY_Current());
//            }
//            else if(troops.get(i) instanceof Archer)
//            {
//                Archer temp = (Archer) troops.get(i);
//                temp.getArrow().setX(troops.get(i).getX_Current());
//                temp.getArrow().setY(troops.get(i).getY_Current());
//            }
//        }
//        for (int i = 0; i < buildings.size(); i++) {
//            if(buildings.get(i) instanceof Cannon)
//            {
//                Cannon temp = (Cannon) buildings.get(i);
//                temp.getCanonnBall().setCenterX(buildings.get(i).getX_Current());
//                temp.getCanonnBall().setCenterY(buildings.get(i).getY_Current());
//            }
//            else if(buildings.get(i) instanceof InfernoTower)
//            {
//                InfernoTower temp = (InfernoTower) buildings.get(i);
//                temp.getFireball().setCenterX(buildings.get(i).getX_Current());
//                temp.getFireball().setCenterY(buildings.get(i).getY_Current());
//            }
//        }
//    }
}
