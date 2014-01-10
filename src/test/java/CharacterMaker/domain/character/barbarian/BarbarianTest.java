package CharacterMaker.domain.character.barbarian;

import CharacterMaker.domain.character.CharacterFactory;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import CharacterMaker.domain.character.constants.Constants;

/**
 * CharacterMaker.domain.character.barbarian
 * 
 * @author Kevin Roberts date: 12/10/13
 */
public class BarbarianTest extends TestCase {

	CharacterFactory barbarianFactory;
	Barbarian testBarbarian;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		barbarianFactory = new CharacterFactory();
		testBarbarian = (Barbarian)barbarianFactory.createCharacter("Barbarian");
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(BarbarianTest.class);
	}

	public void testCreateValidBarbarian() throws Exception {
		assertEquals("Barbarian is created", Barbarian.class, testBarbarian.getClass());
	}

	public void testBarbarianProperties() throws Exception {
		assertEquals("Default health", testBarbarian.getHealth(), (Integer)Constants.DEFAULT_CHARACTER_HEALTH);
		assertEquals("Default level", testBarbarian.getLevel(), (Integer)Constants.DEFAULT_CHARACTER_LEVEL);
		assertEquals("Default battles won", testBarbarian.getBattlesWon(),(Integer) Constants.DEFAULT_CHARACTER_BATTLES_WON);
        assertEquals("Default battles fought", testBarbarian.getBattleFought(),(Integer) Constants.DEFAULT_BATTLES_FOUGHT);
		assertEquals("Default xp", testBarbarian.getExperiencePoints(), (Integer)Constants.DEFAULT_CHARACTER_XP);
        assertEquals("Default max hp", testBarbarian.getMaxHealth(), (Integer)Constants.DEFAULT_CHARACTER_HEALTH);
	}

	public void testBarbarianLevelUp() throws Exception {
		Barbarian testBarbarianLeveler = (Barbarian)barbarianFactory.createCharacter("Barbarian");
		testBarbarianLeveler.levelUp();
		assertEquals("barbarian is at level 2", testBarbarianLeveler.getLevel(), (Integer)2);
	}

    public void testBarbarianUnique() throws Exception {
        Barbarian barbarian1 = (Barbarian)barbarianFactory.createCharacter("Barbarian");
        Barbarian barbarian2 = (Barbarian)barbarianFactory.createCharacter("Barbarian");
        assertFalse("Two barbarians do not have the same ID", barbarian1.getUniqueID().equals(barbarian2.getUniqueID()));
    }

	public void testBarbarianTraining() throws Exception {
		Barbarian testBarbarianTrainee = (Barbarian)barbarianFactory.createCharacter("Barbarian");
		testBarbarianTrainee.train();
		assertTrue("Experience Points increased from training",
			testBarbarianTrainee.getExperiencePoints() > Constants.DEFAULT_CHARACTER_XP);
	}

}
