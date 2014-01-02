package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.barbarian.Barbarian;

/**
 * CharacterMaker.domain.character.fighting
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */

public class BarbarianVsBarbarian extends Fight {

	public static Barbarian fight(Barbarian barbarian1, Barbarian barbarian2) {

		if (isDeadCheck(barbarian1)) {
			return barbarian2;
		}
		if (isDeadCheck(barbarian2)) {
			return barbarian1;
		}

		// Barbarian 1 actions
		int totalDamageDealtFrom1 = 0;
		for (Action action : barbarian1.getEquippedActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;
				totalDamageDealtFrom1 = swingSword.use(barbarian1, barbarian2);
			}
		}

		if (isDeadCheck(barbarian2)) {
			return barbarian1;
		}

		// Barbarian 2 actions
		int totalDamageDealtFrom2 = 0;
		for (Action action : barbarian2.getEquippedActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;
				totalDamageDealtFrom1 = swingSword.use(barbarian2, barbarian1);
			}
		}

		return (Barbarian) processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, barbarian1, barbarian2);


	}
}
