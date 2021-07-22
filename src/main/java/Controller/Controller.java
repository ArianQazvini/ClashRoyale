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
import model.*;
import model.Building.Building;
import model.Building.Cannon;
import model.Building.InfernoTower;
import model.Spell.Arrows;
import model.Spell.Fireball;
import model.Spell.Rage;
import model.Spell.Spell;
import model.Troop.*;
import model.Troop.Troop;
import model.Troop.Wizard;
import model.TimeWorks;
import sample.Main;
import services.DatabaseSaving;
import services.GameManager;
import services.ViewService;

import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Game page controller
 * @author ArianQazvini- NegarAnabestani
 * @version 1.0
 */
public class Controller {
    /**
     * The Database saving.
     */
    DatabaseSaving databaseSaving=Main.databaseSaving;
    @FXML
    Button menu;
    @FXML
    AnchorPane winningPage;
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

    /**
     * Initialize method.
     */
    public void initialize()
    {
        gameManager.CreateMap();
        ViewService.setBackground(winningPage, "menubg1.jpg");
        ViewService.setBackground(MainGround,"menubg1.jpg");
        doubleElixirImage.setVisible(false);
        minText.setEditable(false);
        secondsText.setEditable(false);
        gameResult.setVisible(false);
        winningPage.setVisible(false);
        menu.setVisible(false);
       // gameManager.getOpponent().gameManager=gameManager;
        //ViewService.setBackground(MainGround, "jungle.jpg");
        //gameManager.getOpponent().gameManager=gameManager;
        Warnings.setVisible(false);
        gameManager.getPlayer().creatElixir();
        gameManager.getOpponent().creatElixir();
        gameManager.getPlayer().getElixir().setStyle("-fx-accent: purple");
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

    /**
     * deck clicking method
     */
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

    /**
     *  binding page's text fields to their values
     */
    private void setBinders()
    {
        minText.textProperty().bind(gameTimer.minProperty().asString());
        secondsText.textProperty().bind(gameTimer.secsProperty().asString());
        PlayerCrownsText.textProperty().bind(gameManager.getPlayer().crownProperty().asString());
        RobotCrownsText.textProperty().bind(gameManager.getOpponent().crownProperty().asString());
    }

    /**
     * Press a card.
     * check it's location validity then adds it to gameManger's arraylists
     * @param card the card
     * @param g    the g
     */
    @FXML
    void press(Card card,GameDeckObject g) {
        card.setType("+");
        playGround.setDisable(false);
        playGround.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!(card instanceof Spell) &&!gameManager.isValidLocation("player",mouseEvent.getX(), mouseEvent.getY()))
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

    /**
     * create an instance of selected card an add it to playground also fix it's type
     * @param x mouse x
     * @param y mouse y
     * @param card selected card
     * @param g g
     */
    private void Task(double x,double y,Card card,GameDeckObject g) {
                    if(card instanceof Archer)
                    {
                        Archer temp1 = new Archer();
                        Archer temp2 = new Archer();
                        FixLocation(temp1,x,y);
                        if(temp1.getX_Current()<20)
                        {
                            FixLocation(temp2,x+2*blockSize,y);

                        }
                        else if(temp1.getX_Current()>300)
                        {
                            FixLocation(temp2,x-2*blockSize,y);
                        }
                        else
                        {
                            FixLocation(temp2,x+2*blockSize,y);
                        }
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
                        if(temp1.getX_Current()<20)
                        {
                            FixLocation(temp2,temp1.getX_Current()+blockSize,y);
                            FixLocation(temp3,temp2.getX_Current()+blockSize,y);
                            FixLocation(temp4,temp3.getX_Current()+blockSize,y);
                        }
                        else if(temp1.getX_Current()>280)
                        {
                            FixLocation(temp2,temp1.getX_Current()-blockSize,y);
                            FixLocation(temp3,temp2.getX_Current()-blockSize,y);
                            FixLocation(temp4,temp3.getX_Current()-blockSize,y);
                        }
                        else
                        {
                            FixLocation(temp2,temp1.getX_Current()+blockSize,y);
                            FixLocation(temp3,temp2.getX_Current()+blockSize,y);
                            FixLocation(temp4,temp3.getX_Current()+blockSize,y);
                        }
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
    /**
     * (for robot)create an instance of selected card an add it to playground also fix it's type
     * @param x mouse x
     * @param y mouse y
     * @param card selected card
     * @param g g
     */
    private void TaskR(double x,double y,Card card,GameDeckObject g) {
        if(card instanceof Archer)
        {
            Archer temp1 = new Archer();
            Archer temp2 = new Archer();
            FixLocation(temp1,x,y);
            if(temp1.getX_Current()<20)
            {
                FixLocation(temp2,x+2*blockSize,y);

            }
            else if(temp1.getX_Current()>300)
            {
                FixLocation(temp2,x-2*blockSize,y);
            }
            else
            {
                FixLocation(temp2,x+2*blockSize,y);
            }
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
            FixLocation(temp1,x,y);
            if(temp1.getX_Current()<20)
            {
                FixLocation(temp2,temp1.getX_Current()+blockSize,y);
                FixLocation(temp3,temp2.getX_Current()+blockSize,y);
                FixLocation(temp4,temp3.getX_Current()+blockSize,y);
            }
            else if(temp1.getX_Current()>280)
            {
                FixLocation(temp2,temp1.getX_Current()-blockSize,y);
                FixLocation(temp3,temp2.getX_Current()-blockSize,y);
                FixLocation(temp4,temp3.getX_Current()-blockSize,y);
            }
            else
            {
                FixLocation(temp2,temp1.getX_Current()+blockSize,y);
                FixLocation(temp3,temp2.getX_Current()+blockSize,y);
                FixLocation(temp4,temp3.getX_Current()+blockSize,y);
            }
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
    }

    /**
     * this method fix attack card on a ground blocks
     * at it's first use
     * @param temp selected attack card
     * @param mouse_x mouse x
     * @param mouse_y mouse y
     */
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

    /**
     * this method clears every thing on playground
     * and refill with gameManager arraylists
     */
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

    /**
     * game loop - update page every 0.1 secs
     */
    private void StartTimer()
    {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            update();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        this.timer.schedule(timerTask,0,100);

    }

    /**
     *  update page
     *  and also check if game is finished or not
     * @throws Exception
     */
    private void update() throws Exception {
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
                    showResult(gameManager.getPlayer(),gameManager.getOpponent());
                }
                else
                {
                   showResult(gameManager.getOpponent(),gameManager.getPlayer());
                }
            }
        }
    }

    /**
     * Robot task. - choosing a card and location for it
     */
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
                Thread.sleep(gameManager.getOpponent().putSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    /**
     * Start opponent thread.
     */
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

    /**
     * Show results
     * @param winner winner
     * @param looser looser
     */
    private void showResult(Player winner,Player looser){
        gameResult.setVisible(true);
        winningPage.setVisible(true);
        menu.setVisible(true);
        gameResult.setText(winner.getName()+" wins !");
        winner.win();
        looser.lose();
        try {
            databaseSaving.addBattleHistory(new BattleHistory(winner.getName(), LocalDateTime.now().toString()));
        }catch (SQLException q){
            System.out.println(q);
        }
    }
    @FXML
    public void menuButton() throws Exception {
        gameManager.setRoot("menu");

    }
}
