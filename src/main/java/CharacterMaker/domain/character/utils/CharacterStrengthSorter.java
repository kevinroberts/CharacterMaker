package CharacterMaker.domain.character.utils;

import java.util.Comparator;

import CharacterMaker.domain.character.Character;

/**
 * CharacterStrengthSorter
 * Custom character comparator to sort a list of characters by their strength
 * attribute level
 *
 * CharacterMaker.domain.character.utils
 * 
 * @author Kevin Roberts date: 12/10/13
 */
public class CharacterStrengthSorter implements Comparator<Character> {

	public int compare(Character character1, Character character2) {
		int strengthLevel1 = CharacterUtils.getStrengthLevelForCharacter(character1);
		int strengthLevel2 = CharacterUtils.getStrengthLevelForCharacter(character2);
		if (strengthLevel1 > strengthLevel2) {
			return 0;
		} else {
			return 1;
		}
	}
}
