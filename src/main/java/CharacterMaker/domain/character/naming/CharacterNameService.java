package CharacterMaker.domain.character.naming;

import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.ork.Ork;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * 
 * @author Kevin Roberts Date: 11/24/13 Time: 6:06 PM
 * 
 *         Useful site for generating names http://www.fantasynamegen.com/
 *         http://www.fantasynamegen.com/surnames/short/
 */
public class CharacterNameService {
	private static CharacterNameService ourInstance = new CharacterNameService();

	private List<String> barbarianNames = new ArrayList<String>();
	private List<String> orkFirstNames = new ArrayList<String>();
	private List<String> orkSurnames = new ArrayList<String>();
	private List<String> fantasySurnames = new ArrayList<String>();
	private List<String> monsterNames = new ArrayList<String>();

	public static CharacterNameService getInstance() {
		return ourInstance;
	}

	private CharacterNameService() {
		System.out.println("Initializing data sources");
		initializeDataSources();

	}

	public String generateName(Character character) {
		return readRandomNameFromResources(character);
	}

	private void initializeDataSources() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("BarbarianFirstNames.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("FantasySurnames.txt"));
			BufferedReader reader3 = new BufferedReader(new FileReader("OrkFirstNames.txt"));
			BufferedReader reader4 = new BufferedReader(new FileReader("OrkSurnames.txt"));
			BufferedReader reader5 = new BufferedReader(new FileReader("MonsterNames.txt"));

			String line = reader.readLine();
			String line2 = reader2.readLine();
			String line3 = reader3.readLine();
			String line4 = reader4.readLine();
			String line5 = reader5.readLine();

			while (StringUtils.isNotBlank(line)) {
				barbarianNames.add(line);
				line = reader.readLine();
			}

			while (StringUtils.isNotBlank(line2)) {
				fantasySurnames.add(line2);
				line2 = reader2.readLine();
			}

			while (StringUtils.isNotBlank(line3)) {
				orkFirstNames.add(line3);
				line3 = reader3.readLine();
			}

			while (StringUtils.isNotBlank(line4)) {
				orkSurnames.add(line4);
				line4 = reader4.readLine();
			}

			while (StringUtils.isNotBlank(line5)) {
				monsterNames.add(line5);
				line5 = reader5.readLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String readRandomNameFromResources(Character character) {
		String name = "No Name Given";

		if (character instanceof Barbarian) {
			Random r = new Random();
			String randomFirstName = barbarianNames.get(r.nextInt(barbarianNames.size()));
			String randomLastName = fantasySurnames.get(r.nextInt(fantasySurnames.size()));

			name = randomFirstName + " " + randomLastName;
		} else if (character instanceof Ork) {
			Random r = new Random();
			String randomFirstName = orkFirstNames.get(r.nextInt(orkFirstNames.size()));
			String randomLastName = orkSurnames.get(r.nextInt(orkSurnames.size()));

			name = randomFirstName + " " + randomLastName;
		} else if (character instanceof Monster) {
			Random r = new Random();
			name = monsterNames.get(r.nextInt(monsterNames.size()));
		}

		return name;

	}
}
