package CharacterMaker;

import CharacterMaker.domain.character.utils.PropertyUtils;
import CharacterMaker.game.MainLoop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
		LOG.info("Starting Character Generator!");
		PropertyUtils propertyUtils = PropertyUtils.getInstance();


        MainLoop mainLoop = new MainLoop();
        mainLoop.runLoop();

    }

}
