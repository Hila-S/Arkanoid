//208943555

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A block is a thing we may collide with, it has a location, size, rectangle shape, and color.
 * It can also tell what happens when the collision occurs.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //list of HitListener
    private List<HitListener> hitListeners;
    //the shape of the block
    private Rectangle rectangle;
    //the color of the block
    private java.awt.Color color;

    /**
     * Create a new block with a rectangle and color.
     *
     * @param rect  the rectangle.
     * @param color the shape of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.rectangle = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Get the "collision shape" of the object.
     *
     * @return this rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          the ball that hit.
     * @param collisionPoint  collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity expected after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //notify about a hit event:
        this.notifyHit(hitter);
        //initialize the corner point of the block
        double xLeft = this.rectangle.getUpperLeft().getX();
        double xRight = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        double yUpper = this.rectangle.getUpperLeft().getY();
        double yLower = this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight();
        //initialize the lines
        Line left = new Line(xLeft, yLower, xLeft, yUpper);
        Line right = new Line(xRight, yLower, xRight, yUpper);
        Line upper = new Line(xLeft, yUpper, xRight, yUpper);
        Line lower = new Line(xLeft, yLower, xRight, yLower);
        Line collisionLine = new Line(collisionPoint, collisionPoint);
        //initialize the new velocity
        Velocity vel = currentVelocity;
        //checking intersecting point
        if ((upper.isIntersecting(collisionLine)) || (lower.isIntersecting(collisionLine))) {
            //change the dy of the velocity
            vel.setDy(-currentVelocity.getDy());
        }
        //checking intersecting point
        if ((left.isIntersecting(collisionLine)) || (right.isIntersecting(collisionLine))) {
            //change the dx of the velocity
            vel.setDx(-currentVelocity.getDx());
        }
        return vel;
    }

    /**
     * Draw the block.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        //color the block
        d.setColor(this.color);
        d.fillRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }

    /**
     * Add the block to the game.
     *
     * @param g game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The method removes the block from the game.
     *
     * @param gameLevel the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl HitListener.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl HitListener.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The method will be called whenever a hit() occurs, and will notify all of the
     * registered HitListener objects by calling their hitEvent method.
     *
     * @param hitter the ball that hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}