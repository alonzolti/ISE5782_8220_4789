package geometries;

import java.util.List;
import primitives.*;
import static primitives.Util.*;

/**
 * Sphere class represents sphere in space
 */
public class Sphere extends Geometry {
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
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Ray -> P = P0 + t*v, t >0
        // Sphere points: |P-O|^2 - r^2 = 0

        Vector u;
        try {
            u = center.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            return List.of(new GeoPoint(this, ray.getPoint(radius)));
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
        GeoPoint p2 = new GeoPoint(this, ray.getPoint(t2));

        double t1 = Util.alignZero(tm - th);

        if (alignZero(t1 - maxDistance) >= 0)
            return null;
        if (alignZero(t2 - maxDistance) >= 0)
            return List.of(new GeoPoint(this, ray.getPoint(t1)));
        return t1 <= 0 ? List.of(p2) : List.of(new GeoPoint(this, ray.getPoint(t1)), p2);

    }
}
