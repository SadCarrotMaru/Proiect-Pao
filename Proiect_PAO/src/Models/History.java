package Models;

public class History {
    private int userId;
    private int levelId;
    private int score;
    private int choiceId;

    public History(int userId, int levelId, int score, int choiceId) {
        this.userId = userId;
        this.levelId = levelId;
        this.score = score;
        this.choiceId = choiceId;
    }

    public int getUserId() {return this.userId;}
    public int getLevelId() {return this.levelId;}
    public int getScore() {return this.score;}
    public int getChoiceId() {return this.choiceId;}
    public void setScore(int score) {this.score = score;}

    @Override
    public String toString()
    {
        return "History [userId: " + this.userId + ", levelId: " + this.levelId + ", score: " + this.score + ", choiceId: " + this.choiceId + "]\n";
    }
}