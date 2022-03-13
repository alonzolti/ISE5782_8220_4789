package geometries;

import primitives.*;

/**
 * Sphere class represents sphere in space
 */
public class Sphere implements Geometry {
    final Point center;
    final double radius;

    /**
     * constructor
     * 
     * @param center center of the sphere
     * @param radius radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * getter for center field
     * 
     * @return Point
     */
    public Point getCenter() {
        return center;
    }

    /**
     * getter for radius field
     * 
     * @return double
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
