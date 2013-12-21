package CharacterMaker.domain.character.barbarian;

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

	BarbarianFactory barbarianFactory;
	Barbarian testBarbarian;

	@Override
	public void setUp() throws Exception {
		super.setUp();
		barbarianFactory = new BarbarianFactory();
		testBarbarian = barbarianFactory.createCharacter();
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(BarbarianTest.class);
	}

	public void testCreateValidBarbarian() throws Exception {
		assertEquals("Barbarian is created", Barbarian.class, barbarianFactory.createCharacter().getClass());
	}

	public void testBarbarianProperties() throws Exception {
		assertEquals("Default health", testBarbarian.getHealth(), (Integer)Constants.DEFAULT_CHARACTER_HEALTH);
		assertEquals("Default level", testBarbarian.getLevel(), (Integer)Constants.DEFAULT_CHARACTER_LEVEL);
		assertEquals("Default battles won", testBarbarian.getBattlesWon(),(Integer) Constants.DEFAULT_CHARACTER_BATTLES_WON);
        assertEquals("Default battles fought", testBarbarian.getBattleFought(),(Integer) Constants.DEFAULT_BATTLES_FOUGHT);
		assertEquals("Default xp", testBarbarian.getExperiencePoints(), (Integer)Constants.DEFAULT_CHARACTER_XP);
	}

	public void testBarbarianLevelUp() throws Exception {
		Barbarian testBarbarianLeveler = barbarianFactory.createCharacter();
		testBarbarianLeveler.levelUp();
		assertEquals("barbarian is at level 2", testBarbarianLeveler.getLevel(), (Integer)2);
	}

	public void testBarbarianTraining() throws Exception {
		Barbarian testBarbarianTrainee = barbarianFactory.createCharacter();
		testBarbarianTrainee.train();
		assertTrue("Experience Points increased from training",
			testBarbarianTrainee.getExperiencePoints() > Constants.DEFAULT_CHARACTER_XP);
	}

}
