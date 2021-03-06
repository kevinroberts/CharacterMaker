package com.vinberts.charactermaker.domain.character.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.vinberts.charactermaker.domain.character.Character;
import com.vinberts.charactermaker.domain.character.actions.ShootArrowFromBow;
import com.vinberts.charactermaker.domain.character.attributes.Luck;
import com.vinberts.charactermaker.domain.character.attributes.Stamina;
import com.vinberts.charactermaker.domain.character.attributes.Strength;
import com.vinberts.charactermaker.domain.character.barbarian.Barbarian;
import com.vinberts.charactermaker.domain.character.constants.Constants;
import com.vinberts.charactermaker.domain.character.items.BasicHealthPotion;
import com.vinberts.charactermaker.domain.character.monster.Monster;
import com.vinberts.charactermaker.game.messages.Alert;

import com.google.common.collect.ConcurrentHashMultiset;
import com.google.common.collect.Multiset;
import com.vinberts.charactermaker.domain.character.*;

public class CharacterUtils {

	public CharacterUtils() {
	}

	/**
	 * Given a set list of Characters types, return all the ones with duplicate
	 * names.
	 * 
	 * @param characterList the list of characters to check for dupes
	 * @return List of Characters with Duplicate Names
	 */
	public static List<com.vinberts.charactermaker.domain.character.Character> findDuplicateNames(List<? extends Character> characterList) {
		List<Character> characterDupes = new ArrayList<Character>();

		for (int i = 0; i < characterList.size(); i++) {
			Character character1 = (Character) characterList.toArray()[i];

			for (int j = i + 1; j < characterList.size(); j++) {
				Character character2 = (Character) characterList.toArray()[j];

				if (i != j && character1.getName().equals(character2.getName())) {
					characterDupes.add(character1);
				}
			}

		}

		return characterDupes;
	}

	public static Character battleRoyaleWithOtherCharacters(List<? extends Character> characterList, String idToTrack) {
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

	public static Character battleRoyaleWithMonsters(List<? extends Character> characterList, String idToTrack) {
		Character victor;
		MersenneTwister mersenneTwister = new MersenneTwister();
		CharacterFactory characterFactory = new CharacterFactory();

		int rounds = 1;
		int madeItRound = 0;

		while (characterList.size() > 1) {
			Alert.info("Round: " + rounds);
			int randomSelection1 = mersenneTwister.nextInt(characterList.size());

			Character character1 = characterList.get(randomSelection1);
			Monster monster = (Monster) characterFactory.createCharacter(Constants.MONSTER);

			if (character1.getLevel() > 1) {
				for (int i = 0; i < character1.getLevel(); i++) {
					monster.train();
				}
			}
			// add extra training for higher levels
			if (character1.getLevel() > 10) {
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

	public static boolean equipActionForCharacter(Character character, Action actionToEquip) {
		// ensure action is not already equipped
		boolean equipped = isActionAlreadyEquipped(character, actionToEquip);

		if (!equipped) {
			Multiset<Action> actions = character.getEquippedActions();
			actions.add(actionToEquip);
			character.setEquippedActions(actions);
		}

		return !equipped;
	}

	public static void addActionForCharacter(Character character, Action actionToAdd) {
		// ensure action is not already equipped
		boolean added = doesCharacterHaveAction(character, actionToAdd);

		if (!added) {
			Multiset<Action> actions = character.getActions();
			actions.add(actionToAdd);
			character.setActions(actions);
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

	public static boolean doesCharacterHaveAction(Character character, Action actionToCheck) {
		boolean hasAction = false;
		for (Action action : character.getActions()) {
			if (action.getClass().equals(actionToCheck.getClass())) {
				hasAction = true;
			}
		}
		return hasAction;
	}

	public static boolean hitSuccessCheck(Character character) {
		// give a character a 50/50 chance of hitting opposing character by
		// default
		Random random = new Random();
		// MersenneTwister mersenneTwister = new MersenneTwister();
		Boolean chance = random.nextBoolean();

		for (Attribute attribute : character.getAttributes()) {
			// use the character's luck attribute as a gauge of how successful
			// the attempt will be
			if (attribute instanceof Luck) {

				int chances = attribute.getBattleLevel();
				if (chances > 5 && chances < 7) {
					int prob = random.nextInt(100) + 70;
					if (prob >= 100) {
						return true;
					}
				} else if (chances >= 7 && chances < 10) {
					int prob = random.nextInt(100) + 80;
					if (prob >= 100) {
						return true;
					}
				} else if (chances >= 10 && chances < 20) {
					int prob = random.nextInt(100) + 85;
					if (prob >= 100) {
						return true;
					}
				} else if (chances >= 20) {
					int prob = random.nextInt(100) + 90;
					if (prob >= 100) {
						return true;
					}
				}
				return false;
			}

		}

		return chance;
	}

	/**
	 * Logic borrowed from http://finalfantasy.wikia.com/wiki/Critical_Hit
	 * 
	 * @param character the character performing the hit
	 * @param character2 the target of the hit
	 * @return boolean successful critical hit
	 */
	public static boolean criticalHitSuccesCheck(Character character,
		Character character2) {
		boolean isCriticalHitSuccess = false;
		Random randomObj = new Random();

		int critical = (getLuckLevelForCharacter(character) + character.getLevel() - character2.getLevel()) / 4;
		int random = (randomObj.nextInt(65535) * 99 / 65535) + 1;

		if (random <= critical) {
			isCriticalHitSuccess = true;
			Alert.info(" landing a critical hit ");
			Alert.playSound("crtihit.wav");

		}
		// LOG.debug("random: " + random + " | critical: " + critical);

		return isCriticalHitSuccess;
	}

    public static void determineLootDrop(Character character) {
        // determine random success for drop
        Random randomObj = new Random();
        // create loot drop
        final Integer healthPotionLevel = character.getMaxHealth() / 5;
        BasicHealthPotion healthPotion = new BasicHealthPotion("Health Potion (size " + healthPotionLevel +")", healthPotionLevel);

        if (character.getLevel() < 5) {
            if (randomObj.nextBoolean()) {
                Alert.infoAboutCharacter("You've found a new " + healthPotion.getName() + " item", character);
                ConcurrentHashMultiset<Item> items = character.getItems();
                items.add(healthPotion);
                character.setItems(items);
            } else {
                Alert.infoAboutCharacter("No random items found on dead monster.", character);
            }
        } else {
            // for higher level characters drops are more rare
            double d = randomObj.nextDouble();
            if (d < .7) {
                // 20% chance
                Alert.infoAboutCharacter("You've found a new " + healthPotion.getName() + " item", character);
                ConcurrentHashMultiset<Item> items = character.getItems();
                items.add(healthPotion);
                character.setItems(items);
            } else {
                Alert.infoAboutCharacter("No random items found on dead monster.", character);
            }

        }
    }

	public static void incrementAttributeStats(Character character) {
		for (Attribute attribute : character.getAttributes()) {
			if (character instanceof Barbarian && attribute instanceof Strength) {
				attribute.setBattleLevel(attribute.getBattleLevel() + (Barbarian.STRENGTH_MULTIPLIER));
			} else {
				attribute.setBattleLevel(attribute.getBattleLevel() + 1);
			}
		}
	}

	public static boolean isAtLevelUp(Character character) {
		boolean levelUp = false;

		// Characters level up on a logarithmic scale - modelling increasing
		// difficulty
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
		Double healthBonus = Math.pow(character.getLevel(), 2) + getStaminaLevelForCharacter(character);

		character.setHealth(character.getHealth() + healthBonus.intValue());

		// set character's new max health value
		// Total HP = (baseHP * (Stamina + 40)) / 40
		character.setMaxHealth((character.getMaxHealth() * (getStaminaLevelForCharacter(character) + 40)) / 40);

		// don't exceed the characters max health
		if (character.getHealth() > character.getMaxHealth()) {
			character.setHealth(character.getMaxHealth());
		}

		Alert.infoAboutCharacter(character.getName() + " has just leveled up! Current level: " + character.getLevel()
			+ " and have been granted " + healthBonus + " bonus points of health.", character);

		// grant new actions on level ups
		if (character instanceof Barbarian && character.getLevel() > 5) {
			ShootArrowFromBow shootArrowFromBow = new ShootArrowFromBow("Bow and Arrows", "Barbarian bow", 2, 12);
			if (!doesCharacterHaveAction(character, shootArrowFromBow)) {
				addActionForCharacter(character, shootArrowFromBow);
				Alert
					.infoAboutCharacter(character.getName()
						+ " has just found a bow and arrow! You may equip it to do additional damage in fights.",
						character);
			}

		}



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

	public static int getStaminaLevelForCharacter(Character character) {
		int staminaLevel = 0;
		for (Attribute attribute : character.getAttributes()) {
			if (attribute instanceof Stamina) {
				staminaLevel = attribute.getBattleLevel();
			}
		}
		return staminaLevel;
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

	public static Action getRandomAction(Multiset<Action> actionMultiset) {
		if (actionMultiset.size() == 1) {
			return (Action) actionMultiset.toArray()[0];
		} else if (actionMultiset.size() > 1) {
			Random random = new Random();
			return (Action) actionMultiset.toArray()[random.nextInt(actionMultiset.size())];
		}
		return null;
	}

}
