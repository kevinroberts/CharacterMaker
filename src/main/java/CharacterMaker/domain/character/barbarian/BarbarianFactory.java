package CharacterMaker.domain.character.barbarian;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.CharacterFactory;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.attributes.*;
import CharacterMaker.domain.character.naming.CharacterNameService;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Random;

public class BarbarianFactory extends CharacterFactory {

	public Barbarian createCharacter() {
		Barbarian barbarian = new Barbarian();
		CharacterNameService nameService = CharacterNameService.getInstance();
		barbarian.setName(nameService.generateName(barbarian));
		Random random = new Random();

		// Define Base Attributes for a new Barbarian
		Charisma charisma = new Charisma("Charisma", "Barbarian Charisma", random.nextInt(5) + 2);
		Luck luck = new Luck("Luck", "Barbarian Luck", random.nextInt(10) + 3);
		Dexterity dexterity = new Dexterity("Dexterity", "Barbarian dexterity", random.nextInt(5) + 5);
		Intelligence intelligence = new Intelligence(
			"Intelligence",
			"Barbarians are not known for their intelligence. \"Durr I haz beeg shield and er club and i gonna smack you upside dee head\"",
			random.nextInt(2) + 5);
		Stamina stamina = new Stamina("Stamina", "Stamina determines how many actions you can perform per fight", random.nextInt(10) + 5);
		Strength strength = new Strength("Strength", "Barbarian Strength", random.nextInt(5) + 10);

		Multiset<Attribute> attributes = HashMultiset.create();

		attributes.add(charisma);
		attributes.add(strength);
		attributes.add(luck);
		attributes.add(dexterity);
		attributes.add(intelligence);
		attributes.add(stamina);

		barbarian.setAttributes(attributes);

		// Define Base Actions for a new Barbarian
		Multiset<Action> actions = HashMultiset.create();
		Multiset<Action> equippedActions = HashMultiset.create();


		SwingSword swingSword = new SwingSword("Barbarian Blade", "The barbarian swings his mighty blade", 3, 10 + random
				.nextInt(5));

		actions.add(swingSword);
		equippedActions.add(swingSword);

		barbarian.setActions(actions);
		barbarian.setEquippedActions(equippedActions); // set the default action to use in fights

		return barbarian;
	}
}
