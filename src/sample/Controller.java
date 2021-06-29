package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.util.Callback;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;

public class Controller {

    @FXML
    private BorderPane MainGround;
    // private HBox Deck= new HBox(5);
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
    private AnchorPane PlayGround;
    private Timeline timeline;
    // private final ObservableList<Fighter> fighterObservableList= FXCollections.observableArrayList();
    public void initialize()
    {
//        fighterObservableList.add(new Fighter("Wizard",Speed.MID,"C:/Users/ASUS/Desktop/AP-HOMEWORKS/Final-Project/WizardCard.png","C:/Users/ASUS/Desktop/AP-HOMEWORKS/Final-Project/WizardCard.png"));
//        Deck.setItems(fighterObservableList);
//        Deck.getSelectionModel().selectedItemProperty().
//                addListener(
//                        new ChangeListener<Fighter>() {
//                            @Override
//                            public void changed(ObservableValue<? extends Fighter> ov,
//                                                Fighter oldValue, Fighter newValue) {
//                                temp.setImage(new Image(newValue.getAvatar()));
//                            }
//                        }
//                );
//        Deck.setCellFactory(
//                new Callback<ListView<Fighter>, ListCell<Fighter>>() {
//                    @Override
//                    public ListCell<Fighter> call(ListView<Fighter> listView) {
//                        return new Cell();
//                    }
//                }
//        );
        Fighter fighter = new Fighter("Wizard",Speed.MID,"Wizard.jpg","Wizard.jpg");

        Image image = new Image(new File("Wizard.jpg").toURI().toString());

        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(70);
        imageView.setFitWidth(90);
        imageView.setPreserveRatio(true);
        Button1.setGraphic(imageView);
        Button1.setText("Wizard");
    }
    private void add()
    {
        Fighter fighter = new Fighter("Wizard",Speed.MID,"Wizard.jpg","Wizard.jpg");
        Image image = new Image(new File("Wizard.jpg").toURI().toString());
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        Deck.getChildren().add(imageView);
        //"C:/Users/ASUS/Desktop/AP-HOMEWORKS/Final-Project/Wizard.jpg"
    }

    @FXML
    void Press(ActionEvent event) {
        PlayGround.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Task(mouseEvent.getX(),mouseEvent.getY());
                    }
                });
                thread.setDaemon(true);
                thread.start();

            }
        });
    }
    private void Task(double x,double y)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Fighter fighter = new Fighter("Wizard",Speed.MID,"Wizard.jpg","Wizard.jpg");
                Image image= new Image(new File("Wizard.jpg").toURI().toString());
                ImagePattern imagePattern = new ImagePattern(image);
                fighter.setFill(imagePattern);
                fighter.setHeight(90);
                fighter.setWidth(70);
                fighter.setX(x);
                fighter.setY(y);
//                Image image= new Image(new File("Wizard.jpg").toURI().toString());
//                ImageView imageView = new ImageView(image);
                PlayGround.getChildren().add(fighter);
                //imageView.setY(imageView.getY()+1);
                // }
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.2),event ->{
                    // imageView.setY(imageView.getY()+1);
                    fighter.setY(fighter.getY()+1);
                });
                timeline = new Timeline(keyFrame);
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }
        });
    }
}
