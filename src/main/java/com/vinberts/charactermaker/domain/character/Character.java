package com.vinberts.charactermaker.domain.character;

import java.io.Serializable;

import com.vinberts.charactermaker.domain.character.fighting.Fight;
import com.vinberts.charactermaker.domain.character.utils.CharacterUtils;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * Character object
 * Sets up the base model for a Character with attributes that can
 * be extended by the implementing Character type.
 * @see Character cannot be instantiated on it's own
 */
public abstract class Character implements Serializable {

    protected Character() {
    }

    private Integer health;

	private Integer maxHealth;

	private String name;

	private Multiset<Attribute> attributes;

	private Multiset<Action> actions;

	/**
	 * The set of actions this character will use in a fight / is a subset of
	 * the characters available actions
	 * @return Multiset equippedActions currently assigned to the character
	 */
	private Multiset<Action> equippedActions;

	private ConcurrentHashMultiset<Item> items;

	private Integer level;

	private Integer battlesWon;

	private Integer battleFought;

	private Integer experiencePoints;

	private String uniqueID;

	/**
	 * Must be implemented to initialize a new Character - used internally from the CharacterFactory.class by all subclasses of Character
	 * @return new Monster instance
	 */
	protected abstract Character initializeNewCharacter();

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

	public Integer getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(Integer maxHealth) {
		this.maxHealth = maxHealth;
	}

	public Multiset<Action> getEquippedActions() {
		return equippedActions;
	}

	public void setEquippedActions(Multiset<Action> equippedActions) {
		this.equippedActions = equippedActions;
	}

	public void removeAllEquipedActions() {
		Multiset<Action> actions = HashMultiset.create();
		this.equippedActions = actions;
	}


	public ConcurrentHashMultiset<Item> getItems() {
		return items;
	}

	public void setItems(ConcurrentHashMultiset<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Character{" +
				"health=" + health +
				", maxHealth=" + maxHealth +
				", name='" + name + '\'' +
				", attributes=" + attributes +
				", actions=" + actions +
				", equippedActions=" + equippedActions +
				", items=" + items +
				", level=" + level +
				", battlesWon=" + battlesWon +
				", battleFought=" + battleFought +
				", experiencePoints=" + experiencePoints +
				", uniqueID='" + uniqueID + '\'' +
				'}';
	}

}
