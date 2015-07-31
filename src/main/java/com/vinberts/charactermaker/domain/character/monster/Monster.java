package com.vinberts.charactermaker.domain.character.monster;

import com.vinberts.charactermaker.domain.character.Character;
import com.vinberts.charactermaker.domain.character.constants.Constants;
import com.vinberts.charactermaker.domain.character.utils.CharacterUtils;

/**
 * Monster class
 * 
 * monster is a creature that all other character's must do battle with in order
 * to earn experience
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */
public class Monster extends Character {

	/**
	 * Initializes a new Monster - used internally from the
	 * CharacterFactory.class
	 * 
	 * @return new Monster instance
	 */
	protected Character initializeNewCharacter() {
		MonsterFactory monsterFactory = new MonsterFactory();
		return monsterFactory.createCharacters();
	}

	@Override
	public void train() {
		CharacterUtils.incrementAttributeStats(this);
		this.setExperiencePoints(this.getExperiencePoints() + Constants.XP_FROM_TRAINING);
	}

	@Override
	public String toString() {
		return "Monster {" + "health=" + this.getHealth() + ", name='" + this.getName() + '\'' + ", attributes="
			+ this.getAttributes() + ", actions=" + this.getActions() + ", level=" + this.getLevel() + ", battlesWon="
			+ this.getBattlesWon() + ", experiencePoints=" + this.getExperiencePoints() + '}';
	}

}
