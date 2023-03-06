package javafxapplication3;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafxapplication3.miscs.Joueur;

public class JoueursBox extends VBox {

    private static final int NUM_JOUEURS = 4;

    private Joueur[] joueurs;

    public JoueursBox() {
        super();
        // Création des joueurs
        joueurs = new Joueur[NUM_JOUEURS];
        for (int i = 0; i < NUM_JOUEURS; i++) {
            joueurs[i] = new Joueur("Joueur " + (i+1), 0);
        }

        // Création des éléments de la boîte
        Label titreLabel = new Label("Liste des joueurs");
        titreLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        for (int i = 0; i < NUM_JOUEURS; i++) {
            final int index = i;
            Label nomLabel = new Label("Nom :");
            TextField nomTextField = new TextField(joueurs[index].getPrenom());
            nomTextField.setOnAction(event -> {
                joueurs[index].setPrenom(nomTextField.getText());
            });
            Label scoreLabel = new Label("Score :");
            Label scoreValueLabel = new Label(Integer.toString(joueurs[index].getScore()));
            gridPane.add(nomLabel, 0, i);
            gridPane.add(nomTextField, 1, i);
            gridPane.add(scoreLabel, 2, i);
            gridPane.add(scoreValueLabel, 3, i);
        }

        this.getChildren().addAll(titreLabel, gridPane);
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    /**
     * Change la couleur de fond du joueur en cours de jeu
     * @param index l'indice du joueur en cours de jeu
     */
    public void setJoueurEnCours(int index) {
        for (int i = 0; i < NUM_JOUEURS; i++) {
            if (i == index) {
                ((TextField) ((GridPane) this.getChildren().get(1)).getChildren().get(i * 4 + 1)).setStyle("-fx-control-inner-background: green;");
            } else {
                ((TextField) ((GridPane) this.getChildren().get(1)).getChildren().get(i * 4 + 1)).setStyle("-fx-control-inner-background: white;");
            }
        }
    }
}
