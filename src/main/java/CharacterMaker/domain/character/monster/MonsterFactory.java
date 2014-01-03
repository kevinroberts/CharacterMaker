package CharacterMaker.domain.character.monster;

import java.util.Random;
import java.util.UUID;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.CharacterFactory;
import CharacterMaker.domain.character.actions.Slash;
import CharacterMaker.domain.character.attributes.*;
import CharacterMaker.domain.character.constants.Constants;
import CharacterMaker.domain.character.naming.CharacterNameService;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

/**
 * CharacterMaker.domain.character.monster
 * 
 * @author Kevin Roberts 12/5/13
 */
public class MonsterFactory implements CharacterFactory {

	@Override
	public Monster createCharacter() {
		Monster monster = new Monster();
		CharacterNameService nameService = CharacterNameService.getInstance();
		monster.setName(nameService.generateName(monster));
		monster.setHealth(Constants.DEFAULT_CHARACTER_HEALTH);
		monster.setMaxHealth(Constants.DEFAULT_CHARACTER_HEALTH);
		monster.setLevel(Constants.DEFAULT_CHARACTER_LEVEL);
		monster.setExperiencePoints(Constants.DEFAULT_CHARACTER_XP);
		monster.setBattlesWon(Constants.DEFAULT_CHARACTER_BATTLES_WON);
		monster.setBattleFought(Constants.DEFAULT_BATTLES_FOUGHT);
		monster.setUniqueID(UUID.randomUUID().toString());
		Random random = new Random();

		// Define Base Attributes for a new Monster
		Charisma charisma = new Charisma("Charisma", "Monster Charisma", random.nextInt(5) + 2);
		Luck luck = new Luck("Luck", "Monster Luck", random.nextInt(4) + 3);
		Dexterity dexterity = new Dexterity("Dexterity", "Monster dexterity", random.nextInt(5) + 5);
		Intelligence intelligence = new Intelligence("Intelligence", "Monsters Intelligence", random.nextInt(1) + 3);
		Stamina stamina = new Stamina("Stamina", "Monster Stamina",  5);
		Strength strength = new Strength("Strength", "Monster Strength", random.nextInt(5) + 2);

		Multiset<Attribute> attributes = HashMultiset.create();

		attributes.add(charisma);
		attributes.add(strength);
		attributes.add(luck);
		attributes.add(dexterity);
		attributes.add(intelligence);
		attributes.add(stamina);

		monster.setAttributes(attributes);

		Multiset<Action> actions = HashMultiset.create();

		actions.add(new Slash("Slash", monster.getName() + " attacks with a brutal slash", 3, 5 + random.nextInt(5)));

		monster.setActions(actions);
		monster.setEquippedActions(actions); // set the default action to use in fights

		return monster;

	}

}
