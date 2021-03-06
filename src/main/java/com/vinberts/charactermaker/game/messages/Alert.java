package com.vinberts.charactermaker.game.messages;

import java.io.IOException;
import java.text.NumberFormat;

import javax.sound.sampled.*;

import com.vinberts.charactermaker.domain.character.Action;
import com.vinberts.charactermaker.domain.character.Attribute;
import com.vinberts.charactermaker.domain.character.Character;
import com.vinberts.charactermaker.domain.character.Item;
import com.vinberts.charactermaker.domain.character.monster.Monster;
import com.vinberts.charactermaker.domain.character.utils.CharacterUtils;
import com.vinberts.charactermaker.domain.character.utils.PropertyUtils;

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

	public static void printItems(Character character) {
		String alertMethod = propertyUtils.getAlertMethod();
		if (CONSOLE.equals(alertMethod)) {
			int num = 1;
			if (character.getItems() != null) {
				for (Item item : character.getItems()) {
					consolePrintLine(num + ":       " + item.getName());
					num++;
				}
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

	public static synchronized void playSound(final String soundFile) {
		//URL url = new URL(
		//		"http://wavs.unclebubby.com/wav/Mechwarrior/crtihit.wav");
		//URL url = this.getClass().getResource("/com/path/to/file.txt");
		try {
			Clip clip = AudioSystem.getClip();
			// getAudioInputStream() also accepts a File or InputStream
			AudioInputStream ais = AudioSystem.
					getAudioInputStream( Thread.currentThread().getContextClassLoader().getResource(soundFile) );
			clip.open(ais);
			clip.start();
//			SwingUtilities.invokeLater(new Runnable() {
//				public void run() {
//					// A GUI element to prevent the Clip's daemon Thread
//					// from terminating at the end of the main()
//					JOptionPane.showMessageDialog(null, "Close to exit!");
//				}
//			});
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static void consolePrintLine(String msg) {
		System.out.println(msg);
	}

	private static void consolePrint(String msg) {
		System.out.print(msg);
	}

}
