//208943555

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Background of "Final Four" level.
 */
public class BackgroundFour implements Sprite {

    /**
     * A Constructor that creates a BackgroundFour.
     */
    public BackgroundFour() {
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(51, 153, 255));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.WHITE);
        //create rain
        for (int i = 0; i < 10; i++) {
            d.drawLine(605 + i * 10, 520, 580 + i * 10, 600);
        }
        //create cloud
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(600, 500, 23);
        d.fillCircle(620, 540, 27);
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(640, 510, 29);
        d.setColor(new Color(160, 160, 160));
        d.fillCircle(660, 530, 22);
        d.fillCircle(680, 520, 32);
        //create rain
        d.setColor(Color.WHITE);
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + i * 10, 400, 70 + i * 10, 600);
        }
        //create cloud
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(100, 400, 25);
        d.fillCircle(120, 420, 25);
        d.setColor(new Color(190, 190, 190));
        d.fillCircle(140, 390, 29);
        d.setColor(new Color(160, 160, 160));
        d.fillCircle(160, 420, 22);
        d.fillCircle(180, 400, 32);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
