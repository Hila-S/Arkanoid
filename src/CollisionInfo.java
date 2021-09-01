//208943555

/**
 * CollisionInfo saves the information about the collision. It knows the collision point, and the
 * collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * Constructor that creates a new CollisionInfo with collision point and collidable object.
     *
     * @param p the collision point.
     * @param c the collidable object.
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collidable = c;
    }

    /**
     * Get the point at which the collision occurs.
     *
     * @return the collision point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Get the collidable object involved in the collision.
     *
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}
