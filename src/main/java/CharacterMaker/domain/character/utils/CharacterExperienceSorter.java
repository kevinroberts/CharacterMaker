package CharacterMaker.domain.character.utils;

import java.util.Comparator;

import CharacterMaker.domain.character.Character;

/**
 * CharacterStrengthSorter
 * Custom character comparator to sort a list of characters by their Experience
 * attribute level
 *
 * CharacterMaker.domain.character.utils
 * 
 * @author Kevin Roberts date: 12/10/13
 */
public class CharacterExperienceSorter implements Comparator<Character> {

	public int compare(Character character1, Character character2) {
		if (character1.getExperiencePoints() > character2.getExperiencePoints()) {
			return 0;
		} else {
			return 1;
		}
	}
}
