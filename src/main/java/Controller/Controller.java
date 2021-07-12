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
import javafx.scene.text.Text;
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
    private HBox elixirHBox;
    @FXML
    private Text valueTextOfElixir;
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
        elixirHBox.getChildren().add(gameManager.getPlayer().getElixir());
        valueTextOfElixir.setText(String.valueOf(gameManager.getPlayer().getElixir().getValue()));
        gameDeck=new GameDeck(deckOfGameHBox);
        setDeckOnClick();
        gameDeck.run();
        UpdatePage();
        StartTimer();
        startOpponent();
    }
    private void setDeckOnClick(){
        for (GameDeckObject g:gameDeck.getGameDeckObjects()){
            g.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    press(g.getCard());
//                    gameDeck.getGameDeckObjects().add(g);
//                    deckOfGameHBox.getChildren().remove(g);
//                    gameDeck.run();
                }

            });
        }
    }
    @FXML
    void press(Card card) {
        System.out.println("it runs");
        playGround.setDisable(false);
        gameManager.getPlayer().setElixir(gameManager.getPlayer().getElixir().getValue()- card.getCost());
        valueTextOfElixir.setText(String.valueOf(gameManager.getPlayer().getElixir().getValue()));
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
    private void Task(double x,double y,Card card) {
        if (!(card instanceof Spell)) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    System.out.println("it runsss");
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
        for (int i=0;i<gameManager.getTroops().size();i++)
        {
            playGround.getChildren().add(gameManager.getTroops().get(i).getPicHandler());
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
    void robotTask(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (gameManager.getOpponent().isElixirEnough()) {
                    gameManager.getOpponent().chooseLocation();
                    Card card = gameManager.getOpponent().chooseFromDeck();
                    if (card instanceof AttackCard) {
                        FixLocation((AttackCard) card, gameManager.getOpponent().getX(), gameManager.getOpponent().getY());
                        gameManager.getOpponent().getAttackCardsOnGround().add((AttackCard) card);
                        playGround.getChildren().add(((AttackCard) card).getPicHandler());
                    }
                    gameManager.getOpponent().putCardOnGround(card);
                    gameManager.getOpponent().putDeck();
                }
            }
        });
    }
    void startOpponent(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                robotTask();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
