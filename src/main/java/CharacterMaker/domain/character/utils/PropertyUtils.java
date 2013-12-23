package CharacterMaker.domain.character.utils;

import CharacterMaker.game.messages.Alert;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Properties;

/**
 * Singleton Properties Utility class
 * CharacterMaker.domain.character.utils
 * 
 * @author Kevin Roberts date: 12/23/13
 */
public class PropertyUtils {
	private static PropertyUtils ourInstance = null;
	private Properties properties;

	public static synchronized PropertyUtils getInstance() {
		if (ourInstance == null) {
			ourInstance = new PropertyUtils ();
		}
		return ourInstance;
	}

	private PropertyUtils() {
		loadParams();
		System.out.println("Initialized properties file...");
		//Alert.debug("test");
	}

	private void loadParams() {

		InputStream is = null;
		properties = new Properties();
		try {
			File f = new File("res/characterMaker.properties");
			is = new FileInputStream(f);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {

			// Try loading properties from the file (if found)
			properties.load(is);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getProperty(String propertyKey) {
		if (properties != null) {
			if (StringUtils.isNotEmpty(properties.getProperty(propertyKey))) {
				return properties.getProperty(propertyKey);
			} else {
				return StringUtils.EMPTY;
			}
		}
		return StringUtils.EMPTY;
	}

	public void setProperty(String key, String value) {
		try {
			properties.setProperty(key, value);
			File f = new File("res/characterMaker.properties");
			OutputStream out = new FileOutputStream(f);
			properties.store(out, "properties file updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
