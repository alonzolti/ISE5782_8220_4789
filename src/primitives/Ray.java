package primitives;

import geometries.Intersectable.GeoPoint;
import java.util.List;
import java.util.stream.Collectors;

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
     * @param p0  pothe start of the rayint
     * @param dir the direction of the ray
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
     *         =
     */
    public Point getPoint(double t) {
        try {
            return p0.add(dir.scale(t));
        } catch (IllegalArgumentException ignore) {
            return p0;
        }
    }

    /**
     * find closest point to the start of the ray
     * 
     * @param list of point
     * @return return the closest point to the satart of the ray
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).collect(Collectors.toList())).point;
    }

    /**
     * find the closest geoPoint to the start of the ray
     * 
     * @param geoPoints list of geoPoints
     * @return return the closest geoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> geoPoints) {
        GeoPoint closestPoint = null;
        double distance = Double.POSITIVE_INFINITY;
        if (geoPoints == null || geoPoints.isEmpty())
            return null;

        for (var gp : geoPoints) {
            double d = p0.distance(gp.point); 
            if (d < distance) {
                closestPoint = gp;
                distance = d;
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
        if (obj == null || !(obj instanceof Ray))
            return false;
        Ray other = (Ray)obj;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }

}
