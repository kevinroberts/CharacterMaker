package CharacterMaker.game.messages;

import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.monster.Monster;

/**
 * CharacterMaker.game.messages
 *
 * @author Kevin Roberts
 *         Date: 12/12/2013
 */

public class Alert {

    public static void info(String message) {
        System.out.println(message);
    }

	public static void debug(String message) {
		System.out.println(message);
	}

    public static void infoAboutCharacter(String message, Character character) {
        if (!(character instanceof Monster)) {
            System.out.println(message);
        }
    }

    public static void printStats(Character character) {
        System.out.println("Experience points:    " + character.getExperiencePoints());
        System.out.println("Level:                " + character.getLevel());
        System.out.println("Battles won:          " + character.getBattlesWon());
        System.out.print("Current Health:       " + character.getHealth());

        System.out.println("");
        for (Attribute attribute : character.getAttributes()) {
            int spaces = 14;
            int length = attribute.getName().length();
            spaces = spaces - length;

            System.out.print(attribute.getName() + " - level:");
            for (int i = 1; i < spaces; spaces--) {
                System.out.print(" ");
            }
            System.out.print(attribute.getBattleLevel() + "\n");
        }

    }


}
