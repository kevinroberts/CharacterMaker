package CharacterMaker.domain.character;

import com.google.common.collect.Multiset;

public interface Character {

	public Character fight(Character otherCharacter);

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

	public Multiset<Attribute> getAttributes();

	public void setAttributes(Multiset<Attribute> attributes);

	public void setActions(Multiset<Action> actions);

	public Multiset<Action> getActions();

}
