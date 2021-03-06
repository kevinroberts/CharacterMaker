package com.vinberts.charactermaker.domain.character.ork;

import com.vinberts.charactermaker.domain.character.Character;
import com.vinberts.charactermaker.domain.character.constants.Constants;
import com.vinberts.charactermaker.domain.character.utils.CharacterUtils;

/**
 * Ork class
 * 
 * Muscular humanoids, with green skin and a penchant for violence. The orks
 * live for war and constantly fight anything in sight, including each other.
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */

public class Ork extends com.vinberts.charactermaker.domain.character.Character {

    protected Character initializeNewCharacter() {
        OrkFactory orkFactory = new OrkFactory();
        return orkFactory.createCharacter();
    }

    @Override
	public void train() {
		CharacterUtils.incrementAttributeStats(this);
		this.setExperiencePoints(this.getExperiencePoints() + Constants.XP_FROM_TRAINING);
	}

	@Override
	public String toString() {
		return "Ork {" + "health=" + this.getHealth() + ", name='" + this.getName() + '\'' + ", attributes="
			+ this.getAttributes() + ", actions=" + this.getActions() + ", level=" + this.getLevel() + ", battlesWon="
			+ this.getBattlesWon() + ", experiencePoints=" + this.getExperiencePoints() + '}';
	}
}
