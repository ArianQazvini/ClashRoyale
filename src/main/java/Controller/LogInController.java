package Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Player;

import sample.Main;

public class LogInController {
    Player player = Main.player;
    @FXML
    TextField nameText;
    @FXML
    TextField passwordText;

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



