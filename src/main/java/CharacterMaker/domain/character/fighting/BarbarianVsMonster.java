package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.domain.character.actions.Slash;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.monster.Monster;

/**
 * CharacterMaker.domain.character.fighting
 *
 * @author Kevin Roberts
 *         date: 12/10/13
 */
public class BarbarianVsMonster extends Fight {

	public static CharacterMaker.domain.character.Character fight(Barbarian barbarian1, Monster monster2) {

		if (isDeadCheck(barbarian1)) {
			return monster2;
		}
		if (isDeadCheck(monster2)) {
			return barbarian1;
		}

		// Barbarian 1 actions against monster2
		int totalDamageDealtFrom1 = 0;
		for (Action action : barbarian1.getActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;
				totalDamageDealtFrom1 = swingSword.use(barbarian1, monster2);
			}
		}


		if (isDeadCheck(monster2)) {
			return barbarian1;
		}

		// Monster's actions against Barbarian
		int totalDamageDealtFrom2 = 0;
		for (Action action : monster2.getActions()) {
			if (action instanceof Slash) {
				Slash slash = (Slash) action;
				totalDamageDealtFrom2 = slash.use(monster2, barbarian1);
			}
		}

		return processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, barbarian1, monster2);

	}
}
