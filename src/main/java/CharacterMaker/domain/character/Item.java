package CharacterMaker.domain.character;

import java.util.UUID;

/**
 * CharacterMaker.domain.character
 *
 * @author Kevin Roberts
 *         date: 7/28/14
 */
public abstract class Item {

	protected Item(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
	}

	protected Item(String name, int level) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.level = level;
	}

	public String id;

	public String name;

	public int level;

	public abstract void use(Character user);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Item{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", level=" + level +
				'}';
	}
}
