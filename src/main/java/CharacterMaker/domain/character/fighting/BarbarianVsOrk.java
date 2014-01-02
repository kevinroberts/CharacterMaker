package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.ork.Ork;

/**
 * CharacterMaker.domain.character.fighting
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */

public class BarbarianVsOrk extends Fight {

	public static Character fight(Barbarian barbarian1, Ork ork2) {

		if (isDeadCheck(barbarian1)) {
			return ork2;
		}
		if (isDeadCheck(ork2)) {
			return barbarian1;
		}

		// Barbarian 1 actions
		int totalDamageDealtFrom1 = 0;
		for (Action action : barbarian1.getEquippedActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;
				totalDamageDealtFrom1 = swingSword.use(barbarian1, ork2);
			}
		}

		if (isDeadCheck(ork2)) {
			return barbarian1;
		}

		// Ork 2 actions
		int totalDamageDealtFrom2 = 0;
		for (Action action : ork2.getEquippedActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;
				totalDamageDealtFrom1 = swingSword.use(ork2, barbarian1);
			}
		}

		return processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, barbarian1, ork2);
	}

}
