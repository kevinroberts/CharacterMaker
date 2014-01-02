package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.monster.Monster;
import CharacterMaker.domain.character.utils.CharacterUtils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * CharacterMaker.domain.character.fighting
 * 
 * @author Kevin Roberts date: 12/10/13
 */
public class BarbarianVsMonster extends Fight {

	public static CharacterMaker.domain.character.Character fight(Barbarian barbarian1, Monster monster2) {

		if (isDeadCheck(barbarian1)) {
			return monster2;
		}
		if (isDeadCheck(monster2)) {
			return barbarian1;
		}
		// total damage tracking variables
		int totalDamageDealtFrom1 = 0;
		int totalDamageDealtFrom2 = 0;

		// clone the available actions for each character
		Multiset<Action> availableActionsForBarbarian1 = HashMultiset.create();

		for (Action action : barbarian1.getEquippedActions()) {
			availableActionsForBarbarian1.add(action);
		}

		Multiset<Action> availableActionsForMonster2 = HashMultiset.create();

		for (Action action : monster2.getEquippedActions()) {
			availableActionsForMonster2.add(action);
		}

		// Fight back and forth until each character runs out of actions
		while (availableActionsForBarbarian1.size() > 0 || availableActionsForMonster2.size() > 0) {
			// get a random action and use it from the barbarian
			Action action1 = CharacterUtils.getRandomAction(availableActionsForBarbarian1);
			if (action1 != null) {
				totalDamageDealtFrom1 = action1.use(barbarian1, monster2);
				availableActionsForBarbarian1.remove(action1);
			}
			if (isDeadCheck(monster2)) {
				return barbarian1;
			}
			Action action2 = CharacterUtils.getRandomAction(availableActionsForMonster2);
			if (action2 != null) {
				totalDamageDealtFrom2 = action2.use(monster2, barbarian1);
				availableActionsForMonster2.remove(action2);
			}
			if (isDeadCheck(barbarian1)) {
				return barbarian1;
			}

		}

		return processFightWinner(totalDamageDealtFrom1, totalDamageDealtFrom2, barbarian1, monster2);

	}
}
