package CharacterMaker.domain.character.naming;

import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.Character;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 11/24/13
 * Time: 6:06 PM

 Useful site for generating names
 http://www.fantasynamegen.com/surnames/short/
 */
public class CharacterNameService {
    private static CharacterNameService ourInstance = new CharacterNameService();

    private List<String> barbarianNames = new ArrayList<String>();
    private List<String> fantasySurnames = new ArrayList<String>();

    public static CharacterNameService getInstance() {
        return ourInstance;
    }

    private CharacterNameService() {
        System.out.println("Initializing data sources");
        initializeDataSources();

    }

    public String generateName(Character character) {
        if (character instanceof Barbarian) {
            return readRandomNameFromResources(character);
        }
        else {
            return "No Given Name";
        }
    }

    private void initializeDataSources() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("BarbarianFirstNames.txt"));
            BufferedReader reader2 = new BufferedReader(new FileReader("FantasySurnames.txt"));

            String line = reader.readLine();
            String line2 = reader2.readLine();

            while( line != null ) {
                barbarianNames.add(line);
                line = reader.readLine();
            }

            while( line2 != null ) {
                fantasySurnames.add(line2);
                line2 = reader2.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readRandomNameFromResources(Character character) {
        String name = null;

        if (character instanceof Barbarian) {
            Random r = new Random();
            String randomFirstName = barbarianNames.get(r.nextInt(barbarianNames.size()));
            String randomLastName = fantasySurnames.get(r.nextInt(fantasySurnames.size()));

            name = randomFirstName + " " + randomLastName;
        }

        return name;

    }
}
