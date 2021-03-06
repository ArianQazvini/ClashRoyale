package services;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;

/**
 * The type View service.
 */
public class ViewService {
    /**
     * Set background.
     *
     * @param pane the pane
     * @param name the name
     */
    public static void setBackground(Pane pane,String name){
        Image img=new Image(new File("src/main/resources/pics/"+name).toURI().toString());
        BackgroundImage bgImg = new BackgroundImage(img,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
        pane.setBackground(new Background(bgImg));
    }

}
