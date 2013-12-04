package CharacterMaker.domain.character;

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

	@Override
	public String toString() {
		return "Action{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", damage=" + damage + '}';
	}
}
