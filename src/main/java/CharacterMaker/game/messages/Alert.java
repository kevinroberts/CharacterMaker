package CharacterMaker.game.messages;

import java.text.NumberFormat;

import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.utils.PropertyUtils;

/**
 * CharacterMaker.game.messages
 * 
 * @author Kevin Roberts Date: 12/12/2013
 */

public class Alert {

	private static PropertyUtils propertyUtils = PropertyUtils.getInstance();
	private static String CONSOLE = "console";

	public static void info(String message) {
		if (CONSOLE.equals(propertyUtils.getAlertMethod())) {
			consolePrintLine(message);
		}
	}

	public static void debug(String message) {
		if (propertyUtils.isDebugMode() && CONSOLE.equals(propertyUtils.getAlertMethod())) {
			consolePrintLine(message);
		}
	}

	public static void infoAboutCharacter(String message, Character character) {
		String alertMethod = propertyUtils.getAlertMethod();
		if (!(character instanceof Monster)) {
			if (CONSOLE.equals(alertMethod)) {
				consolePrintLine(message);
			}
		}
	}

	public static void printStats(Character character) {
		String alertMethod = propertyUtils.getAlertMethod();

		if (CONSOLE.equals(alertMethod)) {
			consolePrintLine("Experience points:    " + character.getExperiencePoints());
			consolePrintLine("Level:                " + character.getLevel());
			consolePrintLine("Battles won:          " + character.getBattlesWon());
			consolePrintLine("Battles fought:       " + character.getBattleFought());
			NumberFormat percentFormat = NumberFormat.getPercentInstance();
			percentFormat.setMaximumFractionDigits(2);

			try {
				if (character.getBattleFought() > 0) {
					Double battlesWon = (double) (character.getBattlesWon());
					Double battlesFought = (double) (character.getBattleFought());
					double winPercent = battlesWon / battlesFought;
					String winPercentageFormat = percentFormat.format(winPercent);
					consolePrintLine("Win percentage:       " + winPercentageFormat);
				}
			} catch (Exception e) {
				Alert.debug(e.getMessage());
			}
			consolePrint("Current Health:       " + character.getHealth());

			consolePrintLine("");
			for (Attribute attribute : character.getAttributes()) {
				int spaces = 14;
				int length = attribute.getName().length();
				spaces = spaces - length;

				System.out.print(attribute.getName() + " - level:");
				for (int i = 1; i < spaces; spaces--) {
					consolePrint(" ");
				}
				consolePrint(attribute.getBattleLevel() + "\n");
			}
		}

	}

	private static void consolePrintLine(String msg) {
		System.out.println(msg);
	}

	private static void consolePrint(String msg) {
		System.out.print(msg);
	}

}
