package CharacterMaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.utils.CharacterStrengthSorter;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.barbarian.BarbarianFactory;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.monster.MonsterFactory;
import CharacterMaker.domain.character.ork.Ork;
import CharacterMaker.domain.character.ork.OrkFactory;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class App {

	public static void main(String[] args) {
		System.out.println("Starting Character Generator!");

		BarbarianFactory barbarianFactory = new BarbarianFactory();
		MonsterFactory monsterFactory = new MonsterFactory();

		Monster monster = monsterFactory.createCharacter();

		List<Barbarian> barbarians = new ArrayList<Barbarian>();

		List<Barbarian> barbarianDupes = CharacterUtils.findDuplicateNames(barbarians);
		barbarians.removeAll(barbarianDupes);

		for (int i = 0; i < 50; i++) {
			barbarians.add(barbarianFactory.createCharacter());
		}

		// sort the barbarians by strength level -- ordered by ascending strength level
		Collections.sort(barbarians, new CharacterStrengthSorter());

		Barbarian barbarian1 = (Barbarian) barbarians.toArray()[0];
		Barbarian barbarian2 = (Barbarian) barbarians.toArray()[barbarians.size()-1]; // weakest strength barbarian


		System.out.println("Your chosen barbarian warrior is:\n1: " + barbarian1);

		Scanner console = new Scanner(System.in);

		int quit = 1;
		int monstersKilled = 0;
		int battlesFought = 0;
		String oldMonsterID = monster.getUniqueID();
		do {

			if (quit == 6)
				break;
			System.out.println("\nHere is what I can do for you now:\n " + "1. Battle your barbarian\n "
				+ "2. Health totals\n " + "3. Stat totals\n " + "4. Reset total health\n " + "5. Train Barbarian, "
				+ barbarian1.getName() + "\n 6. Quit the application");
			quit = console.nextInt();
			switch (quit) {
			case 1: // '\001'

				if (battlesFought == 0) {
					System.out.println("A random bad-ass " + monster.getName() + " appears!");
				}

				if (monster.getHealth() <= 0) {
					// refresh with a new monster if the old one is killed
					monster = monsterFactory.createCharacter();
					monstersKilled++;
					System.out.println("You've now killed " + monstersKilled + " monsters!");
				}

				if (oldMonsterID.equals(monster.getUniqueID())) {
					if (battlesFought != 0)
						System.out.println( barbarian1.getName() + " attacks the " + monster.getName() + " again");
				} else {
					oldMonsterID = monster.getUniqueID();
					System.out.println("A new random bad-ass " + monster.getName() + " appears!");
				}

				barbarian1.fight(monster);
				battlesFought++;
				System.out.println("health report is:\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth()
						+ "\n2: " + monster.getName() + " - " + monster.getHealth());

				break;
			case 2: // '\002'
				System.out.println("\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth());
				break;
			case 3: // '\003'
				System.out.println("Your chosen barbarians stats are");
				System.out.println("Barbarian - " + barbarian1.getName() + "\nBattles won: "
					+ barbarian1.getBattlesWon());
				System.out.println("Experience points: " + barbarian1.getExperiencePoints());
				System.out.println("Level: " + barbarian1.getLevel());
				for (Attribute attribute : barbarian1.getAttributes()) {
					int spaces = 14;
					int length = attribute.getName().length();
					spaces = spaces - length;

					System.out.print(attribute.getName() + " - level:");
					for (int i = 1; i < spaces; spaces--) {
						System.out.print(" ");
					}
					System.out.print(attribute.getBattleLevel() + "\n");
				}
				System.out.println(" ");

				break;
			case 4:
				CharacterUtils.resetHealthForCharacter(barbarian1);
				System.out.println("Health reset - your health is now at: " + barbarian1.getHealth());
				break;
			case 5:
				System.out.println("Training Barbarian 1 - " + barbarian1.getName());
				barbarian1.train();
				break;
			case 6:
				System.out.println("Goodbye");
				break;
			}
		} while (true);
	}

}
