package CharacterMaker.domain.character.actions;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.fighting.Fight;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.game.messages.Alert;

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
	public int use(Character user, Character victim) {
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
