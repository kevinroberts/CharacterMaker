package CharacterMaker.domain.character;

import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Strength;

public abstract class Action {

	private String name;
	private String description;
	private int experienceGainedFromUse;
	private int damage;

	protected Action(String name, String description, int damage) {
		this.name = name;
		this.description = description;
		this.damage = damage;
	}

	protected Action(String name, String description, int experienceGainedFromUse, int damage) {
		this.name = name;
		this.description = description;
		this.experienceGainedFromUse = experienceGainedFromUse;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getDamage() {
		return damage;
	}

	public int getExperienceGainedFromUse() {
		return experienceGainedFromUse;
	}

	public void setExperienceGainedFromUse(int experienceGainedFromUse) {
		this.experienceGainedFromUse = experienceGainedFromUse;
	}

	public int calculateTotalDamage(CharacterMaker.domain.character.Character character) {
		int totalDamage = this.getDamage();
		int strength = 0;
		int dexterity = 0;
		for (Attribute attribute : character.getAttributes()) {
			if (attribute instanceof Strength) {
				strength = attribute.getBattleLevel();
			}
			if (attribute instanceof Dexterity) {
				dexterity = attribute.getBattleLevel();
			}
		}

		totalDamage += character.getLevel() * character.getLevel() + ((strength / 4) + (dexterity / 4));

		return totalDamage;
	}

	@Override
	public String toString() {
		return "Action{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", damage=" + damage + '}';
	}
}
