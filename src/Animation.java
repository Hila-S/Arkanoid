//208943555

import biuoop.DrawSurface;

/**
 * The animation on the game.
 */
public interface Animation {
    /**
     * Do one frame of the animation.
     *
     * @param d DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Return if the animation should stop.
     *
     * @return the boolean member stop.
     */
    boolean shouldStop();
}
