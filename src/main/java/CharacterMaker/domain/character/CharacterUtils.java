package CharacterMaker.domain.character;

import java.util.Random;

import CharacterMaker.domain.character.attributes.Luck;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.barbarian.Barbarian;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class CharacterUtils {

	public static int XP_FROM_BATTLE_VICTORY = 20;
	public static int XP_FROM_TRAINING = 10;

	public CharacterUtils() {
	}

	public static Multiset<Barbarian> findDuplicateNames(Multiset<Barbarian> barbarianMultiset) {
		Multiset<Barbarian> barbarianDupes = HashMultiset.create();

		for (int i = 0; i < barbarianMultiset.size(); i++) {
			Barbarian barbarian1 = (Barbarian) barbarianMultiset.toArray()[i];

			for (int j = i + 1; j < barbarianMultiset.size(); j++) {
				Barbarian barbarian2 = (Barbarian) barbarianMultiset.toArray()[j];

				if (i != j && barbarian1.getName().equals(barbarian2.getName())) {
					barbarianDupes.add(barbarian1);
				}
			}

		}

		return barbarianDupes;
	}

	public static void printCharactersByName(Multiset<Barbarian> characters) {
		for (Barbarian character : characters) {
			System.out.println(character.getName());
		}
	}

	public static boolean hitSuccessCheck(Character character) {
		// give a character a 50/50 chance of hitting opposing character by
		// default
		Random random = new Random();
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
        try {
            if (character.getExperiencePoints() < 1000 && character.getExperiencePoints() > 99) {
                int leftMostDigit = character.getExperiencePoints();
                while (leftMostDigit >= 10) {
                    leftMostDigit /= 10;
                }
                // if the left digit equals -- they should be leveled up
                if (leftMostDigit == character.getLevel()) {
                    levelUp = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return levelUp;

    }

	public static void levelUpCharacter(Character character) {
			character.setLevel(character.getLevel() + 1);
			CharacterUtils.incrementAttributeStats(character);
            // restore health and get bonus
            character.setHealth(100 + character.getLevel());
			System.out.println(character.getName() + " has just leveled up! Current level: " + character.getLevel());
	}

}
