package CharacterMaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import CharacterMaker.game.MainLoop;

public class App {
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		LOG.debug("Starting Character Generator!");

		MainLoop mainLoop = new MainLoop();
		mainLoop.runLoop();

	}

}
