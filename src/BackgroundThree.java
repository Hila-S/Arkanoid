//208943555

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Background of "Green 3" level.
 */
public class BackgroundThree implements Sprite {

    /**
     * A Constructor that creates a BackgroundThree.
     */
    public BackgroundThree() {
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //color the background
        d.setColor(new Color(0, 153, 0));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //draw the tower
        d.setColor(new Color(102, 102, 102));
        d.fillRectangle(110, 200, 10, 200);
        d.setColor(new Color(255, 204, 51));
        d.fillCircle(115, 200, 12);
        d.setColor(new Color(255, 51, 51));
        d.fillCircle(115, 200, 8);
        d.setColor(Color.WHITE);
        d.fillCircle(115, 200, 3);
        d.setColor(new Color(51, 51, 51));
        d.fillRectangle(100, 400, 30, 200);
        d.setColor(new Color(41, 41, 41));
        //draw the windows
        d.fillRectangle(65, 450, 100, 200);
        int y = 460;
        int x = 75;
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(x + j * 18, y + i * 32, 10, 25);
            }
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
