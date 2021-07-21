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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.AttackCard;
import model.Building.Building;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Card;
import model.GameDeck;
import model.GameDeckObject;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Spell.Spell;
import model.Troop.*;
import model.Troop.Troop;
import model.Troop.Wizard;
import model.TimeWorks;
import sample.Main;
import services.GameManager;
import services.ViewService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    @FXML
    private ImageView nextCardImageView;
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
    @FXML
    private Text gameResult;
    @FXML
    private TextField minText;
    @FXML
    private TextField secondsText;
    @FXML
    private ImageView doubleElixirImage;
    @FXML
    private TextField RobotCrownsText;

    @FXML
    private TextField PlayerCrownsText;
    private GameDeck gameDeck;
    private Timer timer;
    private final int blockSize = 20;
    private final int rows =18;
    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[32][2];
    private ImageView[][] river = new ImageView[2][16];
    private GameManager gameManager= Main.gameManager;
    private TimeWorks gameTimer  = new TimeWorks();
    private boolean gameisFinished=false;
    public void initialize()
    {
        ViewService.setBackground(MainGround,"menubg1.jpg");
        doubleElixirImage.setVisible(false);
        gameManager.getPlayer().getElixir().setStyle("-fx-accent: purple");
        minText.setEditable(false);
        secondsText.setEditable(false);
        gameResult.setVisible(false);
       // gameManager.getOpponent().gameManager=gameManager;
        //ViewService.setBackground(MainGround, "jungle.jpg");
        //gameManager.getOpponent().gameManager=gameManager;
        gameManager.CreateMap();
        Warnings.setVisible(false);
        elixirHBox.getChildren().add(gameManager.getPlayer().getElixir());
        valueTextOfElixir.setText(String.valueOf(gameManager.getPlayer().getElixir().getValue()));
        gameDeck=new GameDeck(deckOfGameHBox,nextCardImageView);
        gameManager.getPlayer().getElixir().setValueText(valueTextOfElixir);
        setDeckOnClick();
        gameDeck.run();
        UpdatePage();
        StartTimer();
        startOpponent();
        gameTimer.gameTimer();
        setBinders();
    }
    private void setDeckOnClick(){
        for (GameDeckObject g:gameDeck.getGameDeckObjects()){
            g.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (gameDeck.isElixirEnough()) {
                        press(g.getCard(),g);
                    }else {
                        Warnings.setVisible(true);
                        Warnings.setStyle("-fx-text-inner-color:red");
                        Warnings.setText("elixir not enough");
                    }
                }

            });
        }
    }
    private void setBinders()
    {
        minText.textProperty().bind(gameTimer.minProperty().asString());
        secondsText.textProperty().bind(gameTimer.secsProperty().asString());
        PlayerCrownsText.textProperty().bind(gameManager.getPlayer().crownProperty().asString());
        RobotCrownsText.textProperty().bind(gameManager.getOpponent().crownProperty().asString());
    }
    @FXML
    void press(Card card,GameDeckObject g) {
        card.setType("+");
        playGround.setDisable(false);
        //valueTextOfElixir.setText(String.valueOf(gameManager.getPlayer().getElixir().getValue()));
        playGround.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!(card instanceof Spell) && (mouseEvent.getY()<=340.0 || !checkValidity(mouseEvent.getX(), mouseEvent.getY())))
                {
                    Warnings.setVisible(true);
                    Warnings.setStyle("-fx-text-inner-color:red");
                    Warnings.setText("NOT ALLOWED PLACE");
                }
                else
                {
                    Warnings.setVisible(false);
                    gameManager.getPlayer().setElixir(gameManager.getPlayer().getElixir().getValue()- card.getCost());
                    Task(mouseEvent.getX(),mouseEvent.getY(),card,g);
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
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
                    if(card instanceof Archer)
                    {
                        Archer temp1 = new Archer();
                        Archer temp2 = new Archer();
                        FixLocation(temp1,x,y);
                        FixLocation(temp2,x+2*blockSize,y);
                        if(card.getType().equals("+"))
                        {
                            temp1.setType("+");
                            temp1.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                            gameManager.getTroops().add(temp1);
                            playGround.getChildren().add(temp1.getPicHandler());
                            //-----------------------------
                            temp2.setType("+");
                            temp2.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                            gameManager.getTroops().add(temp2);
                            playGround.getChildren().add(temp2.getPicHandler());
                        }
                        else
                        {
                            temp1.setType("-");
                            temp1.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                            gameManager.getTroops().add(temp1);
                            playGround.getChildren().add(temp1.getPicHandler());
                            //-----------------------------
                            temp2.setType("-");
                            temp2.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                            gameManager.getTroops().add(temp2);
                            playGround.getChildren().add(temp2.getPicHandler());
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
                        Barbarian temp1 = new Barbarian();
                        Barbarian temp2 = new Barbarian();
                        Barbarian temp3 = new Barbarian();
                        Barbarian temp4 = new Barbarian();
                        FixLocation(temp1,x,y);
                        FixLocation(temp2,x+2*blockSize,y);
                        FixLocation(temp3,x,y+2*blockSize);
                        FixLocation(temp4,x+2*blockSize,y+2*blockSize);
                        if(card.getType().equals("+"))
                        {
                            temp1.setType("+");
                            temp1.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                            gameManager.getTroops().add(temp1);
                            playGround.getChildren().add(temp1.getPicHandler());
                            //--------------
                            temp2.setType("+");
                            temp2.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                            gameManager.getTroops().add(temp2);
                            playGround.getChildren().add(temp2.getPicHandler());
                            //----------------------------
                            temp3.setType("+");
                            temp3.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp3);
                            gameManager.getTroops().add(temp3);
                            playGround.getChildren().add(temp3.getPicHandler());
                            //---------------------------
                            temp4.setType("+");
                            temp4.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp4);
                            gameManager.getTroops().add(temp4);
                            playGround.getChildren().add(temp4.getPicHandler());
                            //--------------------------------
                        }
                        else
                        {
                            temp1.setType("-");
                            temp1.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                            gameManager.getTroops().add(temp1);
                            playGround.getChildren().add(temp1.getPicHandler());
                            //--------------
                            temp2.setType("-");
                            temp2.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                            gameManager.getTroops().add(temp2);
                            playGround.getChildren().add(temp2.getPicHandler());
                            //----------------------------
                            temp3.setType("-");
                            temp3.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp3);
                            gameManager.getTroops().add(temp3);
                            playGround.getChildren().add(temp3.getPicHandler());
                            //---------------------------
                            temp4.setType("-");
                            temp4.WalkingTopMode();
                            gameManager.getPlayer().getAttackCardsOnGround().add(temp4);
                            gameManager.getTroops().add(temp4);
                            playGround.getChildren().add(temp4.getPicHandler());
                            //--------------------------------
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
                    else if(card instanceof Rage)
                    {
                        Rage temp = new Rage();
                        temp.setX(x);
                        temp.setY(y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            //gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getSpells().add(temp);
                        }
                        else
                        {
                            temp.setType("-");
                            //gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getSpells().add(temp);
                            //playGround.getChildren().add(temp.getPicHandler());
                        }

                    }
                    else if(card instanceof Fireball)
                    {
                        Fireball temp = new Fireball();
                        temp.setX(x);
                        temp.setY(y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            //gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getSpells().add(temp);
                        }
                        else
                        {
                            temp.setType("-");
                            //gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getSpells().add(temp);
                            //playGround.getChildren().add(temp.getPicHandler());
                        }

                    }
                    else if(card instanceof Arrows)
                    {
                        Arrows temp = new Arrows();
                        temp.setX(x);
                        temp.setY(y);
                        if(card.getType().equals("+"))
                        {
                            temp.setType("+");
                            //gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                            gameManager.getSpells().add(temp);
                        }
                        else
                        {
                            temp.setType("-");
                            //gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                            gameManager.getSpells().add(temp);
                            //playGround.getChildren().add(temp.getPicHandler());
                        }

                    }
                    gameDeck.getGameDeckObjects().add(g);
                    deckOfGameHBox.getChildren().remove(g);
                    gameDeck.setNext();
//                }
//            });

       // }
    }
    private void TaskR(double x,double y,Card card,GameDeckObject g) {
        //if (!(card instanceof Spell)) {
//            Platform.runLater(new Runnable() {
//                @Override
//                public void run() {
        if(card instanceof Archer)
        {
            Archer temp1 = new Archer();
            Archer temp2 = new Archer();
            FixLocation(temp1,x,y);
            FixLocation(temp2,x+2*blockSize,y);
            if(card.getType().equals("+"))
            {
                temp1.setType("+");
                temp1.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                gameManager.getTroops().add(temp1);
                playGround.getChildren().add(temp1.getPicHandler());
                //-----------------------------
                temp2.setType("+");
                temp2.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                gameManager.getTroops().add(temp2);
                playGround.getChildren().add(temp2.getPicHandler());
            }
            else
            {
                temp1.setType("-");
                temp1.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                gameManager.getTroops().add(temp1);
                playGround.getChildren().add(temp1.getPicHandler());
                //-----------------------------
                temp2.setType("-");
                temp2.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                gameManager.getTroops().add(temp2);
                playGround.getChildren().add(temp2.getPicHandler());
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
            Barbarian temp1 = new Barbarian();
            Barbarian temp2 = new Barbarian();
            Barbarian temp3 = new Barbarian();
            Barbarian temp4 = new Barbarian();
            FixLocation(temp1,x,y);
            FixLocation(temp2,x+2*blockSize,y);
            FixLocation(temp3,x,y+2*blockSize);
            FixLocation(temp4,x+2*blockSize,y+2*blockSize);
            if(card.getType().equals("+"))
            {
                temp1.setType("+");
                temp1.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                gameManager.getTroops().add(temp1);
                playGround.getChildren().add(temp1.getPicHandler());
                //--------------
                temp2.setType("+");
                temp2.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                gameManager.getTroops().add(temp2);
                playGround.getChildren().add(temp2.getPicHandler());
                //----------------------------
                temp3.setType("+");
                temp3.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp3);
                gameManager.getTroops().add(temp3);
                playGround.getChildren().add(temp3.getPicHandler());
                //---------------------------
                temp4.setType("+");
                temp4.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp4);
                gameManager.getTroops().add(temp4);
                playGround.getChildren().add(temp4.getPicHandler());
                //--------------------------------
            }
            else
            {
                temp1.setType("-");
                temp1.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp1);
                gameManager.getTroops().add(temp1);
                playGround.getChildren().add(temp1.getPicHandler());
                //--------------
                temp2.setType("-");
                temp2.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp2);
                gameManager.getTroops().add(temp2);
                playGround.getChildren().add(temp2.getPicHandler());
                //----------------------------
                temp3.setType("-");
                temp3.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp3);
                gameManager.getTroops().add(temp3);
                playGround.getChildren().add(temp3.getPicHandler());
                //---------------------------
                temp4.setType("-");
                temp4.WalkingTopMode();
                gameManager.getPlayer().getAttackCardsOnGround().add(temp4);
                gameManager.getTroops().add(temp4);
                playGround.getChildren().add(temp4.getPicHandler());
                //--------------------------------
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
        else if(card instanceof Rage)
        {
            Rage temp = new Rage();
            temp.setX(x);
            temp.setY(x);
            if(card.getType().equals("+"))
            {
                temp.setType("+");
                //gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                gameManager.getSpells().add(temp);
            }
            else
            {
                temp.setType("-");
                //gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                gameManager.getSpells().add(temp);
                //playGround.getChildren().add(temp.getPicHandler());
            }

        }
        else if(card instanceof Fireball)
        {
            Fireball temp = new Fireball();
            temp.setX(x);
            temp.setY(x);
            if(card.getType().equals("+"))
            {
                temp.setType("+");
                //gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                gameManager.getSpells().add(temp);
            }
            else
            {
                temp.setType("-");
                //gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                gameManager.getSpells().add(temp);
                //playGround.getChildren().add(temp.getPicHandler());
            }

        }
        else if(card instanceof Arrows)
        {
            Arrows temp = new Arrows();
            temp.setX(x);
            temp.setY(x);
            if(card.getType().equals("+"))
            {
                temp.setType("+");
                //gameManager.getPlayer().getAttackCardsOnGround().add(temp);
                gameManager.getSpells().add(temp);
            }
            else
            {
                temp.setType("-");
                //gameManager.getOpponent().getAttackCardsOnGround().add(temp);
                gameManager.getSpells().add(temp);
                //playGround.getChildren().add(temp.getPicHandler());
            }

        }

//                }
//            });

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
                        break;
                    }
                }
            }
        }
    }
    private void UpdatePage()
    {
        playGround.getChildren().clear();
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j <18 ; j++) {
                playGround.getChildren().add(gameManager.getBlocks()[i][j]);
            }
        }
        for (int i=0;i<gameManager.getTroops().size();i++)
        {
            playGround.getChildren().add(gameManager.getTroops().get(i).getPicHandler());
        }
        for (int i = 0; i < gameManager.getBuildings().size(); i++) {
            playGround.getChildren().add(gameManager.getBuildings().get(i).getPicHandler());

        }
        for (int i = 0; i < gameManager.getBullets().size(); i++) {
            playGround.getChildren().add(gameManager.getBullets().get(i));
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
            if(!gameManager.isGameFinished() && !gameTimer.isGameTimesUp())
            {
                if(gameTimer.isDoubleElxirTime())
                {
                    gameManager.getPlayer().getElixir().setSleep(1000);
                    gameManager.getOpponent().getElixir().setSleep(1000);
                    doubleElixirImage.setVisible(true);
                }
                UpdatePage();
                gameManager.Step();
            }
            else
            {
                gameTimer.setGameTimesUp(true);
                gameisFinished=true;
                timer.cancel();
                gameManager.getPlayer().getElixir().setGameFinished(true);
                gameManager.getOpponent().getElixir().setGameFinished(true);
                if(gameManager.getWinner()== gameManager.getPlayer())
                {
                    gameResult.setVisible(true);
                    gameResult.setText("You won");
                    gameManager.getPlayer().win();
                    gameManager.getOpponent().lose();
                }
                else
                {
                    gameResult.setVisible(true);
                    gameResult.setText("Robot won");
                    gameManager.getOpponent().win();
                    gameManager.getPlayer().lose();
                }
            }
        }
    }
    void robotTask(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (gameManager.getOpponent().isElixirEnough()) {
                    gameManager.getOpponent().chooseLocation();
                    Card card = gameManager.getOpponent().chooseFromDeck();
                    card.setType("-");
                    if (card instanceof AttackCard) {
                        TaskR(gameManager.getOpponent().getX(), gameManager.getOpponent().getY(), (AttackCard) card, null);
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
                gameManager.getOpponent().setBattleDeck();
                while (!gameisFinished)
                {
                    robotTask();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

}
