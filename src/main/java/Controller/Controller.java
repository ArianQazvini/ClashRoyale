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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.AttackCard;
import model.Building.Building;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Card;
import model.GameDeck;
import model.GameDeckObject;
import model.Spell.Spell;
import model.Troop.*;
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
    private Text Warnings;
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
    private int check =0;
    public void initialize()
    {
        gameManager.CreateMap();
        Warnings.setVisible(false);
       elixirHBox.getChildren().add(gameManager.getPlayer().getElixir());
        valueTextOfElixir.setText(String.valueOf(gameManager.getPlayer().getElixir().getValue()));
        gameDeck=new GameDeck(deckOfGameHBox);
        gameManager.getPlayer().getElixir().setValueText(valueTextOfElixir);
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
                    if (gameDeck.isElixirEnough()) {
                        press(g.getCard(),g);
                        gameDeck.getGameDeckObjects().add(g);
                    }else {
                        Warnings.setVisible(true);
                        Warnings.setStyle("-fx-text-inner-color:red");
                        Warnings.setText("elixir not enough");
                    }
                }

            });
        }
    }
    @FXML
    void press(Card card,GameDeckObject g) {
        card.setType("+");
        playGround.setDisable(false);
        //valueTextOfElixir.setText(String.valueOf(gameManager.getPlayer().getElixir().getValue()));
        playGround.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mouseEvent.getY()<=340.0 || !checkValidity(mouseEvent.getX(), mouseEvent.getY()))
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
                            gameManager.getPlayer().setElixir(gameManager.getPlayer().getElixir().getValue()- card.getCost());
                            Task(mouseEvent.getX(),mouseEvent.getY(),card,g);

                        }
                    });
                    thread.setDaemon(true);
                    thread.start();
                }
                playGround.setDisable(true);
            }
        });
    }
    private boolean checkValidity(double x,double y)
    {
        if( x<=80 && x>=20 && y<=620 && y>=560)
        {
            return false;
        }
        else  if( x<=340 && x>=280 && y<=620 && y>=560)
        {
            return false;
        }
        else  if( x<=200 && x>=140 && y<=640 && y>=580) {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void Task(double x,double y,Card card,GameDeckObject g) {
        //if (!(card instanceof Spell)) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    if(card instanceof Archer)
                    {
                        Archer temp = new Archer();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }

                    }
                    else if(card instanceof BabyDragon)
                    {
                        BabyDragon temp = new BabyDragon();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        };
                    }
                    else if(card instanceof Barbarian)
                    {
                        Barbarian temp = new Barbarian();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    else if(card instanceof Giant)
                    {
                        Giant temp = new Giant();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    else if(card instanceof MiniPEKKA)
                    {
                        MiniPEKKA temp = new MiniPEKKA();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    else if(card instanceof Valkyrie)
                    {
                        Valkyrie temp = new Valkyrie();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    else if(card instanceof Wizard)
                    {
                        Wizard temp = new Wizard();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.WalkingDownMode();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getTroops().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    else if(card instanceof InfernoTower)
                    {
                        InfernoTower temp = new InfernoTower();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getBuildings().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getBuildings().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    else if(card instanceof Cannon)
                    {
                        Cannon temp = new Cannon();
                        FixLocation(temp,x,y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            temp.UpPic();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getBuildings().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                        else
                        {
                            temp.setType("-");
                            temp.DownPic();
                            gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getBuildings().add(temp);
                            playGround.getChildren().add(temp.getPicHandler());
                        }
                    }
                    deckOfGameHBox.getChildren().remove(g);
                    gameDeck.run();

                }
            });

       // }
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
//        for (int i=0;i<32;i++)
//        {
//            if(i==15 || i==16)
//            {
//                int index2 ;
//                if(i==15)
//                {
//                    index2=0;
//                }
//                else
//                {
//                    index2=1;
//                }
//                int z=0;
//                for (int j=0;j<18;j++)
//                {
//                    if(j==2 || j== 15)
//                    {
//                        int index;
//                        if(j==2)
//                        {
//                            index=0;
//                        }
//                        else
//                        {
//                            index=1;
//                        }
//                        playGround.getChildren().add(gameManager.getRoads()[i][index]);
//                    }
//                    else
//                    {
//                        playGround.getChildren().add(gameManager.getRiver()[index2][z]);
//                        z++;
//                    }
//                }
//            }
//            else
//            {
//                for (int j=0;j<18;j++)
//                {
//                    if(j==2 || j== 15)
//                    {
//                        int index;
//                        if(j==2)
//                        {
//                            index=0;
//                        }
//                        else
//                        {
//                            index=1;
//                        }
//                        playGround.getChildren().add(gameManager.getRoads()[i][index]);
//                    }
//                    else
//                    {
//                        playGround.getChildren().add(gameManager.getBlocks()[i][j]);
//                    }
//                }
//            }
//        }
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j <18 ; j++) {
                playGround.getChildren().add(gameManager.getBlocks()[i][j]);
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
        if(gameManager.getTroops().size()!=0)
        {
            UpdatePage();
            gameManager.Step();
        }
    }
    void robotTask(){
        Platform.runLater(new Runnable() {
//            Timeline timeline1 =null;
//
//            @Override
//            public void run() {
//                KeyFrame keyFrame = new KeyFrame(Duration.seconds(15),actionEvent -> {
//                    if (gameManager.getOpponent().isElixirEnough()) {
//                        gameManager.getOpponent().chooseLocation();
//                        Card card = gameManager.getOpponent().chooseFromDeck();
//                        card.setType("-");
//                        if (card instanceof AttackCard) {
//                            Task( gameManager.getOpponent().getX(), gameManager.getOpponent().getY(),(AttackCard) card,null);
//                            gameManager.getOpponent().getElixir().setValue(gameManager.getOpponent().getElixir().getValue()-card.getCost());
//                        }
//                    }
//                });
//                timeline1= new Timeline(keyFrame);
//                timeline1.setCycleCount(Timeline.INDEFINITE);
//                timeline1.play();
            @Override
            public void run() {
                if (gameManager.getOpponent().isElixirEnough()) {
                    gameManager.getOpponent().chooseLocation();
                    Card card = gameManager.getOpponent().chooseFromDeck();
                    card.setType("-");
                    if (card instanceof AttackCard) {
                        Task(gameManager.getOpponent().getX(), gameManager.getOpponent().getY(), (AttackCard) card, null);
                        gameManager.getOpponent().getElixir().setValue(gameManager.getOpponent().getElixir().getValue() - card.getCost());
                    }
                }
            }
        });
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
    void startOpponent(){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    gameManager.getOpponent().setBattleDeck();
                    robotTask();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
