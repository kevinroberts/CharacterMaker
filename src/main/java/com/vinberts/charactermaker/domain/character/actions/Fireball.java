package com.vinberts.charactermaker.domain.character.actions;

import com.vinberts.charactermaker.domain.character.Action;
import com.vinberts.charactermaker.domain.character.Character;
import com.vinberts.charactermaker.domain.character.fighting.Fight;
import com.vinberts.charactermaker.domain.character.utils.CharacterUtils;
import com.vinberts.charactermaker.game.messages.Alert;

/**
 * CharacterMaker.domain.character.actions
 *
 * @author Kevin Roberts
 *         date: 1/16/14
 */
public class Fireball extends Action {


	public Fireball(String name, String description, int experienceGainedFromUse, int damage) {
		super(name, description, experienceGainedFromUse, damage);
	}

	@Override
	public int use(com.vinberts.charactermaker.domain.character.Character user, Character victim) {
		Alert.info(user.getName() + " sends a fireball at " + victim.getName(), false);
		int totalDamageDealtFrom2 = 0;
		if (CharacterUtils.hitSuccessCheck(user)) {
			totalDamageDealtFrom2 = this.calculateTotalDamage(user);
			Alert.info(" and hits for " + totalDamageDealtFrom2 + " damage\n", false);
			victim.setHealth(victim.getHealth() - totalDamageDealtFrom2);
			user.setExperiencePoints(user.getExperiencePoints()
					+ this.getExperienceGainedFromUse());
			Fight.isKilledDuringFightCheck(victim);
		} else {
			Alert.info(" and misses!\n", false);
		}

		return totalDamageDealtFrom2;
	}




}
