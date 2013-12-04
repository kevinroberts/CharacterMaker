package CharacterMaker;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.CharacterUtils;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.barbarian.BarbarianFactory;
import CharacterMaker.domain.character.ork.Ork;
import CharacterMaker.domain.character.ork.OrkFactory;

import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class App {

	public static void main(String[] args) {
		System.out.println("Starting Character Generator!");

		BarbarianFactory barbarianFactory = new BarbarianFactory();
		OrkFactory orkFactory = new OrkFactory();

		Multiset<Barbarian> barbarians = HashMultiset.create();

		for (int i = 0; i < 50; i++) {
			barbarians.add(barbarianFactory.createCharacter());
		}

		Stopwatch timer = Stopwatch.createStarted();
		Multiset<Barbarian> barbarianDupes = CharacterUtils.findDuplicateNames(barbarians);
		barbarians.removeAll(barbarianDupes);
		timer.stop();

		System.out.println("Dupes: " + barbarianDupes.size());
		System.out.println("Uniques: " + barbarians.size());
		System.out.println("Execution time: " + timer.elapsed(TimeUnit.MILLISECONDS) + " MILLISECONDS");

		Barbarian barbarian1 = (Barbarian) barbarians.toArray()[0];
		Barbarian barbarian2 = (Barbarian) barbarians.toArray()[1];
		Ork ork = (Ork) orkFactory.createCharacter();
		System.out.println("Your chosen barbarians and ork are:\n1: " + barbarian1 + "\n2: " + barbarian2 + "\n3: "
			+ ork);

		Scanner console = new Scanner(System.in);

		int quit = 1;

		do {
			if (quit == 8)
				break;
			System.out.println("\nHere is what I can do for you now:\n " + "1. Battle two barbarians\n "
				+ "2. Health totals\n " + "3. Stat totals\n " + "4. Reset total health\n " + "5. Train Barbarian 1, "
				+ barbarian1.getName() + " \n " + "6. Train Barbarian 2, " + barbarian2.getName() + " \n "
				+ "7. Battle Barbarian 1 and Ork, " + ork.getName() + "!\n " + "8. Quit the application");
			quit = console.nextInt();
			switch (quit) {
			case 1: // '\001'
				barbarian1.fight(barbarian2);
				System.out.println("health report is:\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth()
					+ "\n2: " + barbarian2.getName() + " - " + barbarian2.getHealth());
				break;
			case 2: // '\002'
				System.out.println("\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth() + "\n2: "
					+ barbarian2.getName() + " - " + barbarian2.getHealth() + "\n3: " + ork.getName() + " - "
					+ ork.getHealth());
				break;
			case 3: // '\003'
				System.out.println("Your chosen barbarians stats are");
				System.out.println("Barbarian 1 - " + barbarian1.getName() + "\nBattles won: "
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
				System.out.println("Barbarian 2 - " + barbarian2.getName() + "\nBattles won: "
					+ barbarian2.getBattlesWon());
				System.out.println("Experience points: " + barbarian2.getExperiencePoints());
				System.out.println("Level: " + barbarian2.getLevel());
				for (Attribute attribute : barbarian2.getAttributes()) {
					int spaces = 14;
					int length = attribute.getName().length();
					spaces = spaces - length;

					System.out.print(attribute.getName() + " - level:");
					for (int i = 1; i < spaces; spaces--) {
						System.out.print(" ");
					}
					System.out.print(attribute.getBattleLevel() + "\n");
				}
				break;
			case 4:
				System.out.println("Health reset");
				CharacterUtils.resetHealthForCharacter(barbarian1);
				CharacterUtils.resetHealthForCharacter(barbarian2);
				CharacterUtils.resetHealthForCharacter(ork);
				break;
			case 5:
				System.out.println("Training Barbarian 1 - " + barbarian1.getName());
				barbarian1.train();
				break;
			case 6:
				System.out.println("Training Barbarian 2 - " + barbarian2.getName());
				barbarian2.train();
				break;
			case 7:
				barbarian1.fight(ork);
				System.out.println("health report is:\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth()
					+ "\n2: " + ork.getName() + " - " + ork.getHealth());
				barbarian2.train();
				break;
			case 8:
				System.out.println("Goodbye");
				break;
			}
		} while (true);
	}

}
