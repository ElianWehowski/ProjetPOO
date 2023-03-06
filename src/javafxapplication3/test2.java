package javafxapplication3;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class test2 extends GridPane {

    private Label lblNumPlayers;
    private TextField txtNumPlayers;
    private Label[] lblPlayerNames;
    private TextField[] txtPlayerNames;

    public test2() {
        super();

        // Initialize labels and text fields
        lblNumPlayers = new Label("Number of players:");
        txtNumPlayers = new TextField();
        txtNumPlayers.setPrefWidth(50);
        txtNumPlayers.setPromptText("Enter a number");

        // Add number of players input to the grid
        add(lblNumPlayers, 0, 0);
        add(txtNumPlayers, 1, 0);

        // Set up player name inputs
        lblPlayerNames = new Label[4];
        txtPlayerNames = new TextField[4];
        for (int i = 0; i < 4; i++) {
            lblPlayerNames[i] = new Label("Player " + (i+1) + " name:");
            txtPlayerNames[i] = new TextField();
            txtPlayerNames[i].setPromptText("Enter player name");

            // Add player name inputs to the grid
            add(lblPlayerNames[i], 0, i+1);
            add(txtPlayerNames[i], 1, i+1);

            // Hide player name inputs by default
            lblPlayerNames[i].setVisible(false);
            txtPlayerNames[i].setVisible(false);
        }

        // Set up listener for number of players input
        txtNumPlayers.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int numPlayers = Integer.parseInt(newValue);

                // Show player name inputs for the selected number of players
                for (int i = 0; i < numPlayers; i++) {
                    lblPlayerNames[i].setVisible(true);
                    txtPlayerNames[i].setVisible(true);
                }

                // Hide player name inputs for any additional players
                for (int i = numPlayers; i < 4; i++) {
                    lblPlayerNames[i].setVisible(false);
                    txtPlayerNames[i].setVisible(false);
                }
            } catch (NumberFormatException e) {
                // Input is not a valid number
                // Hide all player name inputs
                for (int i = 0; i < 4; i++) {
                    lblPlayerNames[i].setVisible(false);
                    txtPlayerNames[i].setVisible(false);
                }
            }
        });
    }

    public int getNumPlayers() {
        try {
            return Integer.parseInt(txtNumPlayers.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String[] getPlayerNames() {
        String[] names = new String[4];
        for (int i = 0; i < 4; i++) {
            names[i] = txtPlayerNames[i].getText();
        }
        return names;
    }
}