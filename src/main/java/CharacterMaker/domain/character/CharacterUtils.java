package CharacterMaker.domain.character;

import java.util.Random;

import CharacterMaker.domain.character.attributes.Luck;
import CharacterMaker.domain.character.barbarian.Barbarian;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class CharacterUtils {

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
				if (chances > 5) {
					int prob = random.nextInt(100) + 70;
					if (prob >= 100) {
						return true;
					} else {
						return false;
					}
				} else if (chances > 7 && chances < 10) {
					int prob = random.nextInt(100) + 80;
					if (prob >= 100) {
						return true;
					} else {
						return false;
					}
				} else if (chances > 10) {
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

}
