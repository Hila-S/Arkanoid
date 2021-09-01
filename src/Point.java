//208943555

/**
 * A point has an x and a y value, and can measure the distance to other points, and if it is equal to another point.
 */
public class Point {
    //the x of the point
    private double x;

    //the y of the point
    private double y;

    /**
     * The method constructs the point.
     *
     * @param x the x of the point.
     * @param y the y of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The method calculate the distance of this point to the other point.
     *
     * @param other Point.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double calculationX = this.x - other.x;
        double calculationY = this.y - other.y;
        return Math.sqrt((calculationX * calculationX) + (calculationY * calculationY));
    }

    /**
     * The method checks if the points are equal.
     *
     * @param other Point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

    /**
     * The method return the x value of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * The method return the y value of this point.
     *
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * the method sets the x.
     *
     * @param newX the x of the point.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * the method sets the y.
     *
     * @param newY the y of the point.
     */
    public void setY(double newY) {
        this.y = newY;
    }
}
