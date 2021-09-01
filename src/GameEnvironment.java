//208943555

import java.util.ArrayList;
import java.util.List;

/**
 * Game environment collects objects a Ball can collide with. the ball will know the game environment,
 * and will use it to check for collisions and direct its movement.
 */
public class GameEnvironment {
    //list of Collidables
    private List<Collidable> collidables;

    /**
     * A constructor that creates a new GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * A constructor that creates a new GameEnvironment.
     *
     * @param c a list of Collidable.
     */
    public GameEnvironment(List<Collidable> c) {
        this.collidables = c;
    }

    /**
     * Add the given collidable to the environment.
     *
     * @param c a collidable objects.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Checking if this object will collide with any of the collidables in this collection.
     *
     * @param trajectory a line, object moving from line.start() to line.end().
     * @return if this object will not collide with any of the collidables in this collection, return null.
     * else, return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point tempPoint = null;
        CollisionInfo tempCollisionInfo = null;
        // if the list of the collidables is empty
        if (this.collidables.isEmpty()) {
            return null;
        }
        // Make a copy of the collidables before iterating over them.
        List<Collidable> collidableList = new ArrayList<Collidable>(this.collidables);
        //go over the list of the collidables
        for (Collidable c : collidableList) {
            Rectangle rect = c.getCollisionRectangle();
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(rect);
            // if we have intersection point
            if (intersectionPoint != null) {
                //if we didn't update the closePoint or intersectionPoint is closer than closePoint
                if ((tempPoint == null)
                        || (trajectory.start().distance(intersectionPoint) < trajectory.start().distance(tempPoint))) {
                    //update the closePoint and the collisionInfo
                    tempPoint = intersectionPoint;
                    tempCollisionInfo = new CollisionInfo(tempPoint, c);
                }
            }
        }
        return tempCollisionInfo;
    }

    /**
     * Get a list of Collidable.
     *
     * @return a list of Collidable.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * The method remove collidable from the list of collidables.
     *
     * @param c the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
