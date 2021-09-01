//208943555

import java.util.List;

/**
 * A line connects two points, a start point and an end point. Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    //the start point of the line
    private Point start;

    //the end point of the line
    private Point end;

    static final double EPSILON = Math.pow(10, -8);

    /**
     * The method constructs the line.
     *
     * @param start the start point of the line.
     * @param end   the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The method constructs the line.
     *
     * @param x1 the x of the start point.
     * @param y1 the y of the start point.
     * @param x2 the x of the end point.
     * @param y2 the y of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The method returns the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The method calculates the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();
        // calculating
        double middleX = (x1 + x2) / 2;
        double middleY = (y1 + y2) / 2;
        return new Point(middleX, middleY);
    }

    /**
     * The method returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * The method returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The method checks if the lines intersect.
     *
     * @param other line.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * The method checks if the lines intersect and calculates the point of the intersection.
     *
     * @param other line.
     * @return the intersection point if the lines intersects, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //equation of the "this line": a1x +b1y=c1
        double a1 = this.end.getY() - this.start.getY();
        double b1 = this.start.getX() - this.end.getX();
        double c1 = a1 * this.start.getX() + b1 * this.start.getY();
        //equation of the "other line": a2x + b2y = c2
        double a2 = other.end.getY() - other.start.getY();
        double b2 = other.start.getX() - other.end.getX();
        double c2 = a2 * other.start.getX() + b2 * other.start.getY();
        double det, xIntersection, yIntersection;
        //if the lines are points
        if (this.start.equals(this.end) || other.start.equals(other.end)) {
            return lineIsPoint(other, a1, b1, c1, a2, b2, c2);
        }
        //the lines are not points
        det = a1 * b2 - a2 * b1;
        //if the lines are parallels
        if (det == 0) {
            return this.parallelIntersection(other);
        } else {
            // the lines are not parallel - we have point intersection
            xIntersection = (b2 * c1 - b1 * c2) / det;
            yIntersection = (a1 * c2 - a2 * c1) / det;
            //checking if the intersection point in the range of the lines
            if (this.rangeIntersection(other, xIntersection, yIntersection)) {
                return new Point(xIntersection, yIntersection);
            }
            return null;
        }
    }

    /**
     * The method checks point intersection with line.
     * equation of the line: a1*x + b1*y = c1 , a2*x + b2*y = c2.
     *
     * @param other the other line.
     * @param a1    = thisEndY - thisStartY.
     * @param b1    = thisStartX - thisEndX.
     * @param c1    = a1 * thisStartX + b1 * thisStartY.
     * @param a2    = otherEndY - otherStartY.
     * @param b2    = otherStartX - otherEndX.
     * @param c2    = a2 * otherStartX + b2 * otherStartY.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    private Point lineIsPoint(Line other, double a1, double b1, double c1, double a2, double b2, double c2) {
        double thisX = this.start.getX();
        double thisY = this.start.getY();
        double otherX = other.start.getX();
        double otherY = other.start.getY();
        //if the two lines are points
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.equals(other)) {
                return this.start;
            } else {
                return null;
            }
        }
        //if "this" line is a point, they have point intersection only if:
        //the point is in the equation of "other" line and in the range of "other" line
        if ((this.start.equals(this.end)) && (Math.abs(a2 * thisX + b2 * thisY - c2) <= EPSILON)
                && (this.rangeIntersection(other, thisX, thisY))) {
            return new Point(thisX, thisY);
        }
        //if "other" line is a point, they have point intersection only if:
        //the point is in the equation of "this" line and in the range of "this" line
        if ((other.start.equals(other.end)) && (Math.abs(a1 * otherX + b1 * otherY - c1) <= EPSILON)
                && (this.rangeIntersection(other, otherX, otherY))) {
            return new Point(otherX, otherY);
        }
        return null;
    }

    /**
     * The method checks point intersection of parallel lines.
     *
     * @param other line.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    private Point parallelIntersection(Line other) {
        //if the first line is in the range of the second line
        if ((this.rangeIntersection(other, this.start.getX(), this.start.getY())
                && this.rangeIntersection(other, this.end.getX(), this.end.getY()))
                || (other.rangeIntersection(this, other.start.getX(), other.start.getY())
                && other.rangeIntersection(this, other.end.getX(), other.end.getY()))) {
            return null;
        }
        //if they have one intersection point
        if ((this.end.equals(other.start)) || (this.end.equals(other.end))) {
            return this.end;
        }
        //if they have one intersection point
        if ((this.start.equals(other.end)) || (this.start.equals(other.start))) {
            return this.start;
        } else {
            return null;
        }
    }

    /**
     * The method checks if the intersection point is in the range of the lines.
     *
     * @param other         line.
     * @param xIntersection the x of the intersection point.
     * @param yIntersection the y of the intersection point.
     * @return true if intersection point in the range of the lines, false otherwise.
     */
    private boolean rangeIntersection(Line other, double xIntersection, double yIntersection) {
        //finding min x and max x  of the "this line"
        double xMinThis = Math.min(this.start.getX(), this.end.getX());
        double xMaxThis = Math.max(this.start.getX(), this.end.getX());
        double yMinThis = Math.min(this.start.getY(), this.end.getY());
        double yMaxThis = Math.max(this.start.getY(), this.end.getY());
        //finding min x and max x  of the "other line"
        double xMinOther = Math.min(other.start.getX(), other.end.getX());
        double xMaxOther = Math.max(other.start.getX(), other.end.getX());
        double yMinOther = Math.min(other.start.getY(), other.end.getY());
        double yMaxOther = Math.max(other.start.getY(), other.end.getY());

        //checking if the intersection point is in the range of the lines
        return ((xMinThis <= xIntersection + EPSILON) && (xIntersection - EPSILON <= xMaxThis)
                && (xMinOther <= xIntersection + EPSILON) && (xIntersection - EPSILON <= xMaxOther)
                && (yMinThis <= yIntersection + EPSILON) && (yIntersection - EPSILON <= yMaxThis)
                && (yMinOther <= yIntersection + EPSILON) && (yIntersection - EPSILON <= yMaxOther));
    }

    /**
     * The method checks if the lines are equal.
     *
     * @param other line.
     * @return true if the line are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)) && (this.end.equals(other.end)));
    }

    /**
     * The method checks if the line intersect with the rectangle, and returns the intersection point.
     *
     * @param rect - Rectangle.
     * @return return null, if this line does not intersect with the rectangle. Otherwise, return the closest
     * intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        // this line does not intersect with the rectangle
        if (pointList.size() == 0) {
            return null;
        }
        // this line intersect with the rectangle once
        if (pointList.size() == 1) {
            return pointList.get(0);
        } else {
            // checking the closest intersection point to the start of the line
            double d1 = this.start.distance(pointList.get(0));
            double d2 = this.start.distance(pointList.get(1));
            return d1 > d2 ? pointList.get(1) : pointList.get(0);
        }
    }
}