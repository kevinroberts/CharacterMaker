package CharacterMaker.domain.character.actions;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Strength;

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
