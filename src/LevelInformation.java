//208943555

import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * The number of balls in the level.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return list of the velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return list of the blocks.
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}