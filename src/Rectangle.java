//208943555

import java.util.ArrayList;
import java.util.List;

/**
 * A rectangle has a point of the location (upper-Left), width, height, and can calculates intersection with
 * other lines.
 */
public class Rectangle {
    // the location of the rectangle
    private Point upperLeft;
    // the width of the rectangle
    private double width;
    // the height of the rectangle
    private double height;

    /**
     * Create a new rectangle with location, width and height.
     *
     * @param upperLeft the location of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new rectangle with location, width and height.
     *
     * @param x      the x of the upper Left point (location).
     * @param y      the y of the upper Left point (location).
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * The method returns a List of intersection points with the specified line.
     *
     * @param line a line that possible to intersect with the rectangle.
     * @return a List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        double xRight = this.upperLeft.getX() + this.width;
        double yLower = this.upperLeft.getY() + this.height;
        //initialize the corner point of thr rectangle
        Point p1 = this.upperLeft;
        Point p2 = new Point(xRight, this.getUpperLeft().getY());
        Point p3 = new Point(xRight, yLower);
        Point p4 = new Point(this.upperLeft.getX(), yLower);
        List<Point> list = new ArrayList<>();
        //checking intersecting point between the line and the upper line
        if (line.isIntersecting(new Line(p1, p2))) {
            list.add(line.intersectionWith(new Line(p1, p2)));
        }
        //checking intersecting point between the line and the right line
        if (line.isIntersecting(new Line(p2, p3))) {
            list.add(line.intersectionWith(new Line(p2, p3)));
        }
        //checking intersecting point between the line and the lower line
        if (line.isIntersecting(new Line(p3, p4))) {
            list.add(line.intersectionWith(new Line(p3, p4)));
        }
        //checking intersecting point between the line and the left line
        if (line.isIntersecting(new Line(p4, p1))) {
            list.add(line.intersectionWith(new Line(p4, p1)));
        }
        return list;
    }

    /**
     * Get the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set the upper-left point of the rectangle.
     *
     * @param p the new upper-left point.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}
