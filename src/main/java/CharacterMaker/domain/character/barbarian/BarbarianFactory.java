package CharacterMaker.domain.character.barbarian;

import java.util.Random;
import java.util.UUID;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.CharacterFactory;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.attributes.*;
import CharacterMaker.domain.character.constants.Constants;
import CharacterMaker.domain.character.naming.CharacterNameService;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class BarbarianFactory implements CharacterFactory {

	@Override
	public Barbarian createCharacter() {
		Barbarian barbarian = new Barbarian();
		CharacterNameService nameService = CharacterNameService.getInstance();
		barbarian.setName(nameService.generateName(barbarian));
		barbarian.setHealth(Constants.DEFAULT_CHARACTER_HEALTH);
		barbarian.setMaxHealth(Constants.DEFAULT_CHARACTER_HEALTH);
		barbarian.setLevel(Constants.DEFAULT_CHARACTER_LEVEL);
		barbarian.setBattlesWon(Constants.DEFAULT_CHARACTER_BATTLES_WON);
		barbarian.setExperiencePoints(Constants.DEFAULT_CHARACTER_XP);
		barbarian.setBattleFought(Constants.DEFAULT_BATTLES_FOUGHT);
        barbarian.setUniqueID(UUID.randomUUID().toString());
		Random random = new Random();

		// Define Base Attributes for a new Barbarian
		Charisma charisma = new Charisma("Charisma", "Barbarian Charisma", random.nextInt(5) + 2);
		Luck luck = new Luck("Luck", "Barbarian Luck", random.nextInt(10) + 3);
		Dexterity dexterity = new Dexterity("Dexterity", "Barbarian dexterity", random.nextInt(5) + 5);
		Intelligence intelligence = new Intelligence(
			"Intelligence",
			"Barbarians are not known for their intelligence. \"Durr I haz beeg shield and er club and i gonna smack you upside dee head\"",
			random.nextInt(2) + 5);
		Stamina stamina = new Stamina("Stamina", "Stamina", random.nextInt(10) + 5);
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



		actions.add(new SwingSword("Barbarian Blade", "The barbarian swings his mighty blade", 3, 10 + random
			.nextInt(5)));

		barbarian.setActions(actions);
		barbarian.setEquippedActions(actions); // set the default action to use in fights

		return barbarian;
	}
}
