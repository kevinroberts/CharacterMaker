package CharacterMaker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.CharacterFactory;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.constants.Constants;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.utils.CharacterStrengthSorter;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.game.messages.Alert;

/**
 * CharacterMaker.game
 * 
 * @author Kevin Roberts Date: 12/12/2013
 */

public class MainLoop {

	private CharacterFactory characterFactory;
	private Barbarian barbarian;

	public MainLoop() {
		characterFactory = new CharacterFactory();
	}

	public void runLoop() {
		Monster monster = (Monster) characterFactory.createCharacter(Constants.MONSTER);

		List<Barbarian> barbarians = new ArrayList<Barbarian>();

		for (int i = 0; i < 50; i++) {
			barbarians.add((Barbarian) characterFactory.createCharacter(Constants.BARBARIAN));
		}

		List<? extends Character> barbarianDupes = CharacterUtils.findDuplicateNames(barbarians);

		// CharacterUtils.printCharactersByNameAndStrength(barbarianDupes);

		barbarians.removeAll(barbarianDupes);

		// sort the barbarians by strength level -- ordered by ascending
		// strength level
		Collections.sort(barbarians, new CharacterStrengthSorter());

		barbarian = (Barbarian) barbarians.toArray()[0];

		Alert.info("Your chosen barbarian warrior is:\n1: " + barbarian);

		Scanner console = new Scanner(System.in);
		int quitCode = 10;
		int quit = 1;
		int monstersKilled = 0;
		int battlesFought = 0;
		String oldMonsterID = monster.getUniqueID();
		do {

			if (quit == quitCode) {
				break;
			}

			Alert.info("\nHere is what I can do for you now:\n " + "1. Battle your barbarian\n "
				+ "2. Health totals\n " + "3. Character status\n " + "4. Reset total health\n "
				+ "5. Train Barbarian, " + barbarian.getName() + "\n 6. Equip a new weapon"
				+ "\n 7. Rename your character" + "\n 8. Enter random battle with 100 barbarians vs each other"
				+ "\n 9. Enter random battle with 100 barbarians vs monsters" + "\n " + quitCode
				+ ". Quit the application");

			quit = console.nextInt();

			switch (quit) {
			case 1: // '\001'

				if (battlesFought == 0) {
					Alert.info("A random bad-ass " + monster.getName() + " appears!");
				}

				if (monster.getHealth() <= 0) {
					// refresh with a new monster if the old one is killed
					monster = (Monster) characterFactory.createCharacter(Constants.MONSTER);
					if (barbarian.getLevel() > 1) {
						for (int i = 0; i < barbarian.getLevel(); i++) {
							monster.train();
						}
					}
					monstersKilled++;
					Alert.info("You've now killed " + monstersKilled + " monsters!");
				}

				if (oldMonsterID.equals(monster.getUniqueID())) {
					if (battlesFought != 0)
						Alert.info(barbarian.getName() + " attacks the " + monster.getName() + " again");
				} else {
					oldMonsterID = monster.getUniqueID();
					Alert.info("A new random bad-ass " + monster.getName() + " appears!");
				}

				barbarian.fight(monster);
				battlesFought++;
				Alert.info("health report is:\n1: " + barbarian.getName() + " - " + barbarian.getHealth() + " / "
					+ barbarian.getMaxHealth() + "\n2: " + monster.getName() + " - " + monster.getHealth());

				break;
			case 2: // '\002'
				Alert.info("\n1: " + barbarian.getName() + " - " + barbarian.getHealth() + " / "
					+ barbarian.getMaxHealth());
				Alert.info("\n2: " + monster.getName() + " - " + monster.getHealth() + " / " + monster.getMaxHealth());
				break;
			case 3: // '\003'
				Alert.info("Barbarian - " + barbarian.getName());
				Alert.info("Your barbarian has slain " + monstersKilled + " monsters");
				Alert.printStats(barbarian);

				Alert.info(" ");
				Alert.info("Current foe is a level " + monster.getLevel() + " " + monster.getName() + " w/ stats:");
				Alert.printStats(monster);
				break;
			case 4:
				CharacterUtils.resetHealthForCharacter(barbarian);
				Alert.info("Health reset - your health is now at: " + barbarian.getHealth());
				break;
			case 5:
				Alert.info("Training Barbarian 1 - " + barbarian.getName());
				barbarian.train();
				break;
			case 6:
				Alert.info("Pick a new action or weapon to equip:");
				Alert.printActions(barbarian);
				int newAction = console.nextInt();
				if (newAction > 0) {
					int step = 1;
					for (Action action : barbarian.getActions()) {
						if (step == newAction) {
							boolean equipped = CharacterUtils.equipActionForCharacter(barbarian, action);
							if (equipped)
								Alert.info("Equipped " + action.getName());
							else
								Alert.info(action.getName() + " is already equipped");
						}
						step++;
					}
				}
				break;
			case 7:
				Alert.info("Pick a new name for " + barbarian.getName() + ":");
				String newName = StringUtils.EMPTY;
				boolean invalidName = true;
				do {
					if (console.hasNextLine()) {

						newName = console.nextLine();

						if (StringUtils.isBlank(newName)) {
							Alert.info("New name must not be blank");
						} else if (!newName.matches("^([ \\u00c0-\\u01ffa-zA-Z'\\-]){1,50}$")) {
							Alert.info("New name is not in a valid format");
						} else {
							invalidName = false;
						}
					} else {
						System.out.println("You have entered an invalid input. Try again.");
						console.nextLine();
					}
				} while (invalidName);

				barbarian.setName(newName);
				Alert.info("Your name is now " + barbarian.getName());

				break;
			case 8:
				Alert.info("Battling your barbarian with 100 other random barbarians");
				List<Barbarian> barbariansList = new ArrayList<Barbarian>();

				for (int i = 0; i < 100; i++) {
					barbariansList.add((Barbarian) characterFactory.createCharacter(Constants.BARBARIAN));
				}

				barbariansList.add(barbarian);

				Character victor = CharacterUtils.battleRoyalWithOtherCharacters(barbariansList,
					barbarian.getUniqueID());

				Alert.info("The victor was: ");
				Alert.info("Barbarian - " + victor.getName());
				Alert.printStats(victor);

				break;
			case 9:
				Alert.info("Battling your barbarian with 100 other random barbarians against monsters");
				List<Barbarian> barbariansList2 = new ArrayList<Barbarian>();

				for (int i = 0; i < 100; i++) {
					barbariansList2.add((Barbarian) characterFactory.createCharacter("Barbarian"));
				}

				barbariansList2.add(barbarian);

				Character victor2 = CharacterUtils.battleRoyalWithMonsters(barbariansList2, barbarian.getUniqueID());

				Alert.info("The victor was: ");
				Alert.info("Barbarian - " + victor2.getName());
				Alert.printStats(victor2);

				break;
			case 10:
				Alert.info("Goodbye");
				break;
			}
		} while (true);

	}
}
