package services;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Directions;
import model.Player;
import sample.Fighter;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class GameManager {
    private final int blockSize=20;
    private Player player=new Player();
    private Parent root;
    private String opponent;
    private Stage stage;
    private Scene scene;
    private final int rows =18;
    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[32][2];
    private ImageView[][] river = new ImageView[2][16];
    public Directions CharacterExist(Fighter fighter, Directions directions)
    {
        if(directions==Directions.TOP)
        {
            for (int i=0;i<player.getFighters().size();i++)
            {
                if(!player.getFighters().get(i).getPicHandler().equals(fighter.getPicHandler()))
                {
                    if(player.getFighters().get(i).getY_Current()- fighter.getY_Current()== -20 && player.getFighters().get(i).getX_Current()==fighter.getX_Current())
                    {
                        return Directions.TOP;
                    }
                }
            }
        }
        else if(directions==Directions.LEFT)
        {
            for (int i=0;i<player.getFighters().size();i++)
            {
                if(!player.getFighters().get(i).getPicHandler().equals(fighter.getPicHandler()))
                {
                    if(player.getFighters().get(i).getX_Current()- fighter.getX_Current()== -20 && player.getFighters().get(i).getY_Current()==fighter.getY_Current())
                    {
                        return Directions.LEFT;
                    }
                }
            }
        }
        else if(directions==Directions.RIGHT)
        {
            for (int i=0;i<player.getFighters().size();i++)
            {
                if(!player.getFighters().get(i).getPicHandler().equals(fighter.getPicHandler()))
                {
                    if(player.getFighters().get(i).getX_Current()- fighter.getX_Current()== 20 && fighter.getY_Current()==player.getFighters().get(i).getY_Current())
                    {
                        return Directions.RIGHT;
                    }
                }
            }
        }
        else if(directions==Directions.DOWN)
        {
            for (int i=0;i<player.getFighters().size();i++)
            {
                if(!player.getFighters().get(i).getPicHandler().equals(fighter.getPicHandler()))
                {
                    if(player.getFighters().get(i).getY_Current()- fighter.getY_Current()== 20 && player.getFighters().get(i).getX_Current()==fighter.getX_Current())
                    {
                        return Directions.DOWN;
                    }
                }
            }
        }
        return null;
    }

    public void setOpponent(String opponent) {
        this.opponent = opponent;
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
    public void add(Fighter fighter, AnchorPane anchorPane)
    {
        anchorPane.getChildren().add(fighter.getPicHandler());
    }
    public void Move()
    {
        for (int i=0;i<player.getFighters().size();i++)
        {
            if(player.getFighters().get(i).getY_Current()>0)
            {
                Directions help = this.CharacterExist(player.getFighters().get(i),Directions.TOP);
                if(help==null)
                {
                    player.getFighters().get(i).Forward();
                    //   Range(fighters.get(i));
                }
                else
                {
                    player.getFighters().get(i).Left();
                    // Range(fighters.get(i));
                }
            }
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
