//208943555

/**
 * A class BallRemover will be in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    //counter the number of the ball in the game
    private Counter counter;

    /**
     * Constructor that creates a BallRemover.
     *
     * @param gameLevel          the Game.
     * @param removedBlocks the Counter.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.counter = removedBlocks;
    }

    /**
     * Ball that hit should be removed from the game.
     *
     * @param beingHit the block that is hit.
     * @param hitter   The hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the ball from the game
        hitter.removeFromGame(gameLevel);
        //update the balls counter
        this.counter.decrease(1);
    }
}
