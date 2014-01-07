package CharacterMaker.game.messages;

import java.text.NumberFormat;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.utils.CharacterUtils;
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

	public static void info(String message, boolean newLine) {
		if (CONSOLE.equals(propertyUtils.getAlertMethod())) {
			if (newLine) {
				consolePrintLine(message);
			} else {
				consolePrint(message);
			}

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

	public static void printActions(Character character) {
		String alertMethod = propertyUtils.getAlertMethod();
		if (CONSOLE.equals(alertMethod)) {
			int num = 1;
			for (Action action : character.getActions()) {
				consolePrint(num + ":       " + action.getName());
				if (CharacterUtils.isActionAlreadyEquipped(character, action)) {
					consolePrint(" (currently equipped)\n");
				} else {
					consolePrintLine("");
				}
				num++;
			}
		}
	}

	public static void printStats(Character character) {
		String alertMethod = propertyUtils.getAlertMethod();

		if (CONSOLE.equals(alertMethod)) {
			consolePrintLine("Current Health:       " + character.getHealth() + " / " + character.getMaxHealth());
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
			consolePrintLine("Equipped Actions: ");
			for (Action action : character.getEquippedActions()) {
				int spaces = 18;
				int length = action.getName().length();
				spaces = spaces - length;

				System.out.print(action.getName() + " - base damage:");
				for (int i = 1; i < spaces; spaces--) {
					consolePrint(" ");
				}
				consolePrint(action.getDamage() + "\n");
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
