package com.vinberts.charactermaker.domain.character;

import java.lang.*;
import java.util.Map;
import java.util.UUID;

import com.vinberts.charactermaker.domain.character.barbarian.Barbarian;
import com.vinberts.charactermaker.domain.character.constants.Constants;
import com.vinberts.charactermaker.domain.character.monster.Monster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vinberts.charactermaker.domain.character.ork.Ork;

import com.google.common.collect.ImmutableMap;

public class CharacterFactory {
	private static final Logger LOG = LoggerFactory.getLogger(CharacterFactory.class);

	// Map object containing all the possible Character types
	private static final Map<String, Class<? extends Character>> CHARACTER_TYPES_TO_CLASS = ImmutableMap
			.<String, Class<? extends Character>>builder()
			.put(Constants.BARBARIAN, Barbarian.class)
			.put(Constants.MONSTER, Monster.class)
			.put(Constants.ORK, Ork.class).build();

	/**
	 * createCharacter using reflection to build an instance of the specified Character type
	 *
	 * @param characterType a String representing the desired Character type e.g. Monster, Barbarian, Ork
	 * @return a new Character or null if a proper Character could not be found
	 */
	public static Character createCharacter(String characterType) {
		Class<? extends Character> clazz = CHARACTER_TYPES_TO_CLASS.get(characterType);

		if (clazz != null) {
			try {
				Character character = clazz.newInstance().initializeNewCharacter();
				// set the character's universal traits
				character.setHealth(Constants.DEFAULT_CHARACTER_HEALTH);
				character.setMaxHealth(Constants.DEFAULT_CHARACTER_HEALTH);
				character.setLevel(Constants.DEFAULT_CHARACTER_LEVEL);
				character.setBattlesWon(Constants.DEFAULT_CHARACTER_BATTLES_WON);
				character.setExperiencePoints(Constants.DEFAULT_CHARACTER_XP);
				character.setBattleFought(Constants.DEFAULT_BATTLES_FOUGHT);
				character.setUniqueID(UUID.randomUUID().toString());

				return character;

			} catch (Exception e) {
				LOG.error("Error instantiating new Character class", e);
			}
		}

		return null;
	}


}
