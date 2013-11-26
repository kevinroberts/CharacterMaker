package CharacterMaker.domain.character.barbarian;

import CharacterMaker.domain.character.Action;
import CharacterMaker.domain.character.Attribute;
import CharacterMaker.domain.character.CharacterFactory;
import CharacterMaker.domain.character.attributes.*;
import CharacterMaker.domain.character.actions.SwingSword;
import CharacterMaker.domain.character.naming.CharacterNameService;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.Random;

public class BarbarianFactory implements CharacterFactory {

    @Override
    public Barbarian createCharacter() {
        Barbarian barbarian = new Barbarian();
        CharacterNameService nameService = CharacterNameService.getInstance();
        barbarian.setName(nameService.generateName(barbarian));
        barbarian.setHealth(100);
        barbarian.setLevel(1);
        barbarian.setBattlesWon(0);
        barbarian.setExperiencePoints(0);
        Random random = new Random();

        // Define Base Attributes for a new Barbarian
        Charisma charisma = new Charisma("Barbarian Charisma", "Barbarian Charisma", random.nextInt(5) + 2);
        Luck luck = new Luck("Barbarian Luck", "Barbarian Luck", random.nextInt(10) + 3);
        Dexterity dexterity = new Dexterity("Barbarian dexterity", "Barbarian dexterity", random.nextInt(5) + 5);
        Intelligence intelligence = new Intelligence("Intelligence", "Intelligence", random.nextInt(2) + 5);
        Stamina stamina = new Stamina("Barbarian Stamina", "Stamina", random.nextInt(10) + 5);
        Strength strength = new Strength("Barbarian Strength", "Barbarian Strength", random.nextInt(5) + 10);


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

        actions.add(new SwingSword("Barbarian Blade", "The barbarian swings his mighty blade", 10 + random.nextInt(10)));

        barbarian.setActions(actions);

        return barbarian;
    }
}
