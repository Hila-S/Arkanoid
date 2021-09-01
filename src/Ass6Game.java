//208943555

import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Ass5Game has a main method that creates a game, initializes and runs it.
 */
public class Ass6Game {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    /**
     * main method creates a new game, initializes and runs it.
     *
     * @param args ignored.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), gui);
        List<LevelInformation> levels = new ArrayList<>();
        if (args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                try {
                    switch (Integer.parseInt(args[i])) {
                        case 1:
                            levels.add(new LevelOne());
                            break;
                        case 2:
                            levels.add(new LevelTwo());
                            break;
                        case 3:
                            levels.add(new LevelThree());
                            break;
                        case 4:
                            levels.add(new LevelFour());
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    ;
                }
            }
        }
        if (levels.size() == 0) {
            levels.add(new LevelOne());
            levels.add(new LevelTwo());
            levels.add(new LevelThree());
            levels.add(new LevelFour());
        }
        gameFlow.runLevels(levels);
    }
}

/**
 * LevelInformation level = new LevelTwo();
 * //create a new game
 * GameLevel gameLevel = new GameLevel(level);
 * //initialize the game
 * gameLevel.initialize();
 * //run the game
 * gameLevel.run();
 */