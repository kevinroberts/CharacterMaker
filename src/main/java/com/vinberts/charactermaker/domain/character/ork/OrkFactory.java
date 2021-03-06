package com.vinberts.charactermaker.domain.character.ork;

import java.util.Random;

import com.vinberts.charactermaker.domain.character.Action;
import com.vinberts.charactermaker.domain.character.Attribute;
import com.vinberts.charactermaker.domain.character.CharacterFactory;
import com.vinberts.charactermaker.domain.character.actions.SwingSword;
import com.vinberts.charactermaker.domain.character.attributes.*;
import com.vinberts.charactermaker.domain.character.naming.CharacterNameService;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * CharacterMaker.domain.character.ork
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */

public class OrkFactory extends CharacterFactory {

	/**
	 * Protected factory for creating new Orks - should only be accessed from inside the Ork character class
	 * @return new Ork
	 */
	protected Ork createCharacter() {
		Ork ork = new Ork();
		CharacterNameService nameService = CharacterNameService.getInstance();
		ork.setName(nameService.generateName(ork));

		Random random = new Random();

		// Define Base Attributes for a new Ork
		Charisma charisma = new Charisma("Charisma", "Ork Charisma", random.nextInt(5) + 2);
		Luck luck = new Luck("Luck", "Ork Luck", random.nextInt(4) + 3);
		Dexterity dexterity = new Dexterity("Dexterity", "Ork dexterity", random.nextInt(5) + 5);
		Intelligence intelligence = new Intelligence(
			"Intelligence",
			"Orks are not known for their intelligence. \"Durr I haz beeg shield and er club and i gonna smack you upside dee head\"",
			random.nextInt(1) + 3);
		Stamina stamina = new Stamina("Stamina", "Ork Stamina", random.nextInt(10) + 5);
		Strength strength = new Strength("Strength", "Ork Strength", random.nextInt(5) + 5);

		Multiset<Attribute> attributes = HashMultiset.create();

		attributes.add(charisma);
		attributes.add(strength);
		attributes.add(luck);
		attributes.add(dexterity);
		attributes.add(intelligence);
		attributes.add(stamina);

		ork.setAttributes(attributes);

		// Define Base Actions for a new Barbarian
		Multiset<Action> actions = HashMultiset.create();

		actions.add(new SwingSword("Ork Blade", "The ork swings his mighty blade", 3, 8 + random.nextInt(9)));

		ork.setActions(actions);
		ork.setEquippedActions(actions); // set the default action to use in fights

		return ork;
	}
}
