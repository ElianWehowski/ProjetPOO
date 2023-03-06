/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafxapplication3.miscs.JoueursBox;
import javafxapplication3.miscs.MemoryGameBoard;


/**
 * @author Elian
 */
public class JavaFXApplication3 extends Application {

    int currentTurn = 0;

    @Override
    public void start(Stage primaryStage) {
        JoueursBox tt = new JoueursBox(); // Liste de joueur
        MemoryGameBoard mgb = new MemoryGameBoard(5, 6,tt); // Tableau de jeu


        Button btn = new Button();
        btn.setText("Joueur suivant");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                currentTurn++;
                System.out.println(currentTurn);
                if (currentTurn > 3)
                    currentTurn = 0;
                tt.setJoueurEnCours(currentTurn); // appel à la méthode setJoueurEnCours() pour changer la couleur de fond

            }
        });
        VBox droiteBas = new VBox();
        HBox box = new HBox();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER_LEFT);

        GridPane root2 = new GridPane();
        root2.setAlignment(Pos.CENTER_RIGHT);

        VBox left = new VBox();
        left.alignmentProperty().set(Pos.CENTER_LEFT);
        left.getChildren().add(mgb);
        left.setLayoutX(20);

        root.getChildren().add(left);

        VBox right = new VBox();
        right.alignmentProperty().set(Pos.CENTER_RIGHT);
        right.setLayoutX(60);
        right.getChildren().add(tt);
        root2.getChildren().add(right);

        //    root.getChildren().add(mgb);
        droiteBas.getChildren().addAll(right, btn);

        box.getChildren().addAll(root, droiteBas);
        Scene scene = new Scene(box, 1000, 550);

        primaryStage.setTitle("Jeu du memory !");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
