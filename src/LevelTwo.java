//208943555

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Wide Easy" level.
 */
public class LevelTwo implements LevelInformation {
    private static final double SIZE_BORDER = 25;
    private static final double WIDTH_BLOCK = 50;
    private static final double HEIGHT_BLOCK = 25;
    private static final double SPEED = 10;
    private static final int ANGLE = 50;
    private static final int INCREASE_ANGLE = 11;

    /**
     * A Constructor that creates a LevelTwo.
     */
    public LevelTwo() {
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //the angle size that increase
        int size = 0;
        List<Velocity> velocityList = new ArrayList<Velocity>();
        //create all the velocity
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity vel = Velocity.fromAngleAndSpeed(ANGLE - size, SPEED);
            velocityList.add(vel);
            size = size + INCREASE_ANGLE;
            //increased the size after the five balls
            if (i == 4) {
                size = size + 10;
            }
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 520;
    }

    @Override
    public String levelName() {
        String levelName = "Wide Easy";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundTwo();
    }

    @Override
    public List<Block> blocks() {
        //create list of blocks
        List<Block> blockList = new ArrayList<>();
        double x = SIZE_BORDER;
        //create array of colors
        Color[] colors = new Color[]{Color.RED, Color.RED, Color.ORANGE, Color.ORANGE, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN, Color.BLUE, Color.BLUE, Color.PINK, Color.PINK, Color.CYAN,
                Color.CYAN};
        //create the blocks
        for (int i = 0; i < this.numberOfBlocksToRemove(); i++) {
            Rectangle rect = new Rectangle(new Point(x, 240), WIDTH_BLOCK, HEIGHT_BLOCK);
            Block block = new Block(rect, colors[i]);
            blockList.add(block);
            x = x + WIDTH_BLOCK;
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
