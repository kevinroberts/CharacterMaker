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

				System.out.print(barbarian1.getName() + " swings his sword at " + monster2.getName());
				if (CharacterUtils.hitSuccessCheck(barbarian1)) {
					totalDamageDealtFrom1 = swingSword.calculateTotalDamage(barbarian1);
					if (CharacterUtils.criticalHitSuccesCheck(barbarian1, monster2)) {
						totalDamageDealtFrom1 = totalDamageDealtFrom1 * 2;
					}
					System.out.print(" and hits for " + totalDamageDealtFrom1 + " damage\n");
					monster2.setHealth(monster2.getHealth() - totalDamageDealtFrom1);
					barbarian1.setExperiencePoints(barbarian1.getExperiencePoints()
							+ swingSword.getExperienceGainedFromUse());
					isKilledDuringFightCheck(monster2);
				} else {
					System.out.print(" and misses\n");
				}

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

				System.out.print(monster2.getName() + " slashes " + barbarian1.getName());
				if (CharacterUtils.hitSuccessCheck(monster2)) {
					totalDamageDealtFrom2 = slash.calculateTotalDamage(monster2);
					System.out.print(" and hits for " + totalDamageDealtFrom2 + " damage\n");
					barbarian1.setHealth(barbarian1.getHealth() - totalDamageDealtFrom2);
					monster2.setExperiencePoints(monster2.getExperiencePoints()
							+ slash.getExperienceGainedFromUse());
					isKilledDuringFightCheck(barbarian1);
				} else {
					System.out.print(" and misses!\n");
				}

			}
		}

		return processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, barbarian1, monster2);

	}
}
