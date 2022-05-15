package geometries;

import java.util.List;
import primitives.*;

/**
 * this abstract class represent a shape in space,
 * that can be intersected by a ray
 */
public abstract class Intersectable {
    /**
     * helper class to represent a pair of geometry and an intersection point
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        /**
         * constructor that get geometry and an intersection point
         * 
         * @param geometry
         * @param point    intersection point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || !(obj instanceof GeoPoint other))
                return false;
            return this.geometry.equals(other.geometry) && this.point.equals(other.point);
        }

        @Override
        public String toString() {
            return "geometry:" + geometry + "\npoint: " + point;
        }
    }

    /**
     * Finds all the intersection points between a ray and the geometric shape
     * 
     * @param ray the ray that intersect the shape
     * @return list of all the points or null if there are no intersection points
     */
    public List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }

    /**
     * find all intersection geoPoints between a ray and geometrics shapes
     * 
     * @param ray the ray
     * @return list of all the intersection geo points
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersections(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * find all intersection geoPoints between a ray and geometrics shapes
     * 
     * @param ray the ray
     * @param maxDistance the max distance to the intersection points from the ray
     * @return list of all the intersection geo points
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * find all the intersection geo points between a ray and geometric shapes
     * 
     * @param ray the ray
     * @param maxDistance the max distance to the intersection points from the ray
     * @return list of all the geoPoints that their distance is lower than maxdistance
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

}
