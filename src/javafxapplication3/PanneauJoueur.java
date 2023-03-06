package javafxapplication3;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class PanneauJoueur {
    private Label nbJoueursLabel;
    private TextField nbJoueursField;
    private Button validerButton;


    public void start(Stage primaryStage) throws Exception {
        nbJoueursLabel = new Label("Nombre de joueurs :");
        nbJoueursField = new TextField();
        validerButton = new Button("Valider");

        // Ajouter un gestionnaire d'événements pour le bouton "Valider"
        validerButton.setOnAction(e -> {
            String nbJoueursText = nbJoueursField.getText();
            if (nbJoueursText.matches("[0-4]+")) {
                int nbJoueurs = Integer.parseInt(nbJoueursText);
                // Faire quelque chose avec le nombre de joueurs entré
                System.out.println("Nombre de joueurs : " + nbJoueurs);
            } else {
                System.out.println("Veuillez entrer un nombre entier positif.");
            }
        });

        // Créer une grille pour disposer les éléments de l'interface
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(nbJoueursLabel, 0, 0);
        gridPane.add(nbJoueursField, 1, 0);
        gridPane.add(validerButton, 2, 0);

        // Créer une scène pour afficher la grille
        Scene scene = new Scene(gridPane, 300, 100);

        // Configurer la fenêtre principale
        primaryStage.setTitle("Nombre de joueurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
