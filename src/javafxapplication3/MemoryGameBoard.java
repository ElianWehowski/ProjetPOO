package javafxapplication3;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafxapplication3.miscs.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGameBoard extends GridPane {

    private int numRows;
    private int numCols;
    private int numPairs;

    private List<Card> cards;
    private Card selectedCard;

    private int numMatches;

    public MemoryGameBoard(int numRows, int numCols) {
        super();

        // Initialize properties
        this.numRows = numRows;
        this.numCols = numCols;
        this.numPairs = (numRows * numCols) / 2;

        // Initialize cards list
        cards = new ArrayList<>();
        for (int i = 0; i < numPairs; i++) {
            cards.add(new Card(i));
            cards.add(new Card(i));
        }
        Collections.shuffle(cards);

        // Set up grid of cards
        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Card card = cards.get(row * numCols + col);
                add(card, col, row);
            }
        }

        // Set up event handler for card clicks
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getTarget() instanceof Card) {
                    Card card = (Card) event.getTarget();
                    if (!card.isMatched() && card != selectedCard) {
                        card.flip();
                        if (selectedCard == null) {
                            selectedCard = card;
                        } else {
                            if (selectedCard.matches(card)) {
                                selectedCard.setMatched(true);
                                card.setMatched(true);
                                numMatches++;
                                if (numMatches == numPairs) {
                                    Label label = new Label("Bravo c'est termine !");
                                    add(label, 0, numRows);
                                }
                            } else {
                                selectedCard.flip();
                                card.flip();
                            }
                            selectedCard = null;
                        }
                    }
                }
            }
        });
    }


}