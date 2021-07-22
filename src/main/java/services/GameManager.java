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
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Spell.Spell;
import model.TimeWorks;
import model.Tower.KingTower;
import model.Tower.PrinceTower;
import model.Tower.Tower;
import model.Troop.*;
import model.robot.Robot;
import model.robot.SimpleRobot;
import model.robot.SmartRobot;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * The type Game manager.
 * This class controls all characters , spells , towers ,...
 * movements and shootings
 * and also delete an element if it dies
 * @author ArianQazvini , NegarAnabestani
 * @version 1.0
 */
public class GameManager {
    private final int blockSize=20;
    private final double Renedering=0.1;
    private Player player;
    private Robot opponent;
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
    private ArrayList<Spell> spells = new ArrayList<>();
    private boolean gameFinished = false;
    private Player winner = null;

    public void setPlayer(Player player) {
        this.player = player;
    }


    /**
     * Set player level.
     */
    public void setPlayerLevel(){
        if (player.getScores()>=300&& player.getScores()<800)
            player.setLevel("1");
        else if (player.getScores()>=800&&player.getScores()<900)
            player.setLevel("2");
        else if (player.getScores()>=900&&player.getScores()<1700)
            player.setLevel("3");
        else if (player.getScores()>=1700&&player.getScores()<2500)
            player.setLevel("4");
        else if (player.getScores()>=2500)
            player.setLevel("5");
    }


    /**
     * Sets opponent.
     *
     * @param opponent the opponent
     */
    public void setOpponent(Robot opponent) {
        this.opponent = opponent;
    }

    /**
     * Gets opponent.
     *
     * @return the opponent
     */
    public Robot getOpponent() {
        return opponent;
    }

    /**
     * Is game finished boolean.
     *
     * @return the boolean
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

    /**
     * Sets game finished.
     *
     * @param gameFinished the game finished
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Sets winner.
     *
     * @param winner the winner
     */
    public void setWinner(Player winner) {
        this.winner = winner;
    }

    /**
     * Gets winner.
     *
     * @return the winner
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Create map.
     * using a 32*18 array for filling ground with blocks
     */
    public void CreateMap()
    {
        double help_row=0.0;
        double help_col=0.0;
        Image grass = new Image(new File("src/main/resources/pics/terrainTile3.png").toURI().toString());
        Image grass2 = new Image(new File("src/main/resources/pics/lightGreenBlock.png").toURI().toString());
        for (int i = 0; i < 32; i++) {
            if(i%2==1)
            {
                for (int j = 0; j < 18; j++) {
                    if(j%2 ==1)
                    {
                        blocks[i][j]=new ImageView(grass2);
                        blocks[i][j].setFitWidth(20);
                        blocks[i][j].setFitHeight(20);
                        blocks[i][j].setY(help_row);
                        blocks[i][j].setX(help_col);
                        help_col+=blockSize;
                    }
                    else
                    {
                        blocks[i][j]=new ImageView(grass);
                        blocks[i][j].setFitWidth(20);
                        blocks[i][j].setFitHeight(20);
                        blocks[i][j].setY(help_row);
                        blocks[i][j].setX(help_col);
                        help_col+=blockSize;
                    }
                }
                help_row+=blockSize;
                help_col=0.0;
            }
            else
            {
                for (int j = 0; j < 18; j++) {
                    if(j%2 ==1)
                    {
                        blocks[i][j]=new ImageView(grass);
                        blocks[i][j].setFitWidth(20);
                        blocks[i][j].setFitHeight(20);
                        blocks[i][j].setY(help_row);
                        blocks[i][j].setX(help_col);
                        help_col+=blockSize;
                    }
                    else
                    {
                        blocks[i][j]=new ImageView(grass2);
                        blocks[i][j].setFitWidth(20);
                        blocks[i][j].setFitHeight(20);
                        blocks[i][j].setY(help_row);
                        blocks[i][j].setX(help_col);
                        help_col+=blockSize;
                    }
                }
                help_row+=blockSize;
                help_col=0.0;
            }
        }
        road();
        river();
        createPlayerTowers();
        createBotTowers();

    }

    /**
     *creating road blocks
     */
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

    /**
     * creating river blocks
     */
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

    /**
     * Create player towers blocks.
     */
    public void createPlayerTowers()
    {
        Image ground = new Image(new File("src/main/resources/pics/terrainTile4.png").toURI().toString());
        Image pCrown = new Image(new File("src/main/resources/pics/PrinceUp.png").toURI().toString());
        Image kCrown = new Image(new File("src/main/resources/pics/BlueKing.png").toURI().toString());
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
        player.getPrinceTower1().setX(player.getPrinceTower1().getImageViews()[1][1].getX());
        player.getPrinceTower1().setY(player.getPrinceTower1().getImageViews()[1][1].getY());
        player.getPrinceTower2().setX(player.getPrinceTower2().getImageViews()[1][1].getX());
        player.getPrinceTower2().setY(player.getPrinceTower2().getImageViews()[1][1].getY());
        player.getKingTower().setX(player.getKingTower().getImageViews()[1][1].getX());
        player.getKingTower().setY(player.getKingTower().getImageViews()[1][1].getY());
        player.getPrinceTower1().setType("+");
        player.getPrinceTower2().setType("+");
        player.getKingTower().setType("+");
        towers.add(player.getPrinceTower1());
        towers.add(player.getPrinceTower2());
        towers.add(player.getKingTower());
    }

    /**
     * Create bot towers blocks.
     */
    public void createBotTowers()
    {
        Image ground = new Image(new File("src/main/resources/pics/terrainTile4.png").toURI().toString());
        Image pCrown = new Image(new File("src/main/resources/pics/PrinceDown.png").toURI().toString());
        Image kCrown = new Image(new File("src/main/resources/pics/RedKing.png").toURI().toString());
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
        opponent.getPrinceTower1().setX(opponent.getPrinceTower1().getImageViews()[1][1].getX());
        opponent.getPrinceTower1().setY(opponent.getPrinceTower1().getImageViews()[1][1].getY());
        opponent.getPrinceTower2().setX(opponent.getPrinceTower2().getImageViews()[1][1].getX());
        opponent.getPrinceTower2().setY(opponent.getPrinceTower2().getImageViews()[1][1].getY());
        opponent.getKingTower().setX(opponent.getKingTower().getImageViews()[1][1].getX());
        opponent.getKingTower().setY(opponent.getKingTower().getImageViews()[1][1].getY());
        opponent.getPrinceTower1().setType("-");
        opponent.getPrinceTower2().setType("-");
        opponent.getKingTower().setType("-");
        towers.add(opponent.getPrinceTower1());
        towers.add(opponent.getPrinceTower2());
        towers.add(opponent.getKingTower());
    }

    /**
     * Fixblocks.
     * check if any ground block's coordinates matches with x , y for changing it's image
     * @param x     the x
     * @param y     the y
     * @param image the image
     */
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


    /**
     * Gets stage.
     *
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Sets stage.
     *
     * @param stage the stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Init.
     *
     * @throws IOException the io exception
     */
    public void init() throws IOException {
        root = FXMLLoader.load(new File("src/main/java/View/log_in.fxml").toURI().toURL());
        scene=new Scene(root);
    }

    /**
     * Sets root.
     *
     * @param viewName the view name
     * @throws Exception the exception
     */
    public void setRoot(String viewName) throws Exception{
        root = FXMLLoader.load(new File("src/main/java/View/"+viewName+".fxml").toURI().toURL());
        scene.setRoot(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.sizeToScene();
    }

    /**
     * Gets root.
     *
     * @return the root
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Get blocks image view [ ] [ ].
     *
     * @return the image view [ ] [ ]
     */
    public ImageView[][] getBlocks() {
        return blocks;
    }

    /**
     * Get river image view [ ] [ ].
     *
     * @return the image view [ ] [ ]
     */
    public ImageView[][] getRiver() {
        return river;
    }

    /**
     * Get roads image view [ ] [ ].
     *
     * @return the image view [ ] [ ]
     */
    public ImageView[][] getRoads() {
        return roads;
    }

    /**
     * Gets troops.
     *
     * @return the troops
     */
    public ArrayList<Troop> getTroops() {
        return troops;
    }

    /**
     * Gets buildings.
     *
     * @return the buildings
     */
    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    /**
     * Is location usable boolean.
     *
     * @param x the x
     * @param y the y
     * @return the boolean
     */
    public boolean isLocationUsable(double x,double y){
        return true;
    }

    /**
     * Gets bullets arraylist.
     *
     * @return the bullets
     */
    public ArrayList<Shape> getBullets() {
        return bullets;
    }

    /**
     * Gets towers arraylist
     *
     * @return the towers
     */
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    /**
     * main method of this class
     * which updates each frame of the game (moving elements , deleting dead elements,...)
     */
    public void Step()
    {
        spellsImpact();
        removeSpells();
        addTargetforRage();
        rageImpactControll();
        checkTowersLife();
        checkBuildingsLife();
        checkTroopsLife();
        buildingsLifeDecrement();
        towersHit();
        for (int i = 0; i < troops.size(); i++) {
            prepareTargetFor(troops.get(i));
            prepareTowerTarget(troops.get(i));
            addBullet(troops.get(i));
            troops.get(i).Hit();
            areaSplash(troops.get(i),troops.get(i).getLockedTarget());
            if(troops.get(i).getShootingTimeTick()== troops.get(i).getHitSpeed()*10)
            {
                troops.get(i).setShootingTimeTick(0);
            }
            checkTowersLife();
            checkBulletsLife(troops.get(i));
            checkBuildingsLife();
            checkTroopsLife();
            updateWinner();
        }
        for (int i = 0; i < buildings.size(); i++) {
            prepareTargetFor(buildings.get(i));
            addBullet(buildings.get(i));
            buildings.get(i).Hit();
            if(buildings.get(i).getShootingTimeTick()== buildings.get(i).getHitSpeed()*10)
            {
                buildings.get(i).setShootingTimeTick(0);
            }
            checkTowersLife();
            checkBulletsLife(buildings.get(i));
            checkBuildingsLife();
            checkTroopsLife();
            updateWinner();
        }
        for (int i=0;i<this.getTroops().size();i++)
        {
            if(checkPalace(troops.get(i)) && !troops.get(i).isLocked() )
            {
                move(troops.get(i));
            }
        }
    }

    /**
     * unrage a character if it's rage spell time is finished or it's distance is out of the
     * rage spell area
     */
    private void rageImpactControll()
    {
        for (int i = 0; i < troops.size(); i++) {
            if(troops.get(i).isRaged())
            {
                if(troops.get(i).getRage().isDone() || distance(troops.get(i).getX_Current(),troops.get(i).getY_Current(),troops.get(i).getRage().getX(),troops.get(i).getRage().getY())> troops.get(i).getRage().getRadius()*blockSize)
                {
                    troops.get(i).undoRage();
                    troops.get(i).setShootingTimeTick(0);
                    troops.get(i).setRaged(false);
                    troops.get(i).setRage(null);
                    System.out.println(troops.get(i).getClass()+ " is un raged");
                }
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).isRaged())
            {
                if(buildings.get(i).getRage().isDone())
                {
                    buildings.get(i).undoRage();
                    buildings.get(i).setShootingTimeTick(0);
                    buildings.get(i).setRaged(false);
                    buildings.get(i).setRage(null);
                }
            }
        }
        for (int i = 0; i < towers.size(); i++) {
            if(towers.get(i).isRaged())
            {
                if(towers.get(i).getRage().isDone())
                {
                    towers.get(i).undoRage();
                    towers.get(i).setShootingTimeTick(0);
                    towers.get(i).setRaged(false);
                    towers.get(i).setRage(null);
                }
            }
        }

    }

    /**
     * get troops , buildings and towers inside a rage spell area
     */
    private void addTargetforRage() {
        Rage temp = null;
        for (int i = 0; i < spells.size(); i++) {
            if(spells.get(i) instanceof Rage)
            {
                temp = (Rage) spells.get(i);
            //    if(!temp.isDone())
             //   {
                    for (int j = 0; j < troops.size(); j++) {
                        if(troops.get(j).getType().equals(temp.getType()) && !troops.get(j).isRaged() && distance(troops.get(j).getX_Current(),troops.get(j).getY_Current(),temp.getX(),temp.getY())<= temp.getRadius()*blockSize)
                        {
                            temp.getAttackCards().add(troops.get(j));
                            troops.get(j).setRaged(true);
                            troops.get(j).rageImpact();
                            troops.get(j).setRage(temp);
                            troops.get(j).setShootingTimeTick(0);
                            System.out.println(troops.get(j).getClass()+" is raged");
                        }
                    }
                    for (int j = 0; j < buildings.size(); j++) {
                        if(buildings.get(j).getType().equals(temp.getType()) && !buildings.get(j).isRaged() && distance(buildings.get(j).getX_Current(),buildings.get(j).getY_Current(),temp.getX(),temp.getY())<= temp.getRadius()*blockSize)
                        {
                            temp.getAttackCards().add(buildings.get(j));
                            buildings.get(j).setRaged(true);
                            buildings.get(j).rageImpact();
                            buildings.get(j).setRage(temp);
                            buildings.get(j).setShootingTimeTick(0);
                        }
                    }
               // }
            }
        }
    }

    /**
     * towers shooting to their target
     */
    private void towersHit()
    {
        prepareTargetForTowers();
        for (int i = 0; i < towers.size(); i++) {
            addBullet(towers.get(i));
            towers.get(i).Hit();
            if(towers.get(i).getShootingTimeTick()== towers.get(i).getHitSpeed()*10)
            {
                towers.get(i).setShootingTimeTick(0);
            }
            checkTowersLife();
            checkBulletsLife(towers.get(i));
            checkBuildingsLife();
            checkTroopsLife();
        }
    }

    /**
     * check if a troop is inside game board or not
     * @param troop troop
     * @return true or false
     */
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

    /**
     * move a troop (check if it reaches to a river or tower or ...)
     * @param troop troop
     */
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
            else if(towerHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity())!=null)
            {
                towerMove(troop);
            }
            else if(moveToKingTower(troop))
            {
                if(troop.getX_Current()>180)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
                else if(troop.getX_Current() <180)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
            }
            else
            {
               troop.WalkingTopMode();
//                troop.Forward();
                troop.Forward();
               // smartUp(troop,troop.getSpeed().getVelocity());
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
            else if(towerHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize)!=null)
            {
               towerMove(troop);
            }
            else if(moveToKingTower(troop))
            {
                if(troop.getX_Current()>180)
                {
                    troop.WalkingLeftMode();
                    troop.Left();
                }
                else if(troop.getX_Current() <180)
                {
                    troop.WalkingRightMode();
                    troop.Right();
                }
            }
            else
            {
                troop.WalkingDownMode();
                troop.Backward();
//                collision(troop);
               // smartDown(troop,troop.getSpeed().getVelocity());
            }
        }
    }

    /**
     *
     * @param troop moving troop
     * @return true -> if troop's next move is inside a tower territory flase -> if not
     */
    private boolean moveToKingTower(Troop troop)
    {
        if(troop.getType().equals("+"))
        {
            if(troop.getX_Current() > 180 && opponent.getPrinceTower2().getLevelInformation().getHp()<=0)
            {
                if(troop.getY_Current()- troop.getSpeed().getVelocity() < 20)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if(troop.getX_Current() <180 && opponent.getPrinceTower1().getLevelInformation().getHp()<=0)
            {
                if(troop.getY_Current()- troop.getSpeed().getVelocity() < 20)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        else
        {
            if(troop.getX_Current() > 180 && player.getPrinceTower2().getLevelInformation().getHp()<=0)
            {
                if(troop.getY_Current()+ troop.getSpeed().getVelocity() > 620)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if(troop.getX_Current() <180 && player.getPrinceTower1().getLevelInformation().getHp()<=0)
            {
                if(troop.getY_Current()+ troop.getSpeed().getVelocity() > 620)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
    }

    /**
     *
     * @param x troop's next step x
     * @param y troop's next step y
     * @return true -> if it reaches to river blocks else -> false
     */
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

    /**
     *
     * @param x troop's next step x
     * @param y troop's next step y
     * @return true -> if it reaches to road blocks else -> false
     */
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
    /**
     *
     * @param x troop's next step x
     * @param y troop's next step y
     * @return true -> if it reaches to a tower's blocks else -> false
     */
    private Tower towerHit(double x, double y)
    {
        if(x<80 && x>=20 && y<=620 && y>=560 && player.getPrinceTower1().getLevelInformation().getHp()>0)
        {
            return player.getPrinceTower1();
        }
        else  if( x<340 && x>=280 && y<=620 && y>=560 && player.getPrinceTower2().getLevelInformation().getHp()>0)
        {
            return player.getPrinceTower2();
        }
        else  if( x<200 && x>=140 && y<=640 && y>=580 && player.getKingTower().getLevelInformation().getHp()>0) {
            return player.getKingTower();
        }
        else if( x<80 && x>=20 && y<=80 && y>=20 && opponent.getPrinceTower1().getLevelInformation().getHp()>0)
        {
            return opponent.getPrinceTower1();
        }
        else  if( x<340 && x>=280 && y<=80 && y>=20 && opponent.getPrinceTower2().getLevelInformation().getHp()>0)
        {
            return opponent.getPrinceTower2();
        }
        else if( x<200 && x>=140 && y<=60 && y>=0 && opponent.getKingTower().getLevelInformation().getHp()>0) {
            return opponent.getKingTower();
        }
        else
        {
            return null;
        }
    }
    /**
     *
     * @return true -> if it reaches to an other attack card's blocks else -> false
     */
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

    /**
     *
     * @param curr moving troop
     * @param dist up moving distance
     * @return true -> if there is a chance of collision  , else -> false
     */
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
    /**
     *
     * @param curr moving troop
     * @param dist down moving distance
     * @return true -> if there is a chance of collision  , else -> false
     */
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
    /**
     *
     * @param curr moving troop
     * @param dist left moving distance
     * @return true -> if there is a chance of collision  , else -> false
     */
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
    /**
     *
     * @param curr moving troop
     * @param dist right moving distance
     * @return true -> if there is a chance of collision  , else -> false
     */
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

    /**
     *
     * @param x1 x1
     * @param y1 y1
     * @param x2 x2
     * @param y2 y2
     * @return distance between (x1,y1) and (x2,y2)
     */
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

    /**
     * turn left or right if troop reaches to river territory
     * @param troop moving troop
     */
    private void riverMove(Troop troop)
    {
        if(!(troop instanceof BabyDragon))
        {
            if(troop.getType().equals("+"))
            {
                if(riverHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity()))
                {
                    if(troop.getX_Current()>=60 && troop.getX_Current() <=70)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(troop.getX_Current()-40);
                       // smartLeft(troop,20);
                    }
                    else if(troop.getX_Current()>70 && troop.getX_Current() <=180)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(troop.getSpeed().getVelocity());
                       // smartLeft(troop,troop.getSpeed().velocity);
                    }
                    else if(troop.getX_Current() >= 180 && troop.getX_Current() < 300)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                       // smartRight(troop,troop.getSpeed().velocity);
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
                        troop.Left(troop.getX_Current()-300);
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
                    if(troop.getX_Current()>=60 && troop.getX_Current() <=70)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(troop.getX_Current()-40);
                        //     smartLeft(troop,20);
                    }
                    else if(troop.getX_Current()>70 && troop.getX_Current() <=180)
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
                        troop.Left(troop.getX_Current()-300);
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

    /**
     * move forward(player's troops) or backward(robot's troops) if troop reaches to roads blocks
     * @param troop moving troop
     */
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

    /**
     * turn left or right if troop reaches to tower's territory
     * @param troop moving troop
     */
    private void towerMove(Troop troop)
    {
        if(!(troop instanceof BabyDragon))
        {
            if (troop.getType().equals("+"))
            {
                Tower temp = towerHit(troop.getX_Current(),troop.getY_Current()-troop.getSpeed().getVelocity());
                if(temp == player.getPrinceTower1())
                {
                    if(troop.getX_Current()<=20)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                    }
                    else if(troop.getX_Current()>20)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                    }
                    //troop.WalkingTopMode();
//                troop.Forward();
                    // smartUp(troop,troop.getSpeed().getVelocity());
                }
                else if(temp == player.getPrinceTower2())
                {
                    if(troop.getX_Current()<=300)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                    }
                    else if(troop.getX_Current()>300)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                    }
                }
                else if(temp==player.getKingTower())
                {
                    if(troop.getX_Current()<=160)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                    }
                    else if(troop.getX_Current()>160)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                    }
                }
                else
                {
                    troop.WalkingTopMode();
                }
            }
            else
            {
                Tower temp = towerHit(troop.getX_Current(),troop.getY_Current()+troop.getSpeed().getVelocity()+blockSize);
                if(temp == opponent.getPrinceTower1())
                {
                    if(troop.getX_Current()<=20)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                    }
                    else if(troop.getX_Current()>20)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                    }
                    //troop.WalkingTopMode();
//                troop.Forward();
                    // smartUp(troop,troop.getSpeed().getVelocity());
                }
                else if(temp == opponent.getPrinceTower2())
                {
                    if(troop.getX_Current()<=300)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                    }
                    else if(troop.getX_Current()>300)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                    }
                }
                else if(temp==opponent.getKingTower())
                {
                    if(troop.getX_Current()<=160)
                    {
                        troop.WalkingLeftMode();
                        troop.Left(20);
                    }
                    else if(troop.getX_Current()>160)
                    {
                        troop.WalkingRightMode();
                        troop.Right();
                    }
                }
                else
                {
                    troop.WalkingDownMode();
                }
            }
        }
        else
        {
            if(troop.getType().equals("+"))
            {
                troop.Forward();
            }
            else
            {
                troop.Backward();
            }
        }
       // collision(troop);
    }

    /**
     * turn left if there is no chance of collisions
     * else move forward or backward
     * @param troop moving troop
     * @param dist distance
     */
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
    /**
     * forward movement if there is no chance of collisions
     * else turn left or right
     * @param troop moving troop
     * @param dist distance
     */
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
    /**
     * turn right if there is no chance of collisions
     * else move forward or backward
     * @param troop moving troop
     * @param dist distance
     */
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
    /**
     * backward movement if there is no chance of collisions
     * else turn left or right
     * @param troop moving troop
     * @param dist distance
     */
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

    /**
     * invoking timer thread for buildings
     */
    private void buildingsLifeDecrement()
    {
//        for (int i = 0; i < buildings.size(); i++) {
//            buildings.get(i).decrementLife(Renedering);
//        }
        for (int i = 0; i < buildings.size(); i++) {
            TimeWorks timer = new TimeWorks();
            timer.buildingThread(buildings.get(i));
        }
    }

    /**
     * remove a building from buildings arraylist(if building is some other attackcard's target , this method will make it's target null )
     * also removing it's bullets
     * @param building dead building
     */
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
        for (int i = 0; i < towers.size(); i++) {
            if(towers.get(i).getLockedTarget()==building)
            {
                towers.get(i).setLockedTarget(null);
            }
        }
    }
//    private void towerTargetRemove(AttackCard attackCard ,Tower tower)
//    {
//        if(tower.getLevelInformation().getHp()>0 && tower.getLockedTarget()== attackCard )
//        {
//            tower.setLockedTarget(null);
//        }
//    }

    /**
     * if a building's health reaches to 0 or it's running time is finished , it will be
     * removed
     */
    private void checkBuildingsLife()
    {
        for (int i = 0; i < buildings.size(); i++) {
            if(buildings.get(i).getLifeTime()<=0 || buildings.get(i).getLevelInformation().getHp()<=0)
            {
                removeBuilding(buildings.get(i));
            }
        }
    }

    /**
     * if a troop's health reaches to 0  it will be
     * removed
     */
    private void checkTroopsLife()
    {
        for (int i = 0; i < troops.size(); i++) {
            if(troops.get(i).getLevelInformation().getHp()<=0)
            {
                removeTroops(troops.get(i));
            }
        }
    }

    /**
     * remove a troop from troops arraylist
     * also remove it's bullets
     * @param troop
     */
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
        for (int i = 0; i < towers.size(); i++) {
            if(towers.get(i).getLockedTarget()==troop)
            {
                towers.get(i).setLockedTarget(null);
            }
        }
    }

    /**
     *
     * @param x circle area's x
     * @param y circle area's y
     * @param radius area' range
     * @return elements inside the area
     */
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

    /**
     * area splash impact (hurt elements inside attacker's bullet location area)
     * @param shooter attacker
     * @param target attacker's target
     */
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
                else if(shooter instanceof Valkyrie)
                {
                    Valkyrie valkyrie = (Valkyrie) shooter;
                    ArrayList<AttackCard> temp = attackCardsInArea(valkyrie.getX_Current(),valkyrie.getY_Current(),shooter.getRange()*blockSize);
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

    /**
     * set closest target for an attackCard
     * @param attacker attacker
     */
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

    /**
     * finding the closest target for an attack card
     * also check attackcard's target kind (Air ,...)
     * @param attacker attacker
     * @param x attacker's x
     * @param y attacker's y
     * @param radius attacker's range
     * @return closest target
     */
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

    /**
     * ignoring adding duplicate bullet to Playground
     * @param attacker attacker
     * @return true or false
     */
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

    /**
     * ignoring adding duplicate bullet to Playground
     * @param tower tower
     * @return true or false
     */
    private boolean checkBulletExistence(Tower tower)
    {
        Shape shape = tower.getCanonnBall();
        for (int i = 0; i < bullets.size(); i++) {
            if(bullets.get(i)== shape)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * add attack card's bullet to bullets arraylist
     * @param attacker attacker
     */
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

    /**
     * add tower's bullet to bullets arraylist
     * @param tower attacker
     */
    private void addBullet(Tower tower)
    {
        if(tower.isLocked() && !checkBulletExistence(tower))
        {
            bullets.add(tower.getCanonnBall());
        }
    }

    /**
     * remove attacker's bullet if it's timetick is done or it is not locked
     * @param attacker attacker
     */
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

    /**
     * remove tower's bullet if it's timetick is done or it is not locked
     * @param tower attacker
     */
    private void checkBulletsLife(Tower tower)
    {
        if(tower.getShootingTimeTick()==tower.getHitSpeed()*10 || !tower.isLocked())
        {
            Iterator<Shape> it= bullets.iterator();
            while (it.hasNext())
            {
                Shape temp = it.next();
                if(temp==tower.getCanonnBall())
                {
                    it.remove();
                    break;
                }
            }
            tower.resetTimeTick();
        }
    }

    /**
     *  check tower's life and increase players won crowns
     */
    private void checkTowersLife()
    {
            if(player.getPrinceTower1().getLevelInformation().getHp()<=0 && !player.getPrinceTower1().isDead())
            {
                player.getKingTower().setCanShoot(true);
                removeTower(player.getPrinceTower1());
                princeTowerDeath(player.getPrinceTower1());
                opponent.incrementCrownsWon();
                opponent.crownProperty().set(opponent.getCrownsWon());
                player.getPrinceTower1().setDead(true);
            }
            if(player.getPrinceTower2().getLevelInformation().getHp()<=0 && !player.getPrinceTower2().isDead())
            {
                player.getKingTower().setCanShoot(true);
                removeTower(player.getPrinceTower2());
                princeTowerDeath(player.getPrinceTower2());
                opponent.incrementCrownsWon();
                opponent.crownProperty().set(opponent.getCrownsWon());
                player.getPrinceTower2().setDead(true);
            }
            if(opponent.getPrinceTower1().getLevelInformation().getHp()<=0 && !opponent.getPrinceTower1().isDead())
            {
                opponent.getKingTower().setCanShoot(true);
                removeTower(opponent.getPrinceTower1());
                princeTowerDeath(opponent.getPrinceTower1());
                player.incrementCrownsWon();
                player.crownProperty().set(player.getCrownsWon());
                opponent.getPrinceTower1().setDead(true);
            }
            if(opponent.getPrinceTower2().getLevelInformation().getHp()<=0 && !opponent.getPrinceTower2().isDead() )
            {
                opponent.getKingTower().setCanShoot(true);
                removeTower(opponent.getPrinceTower2());
                princeTowerDeath(opponent.getPrinceTower2());
                player.incrementCrownsWon();
                player.crownProperty().set(player.getCrownsWon());
                opponent.getPrinceTower2().setDead(true);
            }
            if(player.getKingTower().getLevelInformation().getHp()<=0 && !player.getKingTower().isDead())
            {
                removeTower(player.getKingTower());
                kingTowerDeath(player.getKingTower());
                opponent.setCrownsWon(3);
                opponent.crownProperty().set(3);
                gameFinished=true;
                player.getKingTower().setDead(true);
            }
            if(opponent.getKingTower().getLevelInformation().getHp() <=0 && !opponent.getKingTower().isDead())
            {
                removeTower(opponent.getKingTower());
                kingTowerDeath(opponent.getKingTower());
                player.setCrownsWon(3);
                player.crownProperty().set(3);
                gameFinished=true;
                opponent.getKingTower().setDead(true);
            }
    }

    /**
     * remove a tower from towers arraylist
     * @param tower tower
     */
    private void removeTower(Tower tower)
    {
        Iterator<Shape> shapeIterator = bullets.iterator();
        while (shapeIterator.hasNext())
        {
            Shape temp = shapeIterator.next();
            if(temp== tower.getCanonnBall())
            {
                shapeIterator.remove();
                break;
            }
        }
        for (int i = 0; i < troops.size(); i++) {
            if(tower==troops.get(i).getTowerTarget())
            {
                troops.get(i).setTowerTarget(null);
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(tower==buildings.get(i).getTowerTarget())
            {
                buildings.get(i).setTowerTarget(null);
            }
        }
        Iterator<Tower> towerIterator = towers.iterator();
        while (towerIterator.hasNext())
        {
            Tower temp = towerIterator.next();
            if(temp == tower)
            {
                towerIterator.remove();
                break;
            }
        }
    }

    /**
     *  set closest attackcards as a target for towers
     */
    private void prepareTargetForTowers()
    {
        if(player.getPrinceTower1().getLevelInformation().getHp()>0 && !player.getPrinceTower1().isLocked())
        {
            AttackCard target =closestTargetForTower(player.getPrinceTower1());
            if(target!= null)
            {
                player.getPrinceTower1().setLockedTarget(target);
            }
        }
        if(player.getPrinceTower2().getLevelInformation().getHp()>0 && !player.getPrinceTower2().isLocked())
        {
            AttackCard target =closestTargetForTower(player.getPrinceTower2());
            if(target!= null)
            {
                player.getPrinceTower2().setLockedTarget(target);
            }
        }
        if(opponent.getPrinceTower1().getLevelInformation().getHp()>0 && !opponent.getPrinceTower1().isLocked())
        {
            AttackCard target =closestTargetForTower(opponent.getPrinceTower1());
            if(target!= null)
            {
                opponent.getPrinceTower1().setLockedTarget(target);
            }
        }
        if(opponent.getPrinceTower2().getLevelInformation().getHp()>0 && !opponent.getPrinceTower2().isLocked())
        {
            AttackCard target =closestTargetForTower(opponent.getPrinceTower2());
            if(target!= null)
            {
                opponent.getPrinceTower2().setLockedTarget(target);
            }
        }
        if ((player.getKingTower().getLevelInformation().getHp()>0)&& (player.getKingTower().isGotHurt() || player.getPrinceTower1().getLevelInformation().getHp()<=0 || player.getPrinceTower2().getLevelInformation().getHp()<=0))
        {
            player.getKingTower().setCanShoot(true);
            if(!player.getKingTower().isLocked())
            {
                AttackCard target =closestTargetForTower(player.getKingTower());
                if(target!=null)
                {
                    player.getKingTower().setLockedTarget(target);
                }
            }
        }
        if ((opponent.getKingTower().getLevelInformation().getHp()>0)&&(opponent.getKingTower().isGotHurt() || opponent.getPrinceTower1().getLevelInformation().getHp()<=0 || opponent.getPrinceTower2().getLevelInformation().getHp()<=0))
        {
            opponent.getKingTower().setCanShoot(true);
            if(!opponent.getKingTower().isLocked())
            {
                AttackCard target =closestTargetForTower(opponent.getKingTower());
                if(target!=null)
                {
                    opponent.getKingTower().setLockedTarget(target);
                }
            }
        }
    }

    /**
     * find the closest target for a tower
     * @param tower tower
     * @return closest target
     */
    private AttackCard closestTargetForTower(Tower tower)
    {
        ArrayList<Double> dists= new ArrayList<>();
        for (int i = 0; i < troops.size(); i++) {
            if(!troops.get(i).getType().equals(tower.getType()) &&distance(tower.getX(),tower.getY(),troops.get(i).getX_Current(),troops.get(i).getY_Current())<= tower.getRange()*blockSize)
            {
                dists.add(distance(tower.getX(),tower.getY(),troops.get(i).getX_Current(),troops.get(i).getY_Current()));
            }
        }
        for (int i = 0; i < buildings.size(); i++) {
            if(!buildings.get(i).getType().equals(tower.getType()) && distance(tower.getX(),tower.getY(),buildings.get(i).getX_Current(),buildings.get(i).getY_Current())<= tower.getRange()*blockSize)
            {
                dists.add(distance(tower.getX(),tower.getY(),buildings.get(i).getX_Current(),buildings.get(i).getY_Current()));
            }
        }
        if(dists.size()!=0)
            {
                double min = Collections.min(dists);
                for (int i = 0; i < troops.size(); i++) {
                    if(min== distance(tower.getX(),tower.getY(),troops.get(i).getX_Current(),troops.get(i).getY_Current()))
                    {
                        return troops.get(i);
                    }
                }
                for (int i = 0; i < buildings.size(); i++) {
                    if(min== distance(tower.getX(),tower.getY(),buildings.get(i).getX_Current(),buildings.get(i).getY_Current()))
                    {
                        return buildings.get(i);
                    }
                }
            }
        return null;
    }

    /**
     *
     * @param attackCard attackcard
     * @param tower tower
     * @return true -> if attack card is in tower's area
     */
    private boolean attackCardisinTowerArea(AttackCard attackCard,Tower tower)
    {
        if(!tower.getType().equals(attackCard.getType()))
        {
            double min_y = 0.0;
            double min_x = 0.0;
            double max_x = 0.0;
            double max_y = 0.0;
            if(attackCard instanceof  Troop)
            {
                Troop help = (Troop) attackCard;
             //   if(attackCard.getType().equals("+"))
            //    {
                    max_y = tower.getImageViews()[2][2].getY() + blockSize  + (attackCard.getRange() * blockSize)+ help.getSpeed().getVelocity();
                    min_y = tower.getImageViews()[0][0].getY() - (attackCard.getRange()*blockSize)- help.getSpeed().getVelocity();
//                }
//                else
//                {
//                    max_y = tower.getImageViews()[2][2].getY() + blockSize  + (attackCard.getRange() * blockSize)+ help.getSpeed().getVelocity();
//                    min_y = tower.getImageViews()[0][0].getY() - (attackCard.getRange()*blockSize)- help.getSpeed().getVelocity();
//                }
            }
            else {
                min_y = tower.getImageViews()[0][0].getY() - (attackCard.getRange() * blockSize);
                max_y = tower.getImageViews()[2][2].getY() + blockSize + (attackCard.getRange() * blockSize);
            }
               // double min_y = tower.getImageViews()[0][0].getY() - (attackCard.getRange()*blockSize);
               // double max_y = tower.getImageViews()[2][2].getY() + blockSize  + (attackCard.getRange() * blockSize);
                //------------------------
                 min_x = tower.getImageViews()[0][0].getX() - (attackCard.getRange()*blockSize);
                 max_x = tower.getImageViews()[2][2].getX() + (attackCard.getRange()* blockSize);
                if(attackCard.getType().equals("+"))
                {
                    if(attackCard.getX_Current() >= min_x && attackCard.getX_Current() < max_x && attackCard.getY_Current()> min_y && attackCard.getY_Current()< max_y)
                    {
                        if(attackCard instanceof Valkyrie || attackCard instanceof Barbarian || attackCard instanceof Giant || attackCard instanceof  MiniPEKKA)
                        {
                            if(tower==opponent.getKingTower())
                            {
                                if(attackCard.getY_Current() <180)
                                {
                                    ((Troop) attackCard).Right(tower.getImageViews()[0][0].getX()-(attackCard.getX_Current()+blockSize));
                                }
                                else
                                {
                                    ((Troop) attackCard).Left(attackCard.getX_Current()-tower.getImageViews()[0][0].getX());
                                }
                            }
                            else
                            {
                                ((Troop) attackCard).Forward(attackCard.getY_Current() - (tower.getImageViews()[2][2].getY()+blockSize));
                            }
                        }
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
                else
                {
//                    System.out.println(attackCard.getClass()+" "+attackCard.getX_Current());
//                    System.out.println(attackCard.getClass()+" "+attackCard.getY_Current());
//                    System.out.println(max_x + " max x ");
//                    System.out.println(min_x + " min x ");
//                    System.out.println(max_y + " max y ");
//                    System.out.println(min_y + " min y ");
//                    System.out.println("--------------------------");
                    if(attackCard.getX_Current() >= min_x && attackCard.getX_Current() < max_x && attackCard.getY_Current()> min_y && attackCard.getY_Current()< max_y)
                    {
                        if(attackCard instanceof Valkyrie || attackCard instanceof Barbarian || attackCard instanceof Giant || attackCard instanceof  MiniPEKKA)
                        {
                            if(tower==player.getKingTower())
                            {
                                if(attackCard.getY_Current() <180)
                                {
                                    ((Troop) attackCard).Right(tower.getImageViews()[0][0].getX()-(attackCard.getX_Current()+blockSize));
                                }
                                else
                                {
                                    ((Troop) attackCard).Left(attackCard.getX_Current()-tower.getImageViews()[0][0].getX());
                                }
                            }
                            else
                            {
                                ((Troop) attackCard).Backward(tower.getImageViews()[0][0].getY() - (attackCard.getY_Current()+blockSize));
                            }
                        }
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
       //     }
        }
        else
        {
            return false;
        }
    }

    /**
     * set closest tower target for an attack card
     * @param attacker attackcard
     */
    private void prepareTowerTarget(AttackCard attacker)
    {
        if(!attacker.isLocked())
        {
            if(attacker.getType().equals("+"))
            {
                    if(opponent.getPrinceTower1().getLevelInformation().getHp()>0 && attackCardisinTowerArea(attacker,opponent.getPrinceTower1()))
                    {
                        attacker.setTowerTarget(opponent.getPrinceTower1());
                    }
                    else if(opponent.getPrinceTower2().getLevelInformation().getHp()>0 &&  attackCardisinTowerArea(attacker,opponent.getPrinceTower2()))
                    {
                        attacker.setTowerTarget(opponent.getPrinceTower2());
                    }
                    else if(opponent.getKingTower().getLevelInformation().getHp()>0 &&  attackCardisinTowerArea(attacker,opponent.getKingTower()))
                    {
                        attacker.setTowerTarget(opponent.getKingTower());
                    }

            }
            else
            {
                    if(player.getPrinceTower1().getLevelInformation().getHp()>0 && attackCardisinTowerArea(attacker,player.getPrinceTower1()))
                    {
                        attacker.setTowerTarget(player.getPrinceTower1());
                    }
                    else if(player.getPrinceTower2().getLevelInformation().getHp()>0 && attackCardisinTowerArea(attacker,player.getPrinceTower2()))
                    {
                        attacker.setTowerTarget(player.getPrinceTower2());
                    }
                    else if(player.getKingTower().getLevelInformation().getHp()>0 && attackCardisinTowerArea(attacker,player.getKingTower()))
                    {
                        attacker.setTowerTarget(player.getKingTower());
                    }
            }
        }
    }

    /**
     * reset ground blocks images if a pricetower is dead
     * @param princeTower dead prince tower
     */
    private void princeTowerDeath(PrinceTower princeTower)
    {
        if(princeTower.getType().equals("+"))
        {
            player.getKingTower().setCanShoot(true);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    resetTowersBlocks(princeTower.getImageViews()[i][j].getX(),princeTower.getImageViews()[i][j].getY());
                }
            }
        }
        else
        {
            opponent.getKingTower().setCanShoot(true);
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    resetTowersBlocks(princeTower.getImageViews()[i][j].getX(),princeTower.getImageViews()[i][j].getY());
                }
            }
        }
    }

    /**
     * reset ground blocks images if a kingtower is dead
     * @param kingTower dead kingtower
     */
    private void kingTowerDeath(KingTower kingTower)
    {
        if(kingTower.getType().equals("+"))
        {
            this.gameFinished= true;
            this.winner = opponent;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    resetTowersBlocks(kingTower.getImageViews()[i][j].getX(),kingTower.getImageViews()[i][j].getY());
                }
            }
        }
        else
        {
            this.gameFinished= true;
            this.winner = player;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    resetTowersBlocks(kingTower.getImageViews()[i][j].getX(),kingTower.getImageViews()[i][j].getY());
                }
            }
        }
    }

    /**
     * reset a ground block image
     * @param x x coordinate
     * @param y y coordinate
     */
    private void resetTowersBlocks(double x , double y)
    {
        Image grass = new Image(new File("src/main/resources/pics/terrainTile3.png").toURI().toString());
        Image grass2 = new Image(new File("src/main/resources/pics/lightGreenBlock.png").toURI().toString());
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                if((i%2==1 && j%2==1) || (i%2==0 && j%2==0))
                {
                    if(blocks[i][j].getX()==x && blocks[i][j].getY()==y)
                    {
                        blocks[i][j].setImage(grass2);
                        break;
                    }
                }
                else if((i%2 == 0 && j%2 ==1) || (i%2==1 && j%2 == 0 ))
                {
                    if(blocks[i][j].getX()==x && blocks[i][j].getY()==y)
                    {
                        blocks[i][j].setImage(grass);
                        break;
                    }
                }
            }
        }
    }

    /**
     * update winner (first check number of won crowns if equals -> check towers health)
     */
    private void updateWinner()
    {
        if(player.getCrownsWon()>opponent.getCrownsWon())
        {
            winner= player;
        }
        else if(opponent.getCrownsWon()== player.getCrownsWon())
        {
            if(towersHealthSum(player) > towersHealthSum(opponent))
            {
                winner=player;
            }
            else
            {
                winner=opponent;
            }
        }
        else
        {
            winner=opponent;
        }
    }

    /**
     *
     * @param player player
     * @return sum of all player's alive tower's health
     */
    private double towersHealthSum(Player player)
    {
        double temp = 0.0;
        if(player.getPrinceTower1().getLevelInformation().getHp()>0)
        {
            temp+=player.getPrinceTower1().getLevelInformation().getHp();
        }
        if(player.getPrinceTower2().getLevelInformation().getHp()>0)
        {
            temp+=player.getPrinceTower2().getLevelInformation().getHp();
        }
        if(player.getKingTower().getLevelInformation().getHp()>0)
        {
            temp+=player.getKingTower().getLevelInformation().getHp();
        }
        return temp;
    }

    /**
     * spells effect
     */
    private void spellsImpact()
    {
        for (int i = 0; i < spells.size(); i++) {
            if(spells.get(i) instanceof Rage )
            {
                Rage temp = (Rage) spells.get(i);
                if(!temp.isUsed())
                {
                    rageSpellPurpleBlocks(temp);
                    prepareTargetsforSpell(temp);
                    temp.rageThread();
                }
            }
            else
            {
                prepareTargetsforSpell(spells.get(i));
                if(spells.get(i) instanceof Arrows)
                {
                    Arrows temp = (Arrows) spells.get(i);
                    if(!temp.isUsed())
                    {
                        bullets.add(temp.getArrows());
                        temp.arrowThread();
                    }
                }
                else
                {
                    Fireball temp = (Fireball) spells.get(i);
                    if(!temp.isUsed())
                    {
                        bullets.add(temp.getFireball());
                        temp.fireThread();
                    }
                }
            }
        }
    }
//    private void checkSpellsLife()
//    {
////        for (int i = 0; i < spells.size(); i++) {
////            if(spells.get(i) instanceof Rage)
////            {
////                Rage temp = (Rage) spells.get(i);
////                if(temp.isDone())
////                {
////                    resetBlocksImage(temp);
////                    removeSpell(spells.get(i));
////                }
////            }
////            else
////            {
////
////            }
////        }
//    }
//    private void Rage()
//    {
//        for (int i = 0; i < spells.size(); i++) {
//            if(spells.get(i) instanceof Rage)
//            {
//                Rage rage = (Rage) spells.get(i);
//                if(rage.isUsed())
//                {
//                    for (int j = 0; j < rage.getAttackCards().size(); j++) {
//                        if(rage.getAttackCards().get(i).isRaged())
//                        {
//                            rage.getAttackCards().get(i).rageImpact();
//                        }
//                    }
//                    for (int j = 0; j < rage.getTowers().size(); j++) {
//                        rage.getTowers().get(i).rageImpact();
//                    }
//                }
//            }
//        }
//    }

    /**
     * remove spells if they are finished
     */
    private void removeSpells()
    {
        Iterator<Spell> spellIterator = spells.iterator();
        while (spellIterator.hasNext()) {
            Spell spell = spellIterator.next();
            if (spell instanceof Arrows) {
                Arrows temp = (Arrows) spell;
                if(temp.isDone())
                {
                    Iterator<Shape> bulletsIterator = bullets.iterator();
                    while (bulletsIterator.hasNext()) {
                        Shape help = bulletsIterator.next();
                        if (help == temp.getArrows()) {
                            bulletsIterator.remove();
                            break;
                        }
                    }
                    spellIterator.remove();
                }
            } else if (spell instanceof Fireball) {
                Fireball temp = (Fireball) spell;
                if(temp.isDone())
                {
                    Iterator<Shape> bulletsIterator = bullets.iterator();
                    while (bulletsIterator.hasNext()) {
                        Shape help = bulletsIterator.next();
                        if (help == temp.getFireball()) {
                            bulletsIterator.remove();
                            break;
                        }
                    }
                    spellIterator.remove();
                }
            }
            else
            {
                Rage temp = (Rage) spell;
                if(temp.isDone())
                {
                    resetBlocksImage(temp);
//                    for (int i = 0; i < temp.getTowers().size(); i++) {
//                        temp.getTowers().get(i).setRaged(false);
//                        temp.getTowers().get(i).undoRage();
//                    }
//                    for (int i = 0; i < temp.getAttackCards().size(); i++) {
//                        temp.getAttackCards().get(i).setRaged(false);
//                        temp.getAttackCards().get(i).undoRage();
//                    }
                    road();
                    river();
                    spellIterator.remove();
                }
            }
        }
//        for (int i = 0; i < troops.size(); i++) {
//            if(!troops.get(i).isRaged())
//            {
//                troops.get(i).undoRage();
//            }
//        }
//        for (int i = 0; i <buildings.size(); i++) {
//            if(buildings.get(i).isRaged())
//            {
//                buildings.get(i).undoRage();
//            }
//        }
    }

    /**
     * make rage 's spell area blocks purple
     * @param rage rage spell
     */
    private void rageSpellPurpleBlocks(Rage rage)
    {
        Image purpleBlock = new Image(new File("src/main/resources/pics/purpleTile.png").toURI().toString());
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 18; j++) {
                if(distance(blocks[i][j].getX(),blocks[i][j].getY(),rage.getX(),rage.getY())<= rage.getRadius()*blockSize && blockisNotTower(blocks[i][j].getX(),blocks[i][j].getY()))
                {
                    blocks[i][j].setImage(purpleBlock);
                    ImageView temp = new ImageView(purpleBlock);
                    temp.setX(blocks[i][j].getX());
                    temp.setY(blocks[i][j].getY());
                    rage.getGroundImages().add(temp);
                }
            }
        }
    }

    /**
     *  set closest elements for a spell
     * @param spell spell
     */
    private void prepareTargetsforSpell(Spell spell)
    {
        if(spell instanceof Rage)
        {
            Rage rage = (Rage) spell;
            for (int i = 0; i < troops.size(); i++) {
                if(troops.get(i).getType().equals(spell.getType()) && distance(troops.get(i).getX_Current(),troops.get(i).getY_Current(),spell.getX(),spell.getY())<= spell.getRadius()*blockSize)
                {
                    spell.getAttackCards().add(troops.get(i));
                    troops.get(i).setRage(rage);
                    troops.get(i).setRaged(true);
                    System.out.println(troops.get(i).getSpeed().getVelocity());
                    troops.get(i).rageImpact();
                    troops.get(i).setShootingTimeTick(0);
                    System.out.println(troops.get(i).getClass()+" is raged");
                    System.out.println(troops.get(i).getSpeed().getVelocity());
                }
            }
            for (int i = 0; i < buildings.size(); i++) {
                if(buildings.get(i).getType().equals(spell.getType()) && distance(buildings.get(i).getX_Current(),buildings.get(i).getY_Current(),spell.getX(),spell.getY())<= spell.getRadius()*blockSize)
                {
                    spell.getAttackCards().add(buildings.get(i));
                    buildings.get(i).setRage(rage);
                    buildings.get(i).setRaged(true);
                    buildings.get(i).rageImpact();
                    buildings.get(i).setShootingTimeTick(0);
                }
            }
            for (int i = 0; i <towers.size() ; i++) {
                if(towers.get(i).getType().equals(spell.getType()) && distance(towers.get(i).getX(),towers.get(i).getY(),spell.getX(),spell.getY())<= spell.getRadius()*blockSize)
                {
                    spell.getTowers().add(towers.get(i));
                    towers.get(i).setRage(rage);
                    towers.get(i).setRaged(true);
                    towers.get(i).rageImpact();
                    towers.get(i).setShootingTimeTick(0);
                }
            }
        }
        else
        {
            for (int i = 0; i < troops.size(); i++) {
                if(!troops.get(i).getType().equals(spell.getType()) && distance(troops.get(i).getX_Current(),troops.get(i).getY_Current(),spell.getX(),spell.getY())<= spell.getRadius()*blockSize)
                {
                    spell.getAttackCards().add(troops.get(i));
                }
            }
            for (int i = 0; i < buildings.size(); i++) {
                if(!buildings.get(i).getType().equals(spell.getType()) && distance(buildings.get(i).getX_Current(),buildings.get(i).getY_Current(),spell.getX(),spell.getY())<= spell.getRadius()*blockSize)
                {
                    spell.getAttackCards().add(buildings.get(i));
                }
            }
            for (int i = 0; i <towers.size() ; i++) {
                if(!towers.get(i).getType().equals(spell.getType()) && distance(towers.get(i).getX(),towers.get(i).getY(),spell.getX(),spell.getY())<= spell.getRadius()*blockSize)
                {
                    spell.getTowers().add(towers.get(i));
                }
            }
        }
    }

    /**
     * check if given x , y is not inside a tower 's territory
     * @param x x
     * @param y y
     * @return true or false
     */
    private boolean blockisNotTower(double x , double y)
    {
        if(player.getPrinceTower1().getLevelInformation().getHp()>0)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(player.getPrinceTower1().getImageViews()[i][j].getX()==x && player.getPrinceTower1().getImageViews()[i][j].getY()==y)
                    {
                        return false;
                    }
                }
            }
        }
        if(player.getPrinceTower2().getLevelInformation().getHp()>0)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(player.getPrinceTower2().getImageViews()[i][j].getX()==x && player.getPrinceTower2().getImageViews()[i][j].getY()==y)
                    {
                        return false;
                    }
                }
            }
        }
        if(opponent.getPrinceTower1().getLevelInformation().getHp()>0)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(opponent.getPrinceTower1().getImageViews()[i][j].getX()==x && opponent.getPrinceTower1().getImageViews()[i][j].getY()==y)
                    {
                        return false;
                    }
                }
            }
        }
        if(opponent.getPrinceTower2().getLevelInformation().getHp()>0)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(opponent.getPrinceTower2().getImageViews()[i][j].getX()==x && opponent.getPrinceTower2().getImageViews()[i][j].getY()==y)
                    {
                        return false;
                    }
                }
            }
        }
        if(player.getKingTower().getLevelInformation().getHp()>0)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(player.getKingTower().getImageViews()[i][j].getX()==x && player.getKingTower().getImageViews()[i][j].getY()==y)
                    {
                        return false;
                    }
                }
            }
        }
        if(opponent.getKingTower().getLevelInformation().getHp()>0)
        {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(opponent.getKingTower().getImageViews()[i][j].getX()==x && opponent.getKingTower().getImageViews()[i][j].getY()==y)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }


    /**
     * check if given x , y is not inside  river's territory
     * @param x x
     * @param y y
     * @return true or false
     */
    private boolean blockIsRiver(double x, double y)
    {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 16; j++) {
                if(river[i][j].getX()==x && river[i][j].getY()==y)
                {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * check if given x , y is not inside road's territory
     * @param x x
     * @param y y
     * @return true or false
     */
    private boolean blockIsRoad(double x , double y)
    {
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 2; j++) {
                if(roads[i][j].getX()== x && roads[i][j].getY()== y )
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * reset rage area blocks pic
     * @param rage rage spell
     */
    private void resetBlocksImage(Rage rage){
        for (int i = 0; i < rage.getGroundImages().size(); i++) {
            if(!blockIsRiver(rage.getGroundImages().get(i).getX(),rage.getGroundImages().get(i).getY()) && !blockIsRoad(rage.getGroundImages().get(i).getX(),rage.getGroundImages().get(i).getY()))
            {
                Image grass = new Image(new File("src/main/resources/pics/terrainTile3.png").toURI().toString());
                Image grass2 = new Image(new File("src/main/resources/pics/lightGreenBlock.png").toURI().toString());
                for (int j = 0; j < 32; j++) {
                    for (int k = 0; k < 18; k++) {
                        if((j%2==1 && k%2==1) || (j%2==0 && k%2==0))
                        {
                            if(blocks[j][k].getX()==rage.getGroundImages().get(i).getX() && blocks[j][k].getY()==rage.getGroundImages().get(i).getY())
                            {
                                blocks[j][k].setImage(grass2);
//                            blocks[j][k].setFitWidth(20);
//                            blocks[j][k].setFitHeight(20);
                            }
                        }
                        else
                        {
                            if(blocks[j][k].getX()==rage.getGroundImages().get(i).getX() && blocks[j][k].getY()==rage.getGroundImages().get(i).getY())
                            {
                                blocks[j][k].setImage(grass);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Gets spells.
     *
     * @return the spells
     */
    public ArrayList<Spell> getSpells() {
        return spells;
    }

    /**
     * Is valid location boolean.
     *
     * @param type the type
     * @param x    the x
     * @param y    the y
     * @return the boolean
     */
    public boolean isValidLocation(String type,double x,double y) {
        if (y > 300 && y <= 340) {
            return x > 40 && x < 60 || x > 300 && x < 320;
        } else {
            if (type.equals("player")) {
                if (y < 300 && y > 150) {
                    if (opponent.getPrinceTower1().getLevelInformation().getHp() <= 0&&x < 180) {
                        return true;
                    } else return opponent.getPrinceTower2().getLevelInformation().getHp() <= 0 && x > 180;
                }else if (y<150)
                    return false;
                else if (x > player.getPrinceTower1().getX() - 30 && x < player.getPrinceTower1().getX() + 30) {
                    return !(y > player.getPrinceTower1().getY() - 30) || !(y < player.getPrinceTower1().getY() + 30);
                } else if (x > player.getPrinceTower2().getX() - 30 && x < player.getPrinceTower2().getX() + 30) {
                    return !(y > player.getPrinceTower2().getY() - 30) || !(y < player.getPrinceTower2().getY() + 30);
                } else if (x > player.getKingTower().getX() - 30 && x < player.getKingTower().getX() + 30) {
                    return !(y > player.getKingTower().getY() - 30) || !(y < player.getKingTower().getY() + 30);
                }
            } else {
                if (y > 300 && y < 450) {
                    if (player.getPrinceTower1().getLevelInformation().getHp() <= 0&&x < 180) {
                        return true;
                    } else return player.getPrinceTower2().getLevelInformation().getHp() <= 0 && x > 180;
                } else if (y>450)
                    return false;
                else if (x > opponent.getPrinceTower1().getX() - 30 && x < opponent.getPrinceTower1().getX() + 30) {
                    return !(y > opponent.getPrinceTower1().getY() - 30) || !(y < opponent.getPrinceTower1().getY() + 30);
                } else if (x > opponent.getPrinceTower2().getX() - 30 && x < opponent.getPrinceTower2().getX() + 30) {
                    return !(y > opponent.getPrinceTower2().getY() - 30) || !(y < opponent.getPrinceTower2().getY() + 30);
                } else if (x > opponent.getKingTower().getX() - 30 && x < opponent.getKingTower().getX() + 30) {
                    return !(y > opponent.getKingTower().getY() - 30) || !(y < opponent.getKingTower().getY() + 30);
                }
            }

        }
        return true;
    }
}
