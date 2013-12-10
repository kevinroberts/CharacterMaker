package CharacterMaker.domain.character.barbarian;

import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.CharacterUtils;
import CharacterMaker.domain.character.constants.Constants;

public class Barbarian extends Character {

	public static final int STRENGTH_MULTIPLIER = 2;

	@Override
	public void train() {
		CharacterUtils.incrementAttributeStats(this);
		this.setExperiencePoints(this.getExperiencePoints() + Constants.XP_FROM_TRAINING);
	}

	@Override
	public String toString() {
		return "Barbarian{" + "health=" + this.getHealth() + ", name='" + this.getName() + '\'' + ", attributes="
			+ this.getAttributes() + ", actions=" + this.getActions() + ", level=" + this.getLevel() + ", battlesWon="
			+ this.getBattlesWon() + ", experiencePoints=" + this.getExperiencePoints() + '}';
	}
}
