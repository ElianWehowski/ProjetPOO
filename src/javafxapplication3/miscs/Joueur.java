package javafxapplication3.miscs;

public class Joueur {
    private String prenom;
    private int score;

    public Joueur(String prenom, int score) {
        this.prenom = prenom;
        this.score = score;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void ajouterPoints(int points) {
        score += points;
    }
}
