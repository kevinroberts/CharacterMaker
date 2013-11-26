package CharacterMaker.domain.character;


public abstract class Action {

    private String name;
    private String description;
    private int damage;

    protected Action(String name, String description, int damage) {
        this.name = name;
        this.description = description;
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

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", damage=" + damage +
                '}';
    }
}
