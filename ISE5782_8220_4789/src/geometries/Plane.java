package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Plane class represent a plane in space
 */
public class Plane implements Geometry 
{
    final Point q0;
    final Vector normal;

    /**
     * constructor that get normal and a point
     * @param q0 point
     * @param normal normal to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    /**
     * consturctor that get 3 points
     * @param a point 1
     * @param b point 2
     * @param c point 3
     */
    public Plane(Point a, Point b, Point c)
    {
        normal = null;
        q0 = a;
    }

    /**
     * getter for q0 field
     * @return Point
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * getter for normal field
     * @return Vector
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }

    @Override
    public String toString() {
        return "Plane: " + q0.toString() + '\n' + normal.toString();
    }

    
}
