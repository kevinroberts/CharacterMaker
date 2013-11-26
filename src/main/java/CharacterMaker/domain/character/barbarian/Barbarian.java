package CharacterMaker.domain.character.barbarian;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.CharacterUtils;
import CharacterMaker.domain.character.actions.SwingSword;
import com.google.common.collect.Multiset;


public class Barbarian implements Character {

    private int health;

    private String name;

    private Multiset<Attribute> attributes;

    private Multiset<Action> actions;

    private int level;

    private int battlesWon;

    private int experiencePoints; // experiencePoints points for a barbarian - will level up every 100 points

    @Override
    public Character fight(Character char1, Character char2) {
        if (char1 instanceof Barbarian && char2 instanceof Barbarian) {
            Barbarian barbarian1 = (Barbarian)char1;
            Barbarian barbarian2 = (Barbarian)char2;


            if (barbarian1.getHealth() <= 0) {
                System.out.println(barbarian1.getName() + " is dead and cannot fight until he is revived.");
                return barbarian2;
            }
            if (barbarian2.getHealth() <= 0) {
                System.out.println(barbarian2.getName() + " is dead and cannot fight until he is revived.");
                return barbarian1;
            }

            // Barbarian 1 actions
            int totalDamageDealtFrom1 = 0;
            for (Action action : barbarian1.getActions()) {
                if (action instanceof SwingSword) {
                    SwingSword swingSword = (SwingSword)action;

                    System.out.print(barbarian1.getName() + " swings his sword at " + barbarian2.getName());
                    if (CharacterUtils.hitSuccessCheck(barbarian1)) {
                        totalDamageDealtFrom1 = swingSword.calculateTotalDomage(barbarian1);
                        System.out.print(" and hits for " + totalDamageDealtFrom1 + " damage\n");
                        char2.setHealth(char2.getHealth() - totalDamageDealtFrom1);
                        char2.setExperiencePoints(char2.getExperiencePoints() + 1);
                        if (barbarian2.getHealth() < 0) {
                            System.out.println(barbarian2.getName() + " collapses and dies from his injuries.");
                        }
                    } else {
                        System.out.print(" and misses\n");
                    }

                }
            }

            if (barbarian2.getHealth() <= 0) {
                System.out.println(barbarian2.getName() + " is dead and cannot fight until he is revived.");
                return barbarian1;
            }

            // Barbarian 2 actions
            int totalDamageDealtFrom2 = 0;
            for (Action action : barbarian2.getActions()) {
                if (action instanceof SwingSword) {
                    SwingSword swingSword = (SwingSword)action;

                    System.out.print(barbarian2.getName() + " swings his sword at " + barbarian1.getName());
                    if (CharacterUtils.hitSuccessCheck(barbarian2)) {
                        totalDamageDealtFrom2 = swingSword.calculateTotalDomage(barbarian2);
                        System.out.print(" and hits for " + totalDamageDealtFrom2  + " damage\n");
                        this.setHealth(this.getHealth() - totalDamageDealtFrom2);
                        this.setExperiencePoints(this.getExperiencePoints() + 1);
                        if (char1.getHealth() <= 0) {
                            System.out.println(barbarian2.getName() + " collapses and dies from his injuries.");
                        }
                    } else {
                        System.out.print(" and misses!\n");
                    }

                }
            }

            if (totalDamageDealtFrom1 > totalDamageDealtFrom2) {
                System.out.println(barbarian1.getName() + " wins the fight.");
                this.setBattlesWon(this.getBattlesWon() + 1);
                // award bonus experience if the characters are near the same skill level
                if (Math.abs(this.getLevel() - char2.getLevel()) < 2) {
                    this.setExperiencePoints(this.getExperiencePoints() + 2);
                }

                return barbarian1;
            } else if (totalDamageDealtFrom2 > totalDamageDealtFrom1) {
                System.out.println(barbarian2.getName() + " wins the fight.");
                char2.setBattlesWon(char2.getBattlesWon() + 1);
                if (Math.abs(this.getLevel() - char2.getLevel()) < 2) {
                    char2.setExperiencePoints(barbarian2.getExperiencePoints() + 2);
                }
                return barbarian2;
            } else {
                return null;
            }

        }
        else {
            return null;
        }
    }

    @Override
    public void train() {

    }

    @Override
    public void levelUp() {

    }

    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }

    public int getBattlesWon() {
        return battlesWon;
    }

    public void setBattlesWon(int battlesWon) {
        this.battlesWon = battlesWon;
    }

    @Override
    public int getExperiencePoints() {
        return experiencePoints;
    }

    @Override
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    @Override
    public String toString() {
        return "Barbarian{" +
                "health=" + health +
                ", name='" + name + '\'' +
                ", attributes=" + attributes +
                ", actions=" + actions +
                ", level=" + level +
                ", battlesWon=" + battlesWon +
                ", experiencePoints=" + experiencePoints +
                '}';
    }
}
