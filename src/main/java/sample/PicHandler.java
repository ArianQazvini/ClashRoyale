package sample;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.File;

public class PicHandler extends Rectangle {
    private Fighter owner;
    private String walkingPic;
    private static int index=0;
    private String id ;
    public PicHandler(Fighter owner, String walkingPic) {
        this.owner = owner;
        this.walkingPic = walkingPic;
        index++;
        id = "Pic"+index;
    }

    public void setWalkingMode() {
        Image image = new Image(new File(walkingPic).toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.setFill(imagePattern);
        super.setWidth(20);
        super.setHeight(20);
    }

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object o)
    {
        if(o==this)
        {
            return true;
        }
        else if(!(o instanceof PicHandler))
        {
            return false;
        }
        else
        {
            PicHandler temp  = (PicHandler) o;
            if(temp.getid().equals(this.getid()))
                return true;
            else
            {
                return false;
            }
        }
    }
    public void setOwner(Fighter owner) {
        this.owner = owner;
    }
    public Fighter getOwner() {
        return owner;
    }
}
