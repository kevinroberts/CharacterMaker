package CharacterMaker;

import CharacterMaker.game.MainLoop;

public class App {

    public static void main(String[] args) {
        System.out.println("Starting Character Generator!");

        MainLoop mainLoop = new MainLoop();
        mainLoop.runLoop();

    }

}
