//208943555

import biuoop.DrawSurface;

/**
 * a GameOverScreen is a "end screen" to the game.
 * If the game ended with the player dying, the end screen should display the message "Game Over. Your score is X"
 */
public class GameOverScreen implements Animation {
    private boolean stop;
    private Counter score;

    /**
     * Constructor that creates a GameOverScreen.
     *
     * @param score the score counter.
     */
    public GameOverScreen(Counter score) {
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
