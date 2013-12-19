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
		for (Action action : barbarian1.getActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;

				System.out.print(barbarian1.getName() + " swings his sword at " + barbarian2.getName());
				if (CharacterUtils.hitSuccessCheck(barbarian1)) {
					totalDamageDealtFrom1 = swingSword.calculateTotalDamage(barbarian1);
					if (CharacterUtils.criticalHitSuccesCheck(barbarian1, barbarian2)) {
						totalDamageDealtFrom1 = totalDamageDealtFrom1 * 2;
					}
					System.out.print(" and hits for " + totalDamageDealtFrom1 + " damage\n");
					barbarian2.setHealth(barbarian2.getHealth() - totalDamageDealtFrom1);
					barbarian1.setExperiencePoints(barbarian1.getExperiencePoints()
						+ swingSword.getExperienceGainedFromUse());
					if (barbarian2.getHealth() < 0) {
						System.out.println(barbarian2.getName() + " collapses and dies from his injuries.");
					}
				} else {
					System.out.print(" and misses\n");
				}

			}
		}

		if (isDeadCheck(barbarian2)) {
			return barbarian1;
		}

		// Barbarian 2 actions
		int totalDamageDealtFrom2 = 0;
		for (Action action : barbarian2.getActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;

				System.out.print(barbarian2.getName() + " swings his sword at " + barbarian1.getName());
				if (CharacterUtils.hitSuccessCheck(barbarian2)) {
					totalDamageDealtFrom2 = swingSword.calculateTotalDamage(barbarian2);
					if (CharacterUtils.criticalHitSuccesCheck(barbarian2, barbarian1)) {
						totalDamageDealtFrom2 = totalDamageDealtFrom2 * 2;
					}
					System.out.print(" and hits for " + totalDamageDealtFrom2 + " damage\n");
					barbarian1.setHealth(barbarian1.getHealth() - totalDamageDealtFrom2);
					barbarian2.setExperiencePoints(barbarian2.getExperiencePoints()
						+ swingSword.getExperienceGainedFromUse());
					if (barbarian1.getHealth() <= 0) {
						System.out.println(barbarian1.getName() + " collapses and dies from his injuries.");
					}
				} else {
					System.out.print(" and misses!\n");
				}

			}
		}

		return (Barbarian) processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, barbarian1, barbarian2);


	}
}
