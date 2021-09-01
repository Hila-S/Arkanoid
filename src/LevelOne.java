//208943555

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Direct Hit" level.
 */
public class LevelOne implements LevelInformation {
    private static final double WIDTH = 800;
    private static final double SIZE_BLOCK = 30;
    private static final double SPEED = 7;

    /**
     * A Constructor that creates a LevelOne.
     */
    public LevelOne() {
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //initial list of velocity
        List<Velocity> velocityList = new ArrayList<Velocity>();
        //initial ball velocity
        Velocity vel = Velocity.fromAngleAndSpeed(360, SPEED);
        velocityList.add(vel);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        String levelName = "Direct Hit";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundOne();
    }

    @Override
    public List<Block> blocks() {
        //create rectangle
        Rectangle rect = new Rectangle(new Point(WIDTH / 2 - SIZE_BLOCK / 2, 150), SIZE_BLOCK, SIZE_BLOCK);
        // create block
        Block block = new Block(rect, Color.RED);
        //create list of blocks
        List<Block> blockList = new ArrayList<Block>();
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
