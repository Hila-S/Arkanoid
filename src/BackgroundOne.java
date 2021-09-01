//208943555

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Background of "Direct Hit" level.
 */
public class BackgroundOne implements Sprite {

    /**
     * A Constructor that creates a BackgroundOne.
     */
    public BackgroundOne() {
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //color the Background
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //create the blue shapes
        d.setColor(Color.BLUE);
        d.drawCircle(400, 160, 60);
        d.drawCircle(400, 160, 90);
        d.drawCircle(400, 160, 120);
        d.drawLine(400, 180, 400, 300);
        d.drawLine(420, 160, 540, 160);
        d.drawLine(380, 160, 260, 160);
        d.drawLine(400, 140, 400, 20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
