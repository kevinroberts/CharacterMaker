package com.vinberts.charactermaker.domain.character.monster;

import java.util.List;
import java.util.Random;

import com.vinberts.charactermaker.domain.character.Action;
import com.vinberts.charactermaker.domain.character.Attribute;
import com.vinberts.charactermaker.domain.character.CharacterFactory;
import com.vinberts.charactermaker.domain.character.actions.Fireball;
import com.vinberts.charactermaker.domain.character.actions.Slash;
import com.vinberts.charactermaker.domain.character.attributes.*;
import com.vinberts.charactermaker.domain.character.naming.CharacterNameService;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;

/**
 * CharacterMaker.domain.character.monster
 * 
 * @author Kevin Roberts 12/5/13
 */
public class MonsterFactory extends CharacterFactory {

	/**
	 * Protected factory for creating new Monsters - should only be accessed
	 * from inside the Monster's character class
	 * 
	 * @return new Monster
	 */
	protected Monster createCharacters() {
		Monster monster = new Monster();
		CharacterNameService nameService = CharacterNameService.getInstance();
		monster.setName(nameService.generateName(monster));

		// Define Base Attributes for a new Monster
		Random random = new Random();
		Charisma charisma = new Charisma("Charisma", "Monster Charisma", random.nextInt(5) + 2);
		Luck luck = new Luck("Luck", "Monster Luck", random.nextInt(4) + 3);
		Dexterity dexterity = new Dexterity("Dexterity", "Monster dexterity", random.nextInt(5) + 5);
		Intelligence intelligence = new Intelligence("Intelligence", "Monsters Intelligence", random.nextInt(1) + 3);
		Stamina stamina = new Stamina("Stamina", "Monster Stamina", 6);
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

		// Monsters that get fireballs
		List<String> fireballMonsters = Lists.newArrayList("Dragon", "Beast of Gévaudan", "Chimera", "Wyvern", "Hydra",
			"Drake", "THE INFAMOUS BOB SAGET", "Rabid Demon Hound", "Banshee", "Armor Lord", "Frost Dragon",
			"Giant Worm");

		if (fireballMonsters.contains(monster.getName())) {
			Fireball fireball = new Fireball("Fireball", "A deadly fireball blast", 5, 11);
			actions.add(fireball);
		}

		monster.setActions(actions);
		monster.setEquippedActions(actions); // set the default action to use in
												// fights

		return monster;

	}

}
