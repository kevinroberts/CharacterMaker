package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.domain.character.actions.Slash;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.ork.Ork;

/**
 * CharacterMaker.domain.character.fighting
 *
 * @author Kevin Roberts
 *         date: 12/10/13
 */
public class OrkVsMonster extends Fight {

	public static CharacterMaker.domain.character.Character fight(Ork ork1, Monster monster2) {

		if (isDeadCheck(ork1)) {
			return monster2;
		}
		if (isDeadCheck(monster2)) {
			return ork1;
		}

		// Barbarian 1 actions against monster2
		int totalDamageDealtFrom1 = 0;
		for (Action action : ork1.getEquippedActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;
				totalDamageDealtFrom1 = swingSword.use(ork1, monster2);
			}
		}


		if (isDeadCheck(monster2)) {
			return ork1;
		}

		// Monster's actions against Barbarian
		int totalDamageDealtFrom2 = 0;
		for (Action action : monster2.getEquippedActions()) {
			if (action instanceof Slash) {
				Slash slash = (Slash) action;
				totalDamageDealtFrom2 = slash.use(monster2, ork1);
			}
		}

		return processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, ork1, monster2);

	}
}
