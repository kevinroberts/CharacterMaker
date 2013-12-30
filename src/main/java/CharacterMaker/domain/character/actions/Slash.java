package CharacterMaker.domain.character.actions;

import CharacterMaker.domain.character.*;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.fighting.Fight;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.game.messages.Alert;

/**
 * CharacterMaker.domain.character.actions
 * 
 * @author Kevin Roberts date: 12/5/13
 */
public class Slash extends Action {

	public Slash(String name, String description, int experienceGainedFromUse, int damage) {
		super(name, description, experienceGainedFromUse, damage);
	}

	@Override
	public int use(Character user, Character victim) {
		Alert.info(user.getName() + " slashes " + victim.getName(), false);
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

	@Override
	public int calculateTotalDamage(CharacterMaker.domain.character.Character character) {
		int totalDamage = this.getDamage();
		int strength = 0;
		int dexterity = 0;
		for (Attribute attribute : character.getAttributes()) {
			if (attribute instanceof Strength) {
				strength = attribute.getBattleLevel();
			}
			if (attribute instanceof Dexterity) {
				dexterity = attribute.getBattleLevel();
			}
		}

		totalDamage += character.getLevel() * character.getLevel() + ((strength / 4) + (dexterity / 4));

		return totalDamage;
	}

}
