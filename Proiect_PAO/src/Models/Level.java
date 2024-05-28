package Models;

public class Level {
    private int levelId;
    private String levelDescription;
    private String specialKeyIdentifier;

    public Level(int levelId, String levelDescription, String specialKeyIdentifier) {
        this.levelId = levelId;
        this.levelDescription = levelDescription;
        this.specialKeyIdentifier = specialKeyIdentifier;
    }

    public int getLevelId() {return this.levelId;}
    public String getLevelDescription() {return this.levelDescription;}
    public String getSpecialKeyIdentifier() {return this.specialKeyIdentifier;}
    public void setLevelId(int levelId) {this.levelId = levelId;}
    public void setLevelDescription(String levelDescription) {this.levelDescription = levelDescription;}
    public void setSpecialKeyIdentifier(String specialKeyIdentifier) {this.specialKeyIdentifier = specialKeyIdentifier;}


    @Override
    public String toString()
    {
        return "Level [levelId=" + levelId + ", levelDescription=" + levelDescription + ", specialKeyIdentifier=" + specialKeyIdentifier + "]\n";
    }
}