//208943555

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Green 3" level.
 */
public class LevelThree implements LevelInformation {
    private static final double WIDTH = 800;
    private static final double SIZE_BORDER = 25;
    private static final double WIDTH_BLOCK = 50;
    private static final double HEIGHT_BLOCK = 25;
    private static final double SPEED = 7;
    private static final double ANGLE = 200;

    /**
     * A Constructor that creates a LevelThree.
     */
    public LevelThree() {
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //create list of velocity
        List<Velocity> velocityList = new ArrayList<Velocity>();
        //initialize the velocity
        Velocity vel1 = Velocity.fromAngleAndSpeed(ANGLE, SPEED);
        Velocity vel2 = Velocity.fromAngleAndSpeed(-ANGLE, SPEED);
        //add the velocity to the list
        velocityList.add(vel1);
        velocityList.add(vel2);
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 15;
    }

    @Override
    public int paddleWidth() {
        return 120;
    }

    @Override
    public String levelName() {
        String levelName = "Green 3";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundThree();
    }

    @Override
    public List<Block> blocks() {
        //create list of block
        List<Block> blockList = new ArrayList<>();
        //create array of colors
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        double y = 120;
        //create six row of blocks
        for (int i = 0; i < 5; i++) {
            double x = WIDTH - SIZE_BORDER - WIDTH_BLOCK;
            y = y + HEIGHT_BLOCK;
            //create the blocks on the row
            for (int j = 0; j < 10 - i; j++) {
                Rectangle rect = new Rectangle(x, y, WIDTH_BLOCK, HEIGHT_BLOCK);
                Block block = new Block(rect, colors[i]);
                blockList.add(block);
                //update the location of the block
                x = x - WIDTH_BLOCK;
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
