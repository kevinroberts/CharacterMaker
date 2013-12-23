package CharacterMaker;

import CharacterMaker.domain.character.utils.PropertyUtils;
import CharacterMaker.game.MainLoop;
import CharacterMaker.game.messages.Alert;

public class App {

    public static void main(String[] args) {
        System.out.println("Starting Character Generator!");
		PropertyUtils propertyUtils = PropertyUtils.getInstance();


        MainLoop mainLoop = new MainLoop();
        mainLoop.runLoop();

    }

}
