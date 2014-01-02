package CharacterMaker.domain.character.actions;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.fighting.Fight;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.game.messages.Alert;

public class ShootArrowFromBow extends Action {

	public ShootArrowFromBow(String name, String description, int experienceGainedFromUse, int damage) {
		super(name, description, experienceGainedFromUse, damage);
	}

	@Override
	public int use(Character user, Character victim) {
		Alert.info(user.getName() + " fires an arrow at " + victim.getName(), false);
		int totalDamageDealtFrom1 = 0;
		if (CharacterUtils.hitSuccessCheck(user)) {
			totalDamageDealtFrom1 = this.calculateTotalDamage(user);
			if (CharacterUtils.criticalHitSuccesCheck(user, victim)) {
				totalDamageDealtFrom1 = totalDamageDealtFrom1 * 2;
			}
			Alert.info(" and hits for " + totalDamageDealtFrom1 + " damage\n", false);
			victim.setHealth(victim.getHealth() - totalDamageDealtFrom1);
			user.setExperiencePoints(user.getExperiencePoints()
					+ this.getExperienceGainedFromUse());
			Fight.isKilledDuringFightCheck(victim);
		} else {
			Alert.info(" and misses\n", false);
		}

		return totalDamageDealtFrom1;
	}

	@Override
	public int calculateTotalDamage(Character character) {
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
