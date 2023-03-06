package javafxapplication3.miscs;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Card extends ImageView {

    private int value;
    private boolean flipped;
    private boolean matched;
    private Image frontImage;
    private Image backImage;

    private static Card previousCard;
    private static int score = 0;

    public Card(int value) {
        super();

        // Initialize properties
        System.out.println(value);
        this.value = value;
        this.flipped = false;
        this.matched = false;
        this.frontImage = new Image(getClass().getResourceAsStream("/img/card"+value+".png"));
        this.backImage = new Image(getClass().getResourceAsStream("/img/cardback.png"));
        setImage(backImage);
        setFitWidth(100);
        setFitHeight(100);
        setPreserveRatio(true);

        // Set up event handler for card clicks
        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!flipped && !matched) {
                    flip();
                    System.out.println("On click sur une carte" );
                    if (previousCard == null) {
                        previousCard = Card.this;
                    } else if (!Card.this.matches(previousCard)) {
                        // Si les deux cartes ne correspondent pas, attendez 1 seconde avant de les retourner
                        PauseTransition pause = new PauseTransition(Duration.seconds(1));
                        pause.setOnFinished(e -> {
                            Card.this.flip();
                            if (previousCard != null) {
                                previousCard.flip();
                            }
                            previousCard = null;
                        });
                        pause.play();
                    } else {
                        Card.this.setMatched(true);
                        previousCard.setMatched(true);
                        score += 10;
                        previousCard = null;
                    }
                }
            }
        });
    }

    public boolean matches(Card other,Joueur joueurEnCours) {
        System.out.println("val carte 1 "+value+ " val carte 2 " + other.value);
        System.out.println(value == other.value);
        joueurEnCours.setScore(joueurEnCours.getScore()+5);

        return value == other.value;
    }
    public boolean matches(Card other) {
        System.out.println("val carte 1 "+value+ " val carte 2 " + other.value);
        System.out.println(value == other.value);

        return value == other.value;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public void flip() {
        if (!matched) {
            flipped = !flipped;
            setImage(flipped ? frontImage : backImage);
        }
    }

    public static int getScore() {
        return score;
    }

    public static void resetScore() {
        score = 0;
    }
}
