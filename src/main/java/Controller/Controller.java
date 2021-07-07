package Controller;
import enums.Speed;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.util.Callback;
import javafx.util.Duration;
import sample.Fighter;

import java.io.File;
import java.text.DecimalFormat;

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
    private TextField Warnings;
    @FXML
    private AnchorPane PlayGround;
    private Timeline timeline;
    private final int blockSize = 20;
//    private final int rows =18;
//    private final int cols = 32;
    private ImageView[][] blocks = new ImageView[32][18];
    private ImageView[][] roads = new ImageView[32][2];
    private ImageView[][] river = new ImageView[2][16];
    public void initialize()
    {
        Warnings.setVisible(false);
        Image image = new Image(new File("src/main/resources/pics/Wizard.jpg").toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);
        Button1.setGraphic(imageView);
        CreateMap();
    }
//    private void add()
//    {
//        Fighter fighter = new Fighter("Wizard",Speed.MID,"src/main/resources/pics/Wizard.jpg","src/main/resources/pics/Wizard.jpg");
//        Image image = new Image(new File("src/main/resources/pics/Wizard.jpg").toURI().toString());
//        ImageView imageView = new ImageView(image);
//        imageView.setFitHeight(100);
//        imageView.setFitWidth(100);
//        Deck.getChildren().add(imageView);
//    }

    @FXML
    void Press(ActionEvent event) {
        if(event.getSource()==Button1)
        {
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
                                Task(mouseEvent.getX(),mouseEvent.getY());
                            }
                        });
                        thread.setDaemon(true);
                        thread.start();
                    }
                    PlayGround.setDisable(true);
                }
            });
        }
    }

    private void Task(double x,double y)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Fighter fighter = new Fighter("Wizard",Speed.MEDIUM,"src/main/resources/pics/Wizard.jpg","src/main/resources/pics/Wizard.jpg");
                Image image = new Image(new File("src/main/resources/pics/Wizard.jpg").toURI().toString());
                ImagePattern imagePattern = new ImagePattern(image);
                fighter.setFill(imagePattern);
                fighter.setHeight(20);
                fighter.setWidth(20);
                FixLocation(fighter,x,y);
                PlayGround.getChildren().add(fighter);
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3),event ->{
                    if(fighter.getY()>0)
                    {
                        fighter.setY(fighter.getY()-blockSize);

                    }
                });
                timeline = new Timeline(keyFrame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
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
                        temp.setX(j*blockSize);
                        temp.setY(i*blockSize);
                        break;
                    }
                }
            }
        }
    }
    private void CreateMap()
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
                        PlayGround.getChildren().add(roads[i][index]);
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
                        PlayGround.getChildren().add(river[index2][z]);
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
                        PlayGround.getChildren().add(roads[i][index]);
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
                        PlayGround.getChildren().add(blocks[i][j]);
                    }
                }
                help_col =0.0;
                help_row+= blockSize;
            }
        }
    }
}

