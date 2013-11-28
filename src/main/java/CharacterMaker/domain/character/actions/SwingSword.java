package CharacterMaker.domain.character.actions;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.attributes.Dexterity;
import CharacterMaker.domain.character.attributes.Strength;
import CharacterMaker.domain.character.barbarian.Barbarian;

public class SwingSword extends Action {

    public SwingSword(String name, String description, int experienceGainedFromUse, int damage) {
        super(name, description, experienceGainedFromUse, damage);
    }

    public int calculateTotalDomage(Barbarian barbarian) {
        int totalDamage = this.getDamage();
        int strength = 0;
        int dexterity = 0;
        for (Attribute attribute : barbarian.getAttributes()) {
            if (attribute instanceof Strength) {
                strength = attribute.getBattleLevel();
            }
            if (attribute instanceof Dexterity) {
                dexterity = attribute.getBattleLevel();
            }

            totalDamage += (strength / 4) + (dexterity / 4);
        }
        return totalDamage;
    }


}
