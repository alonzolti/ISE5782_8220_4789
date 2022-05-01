package geometries;

import java.util.List;
import primitives.*;

/**
 * Sphere class represents sphere in space
 */
public class Sphere implements Geometry {
    private final Point center;
    private final double radius;
    private final double radius2;

    /**
     * constructor that get point and a radius and create a sphere
     * 
     * @param center center of the sphere
     * @param radius radius of the sphere
     */
    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
        this.radius2 = radius * radius;
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
        return p.subtract(center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        // Ray -> P = P0 + t*v, t >0
        // Sphere points: |P-O|^2 - r^2 = 0

        Vector u;
        try {
            u = center.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            return List.of(ray.getPoint(radius));
        }

        double tm = Util.alignZero(ray.getDir().dotProduct(u));
        double d2 = Util.alignZero(u.lengthSquared() - tm * tm);
        double th2 = Util.alignZero(radius2 - d2);
        if (th2 <= 0)
            return null;
        double th = Math.sqrt(th2);

        double t2 = Util.alignZero(tm + th);
        if (t2 <= 0)
            return null;
        Point p2 = ray.getPoint(t2);

        double t1 = Util.alignZero(tm - th);
        return t1 <= 0 ? List.of(p2) : List.of(ray.getPoint(t1), p2);
    }
}
