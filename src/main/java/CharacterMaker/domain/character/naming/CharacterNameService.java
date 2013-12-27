package CharacterMaker.domain.character.naming;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.ork.Ork;
import CharacterMaker.domain.character.utils.MersenneTwister;

/**
 * Created with IntelliJ IDEA.
 * 
 * @author Kevin Roberts Date: 11/24/13 Time: 6:06 PM
 * 
 *         Useful site for generating names http://www.fantasynamegen.com/
 *         http://www.fantasynamegen.com/surnames/short/
 */
public class CharacterNameService {
	private static final Logger LOG = LoggerFactory.getLogger(CharacterNameService.class);
	private static CharacterNameService ourInstance = null;

	private List<String> barbarianNames = new ArrayList<String>();
	private List<String> orkFirstNames = new ArrayList<String>();
	private List<String> orkSurnames = new ArrayList<String>();
	private List<String> fantasySurnames = new ArrayList<String>();
	private List<String> monsterNames = new ArrayList<String>();

	public static CharacterNameService getInstance() {
		if (ourInstance == null) {
			synchronized (CharacterNameService.class) {
				if (ourInstance == null) {
					ourInstance = new CharacterNameService();
				}
			}
		}
		return ourInstance;
	}

	private CharacterNameService() {
		LOG.debug("Initializing data sources");
		initializeDataSources();
	}

	public String generateName(Character character) {
		return readRandomNameFromResources(character);
	}

	private void initializeDataSources() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/BarbarianFirstNames.txt"));
			BufferedReader reader2 = new BufferedReader(new FileReader("res/FantasySurnames.txt"));
			BufferedReader reader3 = new BufferedReader(new FileReader("res/OrkFirstNames.txt"));
			BufferedReader reader4 = new BufferedReader(new FileReader("res/OrkSurnames.txt"));
			BufferedReader reader5 = new BufferedReader(new FileReader("res/MonsterNames.txt"));

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
		MersenneTwister mersenneTwister = new MersenneTwister();
		if (character instanceof Barbarian) {
			String randomFirstName = barbarianNames.get(mersenneTwister.nextInt(barbarianNames.size()));
			String randomLastName = fantasySurnames.get(mersenneTwister.nextInt(fantasySurnames.size()));

			name = randomFirstName + " " + randomLastName;
		} else if (character instanceof Ork) {
			String randomFirstName = orkFirstNames.get(mersenneTwister.nextInt(orkFirstNames.size()));
			String randomLastName = orkSurnames.get(mersenneTwister.nextInt(orkSurnames.size()));

			name = randomFirstName + " " + randomLastName;
		} else if (character instanceof Monster) {
			name = monsterNames.get(mersenneTwister.nextInt(monsterNames.size()));
		}

		return name;

	}
}
