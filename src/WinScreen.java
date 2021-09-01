//208943555

import biuoop.DrawSurface;

/**
 * a WinScreen is a "end screen" to the game.
 * If the game ended by clearing all the levels, the screen should display "You Win! Your score is X".
 */
public class WinScreen implements Animation {
    private boolean stop;
    private Counter score;

    /**
     * Constructor that creates a WinScreen.
     *
     * @param score a counter of the score.
     */
    public WinScreen(Counter score) {
        this.stop = false;
        this.score = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.score.getValue(), 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
