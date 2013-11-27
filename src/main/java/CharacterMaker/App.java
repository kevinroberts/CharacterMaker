package CharacterMaker;


import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.CharacterUtils;
import CharacterMaker.domain.character.barbarian.BarbarianFactory;
import com.google.common.base.Stopwatch;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class App {

    public static void main(String[] args) {
        System.out.println("Starting Character Generator!");

        BarbarianFactory barbarianFactory = new BarbarianFactory();

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
        System.out.println("Your chosen barbarians are:\n1: " + barbarian1 + "\n2: " + barbarian2);

        Scanner console = new Scanner(System.in);

        int quit = 1;

        do {
            if (quit == 5)
                break;
            System.out.println("\nThank you for using this application. " +
                    "Here is what I can do for you now:\n " +
                    "1. Battle two barbarians\n " +
                    "2. Health totals\n " +
                    "3. Stat totals\n " +
                    "4. Reset total health\n " +
                    "5. Quit the application");
            quit = console.nextInt();
            switch (quit) {
                case 1: // '\001'
                    barbarian1.fight(barbarian2);
                    System.out.println("health report is:\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth() + "\n2: "
                            + barbarian2.getName() + " - " + barbarian2.getHealth());
                    break;
                case 2: // '\002'
                    System.out.println("Your chosen barbarians health are:\n1: " + barbarian1.getName() + " - " + barbarian1.getHealth() + "\n2: "
                            + barbarian2.getName() + " - " + barbarian2.getHealth());
                    break;
                case 3: // '\003'
                    System.out.println("Your chosen barbarians stats are");
                    System.out.println("Barbarian 1 - " + barbarian1.getName() + "\nBattles won: " + barbarian1.getBattlesWon());
                    System.out.println("Experience points: " + barbarian1.getExperiencePoints());
                    for (Attribute attribute : barbarian1.getAttributes()) {
                        System.out.println(attribute);
                    }
                    System.out.println(" ");
                    System.out.println("Barbarian 2 - " + barbarian2.getName() + "\nBattles won: " + barbarian2.getBattlesWon());
                    System.out.println("Experience points: " + barbarian2.getExperiencePoints());
                    for (Attribute attribute : barbarian2.getAttributes()) {
                        System.out.println(attribute);
                    }
                    break;
                case 4:
                    System.out.println("Health reset");
                    barbarian1.setHealth(100);
                    barbarian2.setHealth(100);
                    break;
                case 5:
                    System.out.println("Goodbye");
                    break;
            }
        } while (true);
    }


}
