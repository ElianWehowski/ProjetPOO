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
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * @author Elian
 */
public class JavaFXApplication3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        test2 tt = new test2();
        MemoryGameBoard mgb = new MemoryGameBoard(5, 6);

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
    HBox box = new HBox();
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER_LEFT);

        GridPane root2 = new GridPane();
        root2.setAlignment(Pos.CENTER_RIGHT);

        //      root.getChildren().add(btn);
        VBox left = new VBox();
        left.alignmentProperty().set(Pos.CENTER_LEFT);
        left.getChildren().add(mgb);
        left.setLayoutX(20);
        left.setBorder(new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        root.getChildren().add(left);

        VBox right = new VBox();
        right.alignmentProperty().set(Pos.CENTER_RIGHT);
        right.setLayoutX(60);
        right.getChildren().add(tt);
        right.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        root2.getChildren().add(right);

        //    root.getChildren().add(mgb);

        box.getChildren().addAll(root,root2);
        Scene scene = new Scene(box, 1000, 550);

        primaryStage.setTitle("Hello World!");
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
