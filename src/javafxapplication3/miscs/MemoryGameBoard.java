package javafxapplication3.miscs;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

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

    private JoueursBox joueursBox;
    private int joueurEnCours;

    public MemoryGameBoard(int numRows, int numCols, JoueursBox joueursBox) {
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
                            if (selectedCard.matches(card,joueursBox.getJoueurs()[joueurEnCours])) {
                                selectedCard.setMatched(true);
                                joueursBox.getJoueurs()[joueurEnCours].ajouterPoints(5);
                                card.setMatched(true);
                                numMatches++;
                                // Ajouter 5 points pour le joueur en cours
                                joueursBox.getJoueurs()[joueurEnCours].ajouterPoints(5);
                                joueursBox.setJoueurEnCours(joueurEnCours);
                                if (numMatches == numPairs) {
                                    Label label = new Label("Bravo c'est terminé !");
                                    add(label, 0, numRows);
                                }
                                if (selectedCard != null && selectedCard != card && selectedCard.getId() == card.getId()) {
                                    // Les cartes correspondent
                                    numMatches++;
                                    // Mise à jour du score du joueur en cours
                                    joueursBox.getJoueurs()[joueurEnCours].setScore(joueursBox.getJoueurs()[joueurEnCours].getScore() + 1);
                                    selectedCard.setDisable(true);
                                    card.setDisable(true);
                                    selectedCard = null;
                                    // Si toutes les paires ont été trouvées
                                    if (numMatches == numPairs) {
                                        // Affichage d'un message de victoire
                                        System.out.println("Vous avez gagné !");
                                    }
                                }
                            } else {
                                selectedCard.flip();
                                card.flip();
                                // Passer au joueur suivant
                                joueurEnCours = (joueurEnCours + 1) % joueursBox.getJoueurs().length;
                                joueursBox.setJoueurEnCours(joueurEnCours);
                            }
                            selectedCard = null;
                        }
                    }
                }
            }
        });

        // Initialize player turn
        this.joueursBox = joueursBox;
        this.joueurEnCours = 0;
        joueursBox.setJoueurEnCours(joueurEnCours);
    }
}
