package geometries;

import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import static primitives.Util.*;

/**
 * Plane class represent a plane in space
 */
public class Plane extends Geometry {
    private final Point q0;
    private final Vector normal;

    /**
     * constructor that get normal and a point
     * 
     * @param q0     point on the plane
     * @param normal normal to the plane
     */
    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal.normalize();
    }

    /**
     * consturctor that get 3 points
     * 
     * @param a point 1
     * @param b point 2
     * @param c point 3
     */
    public Plane(Point a, Point b, Point c) {
        Vector v1 = a.subtract(b);
        Vector v2 = a.subtract(c);

        // if the 3 points on the same line, then we would get vector 0
        normal = v1.crossProduct(v2).normalize();
        this.q0 = a;
    }

    /**
     * getter for q0 field
     * 
     * @return reference point of the plane
     */
    public Point getQ0() {
        return q0;
    }

    /**
     * getter for normal field
     * 
     * @return normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point p) {
        return normal;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        // Ray Points: P = P0 + t * v, t > 0
        // Plane points: n * (Q - P) = 0
        double nv = normal.dotProduct(ray.getDir());
        if (isZero(nv))
            return null;

        Vector u;
        try {
            u = q0.subtract(ray.getP0());
        } catch (IllegalArgumentException ignore) {
            return null;
        }
        double t = alignZero(normal.dotProduct(u) / nv);
        if(alignZero(t - maxDistance)>=0)
            return null;
        return t <= 0 ? null : List.of(new GeoPoint(this, ray.getPoint(t)));
    }

}
