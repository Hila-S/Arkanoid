//208943555

/**
 * A class ScoreTrackingListener update the score counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int SCORE = 5;
    //counter the score
    private Counter currentScore;

    /**
     * Constructor that creates a ScoreTrackingListener.
     *
     * @param scoreCounter a Counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the block that is hit.
     * @param hitter   The hitter parameter is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(SCORE);
    }
}