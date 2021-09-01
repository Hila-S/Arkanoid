// 208943555

/**
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    //counter the number of the block in the game
    private Counter blocksCounter;

    /**
     * Constructor that creates a BlockRemover.
     *
     * @param gameLevel          the Game.
     * @param removedBlocks the Counter.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.blocksCounter = removedBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * Blocks that are hit should be removed from the game.
     *
     * @param beingHit the block that is hit.
     * @param hitter   The hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove the block from the game
        beingHit.removeFromGame(gameLevel);
        //remove the block from the list of listeners
        beingHit.removeHitListener(this);
        //update the blocks counter
        this.blocksCounter.decrease(1);
    }
}