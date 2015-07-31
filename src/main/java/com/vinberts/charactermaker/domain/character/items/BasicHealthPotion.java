package com.vinberts.charactermaker.domain.character.items;

import com.vinberts.charactermaker.domain.character.*;
import com.vinberts.charactermaker.game.messages.Alert;

import com.google.common.collect.ConcurrentHashMultiset;

/**
 * CharacterMaker.domain.character.items
 * 
 * @author Kevin Roberts date: 7/28/14
 */
public class BasicHealthPotion extends Item {

	public BasicHealthPotion(String name, int level) {
		super(name, level);
	}

	/**
	 * Grants the using character 25 health points
	 * 
	 * @param character
	 */
	public void use(com.vinberts.charactermaker.domain.character.Character character) {

		character.setHealth(character.getHealth() + this.getLevel());

		if (character.getHealth() > character.getMaxHealth()) {
			character.setHealth(character.getMaxHealth());
		}

		Alert.infoAboutCharacter(
				"Item " + this.getName() + " used. " + character.getName()
						+ " has been granted " + this.getLevel() + " health points and now has " + character.getHealth() + "/"
						+ character.getMaxHealth(), character);

		// remove item from character after using
		ConcurrentHashMultiset<Item> items = character.getItems();
		items.remove(this);
		character.setItems(items);
	}
}
