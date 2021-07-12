package services;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import model.Building.Building;
import model.Directions;
import model.Player;
import model.Tower.Tower;
import model.Troop.Troop;
import model.robot.Robot;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class GameManager {
    private final int blockSize=20;
    private Player player=new Player();
    private Robot opponent=new Robot();
    private Parent root;
    private Stage stage;
    private Scene scene;
    private final int rows =18;
    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[32][2];
    private ImageView[][] river = new ImageView[2][16];
    private ArrayList<Shape> bullets = new ArrayList<>();
    private ArrayList<Troop> troops = new ArrayList<>();
    private ArrayList<Tower>  towers= new ArrayList<>();
    private ArrayList<Building> buildings = new ArrayList<>();
    public Directions CharacterExist(Troop troop, Directions directions)
    {
        if(directions==Directions.TOP)
        {
            for (int i=0;i<player.getTroops().size();i++)
            {
                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
                {
                    if(player.getTroops().get(i).getY_Current()- troop.getY_Current()== -20 && player.getTroops().get(i).getX_Current()==troop.getX_Current())
                    {
                        return Directions.TOP;
                    }
                }
            }
        }
        else if(directions==Directions.LEFT)
        {
            for (int i=0;i<player.getTroops().size();i++)
            {
                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
                {
                    if(player.getTroops().get(i).getX_Current()- troop.getX_Current()== -20 && player.getTroops().get(i).getY_Current()==troop.getY_Current())
                    {
                        return Directions.LEFT;
                    }
                }
            }
        }
        else if(directions==Directions.RIGHT)
        {
            for (int i=0;i<player.getTroops().size();i++)
            {
                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
                {
                    if(player.getTroops().get(i).getX_Current()- troop.getX_Current()== 20 && troop.getY_Current()==player.getTroops().get(i).getY_Current())
                    {
                        return Directions.RIGHT;
                    }
                }
            }
        }
        else if(directions==Directions.DOWN)
        {
            for (int i=0;i<player.getTroops().size();i++)
            {
                if(!player.getTroops().get(i).getPicHandler().equals(troop.getPicHandler()))
                {
                    if(player.getTroops().get(i).getY_Current()- troop.getY_Current()== 20 && player.getTroops().get(i).getX_Current()==troop.getX_Current())
                    {
                        return Directions.DOWN;
                    }
                }
            }
        }
        return null;
    }

    public Robot getOpponent() {
        return opponent;
    }

    public void CreateMap()
    {
        double help_col = 0.0;
        double help_row = 0.0;
        for (int i=0;i<32;i++)
        {
            if(i==15 || i==16)
            {
                int index2 ;
                if(i==15)
                {
                    index2=0;
                }
                else
                {
                    index2=1;
                }
                int z=0;
                for (int j=0;j<18;j++)
                {
                    if(j==2 || j== 15)
                    {
                        int index;
                        if(j==2)
                        {
                            index=0;
                        }
                        else
                        {
                            index=1;
                        }
                        Image image = new Image(new File("src/main/resources/pics/roadWater.png").toURI().toString());
                        roads[i][index] = new ImageView(image);
                        roads[i][index].setFitWidth(20);
                        roads[i][index].setFitHeight(20);
                        roads[i][index].setY(help_row);
                        roads[i][index].setX(help_col);
                        help_col += blockSize;
                        // PlayGround.getChildren().add(roads[i][index]);
                    }
                    else
                    {
                        Image image = new Image(new File("src/main/resources/pics/terrainTile6.png").toURI().toString());
                        river[index2][z] = new ImageView(image);
                        river[index2][z].setFitWidth(20);
                        river[index2][z].setFitHeight(20);
                        river[index2][z].setY(help_row);
                        river[index2][z].setX(help_col);
                        help_col += blockSize;
                        //   PlayGround.getChildren().add(river[index2][z]);
                        z++;
                    }
                }
                help_col =0.0;
                help_row+= blockSize;
            }
            else
            {
                for (int j=0;j<18;j++)
                {
                    if(j==2 || j== 15)
                    {
                        int index;
                        if(j==2)
                        {
                            index=0;
                        }
                        else
                        {
                            index=1;
                        }
                        Image image = new Image(new File("src/main/resources/pics/roadTile.png").toURI().toString());
                        roads[i][index] = new ImageView(image);
                        roads[i][index].setFitWidth(20);
                        roads[i][index].setFitHeight(20);
                        roads[i][index].setY(help_row);
                        roads[i][index].setX(help_col);
                        help_col += blockSize;
                        //PlayGround.getChildren().add(roads[i][index]);
                    }
                    else
                    {
                        Image image = new Image(new File("src/main/resources/pics/terrainTile3.png").toURI().toString());
                        blocks[i][j] = new ImageView(image);
                        blocks[i][j].setFitWidth(20);
                        blocks[i][j].setFitHeight(20);
                        blocks[i][j].setY(help_row);
                        blocks[i][j].setX(help_col);
                        help_col += blockSize;
                        // PlayGround.getChildren().add(blocks[i][j]);
                    }
                }
                help_col =0.0;
                help_row+= blockSize;
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
        col=100;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                player.getKingTower().getImageViews()[i][j].setY(row);
                player.getKingTower().getImageViews()[i][j].setX(col);
                col+=20;
            }
            row+=20;
            col=100;
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
                fixblocks(player.getPrinceTower2().getImageViews()[i][j].getX(),player.getPrinceTower1().getImageViews()[i][j].getY(),player.getPrinceTower2().getImageViews()[i][j].getImage());
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
    //    public void add(Fighter fighter, AnchorPane anchorPane)
//    {
//        anchorPane.getChildren().add(fighter.getPicHandler());
//    }
    public void Move()
    {
        for (int i=0;i<this.getTroops().size();i++)
        {
           this.getTroops().get(i).Forward();
        }

    }
//    private void Range(Fighter fighter)
//    {
//        for (int i=0;i<32;i++)
//        {
//            for (int j=0;j<18;j++)
//            {
//                if(blocks[i][j].getX() -fighter.getX_Current()== -20 && blocks[i][j].getY()-fighter.getY_Current()==20)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== 0 && blocks[i][j].getY()-fighter.getY_Current()==20)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== 20 && blocks[i][j].getY()-fighter.getY_Current()==20)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== -20 && blocks[i][j].getY()-fighter.getY_Current()==0)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== 20 && blocks[i][j].getY()-fighter.getY_Current()==0)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== -20 && blocks[i][j].getY()-fighter.getY_Current()==20)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== 0 && blocks[i][j].getY()-fighter.getY_Current()==20)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//                else if(blocks[i][j].getX() -fighter.getX_Current()== 20 && blocks[i][j].getY()-fighter.getY_Current()==20)
//                {
//                    Image image = new Image(new File("src/main/resources/pics/gray.jpg").toURI().toString());
//                    blocks[i][j] = new ImageView(image);
//                    blocks[i][j].setFitHeight(20);
//                    blocks[i][j].setFitWidth(20);
//                }
//
//
//            }
//        }
//    }
}
