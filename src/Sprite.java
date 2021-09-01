//208943555

import biuoop.DrawSurface;

/**
 * Sprites can be drawn on the screen, and can be notified that time has passed.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
