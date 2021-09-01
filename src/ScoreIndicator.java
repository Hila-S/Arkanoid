//208943555

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * A class ScoreIndicator will be in charge of displaying the current score.
 * The ScoreIndicator will hold a reference to the scores counter,
 * and will be added to the game as a sprite positioned at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    //the size of the text
    private static final int SIZE_OF_TEXT = 15;
    //counter the score
    private Counter score;
    //the rectangle of the score indicator
    private Rectangle rectangle;
    //the name of the level
    private String levelName;

    /**
     * Constructor that creates a ScoreTrackingListener.
     *
     * @param score  a Counter.
     * @param rect   a Rectangle.
     * @param string the name of the level.
     */
    public ScoreIndicator(Counter score, Rectangle rect, String string) {
        this.score = score;
        this.rectangle = rect;
        this.levelName = string;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //the x of the location of the text
        int xOfLives = 150;
        int xOfLevelName = 550;
        int xOfScore = 350;
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        //create the rectangle of the score indicator
        d.setColor(Color.WHITE);
        d.fillRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        //create the text of the score indicator
        d.setColor(Color.BLACK);
        d.drawText(xOfScore, SIZE_OF_TEXT, "Score: " + this.score.getValue(), SIZE_OF_TEXT);
        d.drawText(xOfLevelName, SIZE_OF_TEXT, "Level Name: " + this.levelName, SIZE_OF_TEXT);
        d.drawText(xOfLives, SIZE_OF_TEXT, "Lives: 7", SIZE_OF_TEXT);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add the ScoreIndicator to the game.
     *
     * @param g a Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
