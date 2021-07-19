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
import services.GameManager;
import services.ViewService;

public class DeckController {
    GameManager gameManager= Main.gameManager;
    @FXML
    AnchorPane pageArea;
    @FXML
    HBox cardCollectionHBox;
    @FXML
    HBox deckHBox1;
    @FXML
    HBox deckHBox2;

    @FXML
    ScrollPane  cardCollectionScrollPane;
    public void initialize() {
        ViewService.setBackground(pageArea, "menubg1.jpg");
        cardCollectionHBox.setStyle("-fx-background-color:#341e16");
        deckHBox1.setStyle("-fx-background-color:#341e16");
        deckHBox2.setStyle("-fx-background-color:#341e16");
        cardCollectionScrollPane.setStyle("-fx-background-color:#341e16");
        setBattleDeck();
        setCollection();
    }

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
    void setCollection(){
        for (Card c:gameManager.getPlayer().getCards()){
            CollectionObject collectionObject=new CollectionObject(deckHBox1,deckHBox2,c);
            cardCollectionHBox.getChildren().add(collectionObject);
        }
    }
    @FXML
    public void back() throws Exception {
        if (deckHBox1.getChildren().stream().count()==4&&deckHBox2.getChildren().stream().count()==4){
            gameManager.setRoot("menu");
        }else
            System.out.println("complete your deck ");
    }
}
