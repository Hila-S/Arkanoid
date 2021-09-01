//208943555

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys, and moves according
 * to the player key presses. It should also know how to move to the left and to the right.
 */
public class Paddle implements Sprite, Collidable {
    //keyboard sensor
    private biuoop.KeyboardSensor keyboard;
    //the shape of the paddle
    private Rectangle rectangle;
    //the color of the paddle
    private java.awt.Color color;
    //the left border of the paddle
    private double leftLimit;
    //the right border of the paddle
    private double rightLimit;
    //the speed of the paddle
    private int paddleSpeed;

    /**
     * Constructor that creates a new Paddle with keyboard, rectangle, color, speed, and right/left border.
     *
     * @param keyboard   keyboard sensor.
     * @param rect       rectangle, the shape of the paddle.
     * @param color      the color of the paddle.
     * @param speed      the speed of the paddle.
     * @param leftLimit  the left border of the paddle.
     * @param rightLimit the right border of the paddle.
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, Color color, int speed, double leftLimit,
                  double rightLimit) {
        this.keyboard = keyboard;
        this.rectangle = rect;
        this.color = color;
        this.leftLimit = leftLimit;
        this.rightLimit = rightLimit;
        this.paddleSpeed = speed;
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        //update the point to be the destination point of moving left
        Point p = new Point(this.rectangle.getUpperLeft().getX() - this.paddleSpeed,
                this.rectangle.getUpperLeft().getY());
        //checking the move does not cross the border
        if (p.getX() >= this.leftLimit) {
            // update the location of the paddle
            this.rectangle.setUpperLeft(p);
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        //update the point to be the destination point of moving right
        Point p = new Point(this.rectangle.getUpperLeft().getX() + this.paddleSpeed,
                this.rectangle.getUpperLeft().getY());
        //checking the move does not cross the border
        if (p.getX() + this.rectangle.getWidth() <= this.rightLimit) {
            // update the location of the paddle
            this.rectangle.setUpperLeft(p);
        }
    }

    /**
     * Notify the sprite that time has passed.
     * Move the paddle right or left according to the Keyboard sensor.
     */
    @Override
    public void timePassed() {
        //move left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        //move right
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw the sprite to the screen.
     *
     * @param d DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        //color the paddle
        d.setColor(this.color);
        d.fillRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle(x, y, (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * Get the "collision shape" of the object.
     *
     * @return the collision rectangle.
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
        //initialize the corner points of the paddle
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
        // initialize  the velocity of the ball
        Velocity vel = currentVelocity;
        //checking intersecting point with the upper side
        if (upper.isIntersecting(collisionLine)) {
            double xIntersection = upper.intersectionWith(collisionLine).getX();
            //divide the top of the paddle into five
            double littleWidth = this.rectangle.getWidth() / 5;
            double angle = 0;
            // initialize the points of the top of the paddle
            double x1 = xLeft;
            double x2 = x1 + littleWidth;
            double x3 = x2 + littleWidth;
            double x4 = x3 + littleWidth;
            double x5 = x4 + littleWidth;
            double x6 = xRight;
            //change the angle according to the collision point
            if (this.range(x1, x2, xIntersection)) {
                angle = 300;
            } else if (this.range(x2, x3, xIntersection)) {
                angle = 330;
            } else if (this.range(x3, x4, xIntersection)) {
                vel.setDy(-currentVelocity.getDy());
                return vel;
            } else if (this.range(x4, x5, xIntersection)) {
                angle = 30;
            } else if (this.range(x5, x6, xIntersection)) {
                angle = 60;
            }
            // get the speed of the ball
            double speed = vel.getSpeed();
            // update the velocity of the ball
            vel = Velocity.fromAngleAndSpeed(angle, speed);
        } else if ((left.isIntersecting(collisionLine)) || (right.isIntersecting(collisionLine))) {
            //checking intersecting point with the left/right side
            //change the dx of the velocity
            vel.setDx(-1 * currentVelocity.getDx());
        }
        return vel;
    }

    /**
     * The method checks if the intersection point is in the range of the xMin and the xMax.
     *
     * @param xMin          the small x.
     * @param xMax          the big x.
     * @param xIntersection the x of the intersection point.
     * @return true if xIntersection of the range between xMin to xMax, otherwise false.
     */
    private boolean range(double xMin, double xMax, double xIntersection) {
        return ((xMin <= xIntersection) && (xIntersection <= xMax));
    }

    /**
     * Add this paddle to the game.
     *
     * @param g Game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}