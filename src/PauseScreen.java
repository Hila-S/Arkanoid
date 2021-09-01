//208943555

import biuoop.DrawSurface;

/**
 * A PauseScreen will display a screen with the message paused -- press space to continue until a key is pressed.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * A Constructor that creates a PauseScreen.
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Do one frame of pause screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Return if the animation should stop.
     *
     * @return the boolean member stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}