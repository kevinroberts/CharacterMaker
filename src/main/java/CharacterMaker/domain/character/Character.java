package CharacterMaker.domain.character;


public interface Character {

    public Character fight(Character char1, Character char2);
    public void train();
    public void levelUp();
    public String getName();
    public void setName(String name);
    public void setHealth(int health);
    public int getHealth();
    public int getLevel();
    public void setLevel(int level);
    public int getExperiencePoints();
    public void setExperiencePoints(int points);
    public int getBattlesWon();
    public void setBattlesWon(int battlesWon);


}
