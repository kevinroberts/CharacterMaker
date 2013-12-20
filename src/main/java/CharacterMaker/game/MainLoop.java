package CharacterMaker.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.barbarian.BarbarianFactory;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.monster.MonsterFactory;
import CharacterMaker.domain.character.utils.CharacterStrengthSorter;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.game.messages.Alert;

/**
 * CharacterMaker.game
 * 
 * @author Kevin Roberts Date: 12/12/2013
 */

public class MainLoop {

	private MonsterFactory monsterFactory;
	private BarbarianFactory barbarianFactory;
	private Barbarian barbarian;

	public MainLoop() {
		monsterFactory = new MonsterFactory();
		barbarianFactory = new BarbarianFactory();
	}

	public void runLoop() {
		Monster monster = monsterFactory.createCharacter();

		List<Barbarian> barbarians = new ArrayList<Barbarian>();

		for (int i = 0; i < 50; i++) {
			barbarians.add(barbarianFactory.createCharacter());
		}

		List<? extends Character> barbarianDupes = CharacterUtils.findDuplicateNames(barbarians);

		//CharacterUtils.printCharactersByNameAndStrength(barbarianDupes);

		barbarians.removeAll(barbarianDupes);

		// sort the barbarians by strength level -- ordered by ascending
		// strength level
		Collections.sort(barbarians, new CharacterStrengthSorter());

		barbarian = (Barbarian) barbarians.toArray()[0];

		Alert.info("Your chosen barbarian warrior is:\n1: " + barbarian);

		Scanner console = new Scanner(System.in);

		int quit = 1;
		int monstersKilled = 0;
		int battlesFought = 0;
		String oldMonsterID = monster.getUniqueID();
		do {

			if (quit == 6)
				break;
			Alert.info("\nHere is what I can do for you now:\n " + "1. Battle your barbarian\n "
				+ "2. Health totals\n " + "3. Stat totals\n " + "4. Reset total health\n " + "5. Train Barbarian, "
				+ barbarian.getName() + "\n 6. Quit the application");
			quit = console.nextInt();
			switch (quit) {
			case 1: // '\001'

				if (battlesFought == 0) {
					Alert.info("A random bad-ass " + monster.getName() + " appears!");
				}

				if (monster.getHealth() <= 0) {
					// refresh with a new monster if the old one is killed
					monster = monsterFactory.createCharacter();
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
				Alert.info("health report is:\n1: " + barbarian.getName() + " - " + barbarian.getHealth() + "\n2: "
					+ monster.getName() + " - " + monster.getHealth());

				break;
			case 2: // '\002'
				Alert.info("\n1: " + barbarian.getName() + " - " + barbarian.getHealth());
				Alert.info("\n2: " + monster.getName() + " - " + monster.getHealth());
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
				Alert.info("Goodbye");
				break;
			case 7:
				Alert.info("Battling your barbarian with 100 other random barbarians");
				List<Barbarian> barbariansList = new ArrayList<Barbarian>();

				for (int i = 0; i < 100; i++) {
					barbariansList.add(barbarianFactory.createCharacter());
				}

				barbariansList.add(barbarian);

				Character victor = CharacterUtils.battleRoyalWithOtherCharacters(barbariansList, barbarian.getUniqueID());

				Alert.info("The victor was: ");
				Alert.info("Barbarian - " + victor.getName());
				Alert.printStats(victor);

				break;
				case 8:
					Alert.info("Battling your barbarian with 100 other random barbarians against monsters");
					List<Barbarian> barbariansList2 = new ArrayList<Barbarian>();

					for (int i = 0; i < 100; i++) {
						barbariansList2.add(barbarianFactory.createCharacter());
					}

					barbariansList2.add(barbarian);

					Character victor2 = CharacterUtils.battleRoyalWithMonsters(barbariansList2, barbarian.getUniqueID());

					Alert.info("The victor was: ");
					Alert.info("Barbarian - " + victor2.getName());
					Alert.printStats(victor2);

					break;
			}


		} while (true);

	}
}
