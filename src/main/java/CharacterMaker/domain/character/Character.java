package CharacterMaker.domain.character;

import CharacterMaker.domain.character.fighting.Fight;
import CharacterMaker.domain.character.utils.CharacterUtils;
import com.google.common.collect.Multiset;

import java.io.Serializable;

public abstract class Character implements Serializable {

	private Integer health;

	private String name;

	private Multiset<Attribute> attributes;

	private Multiset<Action> actions;

	private Integer level;

	private Integer battlesWon;

	private Integer battleFought;

	private Integer experiencePoints;

    private String uniqueID;

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

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(Integer experiencePoints) {
		this.experiencePoints = experiencePoints;
		// call to level up on all experience gains
		if (CharacterUtils.isAtLevelUp(this)) {
			this.levelUp();
		}
	}

	public Integer getBattlesWon() {
		return battlesWon;
	}

	public void setBattlesWon(Integer battlesWon) {
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

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

	public Integer getBattleFought() {
		return battleFought;
	}

	public void setBattleFought(Integer battleFought) {
		this.battleFought = battleFought;
	}

	@Override
	public String toString() {
		return "Character{" + "health=" + health + ", name='" + name + '\'' + ", attributes=" + attributes
			+ ", actions=" + actions + ", level=" + level + ", battlesWon=" + battlesWon + ", experiencePoints="
			+ experiencePoints + '}';
	}
}
