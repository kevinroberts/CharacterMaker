package CharacterMaker.domain.character.constants;

public final class Constants {

	/** System property - <tt>line.separator</tt>*/
	public static final String NEW_LINE = System.getProperty("line.separator");
	/** System property - <tt>file.separator</tt>*/
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	/** System property - <tt>path.separator</tt>*/
	public static final String PATH_SEPARATOR = System.getProperty("path.separator");

	public static final int XP_FROM_BATTLE_VICTORY = 10;
	public static final int XP_FROM_TRAINING = 5;
	public static final int STAMINA_USE_FROM_ACTION = 5;
	public static final int DEFAULT_CHARACTER_LEVEL = 1;
	public static final int DEFAULT_CHARACTER_HEALTH = 100;
	public static final int DEFAULT_CHARACTER_BATTLES_WON = 0;
	public static final int DEFAULT_CHARACTER_XP = 0;
	public static final int DEFAULT_BATTLES_FOUGHT = 0;
	public static final int CHARACTER_LEVEL_DIFFERENCE_FOR_BONUS_XP = 3;
	public static final String PROPERTIES_FILE_LOCATION = "characterMaker.properties";

	public static final String MONSTER = "Monster";
	public static final String BARBARIAN = "Barbarian";
	public static final String ORK = "Ork";

	private Constants() {
		//this prevents even the native class from
		throw new AssertionError();
	}

}
