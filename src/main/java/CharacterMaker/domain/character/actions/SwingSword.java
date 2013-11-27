package CharacterMaker.domain.character.actions;

import java.util.Random;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Luck;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.barbarian.Barbarian;

public class SwingSword extends Action {

    public SwingSword(String name, String description, int experienceGainedFromUse, int damage) {
        super(name, description, experienceGainedFromUse, damage);
    }

    public int calculateTotalDomage(Barbarian barbarian) {
		int totalDamage = this.getDamage();
		int strength = 0;
		int dexterity = 0;
		for (Attribute attribute : barbarian.getAttributes()) {
			if (attribute instanceof Strength) {
				strength = attribute.getBattleLevel();
			}
			if (attribute instanceof Dexterity) {
				dexterity = attribute.getBattleLevel();
			}

			totalDamage += (strength / 4) * (dexterity / 4);
			totalDamage += randomBonusDamage(barbarian);
		}
		return totalDamage;
	}

	public int randomBonusDamage(Barbarian barbarian) {
		Random random = new Random();
		int chanceDamage = random.nextInt(1);

		for (Attribute attribute : barbarian.getAttributes()) {
			if (attribute instanceof Luck) {

				int chances = attribute.getBattleLevel();
				if (chances > 5) {
					// they get an extra chance
					int prob = random.nextInt(3);
					chanceDamage += prob;
				} else if (chances > 7 && chances < 10) {
					int prob = random.nextInt(10);
					chanceDamage += prob;
				}

			}

		}

		return chanceDamage;
	}

}
