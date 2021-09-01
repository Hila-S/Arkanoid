//208943555

/**
 * the class velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    //the change in position on the `x` axes
    private double dx;
    //the change in position on the `y` axes
    private double dy;

    /**
     * Constructor of velocity.
     *
     * @param dx the change in position on the `x` axes.
     * @param dy the change in position on the `y` axes.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * The method returns the dx - the change in position on the `x` axes.
     *
     * @return double dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The method returns the dy - the change in position on the `y` axes.
     *
     * @return double dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The method sets the dx.
     *
     * @param newDx the change in position on the `x` axes.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * The method sets the dy.
     *
     * @param newDy the change in position on the `y` axes.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * The method calculates the velocity from angle and speed.
     *
     * @param angle double - determines the direction of speed.
     * @param speed double - determines the speed.
     * @return the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle);
        double dx = speed * Math.sin(radians);
        double dy = -1 * speed * Math.cos(radians);
        return new Velocity(dx, dy);
    }

    /**
     * Get the speed.
     *
     * @return the speed.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }


    /**
     * The method takes a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p - point.
     * @return new point - (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
