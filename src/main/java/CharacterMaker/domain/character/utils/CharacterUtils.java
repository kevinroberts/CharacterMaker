package CharacterMaker.domain.character.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.attributes.Luck;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.monster.MonsterFactory;
import CharacterMaker.game.messages.Alert;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class CharacterUtils {

	public CharacterUtils() {
	}

	/**
	 * Given a set list of Characters types, return all the ones with
	 * duplicate names.
	 * @param characterList
	 * @return List of Characters with Duplicate Names
	 */
	public static List<Character> findDuplicateNames(List<? extends Character> characterList) {
		List<Character> characterDupes = new ArrayList<Character>();

		for (int i = 0; i < characterList.size(); i++) {
			Barbarian barbarian1 = (Barbarian) characterList.toArray()[i];

			for (int j = i + 1; j < characterList.size(); j++) {
				Barbarian barbarian2 = (Barbarian) characterList.toArray()[j];

				if (i != j && barbarian1.getName().equals(barbarian2.getName())) {
					characterDupes.add(barbarian1);
				}
			}

		}

		return characterDupes;
	}

	public static Character battleRoyalWithOtherCharacters(List<? extends Character> characterList, String idToTrack) {
		Character victor;
		MersenneTwister mersenneTwister = new MersenneTwister();

		int rounds = 1;
		int madeItRound = 0;

		while (characterList.size() > 1) {
			Alert.info("Round: " + rounds);
			int randomSelection1 = mersenneTwister.nextInt(characterList.size());

			int randomSelection2 = mersenneTwister.nextInt(characterList.size());

			while (randomSelection1 == randomSelection2) {
				randomSelection2 = mersenneTwister.nextInt(characterList.size());
			}

			Character character1 = characterList.get(randomSelection1);
			Character character2 = characterList.get(randomSelection2);

			character1.fight(character2);

			if (character1.getUniqueID().equals(idToTrack) || character2.getUniqueID().equals(idToTrack)) {
				madeItRound = rounds;
			}

			if (character1.getHealth() <= 0) {
				characterList.remove(randomSelection1);
			}
			if (character2.getHealth() <= 0) {
				characterList.remove(randomSelection2);
			}


			rounds++;
		}

		Alert.info("Your chosen character made it: " + madeItRound + " rounds");

		victor = characterList.get(0);

		return victor;
	}

	public static Character battleRoyalWithMonsters(List<? extends Character> characterList, String idToTrack) {
		Character victor;
		MersenneTwister mersenneTwister = new MersenneTwister();
		MonsterFactory monsterFactory = new MonsterFactory();

		int rounds = 1;
		int madeItRound = 0;

		while (characterList.size() > 1) {
			Alert.info("Round: " + rounds);
			int randomSelection1 = mersenneTwister.nextInt(characterList.size());

			Character character1 = characterList.get(randomSelection1);
			Monster monster = monsterFactory.createCharacter();

			if (character1.getLevel() > 1) {
					for (int i = 0; i < character1.getLevel(); i++) {
						monster.train();
					}
			}
			// add extra training for higher levels
			if (character1.getLevel() > 3) {
				for (int i = 0; i < character1.getLevel(); i++) {
					monster.train();
				}
			}

			character1.fight(monster);

			if (character1.getUniqueID().equals(idToTrack)) {
				madeItRound = rounds;
			}

			if (character1.getHealth() <= 0) {
				characterList.remove(randomSelection1);
			}

			rounds++;
		}

		Alert.info("Your chosen character made it: " + madeItRound + " rounds");

		victor = characterList.get(0);

		return victor;
	}


	public static void printCharactersByNameAndStrength(List<? extends Character> characters) {
		for (Character character : characters) {
			Alert.info(character.getName() + " - Strength: " + getStrengthLevelForCharacter(character));
		}
	}

	public static void equipActionForCharacter(Character character, Action actionToEquip) {
		// ensure action is not already equipped
		boolean equipped = isActionAlreadyEquipped(character, actionToEquip);

		if (!equipped) {
			Multiset<Action> actions = character.getEquippedActions();
			actions.add(actionToEquip);
			character.setEquippedActions(actions);
		}
	}

	public static boolean isActionAlreadyEquipped(Character character, Action actionToCheck) {
		boolean equipped = false;
		for (Action action : character.getEquippedActions()) {
			if (action.getClass().equals(actionToCheck.getClass())) {
				equipped = true;
			}
		}
		return equipped;
	}

	public static boolean hitSuccessCheck(CharacterMaker.domain.character.Character character) {
		// give a character a 50/50 chance of hitting opposing character by
		// default
		Random random = new Random();
		MersenneTwister mersenneTwister = new MersenneTwister();
		Boolean chance = mersenneTwister.nextBoolean();

		for (Attribute attribute : character.getAttributes()) {
			// use the character's luck attribute as a gauge of how successful
			// the attempt will be
			if (attribute instanceof Luck) {

				int chances = attribute.getBattleLevel();
				if (chances > 5 && chances < 7) {
					int prob = random.nextInt(100) + 70;
					if (prob >= 100) {
						return true;
					} else {
						return false;
					}
				} else if (chances >= 7 && chances < 10) {
					int prob = random.nextInt(100) + 80;
					if (prob >= 100) {
						return true;
					} else {
						return false;
					}
				} else if (chances >= 10) {
					int prob = random.nextInt(100) + 90;
					if (prob >= 100) {
						return true;
					} else {
						return false;
					}
				}

			}

		}

		return chance;
	}

	/**
	 *  Logic borrowed from
	 *  http://finalfantasy.wikia.com/wiki/Critical_Hit
	 * @param character the character performing the hit
	 * @param character2 the target of the hit
	 * @return boolean successful critical hit
	 */
	public static boolean criticalHitSuccesCheck(CharacterMaker.domain.character.Character character, CharacterMaker.domain.character.Character character2) {
		boolean isCriticalHitSuccess = false;
		Random randomObj = new Random();

		int critical = (getLuckLevelForCharacter(character) + character.getLevel() - character2.getLevel())/4;
		int random = (randomObj.nextInt(65535) * 99/65535) + 1;

		if (random <= critical) {
			isCriticalHitSuccess = true;
			Alert.info(" landing a critical hit ");

		}
		//LOG.debug("random: " + random + " | critical: " + critical);

		return isCriticalHitSuccess;
	}

	public static void incrementAttributeStats(Character character) {
		for (Attribute attribute : character.getAttributes()) {
			if (character instanceof Barbarian && attribute instanceof Strength) {
				attribute.setBattleLevel(attribute.getBattleLevel() + (1 * Barbarian.STRENGTH_MULTIPLIER));
			} else {
				attribute.setBattleLevel(attribute.getBattleLevel() + 1);
			}
		}
	}

	public static boolean isAtLevelUp(Character character) {
		boolean levelUp = false;

		// Characters level up on a logarithmic scale to model an increasing
		// difficulty level
		Double newLevel = Math.cbrt(character.getExperiencePoints());

		int levelsUp = newLevel.intValue() - character.getLevel();

		if (levelsUp > 0) {
			levelUp = true;
			if (levelsUp > 1) {
				do {
					levelUpCharacter(character);
					levelsUp--;
				} while (levelsUp > 1);
			}
		}

		return levelUp;

	}

	public static void levelUpCharacter(Character character) {
		character.setLevel(character.getLevel() + 1);
		CharacterUtils.incrementAttributeStats(character);
		// restore health and get bonus
		Double healthBonus = Math.pow(character.getLevel(), 2);
		character.setHealth(character.getHealth() + healthBonus.intValue());

		// set character's new max health value
		character.setMaxHealth(100 + healthBonus.intValue());

        Alert.infoAboutCharacter(character.getName() + " has just leveled up! Current level: " + character.getLevel(), character);
	}

	public static void resetHealthForCharacter(Character character) {
			character.setHealth(character.getMaxHealth());
	}

	public static int getLuckLevelForCharacter(Character character) {
		int luckLevel = 0;
		for (Attribute attribute : character.getAttributes()) {
			if (attribute instanceof Luck) {
				luckLevel = attribute.getBattleLevel();
			}
		}
		return luckLevel;
	}

	public static int getStrengthLevelForCharacter(Character character) {
		int strengthLevel = 0;
		for (Attribute attribute : character.getAttributes()) {
			if (attribute instanceof Strength) {
				strengthLevel = attribute.getBattleLevel();
			}
		}
		return strengthLevel;
	}

}
