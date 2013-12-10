package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.CharacterUtils;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.constants.Constants;
import CharacterMaker.domain.character.monster.Monster;
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
		} else if (character1 instanceof Barbarian && character2 instanceof Ork ) {
			return BarbarianVsOrk.fight((Barbarian) character1, (Ork) character2);
		} else if (character1 instanceof Ork && character2 instanceof Barbarian) {
			return BarbarianVsOrk.fight((Barbarian) character2, (Ork) character1);
		} else if (character1 instanceof Barbarian && character2 instanceof Monster) {
			return BarbarianVsMonster.fight((Barbarian) character1, (Monster) character2);
		} else if (character1 instanceof Monster && character2 instanceof Barbarian) {
			return BarbarianVsMonster.fight((Barbarian) character2, (Monster) character1);
		} else if (character1 instanceof Ork && character2 instanceof Monster) {
			return OrkVsMonster.fight((Ork) character1, (Monster) character2);
		} else if (character2 instanceof Ork && character1 instanceof Monster) {
			return OrkVsMonster.fight((Ork) character2, (Monster) character1);
		}
		else {
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

	public static Character processFightWinner(int totalDamageDealtFrom1, int totalDamageDealtFrom2, Character character1, Character character2) {

		if (totalDamageDealtFrom1 > totalDamageDealtFrom2) {
			System.out.println(character1.getName() + " wins the fight.");
			character1.setBattlesWon(character1.getBattlesWon() + 1);
			// award bonus experience if the characters are near the same
			// skill level
			if (Math.abs(character1.getLevel() - character2.getLevel()) < Constants.CHARACTER_LEVEL_DIFFERENCE_FOR_BONUS_XP) {
				character1.setExperiencePoints(character1.getExperiencePoints() + Constants.XP_FROM_BATTLE_VICTORY);
			}

			return character1;
		} else if (totalDamageDealtFrom2 > totalDamageDealtFrom1) {
			System.out.println(character2.getName() + " wins the fight.");
			character2.setBattlesWon(character2.getBattlesWon() + 1);
			if (Math.abs(character1.getLevel() - character2.getLevel()) < Constants.CHARACTER_LEVEL_DIFFERENCE_FOR_BONUS_XP) {
				character2.setExperiencePoints(character2.getExperiencePoints() + Constants.XP_FROM_BATTLE_VICTORY);
			}
			return character2;
		} else if (totalDamageDealtFrom1 == totalDamageDealtFrom2) {
			// there was a tie in damage dealt -- we determine a random winner based on health / luck
			if (character1.getHealth() > character2.getHealth()) {
				return character1;
			} else if (character2.getHealth() < character1.getHealth()) {
				return character2;
			}

			// if one character is "luckier" than the other return them
			int luckLevel1 = CharacterUtils.getLuckLevelForCharacter(character1);
			int luckLevel2 = CharacterUtils.getLuckLevelForCharacter(character2);
			if (luckLevel1 > luckLevel2) {
				return character1;
			} else {
				return character2;
			}

		} else {
			return null;
		}

	}

}
