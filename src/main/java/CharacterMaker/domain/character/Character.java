package CharacterMaker.domain.character;

import CharacterMaker.domain.character.fighting.Fight;
import com.google.common.collect.Multiset;

public abstract class Character {

	private int health;

	private String name;

	private Multiset<Attribute> attributes;

	private Multiset<Action> actions;

	private int level;

	private int battlesWon;

	private int experiencePoints;

	public Character fight(Character otherCharacter) {
		return Fight.fight(this, otherCharacter);
	}

	public abstract void train();

	public void levelUp() {
		CharacterUtils.levelUpCharacter(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
		// call to level up on all experience gains
		if (CharacterUtils.isAtLevelUp(this)) {
			this.levelUp();
		}
	}

	public int getBattlesWon() {
		return battlesWon;
	}

	public void setBattlesWon(int battlesWon) {
		this.battlesWon = battlesWon;
	}

	public Multiset<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Multiset<Attribute> attributes) {
		this.attributes = attributes;
	}

	public Multiset<Action> getActions() {
		return actions;
	}

	public void setActions(Multiset<Action> actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		return "Character{" + "health=" + health + ", name='" + name + '\'' + ", attributes=" + attributes
			+ ", actions=" + actions + ", level=" + level + ", battlesWon=" + battlesWon + ", experiencePoints="
			+ experiencePoints + '}';
	}
}
