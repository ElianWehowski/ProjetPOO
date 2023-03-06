/*
package javafxapplication3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryGame extends Application {

    private static final int GRID_SIZE = 4;
    private static final int CARD_SIZE = 100;
    private static final int PAIR_COUNT = GRID_SIZE * GRID_SIZE / 2;

    private List<Card> cards = new ArrayList<>();
    private int selectedCardIndex = -1;
    private int movesCount = 0;
    private int pairsFound = 0;
    private long startTime;

    private Label movesLabel = new Label("Moves: 0");
    private Label timeLabel = new Label("Time: 0 sec");
    private Label messageLabel = new Label("Click two cards to start the game");

    private Button newGameButton = new Button("New Game");
    private Button scoresButton = new Button("High Scores");

    private Player currentPlayer;

    private List<Player> players = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        // Create the game board
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        for (int i = 0; i < PAIR_COUNT; i++) {
            Card card1 = new Card(i);
            Card card2 = new Card(i);
            cards.add(card1);
            cards.add(card2);
        }

        Collections.shuffle(cards);

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                Card card = cards.get(i * GRID_SIZE + j);
                card.setPrefSize(CARD_SIZE, CARD_SIZE);
                card.setOnAction(event -> handleCardClick(card));
                gridPane.add(card, j, i);
            }
        }

        // Create the control panel
        HBox controlPane = new HBox(10);
        controlPane.setAlignment(Pos.CENTER);
        controlPane.getChildren().addAll(movesLabel, timeLabel, newGameButton, scoresButton);

        newGameButton.setOnAction(event -> startNewGame());
        scoresButton.setOnAction(event -> showHighScores());

        // Create the message panel
        VBox messagePane = new VBox(10);
        messagePane.setAlignment(Pos.CENTER);
        messagePane.getChildren().addAll(messageLabel);

        // Create the main pane
        VBox mainPane = new VBox(10);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(gridPane, messagePane, controlPane);

        // Create the scene and show the
        Scene scene = new Scene(mainPane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memory Game");
        primaryStage.show();

        startNewGame();
    }

    private void startNewGame() {
        movesCount = 0;
        pairsFound = 0;
        messageLabel.setText("Click two cards to start the game");
        startTime = System.currentTimeMillis();

        for (Card card : cards) {
            card.hide();
        }

        selectedCardIndex = -1;

        shuffleCards();

        // Ask for the player's name
        String name = TextInputDialog.showAndWait("Enter your name").orElse("Anonymous");
        currentPlayer = getPlayerByName(name);

        // Update the moves and time labels
        updateMovesLabel();
        updateTimeLabel();
    }

    private void handleCardClick(Card card) {
        int index = cards.indexOf(card);

        // Ignore clicks on already revealed cards
        if (card.isFlipped()) {
            return;
        }

        // Ignore clicks on more than two cards
        if (selectedCardIndex != -1 && index != selectedCardIndex) {
            return;
        }

        card.flip();

        if (selectedCardIndex == -1) {
            selectedCardIndex = index;
        } else {
            if (cards.get(selectedCardIndex).getValue() == card.()) {
                // Cards match, keep them revealed
                pairsFound++;
                messageLabel.setText("Match found!");
                currentPlayer.addScore();
            } else {
                // Cards don't match, hide them again
                messageLabel.setText("No match, try again");
                hideCards(selectedCardIndex, index);
            }

            selectedCardIndex = -1;
            movesCount++;
            updateMovesLabel();

            if (pairsFound == PAIR_COUNT) {
                // Game is won
                long elapsedTime = System.currentTimeMillis() - startTime;
                messageLabel.setText("Congratulations! You won the game in " + elapsedTime / 1000 + " seconds with " + movesCount + " moves.");

                currentPlayer.setTime(elapsedTime);
                currentPlayer.setMoves(movesCount);

                showHighScores();
            }
        }
    }

    private void updateMovesLabel() {
        movesLabel.setText("Moves: " + movesCount);
    }

    private void updateTimeLabel() {
        new Thread(() -> {
            while (pairsFound < PAIR_COUNT) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                timeLabel.setText("Time: " + elapsedTime / 1000 + " sec");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void shuffleCards() {
        Collections.shuffle(cards);
    }

    private void hideCards(int index1, int index2) {
        Card card1 = cards.get(index1);
        Card card2 = cards.get(index2);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            card1.hide();
            card2.hide();
        }).start();
    }

    private Player getPlayerByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }

        Player player = new Player(name);
        players.add(player);
        return player;
    }

    private void showHighScores() {
        // Create a new window with the high scores table
        Stage stage = new Stage();
        stage.setTitle("High Scores");

        VBox pane = new VBox(
                pane.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("High Scores");
        titleLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 20));

        TableView<Player> tableView = new TableView<>();
        tableView.setEditable(false);

        TableColumn<Player, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setSortable(false);

        TableColumn<Player, Integer> scoreColumn = new TableColumn<>("Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        scoreColumn.setSortable(false);

        TableColumn<Player, Long> timeColumn = new TableColumn<>("Time (sec)");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeColumn.setSortable(false);

        TableColumn<Player, Integer> movesColumn = new TableColumn<>("Moves");
        movesColumn.setCellValueFactory(new PropertyValueFactory<>("moves"));
        movesColumn.setSortable(false);

        tableView.getColumns().addAll(nameColumn, scoreColumn, timeColumn, movesColumn);
        tableView.getItems().addAll(players);

        pane.getChildren().addAll(titleLabel, tableView);

        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/
