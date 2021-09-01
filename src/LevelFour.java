//208943555

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The "Final Four" level.
 */
public class LevelFour implements LevelInformation {
    private static final double WIDTH = 800;
    private static final double SIZE_BORDER = 25;
    private static final double WIDTH_BLOCK = 50;
    private static final double HEIGHT_BLOCK = 25;
    private static final double SPEED = 6;
    private static final double ANGLE = 200;
    private static final int INCREASE_ANGLE = 20;

    /**
     * A Constructor that creates a LevelFour.
     */
    public LevelFour() {
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        //the angle size tht increase
        int size = 0;
        List<Velocity> velocityList = new ArrayList<Velocity>();
        //create all the velocity
        for (int i = 0; i < this.numberOfBalls(); i++) {
            Velocity vel = Velocity.fromAngleAndSpeed(ANGLE - size, SPEED);
            velocityList.add(vel);
            size = size + INCREASE_ANGLE;
        }
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
        String levelName = "Final Four";
        return levelName;
    }

    @Override
    public Sprite getBackground() {
        return new BackgroundFour();
    }

    @Override
    public List<Block> blocks() {
        //create list of block
        List<Block> blockList = new ArrayList<>();
        //create array of colors
        Color[] colors = new Color[]{Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE,
                Color.PINK, Color.CYAN};
        double y = 80;
        //create six row of blocks
        for (int i = 0; i < 7; i++) {
            double x = WIDTH - SIZE_BORDER - WIDTH_BLOCK;
            y = y + HEIGHT_BLOCK;
            //create the blocks on the row
            for (int j = 0; j < 15; j++) {
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
        return 105;
    }
}
