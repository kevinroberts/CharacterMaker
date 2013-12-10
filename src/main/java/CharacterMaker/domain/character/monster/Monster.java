package CharacterMaker.domain.character.monster;

import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.utils.CharacterUtils;
import CharacterMaker.domain.character.constants.Constants;

/**
 * Monster class
 * 
 * monster is a creature that all other character's must do battle with in order
 * to earn experience
 * 
 * @author Kevin Roberts Date: 12/02/2013
 */
public class Monster extends Character {

	String uniqueID;

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

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}
}
