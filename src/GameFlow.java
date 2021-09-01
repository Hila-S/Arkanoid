//208943555

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * This class will be in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private GUI gui;
    private Counter score;

    /**
     * Constructor that creates a BallRemover.
     *
     * @param ar  AnimationRunner.
     * @param ks  KeyboardSensor.
     * @param gui GUI.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        //counter the score in the game
        this.score = new Counter();
    }

    /**
     * This method runs the levels.
     *
     * @param levels lis of LevelInformation.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            // initialize GameLevel
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.gui, this.score);
            // initialize the level
            level.initialize();
            //level has more blocks and balls
            while ((level.getBallsCounter().getValue() != 0) && (level.getBlocksCounter().getValue() != 0)) {
                level.run();
            }
            //no more balls
            if (level.getBallsCounter().getValue() == 0) {
                break;
            }
        }
        //if the game ended by clearing all the level
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                new WinScreen(this.score)));
    }
}
