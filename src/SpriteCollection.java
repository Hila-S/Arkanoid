//208943555

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * SpriteCollection will hold a collection of sprites.
 */
public class SpriteCollection {
    //list of Sprite
    private List<Sprite> sprites;

    /**
     * Constructor that creates a new SpriteCollection.
     * and initialize the array list of Sprite.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Constructor that create a new SpriteCollection with list of Sprite.
     *
     * @param s list of Sprite.
     */
    public SpriteCollection(List<Sprite> s) {
        this.sprites = s;
    }

    /**
     * Add sprite to the list.
     *
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        // Make a copy of the sprites before iterating over them.
        List<Sprite> sprite = new ArrayList<Sprite>(this.sprites);
        //go over the sprites list
        for (Sprite s : sprite) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        //go over the sprites list
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }

    /**
     * The method remove sprite from the list of sprites.
     *
     * @param s the sprite we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }
}
