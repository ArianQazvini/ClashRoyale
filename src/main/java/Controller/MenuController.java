package Controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import services.ViewService;

public class MenuController {
    @FXML
    AnchorPane pageArea;

    public void initialize(){
        ViewService.setBackground(pageArea,"menubg1.jpg");
    }
}
