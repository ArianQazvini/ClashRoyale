package model;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.io.File;

/**
 * The type Pic handler.
 */
public class PicHandler extends Rectangle {
    private Card owner;
    private String walkingPic;
    private static int index=0;
    private String id ;

    /**
     * Instantiates a new Pic handler.
     *
     * @param owner      the owner
     * @param walkingPic the walking pic
     */
    public PicHandler(Card owner, String walkingPic) {
        this.owner = owner;
        this.walkingPic = walkingPic;
        index++;
        id = "Pic"+index;
    }

    /**
     * Sets walking mode.
     */
    public void setWalkingMode() {
        Image image = new Image(new File(walkingPic).toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        super.setFill(imagePattern);
        super.setWidth(20);
        super.setHeight(20);
    }

    /**
     * Gets .
     *
     * @return the
     */
    public String getid() {
        return id;
    }

    /**
     * Sets .
     *
     * @param id the id
     */
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

    /**
     * Sets owner.
     *
     * @param owner the owner
     */
    public void setOwner(Card owner) {
        this.owner = owner;
    }

    /**
     * Gets owner.
     *
     * @return the owner
     */
    public Card getOwner() {
        return owner;
    }
}
