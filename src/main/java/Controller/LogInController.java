package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.Player;

import sample.Main;
import services.GameManager;
import services.ViewService;

import java.io.File;

public class LogInController {
    GameManager gameManager=Main.gameManager;
    Player player= gameManager.getPlayer();
    @FXML
    TextField nameText;
    @FXML
    TextField passwordText;
    @FXML
    Button signUpButton;
    @FXML
    AnchorPane pageArea;
    @FXML
    public void signUp() {
        if (nameText != null) {
            if (!nameText.getText().isBlank()) {
                player.setName(nameText.getText());
            }
        }
        if (passwordText != null) {
            if (!passwordText.getText().isBlank()) {
                player.setPassword(passwordText.getText());
            }
        }
        setBorderName();
        setBorderPass();
        if (player.getName()!=null&&player.getPassword()!=null) {
            try {
                gameManager.setRoot("menu");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void initialize(){
        ViewService.setBackground(pageArea,"start_page.jpg");
        signUpButton.setStyle("-fx-background-color:#ffaa00");
//        ImageView buttBg=new ImageView(new Image(new File("src/main/resources/pics/20.png").toURI().toString()));
//        buttBg.setFitWidth(190);
//        buttBg.setFitHeight(30);
//        signUpButton.setGraphic(buttBg);
        //        BackgroundImage bgButt = new BackgroundImage(new Image(new File("src/main/resources/pics/20.png").toURI().toString()),
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.DEFAULT,
//                new BackgroundSize(190, 30, false, false, true, false));
//        signUpButton.setBackground(new Background(bgButt));


    }
    void setBorderName() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                    if (nameText.getText().isBlank()) {
                        nameText.setStyle("-fx-text-box-border:Red");
                    } else {
                        nameText.setStyle("-fx-text-box-border:Green");
                    }
                }

        });
    }

    void setBorderPass() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (passwordText.getText().isBlank()) {
                    passwordText.setStyle("-fx-text-box-border:Red");
                } else {
                    passwordText.setStyle("-fx-text-box-border:Green");
                }
            }

        });
    }

}



