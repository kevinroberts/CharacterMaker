package com.vinberts.charactermaker.domain.character.fighting;

import com.vinberts.charactermaker.domain.character.Action;
import com.vinberts.charactermaker.domain.character.Character;
import com.vinberts.charactermaker.domain.character.constants.Constants;
import com.vinberts.charactermaker.domain.character.monster.Monster;
import com.vinberts.charactermaker.domain.character.utils.CharacterUtils;
import com.vinberts.charactermaker.game.messages.Alert;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * CharacterMaker.domain.character.fighting
 *
 * @author Kevin Roberts Date: 12/02/2013
 */
public abstract class Fight {
	// total damage tracking variables
	private static int totalDamageDealtFrom1;
	private static int totalDamageDealtFrom2;

	public static Character fight(Character character1, Character character2) {
		if (isDeadCheck(character1)) {
			return character2;
		}
		if (isDeadCheck(character2)) {
			return character1;
		}

		totalDamageDealtFrom1 = 0;
		totalDamageDealtFrom2 = 0;

		// stamina tracking
		int staminaForCharacter1 = CharacterUtils.getStaminaLevelForCharacter(character1);
		int staminaForCharacter2 = CharacterUtils.getStaminaLevelForCharacter(character2);

		// clone the available actions for each character
		Multiset<Action> availableActionsForCharacter1 = HashMultiset.create();

		for (Action action : character1.getEquippedActions()) {
			availableActionsForCharacter1.add(action);
		}

		Multiset<Action> availableActionsForCharacter2 = HashMultiset.create();

		for (Action action : character2.getEquippedActions()) {
			availableActionsForCharacter2.add(action);
		}
		boolean isOutOfStamina1 = false;
		boolean isOutOfStamina2 = false;

		// Fight back and forth until each character runs out of actions or stamina (whichever comes first)
		while ((availableActionsForCharacter1.size() > 0 || availableActionsForCharacter2.size() > 0) &&
				(staminaForCharacter1 > 0 || staminaForCharacter2 > 0)) {
			// subtract stamina from character 1 on use of action
			staminaForCharacter1 = staminaForCharacter1 - Constants.STAMINA_USE_FROM_ACTION;
			//LOG.debug("stamina for character " + character1.getName() + " is now " + staminaForCharacter1);
			if (staminaForCharacter1 <= 0 && !isOutOfStamina1 && availableActionsForCharacter1.size() > 0) {
				isOutOfStamina1 = true;
				Alert.info(character1.getName() + " ran out of stamina before he could finish his attacks");
			}
			// check if character 1 ran out of stamina - if so end his actions
			if (staminaForCharacter1 > 0) {
				// get a random action and use it from the first character
				Action action1 = CharacterUtils.getRandomAction(availableActionsForCharacter1);
				if (action1 != null) {
					totalDamageDealtFrom1 += action1.use(character1, character2);
					availableActionsForCharacter1.remove(action1);
				}
				if (isDeadCheck(character2)) {
					return character1;
				}
			}


			// subtract stamina from character 2 on use of action
			staminaForCharacter2 = staminaForCharacter2 - Constants.STAMINA_USE_FROM_ACTION;

			if (staminaForCharacter2 <= 0 && !isOutOfStamina2 && availableActionsForCharacter2.size() > 0) {
				isOutOfStamina2 = true;
				Alert.info(character2.getName() + " ran out of stamina before he could finish his attacks");
			}

			// check if character 2 ran out of stamina - if so end his actions
			if (staminaForCharacter2 > 0) {
				Action action2 = CharacterUtils.getRandomAction(availableActionsForCharacter2);
				if (action2 != null) {
					totalDamageDealtFrom2 += action2.use(character2, character1);
					availableActionsForCharacter2.remove(action2);
				}
				if (isDeadCheck(character1)) {
					return character1;
				}
			}


		}

		return processFightWinner(character1, character2);
	}

	public static boolean isDeadCheck(Character character) {
		if (character.getHealth() <= 0) {
			Alert.info(character.getName() + " is dead and cannot fight until revived.");
			return true;
		} else {
			return false;
		}
	}

	public static void isKilledDuringFightCheck(Character character) {
		if (character.getHealth() <= 0) {
			if (character instanceof Monster) {
				// are monsters truly genderless - is this sexist to monsters?
				Alert.info(character.getName() + " collapses and dies from its injuries.");
			} else {
				Alert.info(character.getName() + " collapses and dies from his injuries.");
				Alert.playSound("death.wav");
			}
		}
	}

	public static Character processFightWinner(Character character1, Character character2) {

		character1.setBattleFought(character1.getBattleFought() + 1);
		character2.setBattleFought(character2.getBattleFought() + 1);

		if (totalDamageDealtFrom1 > totalDamageDealtFrom2) {
			Alert.info(character1.getName() + " wins the fight.");
			character1.setBattlesWon(character1.getBattlesWon() + 1);
			// award bonus experience if the characters are near the same
			// skill level
			if (Math.abs(character1.getLevel() - character2.getLevel()) < Constants.CHARACTER_LEVEL_DIFFERENCE_FOR_BONUS_XP) {
				character1.setExperiencePoints(character1.getExperiencePoints() + Constants.XP_FROM_BATTLE_VICTORY);
			}

			return character1;
		} else if (totalDamageDealtFrom2 > totalDamageDealtFrom1) {
			Alert.info(character2.getName() + " wins the fight.");
			character2.setBattlesWon(character2.getBattlesWon() + 1);
			if (Math.abs(character1.getLevel() - character2.getLevel()) < Constants.CHARACTER_LEVEL_DIFFERENCE_FOR_BONUS_XP) {
				character2.setExperiencePoints(character2.getExperiencePoints() + Constants.XP_FROM_BATTLE_VICTORY);
			}
			return character2;
		} else if (totalDamageDealtFrom1 == totalDamageDealtFrom2) {
			// there was a tie in damage dealt -- we determine a random winner
			// based on health / luck
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
