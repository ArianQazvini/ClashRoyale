package Controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Card;
import model.CollectionObject;
import model.DeckObject;
import sample.Main;
import services.DatabaseSaving;
import services.GameManager;
import services.ViewService;

/**
 * The type Deck controller.
 */
public class DeckController {
    /**
     * The Database saving.
     */
    DatabaseSaving databaseSaving=Main.databaseSaving;
    /**
     * The Game manager.
     */
    GameManager gameManager= Main.gameManager;
    /**
     * The Page area.
     */
    @FXML
    AnchorPane pageArea;
    /**
     * The Card collection h box.
     */
    @FXML
    HBox cardCollectionHBox;
    /**
     * The Deck h box 1.
     */
    @FXML
    HBox deckHBox1;
    /**
     * The Deck h box 2.
     */
    @FXML
    HBox deckHBox2;
    /**
     * The Card collection scroll pane.
     */
    @FXML
    ScrollPane  cardCollectionScrollPane;

    /**
     * Initialize.
     */
    public void initialize() {
        ViewService.setBackground(pageArea, "menubg1.jpg");
        cardCollectionHBox.setStyle("-fx-background-color:#341e16");
        deckHBox1.setStyle("-fx-background-color:#341e16");
        deckHBox2.setStyle("-fx-background-color:#341e16");
        cardCollectionScrollPane.setStyle("-fx-background-color:#341e16");
        setBattleDeck();
        setCollection();
    }

    /**
     * Set battle deck.
     */
    void setBattleDeck(){
        for (Card c:gameManager.getPlayer().getDeck().getCards()){
            DeckObject deckObject=new DeckObject(cardCollectionHBox,deckHBox1,deckHBox2,c);

            if (deckHBox1.getChildren().stream().count()<4){
                deckHBox1.getChildren().add(deckObject);
            }else if (deckHBox2.getChildren().stream().count()<4){
                deckHBox2.getChildren().add(deckObject);
            }
        }

    }

    /**
     * Set collection.
     */
    void setCollection(){
        for (Card c:gameManager.getPlayer().getCards()){
            CollectionObject collectionObject=new CollectionObject(deckHBox1,deckHBox2,c);
            cardCollectionHBox.getChildren().add(collectionObject);
        }
    }

    /**
     * Back.
     *
     * @throws Exception the exception
     */
    @FXML
    public void back() throws Exception {
        if (deckHBox1.getChildren().stream().count()==4&&deckHBox2.getChildren().stream().count()==4){
            databaseSaving.save();
            gameManager.setRoot("menu");
        }else
            System.out.println("complete your deck ");
    }
}
