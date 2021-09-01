//208943555

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will appear on the screen for (numOfSeconds / countFrom)
 * seconds, before it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private static final int SIZE_OF_TEXT = 200;
    private double numOfSeconds;
    private int tempCountFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private int constCountFrom;

    /**
     * Constructor that creates a CountdownAnimation.
     *
     * @param numOfSeconds the number of seconds
     * @param countFrom    the count we count from "3, 2, 1".
     * @param gameScreen   the SpriteCollection.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.tempCountFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.constCountFrom = countFrom;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // draw all the sprite animation
        this.gameScreen.drawAllOn(d);
        // draw the text
        if (this.tempCountFrom > 0) {
            d.setColor(Color.RED);
            d.drawText(350, 450, Integer.toString(this.tempCountFrom), SIZE_OF_TEXT);
        } else {
            this.stop = true;
        }
        //this if condition helps by starting the text without pause
        if (this.tempCountFrom == constCountFrom) {
            this.tempCountFrom--;
            return;
        }
        // time calculations
        Sleeper sleeper = new biuoop.Sleeper();
        int millisecondsPerFrame = (int) (this.numOfSeconds * 1000 / constCountFrom);
        long startTime = System.currentTimeMillis(); // timing
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
        // reduce tempCountFrom
        this.tempCountFrom--;
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}