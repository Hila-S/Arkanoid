//208943555

/**
 * The Collidable interface will be used by things that can be collided with.
 * We need to know their location and size, and we need to know what happens when the collision occurs.
 */
public interface Collidable {
    /**
     * Get the "collision shape" of the object.
     *
     * @return the collision rectangle.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param hitter          the ball that hit.
     * @param collisionPoint  collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

