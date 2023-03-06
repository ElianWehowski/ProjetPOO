package javafxapplication3.miscs;

public class Player {
    private final String name;
    private int score;
    private long time;
    private int moves;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.time = 0;
        this.moves = 0;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public long getTime() {
        return time;
    }

    public int getMoves() {
        return moves;
    }

    public void addScore() {
        score += 10;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }
}
