package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.ork.Ork;

/**
 * CharacterMaker.domain.character.fighting
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */

public abstract class Fight {

	public static Character fight(Character character1, Character character2) {
		if (character1 instanceof Barbarian && character2 instanceof Barbarian) {
			return BarbarianVsBarbarian.fight((Barbarian) character1, (Barbarian) character2);
		} else if (character1 instanceof Barbarian && character2 instanceof Ork) {
			return BarbarianVsOrk.fight((Barbarian) character1, (Ork) character2);
		} else {
			return null;
		}
	}

	public static boolean isDeadCheck(Character character) {
		if (character.getHealth() <= 0) {
			System.out.println(character.getName() + " is dead and cannot fight until revived.");
			return true;
		} else {
			return false;
		}
	}

}
