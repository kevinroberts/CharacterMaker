package CharacterMaker.domain.character.fighting;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Character;
import CharacterMaker.domain.character.CharacterUtils;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.barbarian.Barbarian;
import CharacterMaker.domain.character.ork.Ork;

/**
 * CharacterMaker.domain.character.fighting
 *
 * @author Kevin Roberts
 *         Date: 12/02/2013
 */

public class BarbarianVsOrk extends Fight {

    public static Character fight(Barbarian barbarian1, Ork ork2) {

        if (isDeadCheck(barbarian1)) {
            return ork2;
        }
        if (isDeadCheck(ork2)) {
            return barbarian1;
        }

        // Barbarian 1 actions
        int totalDamageDealtFrom1 = 0;
        for (Action action : barbarian1.getActions()) {
            if (action instanceof SwingSword) {
                SwingSword swingSword = (SwingSword) action;

                System.out.print(barbarian1.getName() + " swings his sword at " + ork2.getName());
                if (CharacterUtils.hitSuccessCheck(barbarian1)) {
                    totalDamageDealtFrom1 = swingSword.calculateTotalDamage(barbarian1);
                    System.out.print(" and hits for " + totalDamageDealtFrom1 + " damage\n");
                    ork2.setHealth(ork2.getHealth() - totalDamageDealtFrom1);
                    ork2.setExperiencePoints(ork2.getExperiencePoints() + swingSword.getExperienceGainedFromUse());
                    if (ork2.getHealth() < 0) {
                        System.out.println(ork2.getName() + " collapses and dies from his injuries.");
                    }
                } else {
                    System.out.print(" and misses\n");
                }

            }
        }

        if (isDeadCheck(ork2)) {
            return barbarian1;
        }

        // Barbarian 2 actions
        int totalDamageDealtFrom2 = 0;
        for (Action action : ork2.getActions()) {
            if (action instanceof SwingSword) {
                SwingSword swingSword = (SwingSword) action;

                System.out.print(ork2.getName() + " swings his sword at " + barbarian1.getName());
                if (CharacterUtils.hitSuccessCheck(ork2)) {
                    totalDamageDealtFrom2 = swingSword.calculateTotalDamage(ork2);
                    System.out.print(" and hits for " + totalDamageDealtFrom2 + " damage\n");
                    barbarian1.setHealth(barbarian1.getHealth() - totalDamageDealtFrom2);
                    barbarian1.setExperiencePoints(barbarian1.getExperiencePoints() + swingSword.getExperienceGainedFromUse());
                    if (barbarian1.getHealth() <= 0) {
                        System.out.println(barbarian1.getName() + " collapses and dies from his injuries.");
                    }
                } else {
                    System.out.print(" and misses!\n");
                }

            }
        }

        if (totalDamageDealtFrom1 > totalDamageDealtFrom2) {
            System.out.println(barbarian1.getName() + " wins the fight.");
            barbarian1.setBattlesWon(barbarian1.getBattlesWon() + 1);
            // award bonus experience if the characters are near the same
            // skill level
            if (Math.abs(barbarian1.getLevel() - ork2.getLevel()) < 2) {
                barbarian1.setExperiencePoints(barbarian1.getExperiencePoints() + CharacterUtils.XP_FROM_BATTLE_VICTORY);
            }

            return barbarian1;
        } else if (totalDamageDealtFrom2 > totalDamageDealtFrom1) {
            System.out.println(ork2.getName() + " wins the fight.");
            ork2.setBattlesWon(ork2.getBattlesWon() + 1);
            if (Math.abs(barbarian1.getLevel() - ork2.getLevel()) < 2) {
                ork2.setExperiencePoints(ork2.getExperiencePoints() + CharacterUtils.XP_FROM_BATTLE_VICTORY);
            }
            return ork2;
        } else {
            return null;
        }
    }
}
