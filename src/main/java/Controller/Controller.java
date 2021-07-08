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
import services.GameManager;
import sample.Fighter;
import sample.PicHandler;

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
    private HBox Deck;

    @FXML
    private Button Button1;

    @FXML
    private Button Button2;

    @FXML
    private Button Button3;

    @FXML
    private Button Button4;
    @FXML
    private ProgressBar ElxirBar;
    @FXML
    private TextField Elxirs;
    @FXML
    private TextField Warnings;
    @FXML
    private AnchorPane PlayGround;
    private Timer timer;
    private final int blockSize = 20;
    private final int rows =18;
    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[32][2];
    private ImageView[][] river = new ImageView[2][16];
    private GameManager gameManager;
    private ArrayList<Fighter> elements = new ArrayList<>();
    private int check =0;
    //    private ArrayList<Fighter> fighters = new ArrayList<>();
    public void initialize()
    {
        gameManager = new GameManager();
        gameManager.CreateMap();
        Warnings.setVisible(false);
        Image image = new Image(new File("src/main/resources/pics/Wizard.jpg").toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);
        Button1.setGraphic(imageView);
        CreateMap();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                StartTimer();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    @FXML
    void Press(ActionEvent event) {
        PlayGround.setDisable(false);
        PlayGround.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
                            if(event.getSource()==Button1)
                            {
                                Task(mouseEvent.getX(),mouseEvent.getY(),300);
                            }
                            else
                            {
                                Task(mouseEvent.getX(),mouseEvent.getY(),400);

                            }

                        }
                    });
                    thread.setDaemon(true);
                    thread.start();
                }
                PlayGround.setDisable(true);
            }
        });
    }
    private void ElxirStart()
    {

        Platform.runLater(new Runnable() {
            Timeline timeline1 =null;
            double i = 0.0;
            int j =0;
            @Override
            public void run() {
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),actionEvent -> {
                    Elxirs.setText(String.valueOf(j));
                    ElxirBar.setProgress(i);
                    i+=0.1;
                    j++;
                });
                timeline1= new Timeline(keyFrame);
                timeline1.setCycleCount(Timeline.INDEFINITE);
                timeline1.play();
            }

        });
    }
    private void ElxirThread()
    {
        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {
                ElxirStart();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    private void Task(double x,double y,int seconds)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Fighter fighter = new Fighter("Wizard",Speed.MEDIUM,"src/main/resources/pics/Wizard.jpg","src/main/resources/pics/Wizard.jpg");
                fighter.getPicHandler().setWalkingMode();
                FixLocation(fighter,x,y);
                fighter.setSeconds(seconds);
                gameManager.getFighters().add(fighter);
                PlayGround.getChildren().add(fighter.getPicHandler());
            }
        });
    }
    private void FixLocation(Fighter temp , double mouse_x,double mouse_y)
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
    private void CreateMap()
    {
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
                        PlayGround.getChildren().add(gameManager.getRoads()[i][index]);
                    }
                    else
                    {
                        PlayGround.getChildren().add(gameManager.getRiver()[index2][z]);
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
                        PlayGround.getChildren().add(gameManager.getRoads()[i][index]);
                    }
                    else
                    {
                        PlayGround.getChildren().add(gameManager.getBlocks()[i][j]);
                    }
                }
            }
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
        this.timer.schedule(timerTask,0,200);

    }
    private void update()
    {
        if(gameManager.getFighters().size()!=0)
        {
//            for (int i=0;i<gameManager.getFighters().size();i++)
//            {
//               PlayGround.getChildren().add(gameManager.getFighters().get(i).getPicHandler());
//            }

            gameManager.Move();
//            elements= gameManager.getFighters();
//            remove();
        }
    }
    private void remove()
    {
        Iterator<Node> it = PlayGround.getChildren().iterator();
        int z = 0;
        while (it.hasNext())
        {
            Node temp = it.next();
            if(temp instanceof PicHandler)
            {
                PicHandler temp2 = (PicHandler) temp;
                if(temp2.equals(elements.get(z).getPicHandler()))
                {
                    it.remove();
                    z++;
                }
                else
                {
                }
            }
            else
            {

            }
        }
        elements.clear();;
    }
}
