package CharacterMaker.domain.character.actions;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.barbarian.Barbarian;

public class SwingSword extends Action {

	public SwingSword(String name, String description, int experienceGainedFromUse, int damage) {
		super(name, description, experienceGainedFromUse, damage);
	}

}
