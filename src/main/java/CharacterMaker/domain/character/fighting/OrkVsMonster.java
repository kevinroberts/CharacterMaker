package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.CharacterUtils;
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
		for (Action action : ork1.getActions()) {
			if (action instanceof SwingSword) {
				SwingSword swingSword = (SwingSword) action;

				System.out.print(ork1.getName() + " swings his sword at " + monster2.getName());
				if (CharacterUtils.hitSuccessCheck(ork1)) {
					totalDamageDealtFrom1 = swingSword.calculateTotalDamage(ork1);
					System.out.print(" and hits for " + totalDamageDealtFrom1 + " damage\n");
					monster2.setHealth(monster2.getHealth() - totalDamageDealtFrom1);
					ork1.setExperiencePoints(ork1.getExperiencePoints()
							+ swingSword.getExperienceGainedFromUse());
					if (monster2.getHealth() < 0) {
						System.out.println(monster2.getName() + " collapses and dies from its injuries.");
					}
				} else {
					System.out.print(" and misses\n");
				}

			}
		}


		if (isDeadCheck(monster2)) {
			return ork1;
		}

		// Monster's actions against Barbarian
		int totalDamageDealtFrom2 = 0;
		for (Action action : monster2.getActions()) {
			if (action instanceof Slash) {
				Slash slash = (Slash) action;

				System.out.print(monster2.getName() + " swings slashes " + ork1.getName());
				if (CharacterUtils.hitSuccessCheck(monster2)) {
					totalDamageDealtFrom2 = slash.calculateTotalDamage(monster2);
					System.out.print(" and hits for " + totalDamageDealtFrom2 + " damage\n");
					ork1.setHealth(ork1.getHealth() - totalDamageDealtFrom2);
					monster2.setExperiencePoints(monster2.getExperiencePoints()
							+ slash.getExperienceGainedFromUse());
					if (ork1.getHealth() <= 0) {
						System.out.println(ork1.getName() + " collapses and dies from his injuries.");
					}
				} else {
					System.out.print(" and misses!\n");
				}

			}
		}

		return processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, ork1, monster2);

	}
}
