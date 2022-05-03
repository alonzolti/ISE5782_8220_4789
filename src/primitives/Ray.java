package primitives;

import geometries.Intersectable.GeoPoint;
import static primitives.Util.*;
import java.util.List;

/**
 * Ray class represents a ray in the space
 */
public class Ray {
    private final Point p0;
    private final Vector dir;
    private static final double DELTA = 0.1;

    /**
     * constructor, normalizez the vector before saving it
     * 
     * @param p0  point
     * @param dir vector
     */
    public Ray(Point p0, Vector dir) {
        this.p0 = p0;
        this.dir = dir.normalize();
    }

    /**
     * constructor, moving the start of the ray by delta in the direction of the
     * normal
     * 
     * @param p0  the start of the ray
     * @param dir the direction of the ray
     * @param n   the normal
     */
    public Ray(Point p0, Vector dir, Vector n) {
        Vector delVector = n.scale(n.dotProduct(dir) > 0 ? DELTA : -DELTA);
        this.p0 = p0.add(delVector);
        this.dir = dir.normalize();
    }

    /**
     * getter for p0 field
     * 
     * @return point p0
     */
    public Point getP0() {
        return p0;
    }

    /**
     * getter for dir field
     * 
     * @return vector
     */
    public Vector getDir() {
        return dir;
    }

    /**
     * calculate spacific place on the ray
     * 
     * @param t the scalar
     * @return return point on the ray
     * @throws IllegalArgumentException if t is negative
     */
    public Point getPoint(double t) {
        if (t < 0)
            throw new IllegalArgumentException("t cannot be negative");
        return isZero(t) ? p0 : p0.add(dir.scale(t));
    }

    /**
     * find closest point to the start of the ray
     * 
     * @param list of point
     * @return return the closest point to the satart of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

    /**
     * find the closest geoPoint to the start of the ray
     * 
     * @param geoPoints list of geoPoints
     * @return return the closest geoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        GeoPoint closestPoint = null;
        double distance;
        if (geoPoints != null && !geoPoints.isEmpty()) {
            distance = p0.distance(geoPoints.get(0).point);
            closestPoint = geoPoints.get(0);
            for (int i = 0; i < geoPoints.size(); i++) {
                if (p0.distance(geoPoints.get(i).point) < distance) {
                    closestPoint = geoPoints.get(i);
                    distance = p0.distance(geoPoints.get(i).point);
                }
            }
        }
        return closestPoint;
    }

    @Override
    public String toString() {
        return "Ray: " + p0.toString() + '\n' + dir.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || !(obj instanceof Ray other))
            return false;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }

}
