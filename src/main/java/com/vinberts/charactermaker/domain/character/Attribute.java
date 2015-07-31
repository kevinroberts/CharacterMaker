package com.vinberts.charactermaker.domain.character;

public abstract class Attribute {

	private String name;
	private String description;
	private int battleLevel; // determines the factor of importance in fighting

	protected Attribute(String name, String description, int battleLevel) {
		this.name = name;
		this.description = description;
		this.battleLevel = battleLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getBattleLevel() {
		return battleLevel;
	}

	public void setBattleLevel(int battleLevel) {
		this.battleLevel = battleLevel;
	}

	@Override
	public String toString() {
		return "Attribute{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", battleLevel="
			+ battleLevel + '}';
	}
}
