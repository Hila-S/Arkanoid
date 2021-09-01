//208943555

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Background of "Wide Easy" level.
 */
public class BackgroundTwo implements Sprite {

    /**
     * A Constructor that creates a BackgroundTwo.
     */
    public BackgroundTwo() {
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(255, 255, 153));
        d.fillCircle(150, 150, 60);
        int startX = 25;
        int endX = 775;
        int sunRays = 100;
        for (int i = 1; i <= sunRays; i++) {
            d.drawLine(150, 150, (endX - startX) / sunRays * i, 250);
        }
        d.setColor(new Color(255, 204, 50));
        d.fillCircle(150, 150, 50);
        d.setColor(new Color(255, 255, 0));
        d.fillCircle(150, 150, 40);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
