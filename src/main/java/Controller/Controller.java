package Controller;
import enums.Speed;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;;
import javafx.scene.layout.HBox;
import javafx.util.Duration;
import model.AttackCard;
import model.Building.Building;
import model.Card;
import model.GameDeck;
import model.GameDeckObject;
import model.Spell.Spell;
import model.Troop.Troop;
import model.Troop.Wizard;
import sample.Main;
import services.GameManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {

    @FXML
    private AnchorPane MainGround;
    private ImageView temp;
    @FXML
    private HBox deckOfGameHBox;

    @FXML
    private Button Button1;

    @FXML
    private Button Button2;

    @FXML
    private Button Button3;

    @FXML
    private Button Button4;
    @FXML
    private ProgressBar ElixirBar;
    @FXML
    private TextField Elixirs;
    @FXML
    private TextField Warnings;
    @FXML
    private AnchorPane playGround;
    private GameDeck gameDeck;
    private Timer timer;
    private final int blockSize = 20;
    private final int rows =18;
    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[32][2];
    private ImageView[][] river = new ImageView[2][16];
    private GameManager gameManager= Main.gameManager;
   // private ArrayList<Fighter> elements = new ArrayList<>();
    private int check =0;
    //    private ArrayList<Fighter> fighters = new ArrayList<>();
    public void initialize()
    {
        gameManager.CreateMap();
        Warnings.setVisible(false);
//        Image image = new Image(new File("src/main/resources/pics/Wizard.jpg").toURI().toString());
//        ImageView imageView = new ImageView(image);
//        imageView.setFitHeight(70);
//        imageView.setFitWidth(70);
//        imageView.setPreserveRatio(true);
//        Button1.setGraphic(imageView);
        gameDeck=new GameDeck(deckOfGameHBox);
        gameDeck.start();
        UpdatePage();
        StartTimer();
    }
    private void setDeckOnClick(){
        for (GameDeckObject g:gameDeck.getGameDeckObjects()){
            g.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    press(g.getCard());
                }
            });
        }
    }
    void press(Card card) {
        playGround.setDisable(false);
        playGround.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getY()<=340.0)
                {
                    Warnings.setVisible(true);
                    Warnings.setStyle("-fx-text-inner-color:red");
                    Warnings.setText("NOT ALLOWED PLACE");
                }
                else
                {
                    Warnings.setVisible(false);

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                                Task(mouseEvent.getX(),mouseEvent.getY(),card);

                        }
                    });
                    thread.setDaemon(true);
                    thread.start();
                }
                playGround.setDisable(true);
            }
        });
    }
    private void ElixirStart()
    {

        Platform.runLater(new Runnable() {
            Timeline timeline1 =null;
            double i = 0.0;
            int j =0;
            @Override
            public void run() {
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),actionEvent -> {
                    Elixirs.setText(String.valueOf(j));
                    ElixirBar.setProgress(i);
                    i+=0.1;
                    j++;

                });
                timeline1= new Timeline(keyFrame);
                timeline1.setCycleCount(Timeline.INDEFINITE);
                timeline1.play();
            }

        });
    }
    private void ElixirThread()
    {
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                ElixirStart();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    private void Task(double x,double y,Card card) {
        if (!(card instanceof Spell)) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    AttackCard tmp=(AttackCard)card;
                    FixLocation(tmp, x, y);
                    gameManager.getPlayer().getAttackCardsOnGround().add(tmp);
                    playGround.getChildren().add(tmp.getPicHandler());
                }
            });
        }
    }
    private void FixLocation(AttackCard temp , double mouse_x, double mouse_y)
    {
        int xPass = (int)mouse_x/blockSize;
        int yPass = (int)mouse_y/blockSize;
        for (int i=0;i<32;i++)
        {
            if(i==yPass)
            {
                for (int j=0;j<18;j++)
                {
                    if(j==xPass)
                    {
                        temp.setCurrent(j*blockSize,i*blockSize);
//                          temp.setX(j*blockSize);
//                          temp.setY(i*blockSize);
                        break;
                    }
                }
            }
        }
    }
    private void UpdatePage()
    {
        playGround.getChildren().clear();
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
                        playGround.getChildren().add(gameManager.getRoads()[i][index]);
                    }
                    else
                    {
                        playGround.getChildren().add(gameManager.getRiver()[index2][z]);
                        z++;
                    }
                }
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
                        playGround.getChildren().add(gameManager.getRoads()[i][index]);
                    }
                    else
                    {
                        playGround.getChildren().add(gameManager.getBlocks()[i][j]);
                    }
                }
            }
        }
        for (int i=0;i<gameManager.getPlayer().getTroops().size();i++)
        {
            playGround.getChildren().add(gameManager.getPlayer().getTroops().get(i).getPicHandler());
        }
    }
    private void StartTimer()
    {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        update();
                    }
                });
            }
        };
        this.timer.schedule(timerTask,0,100);

    }
    private void update()
    {
        if(gameManager.getPlayer().getTroops().size()!=0)
        {
            UpdatePage();
            gameManager.Move();
        }
    }

}
