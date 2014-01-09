package CharacterMaker.domain.character;

import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.barbarian.BarbarianFactory;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.monster.MonsterFactory;
import CharacterMaker.domain.character.ork.Ork;
import CharacterMaker.domain.character.ork.OrkFactory;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Map;

public class CharacterFactory {
	private static final Logger LOG = LoggerFactory.getLogger(CharacterFactory.class);
	private static final Map<String, Class<? extends CharacterFactory>> CHATACTER_TYPES_TO_CLASS = ImmutableMap
			.<String, Class<? extends AjamAbstractComponent>> builder()
			.put("Barbarian", BarbarianFactory.class)
			.put("Monster", MonsterFactory.class)
			.put("Ork", OrkFactory.class).build();


	public CharacterFactory() {
	}

	public static CharacterFactory getFactory(String characterType) {
		Class<? extends CharacterFactory> clazz = CHATACTER_TYPES_TO_CLASS.get(characterType);

		if (clazz != null) {
			try {
				Constructor<? extends CharacterFactory> constructor = clazz
						.getConstructor(CharacterFactory.class);
				return constructor.newInstance(characterType);
			} catch (Exception e) {
				LOG.error("Error instantiating class", e);
			}
		}

		return null;
	}

	public static Character createCharacter(String characterType) {
		CharacterFactory characterFactory = getFactory(characterType);
		return characterFactory.createCharacter(characterType);
	}

}
