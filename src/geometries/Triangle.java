package geometries;

import java.util.LinkedList;
import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * triangle represents a triangle in space
 */
public class Triangle extends Polygon {

    /**
     * constructor that get 3 points
     * 
     * @param v1 point 1
     * @param v2 point 2
     * @param v3 point 3
     */
    public Triangle(Point v1, Point v2, Point v3) {
        super(v1, v2, v3);
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<GeoPoint> points = plane.findGeoIntersections(ray);
        if (points == null)
            return null;

        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        Vector v1 = vertices.get(0).subtract(p0);
        Vector v2 = vertices.get(1).subtract(p0);
        Vector n1 = v1.crossProduct(v2).normalize();
        double r1 = alignZero(dir.dotProduct(n1));
        if (r1 == 0)
            return null;

        Vector v3 = vertices.get(2).subtract(p0);
        Vector n2 = v2.crossProduct(v3).normalize();
        double r2 = alignZero(dir.dotProduct(n2));
        if (r1 * r2 <= 0)
            return null;

        Vector n3 = v3.crossProduct(v1).normalize();
        double r3 = alignZero(dir.dotProduct(n3));
        if (r1 * r3 <= 0)
            return null;

        return List.of(new GeoPoint(this, points.get(0).point));
    }

}
