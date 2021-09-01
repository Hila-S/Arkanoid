//208943555

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation decorator-class that will wrap an existing animation and add a "waiting-for-key"
 * behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor that creates a KeyPressStoppableAnimation.
     *
     * @param sensor    KeyboardSensor.
     * @param key       a String.
     * @param animation the Animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        //show the animation of the screen
        this.animation.doOneFrame(d);
        // if the user press the key
        if (this.sensor.isPressed(key)) {
            //if isAlreadyPressed== false
            if (!this.isAlreadyPressed) {
                this.stop = true;
            } else {
                this.isAlreadyPressed = false;
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
