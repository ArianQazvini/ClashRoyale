package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.Player;

import sample.Main;
import services.DatabaseSaving;
import services.GameManager;
import services.ViewService;

import java.io.File;
import java.sql.SQLException;

public class LogInController {
    DatabaseSaving databaseSaving = Main.databaseSaving;
    GameManager gameManager = Main.gameManager;
    Player player = gameManager.getPlayer();
    @FXML
    TextField nameText;
    @FXML
    PasswordField passwordText;
    @FXML
    Button signUpButton;
    @FXML
    Button signInButton;
    @FXML
    AnchorPane pageArea;

    @FXML
    public void signIn() {
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
        if (player.getName() != null && player.getPassword() != null) {
            try {
                databaseSaving.logIn(player.getName(), player.getPassword());
                if (databaseSaving.importValues())
                    gameManager.setRoot("menu");
                else
                    System.out.println("wrong information");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void initialize() {
        gameManager.getPlayer().getDeck().getCards().removeAll(gameManager.getPlayer().getDeck().getCards());
        databaseSaving.startConnection();
        ViewService.setBackground(pageArea, "start_page.jpg");
        signUpButton.setStyle("-fx-background-color:#48cf01");
        signInButton.setStyle("-fx-background-color:#ffb700");


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

    @FXML
    public void signUp() {
        try {

            if (!passwordText.getText().isBlank() && !nameText.getText().isBlank()) {
                try {
                    databaseSaving.signUp(nameText.getText(), passwordText.getText());
                    gameManager.setRoot("menu");
                } catch (SQLException q) {
                    System.out.println(q);
                }
            }
            setBorderName();
            setBorderPass();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




