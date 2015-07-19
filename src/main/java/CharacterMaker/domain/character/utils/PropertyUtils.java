package CharacterMaker.domain.character.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CharacterMaker.domain.character.constants.Constants;

/**
 * A generic Properties Utility class - follows the Singleton pattern for instantiation
 * CharacterMaker.domain.character.utils
 * 
 * @author Kevin Roberts date: 12/23/13
 */
public class PropertyUtils {
	private static final Logger LOG = LoggerFactory.getLogger(PropertyUtils.class);
	private static PropertyUtils ourInstance = null;
	private Properties properties;
	private String propertiesFileLocation = Constants.PROPERTIES_FILE_LOCATION;

	public static PropertyUtils getInstance() {
		if (ourInstance == null) {
			synchronized (PropertyUtils.class) {
				if (ourInstance == null) {
					ourInstance = new PropertyUtils();
				}
			}
		}
		return ourInstance;
	}

	private PropertyUtils() {
		loadParams();
		LOG.debug("Initialized properties file...");
	}

	private void loadParams() {

		InputStream is = null;
		properties = new Properties();
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileLocation);
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
			File f = new File(propertiesFileLocation);
			OutputStream out = new FileOutputStream(f);
			properties.store(out, "properties file updated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isDebugMode() {
		if (this.getProperty("debugMode").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	public String getAlertMethod() {
		if (StringUtils.isNotEmpty(this.getProperty("alertMethod"))) {
			return this.getProperty("alertMethod");
		} else {
			return StringUtils.EMPTY;
		}
	}

}
