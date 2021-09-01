//208943555

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Balls have size (radius), color, and location (a Point).
 * Balls also know how to draw themselves on a DrawSurface.
 */
public class Ball implements Sprite {
    //the center point of the circle
    private Point center;
    //the size of the radius
    private int radius;
    //the color of the ball
    private java.awt.Color color;
    //velocity specifies the change in position on the `x` and the `y` axes
    private Velocity velocity;
    //GameEnvironment - information about objects that ball can collide with
    private GameEnvironment game;

    /**
     * The method constructs the ball.
     *
     * @param center the center point of the circle.
     * @param r      the size of the radius.
     * @param color  the color of the ball.
     * @param game   the GameEnvironment.
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment game) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.game = game;
    }

    /**
     * The method constructs the ball.
     *
     * @param x     the x of the center point of the circle.
     * @param y     the y of the center point of the circle.
     * @param r     the size of the radius.
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * The method makes accessors to the x.
     *
     * @return the x of the center point.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * The method makes accessors to the y.
     *
     * @return the y of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The method makes accessors to the size of the radius.
     *
     * @return the size of the radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * The method makes accessors to the color of the circle.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param surface DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        int x = this.getX();
        int y = this.getY();
        int r = this.getSize();
        surface.setColor(this.color);
        //color the ball.
        surface.fillCircle(x, y, r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(x, y, r);
    }

    /**
     * The method sets the velocity.
     *
     * @param v the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * The method sets the velocity.
     *
     * @param dx the change in position on the `x` axes.
     * @param dy the change in position on the `y` axes.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * The method returns the velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method calculates the new center of the circle according to the velocity.
     */
    public void moveOneStep() {
        //compute the ball trajectory (how the ball will move without any obstacles)
        Point end = this.getVelocity().applyToPoint(this.center);
        Line line = new Line(this.center, end);
        //check if moving on this trajectory will hit anything
        // if isn't hit, then move the ball to the end of the trajectory
        if (this.game.getClosestCollision(line) == null) {
            this.center = end;
        } else {
            //move on this trajectory hit anything
            double size = 0.01;
            Collidable collidable = this.game.getClosestCollision(line).collisionObject();
            Point collisionPoint = this.game.getClosestCollision(line).collisionPoint();
            //move the ball to "almost" the hit point
            //calculate the location of the x of the new center
            if (line.start().getX() < collisionPoint.getX()) {
                this.center.setX(collisionPoint.getX() - size);
            } else if (line.start().getX() > collisionPoint.getX()) {
                this.center.setX(collisionPoint.getX() + size);
            } else {
                this.center.setX(collisionPoint.getX());
            }
            //calculate the location of the y of the new center
            if (line.start().getY() < collisionPoint.getY()) {
                this.center.setY(collisionPoint.getY() - size);
            } else if (line.start().getY() > collisionPoint.getY()) {
                this.center.setY(collisionPoint.getY() + size);
            } else {
                this.center.setY(collisionPoint.getY());
            }
            //update the velocity to the new velocity returned by the hit() method
            this.setVelocity(collidable.hit(this, collisionPoint, this.velocity));
        }
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Get the GameEnvironment of the ball.
     *
     * @return the GameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.game;
    }

    /**
     * Set the GameEnvironment of the ball.
     *
     * @param g the GameEnvironment.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.game = g;
    }

    /**
     * Add the ball to the game.
     *
     * @param g Game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove block from the game.
     *
     * @param g the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}